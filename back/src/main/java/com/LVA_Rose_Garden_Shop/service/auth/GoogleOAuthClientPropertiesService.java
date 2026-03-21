package com.LVA_Rose_Garden_Shop.service.auth;

import com.LVA_Rose_Garden_Shop.domain.auth.google.GoogleClientSecretFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Getter
@Service
public class GoogleOAuthClientPropertiesService {

    private final String clientId;
    private final String clientSecret;
    private final String authUri;
    private final String tokenUri;
    private final String projectId;
    private final String redirectUri;
    private final String redirectPath;

    public GoogleOAuthClientPropertiesService(ObjectMapper objectMapper,
                                              ResourcePatternResolver resourcePatternResolver) {
        GoogleClientSecretFile.WebCredentials credentials = carregarCredenciais(objectMapper, resourcePatternResolver);

        this.clientId = credentials.getClientId();
        this.clientSecret = credentials.getClientSecret();
        this.authUri = credentials.getAuthUri();
        this.tokenUri = credentials.getTokenUri();
        this.projectId = credentials.getProjectId();
        this.redirectUri = credentials.getRedirectUris().getFirst();
        this.redirectPath = extrairRedirectPath(this.redirectUri);
    }

    private GoogleClientSecretFile.WebCredentials carregarCredenciais(ObjectMapper objectMapper,
                                                                      ResourcePatternResolver resourcePatternResolver) {
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath*:client_secret_*.json");

            if (resources.length == 0) {
                throw new IllegalStateException("Arquivo de credenciais do Google não encontrado em resources.");
            }

            try (InputStream inputStream = resources[0].getInputStream()) {
                GoogleClientSecretFile clientSecretFile = objectMapper.readValue(inputStream, GoogleClientSecretFile.class);
                return clientSecretFile.getWeb();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel carregar as credenciais do Google.", e);
        }
    }

    private String extrairRedirectPath(String redirectUri) {
        String path = URI.create(redirectUri).getPath();
        return path == null || path.isBlank() ? "/" : path;
    }
}
