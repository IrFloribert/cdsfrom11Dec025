package bi.gov.otraco.ct.orientation.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        var devServer = new Server();
        devServer.setUrl("http://127.0.0.1:9200");
        devServer.setDescription("Server URL development environment");
        var prodServer = new Server();
        prodServer.setUrl("http://192.168.80.38:9200");
        prodServer.setDescription("Server URL production environment");
        var contact = new Contact();
        contact.setEmail("biwep@gmail.com");
        contact.setName("BI-WEP");
        contact.setName("https://www.biwep.com");
        var license = new License()
                .name("Apache License").url("https://www.biwep.com");
        var info = new Info().title("INSPECTION ORIENTATION OTRACO CT RESTFUL API").version("1.0.0").contact(contact).description("INSPECTION ORIENTATION OTRACO CT SYSTEM RESTFUL API").termsOfService("[https://www.biwep.com](https://www.biwep.com)").license(license);
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer)).addSecurityItem(new SecurityRequirement().addList("Token")).components(new Components().addSecuritySchemes("Token", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT").description("JWT authentication with Token")));
    }
}
