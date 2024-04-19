package kz.edu.astanait.gatewayservice.configuration;

import kz.edu.astanait.gatewayservice.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author aldi
 * @since 24.03.2024
 */

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtTokenUtils jwtTokenUtils;
    private final RouterValidator validator;

    public AuthenticationFilter(JwtTokenUtils jwtTokenUtils, RouterValidator validator) {
        super(Config.class);
        this.jwtTokenUtils = jwtTokenUtils;
        this.validator = validator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new RuntimeException("Missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//
//                try {
//                    jwtTokenUtils.validateToken(authHeader);
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                    throw new RuntimeException("Token is not valid");
//                }
//            }

            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
