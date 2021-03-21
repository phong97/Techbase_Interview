package com.challenge.techbase.config;

import com.challenge.techbase.util.Constants;
import com.challenge.techbase.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String token = req.getHeader(Constants.SECURITY_HEADER);

        if (token != null && token.startsWith(Constants.SECURITY_PREFIX)) {
            token = token.replace(Constants.SECURITY_PREFIX, "");
            if (jwtUtils.validateJwtToken(token)) {
                UsernamePasswordAuthenticationToken auth = this.jwtUtils.getAuthentication(token);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(req, res);
    }

}
