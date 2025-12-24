package bi.gov.otraco.ct.orientation.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.ErrorResponse;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSchemas("ErrorResponse", new Schema<ErrorResponse>()
                    .type("object")
                    .addProperty("timestamp", new DateSchema().example("2026-01-01 12:00:00"))
                    .addProperty("status", new IntegerSchema().example(400))
                    .addProperty("error", new StringSchema().example("Bad Request"))
                    .addProperty("message", new StringSchema().example("Détails de l'erreur"))
                    .addProperty("path", new StringSchema().example("/api/endpoint"))
                )
            );
//            .info(new Info()
//                .title("API Documentation")
//                .version("1.0")
//                .description("Documentation de l'API"));
    }
}