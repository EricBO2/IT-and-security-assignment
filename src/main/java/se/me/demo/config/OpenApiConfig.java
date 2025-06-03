package se.me.demo.config;

@OpenAPIDefinition(info=@Info(
        title="API whit JWT",version ="1.0"),
        security =@SecurityRequirement(name="bearerAuth")
)
@SecurityScheme(
        name="bearerAuth",
        type =SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JTW"
)
public class OpenApiConfig {
}
