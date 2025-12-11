package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Orientation Combo Command")
public record OrientationComboCommand(
        @NotNull(message = "Le code d'orientation ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code d'orientation doit contenir au moins 6 caractères alphanumériques majuscules")
        @Schema(description = "Code d'orientation", example = "OR30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationCode,


        @NotNull(message = "Le code de ligne d'orientation ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code de ligne d'orientation doit contenir au moins 6 caractères alphanumériques majuscules")
        @Schema(description = "Code de ligne d'orientation", example = "ORL30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationLineCode,

        @NotNull(message = "Le nom de la ligne d'orientation ne doit pas être nul")
        @Pattern(regexp = "^[\\p{L}0-9 .'-]{2,60}$", message = "Le nom de la ligne d'orientation doit contenir entre 2 et 60 caractères")
        @Schema(description = "Nom de la ligne d'orientation", example = "Ligne de Contrôle Technique A", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationLineName
) implements Serializable {}

