package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "FindNatureByPlateNo")
public record FindNatureByPlateNo(
    @NotBlank(message = "Plate number is required")
    String plateNo
) {}
