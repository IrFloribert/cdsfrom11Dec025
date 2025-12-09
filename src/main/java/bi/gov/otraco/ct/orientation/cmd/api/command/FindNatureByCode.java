package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "FindNatureByCode")
public record FindNatureByCode(
    @NotBlank(message = "Chassis number is required")
    String chassisNo
) {}
