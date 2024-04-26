//package kz.edu.astanait.authentiactionservice.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.List;
//
///**
// * @author aldi
// * @since 27.04.2024
// */
//
//@EnableWebMvc
//@Configuration
//public class SwaggerConfiguration implements WebMvcConfigurer {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("kz.edu.astanait.authenticationservice"))
//                .paths(PathSelectors.regex("/.*"))
//                .build()
//                .useDefaultResponseMessages(false)
//                .securitySchemes(List.of(apiKey()))
//                .securityContexts(List.of(securityContext()));
//    }
//
//    @Bean
//    public InternalResourceViewResolver defaultViewResolver() {
//        return new InternalResourceViewResolver();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return List.of(new SecurityReference("JWT", authorizationScopes));
//    }
//}
