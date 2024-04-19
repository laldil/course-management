package kz.edu.astanait.authentiactionservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * @author aldi
 * @since 25.03.2024
 */
public class SecurityUtils {
    public static Authentication getAuthInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getCurrentId() {
        Authentication authInfo = getAuthInfo();
        return (Long) ((Map<String, Object>) authInfo.getDetails()).get("id");
    }
}
