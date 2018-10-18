package com.walkd.dmzing.auth.ajax.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkd.dmzing.dto.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;


@Slf4j
public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public AjaxAuthenticationFilter(RequestMatcher requestMatcher, ObjectMapper objectMapper) {
        super(requestMatcher);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (isJson(request)) {
            UserDto userDto = objectMapper.readValue(request.getReader(), UserDto.class);
            UsernamePasswordAuthenticationToken authentication = userDto.createUsernamePasswordAuthenticationToken();

            return getAuthenticationManager().authenticate(authentication);
        }

        throw new AccessDeniedException("Don't use content type for " + request.getContentType());
    }

    private boolean isJson(HttpServletRequest request) {
        return MediaType.APPLICATION_JSON.toString().equals(request.getContentType());
    }

}
