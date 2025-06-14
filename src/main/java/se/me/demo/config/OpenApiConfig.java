package se.me.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Denna klass konfigurerar REST API:t. Så Swagger får åtkomst till vår REST API:er.
 */
@OpenAPIDefinition(info=@Info(
        title="API whit JWT",version ="1.0"),
        security =@SecurityRequirement(name="bearerAuth")
)
@SecurityScheme(
        name="bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JTW"
)
public class OpenApiConfig {
}
