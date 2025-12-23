package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Orientation Combo Command")
public record OrientationComboCommand(
//        @NotNull(message = "Le natureCode d'orientation ne doit pas être nul")
//        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le natureCode d'orientation doit contenir au moins 6 caractères alphanumériques majuscules")
//        @Schema(description = "Code d'orientation", example = "OR30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
//        String orientationCode



) implements Serializable {}

