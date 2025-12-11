package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

@Schema(name = "OrientationStatusCommand")
public record OrientationStatusCommand(
    @NotNull 
    @Pattern(regexp = "^[A-Z0-9]{6,}$") 
    @Schema(description = "Code", example = "OR30000000001") 
    String code
) implements Serializable {}
