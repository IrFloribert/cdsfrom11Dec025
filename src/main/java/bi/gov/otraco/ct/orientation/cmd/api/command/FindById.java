package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Find By Id")
public record FindById(
    @NotNull 
    @Pattern(regexp = "^[a-z0-9-]{36}$")
    @Schema(description = "ID", example = "3e9ed252-9c1c-4ac0-bf83-c53742fdb4ee")
    String id
) implements Serializable {}
