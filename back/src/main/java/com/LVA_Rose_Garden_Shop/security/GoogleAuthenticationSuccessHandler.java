package com.LVA_Rose_Garden_Shop.security;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.service.auth.GoogleAutenticacaoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class GoogleAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final GoogleAutenticacaoService googleAutenticacaoService;

    @Value("${app.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    @Value("${app.auth.cookie-name:lva_session}")
    private String authCookieName;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        AutenticacaoDto autenticacaoDto = googleAutenticacaoService.autenticar(oauth2User);

        ResponseCookie cookie = ResponseCookie.from(authCookieName, autenticacaoDto.getToken())
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofDays(1))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        getRedirectStrategy().sendRedirect(request, response, frontendUrl + "/");
    }
}
