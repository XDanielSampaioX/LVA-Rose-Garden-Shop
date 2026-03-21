package com.LVA_Rose_Garden_Shop.domain.auth.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoogleClientSecretFile {

    private WebCredentials web;

    @Data
    public static class WebCredentials {
        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("project_id")
        private String projectId;

        @JsonProperty("auth_uri")
        private String authUri;

        @JsonProperty("token_uri")
        private String tokenUri;

        @JsonProperty("client_secret")
        private String clientSecret;

        @JsonProperty("redirect_uris")
        private List<String> redirectUris;
    }
}
