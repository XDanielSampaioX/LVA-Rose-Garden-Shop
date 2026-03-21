package com.LVA_Rose_Garden_Shop.security;

import com.LVA_Rose_Garden_Shop.service.auth.GoogleOAuthClientPropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SegurancaConfig {

    private final GoogleAuthenticationSuccessHandler googleAuthenticationSuccessHandler;
    private final GoogleOAuthClientPropertiesService googleOAuthClientPropertiesService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/",
                                "/assistente/**",
                                "/oauth2/**",
                                "/login/oauth2/**",
                                "/autenticacao/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth -> oauth
                        .redirectionEndpoint(redirection -> redirection.baseUri(googleOAuthClientPropertiesService.getRedirectPath()))
                        .successHandler(googleAuthenticationSuccessHandler)
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration googleRegistration = ClientRegistration.withRegistrationId("google")
                .clientId(googleOAuthClientPropertiesService.getClientId())
                .clientSecret(googleOAuthClientPropertiesService.getClientSecret())
                .clientName("Google")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .redirectUri(googleOAuthClientPropertiesService.getRedirectUri())
                .scope("openid", "profile", "email")
                .authorizationUri(googleOAuthClientPropertiesService.getAuthUri())
                .tokenUri(googleOAuthClientPropertiesService.getTokenUri())
                .userInfoUri("https://openidconnect.googleapis.com/v1/userinfo")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .issuerUri("https://accounts.google.com")
                .userNameAttributeName("email")
                .build();

        return new InMemoryClientRegistrationRepository(googleRegistration);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }
}
