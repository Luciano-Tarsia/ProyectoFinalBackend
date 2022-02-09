package com.coderhouse.auxiliaries.Jwt;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    private String jwtSecret = "springbootjwtcoderhouse";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try{
            if (existJWTToke(request, response)){
                Claims claims = validateToken(request);
                if (!Objects.isNull(claims.get("authorities"))){
                    setUpStringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    public boolean existJWTToke(HttpServletRequest request, HttpServletResponse response){
        String authentificationHeader = request.getHeader(HEADER);
        if (authentificationHeader == null || !authentificationHeader.startsWith(PREFIX)){
            return false;
        }
        return true;
    }

    public Claims validateToken(HttpServletRequest request){
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJwt(jwtToken).getBody();
    }

    @SuppressWarnings("unchecked")
    private void setUpStringAuthentication(Claims claims){
        List<String> authorities = (List) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth) ;

    }

}
