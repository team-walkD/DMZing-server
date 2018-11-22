package com.walkd.dmzing.auth;

import com.google.gson.Gson;
import com.walkd.dmzing.auth.jwt.JwtInfo;
import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class BaseSecurityHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private final Gson gson;

    private static final String FIELD = "AUTH";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {

        UserDetails userDetails = new UserDetailsImpl(authentication.getName(), new ArrayList<>(authentication.getAuthorities()));
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setHeader(JwtInfo.HEADER_NAME, JwtUtil.createToken(userDetails));
        log.info("[request end] -> {}",request.getRequestURI());
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.warn("[BadCredentialsException] credentials exception {}", exception.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(gson.toJson(ExceptionDto.builder().field(FIELD).message(exception.getMessage())));
    }
}
