package com.baeldung.resource.filter;

import com.baeldung.resource.web.dto.JwtPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Service
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final Base64.Decoder DECODER = Base64.getUrlDecoder();
    private static final Gson GSON = new GsonBuilder().create();
    private final String allowedDomain;

    public JwtFilter(@Value("${application.allowed-domain}") String allowedDomain) {
        this.allowedDomain = allowedDomain;
    }

    // below method will only be executed after the successful authentication from authorization server, and it will
    // set the authentication to null if the 'preferred_username' doesn't end with the '@test.com'.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTH_HEADER);
        String jwt = authHeader.substring(7); // extract token after 7th index, ie. "Bearer xyz.abc.pqr"
        String[] chunks = jwt.split("\\.");
        String payloadString = new String(DECODER.decode(chunks[1]));
        JwtPayload payload = GSON.fromJson(payloadString, JwtPayload.class);
        if (!payload.getPreferredUsername().endsWith(allowedDomain)) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request, response);
    }
}
