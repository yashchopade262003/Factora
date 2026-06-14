package org.api.gateway.filter;

import org.api.gateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter
        extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if(authHeader == null || !authHeader.startsWith("Bearer ")) {

                throw new RuntimeException("Missing JWT Token");
            }

            String token = authHeader.substring(7);

            if(!JwtUtil.validateToken(token)) {

                throw new RuntimeException("Invalid JWT Token");
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}