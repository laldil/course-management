package kz.edu.astanait.gatewayservice.configuration;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author aldi
 * @since 24.03.2024
 */
@Service
public class RouterValidator {
    public static final List<String> openEndpoints = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/validate"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
