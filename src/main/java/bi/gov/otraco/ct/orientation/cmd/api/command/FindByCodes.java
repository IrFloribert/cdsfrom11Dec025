package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Find By Code3")
public record FindByCodes(
    @NotNull 
    @Pattern(regexp = "^[A-Z0-9-]{6,}$")
    @Schema(description = "Plate", example = "A432-1A")
    String plateNo,
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{6,}$")
    @Schema(description = "NIF", example = "44000000006")
    String tinNo,
    @NotNull
    @Pattern(regexp = "^[A-Z0-9-/.]{6,}$")
    @Schema(description = "Chassis", example = "TY30787689701")
    String chassisNo
) implements Serializable {}
