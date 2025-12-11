package bi.gov.otraco.ct.orientation.cmd.api.command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
@Schema(name = "Orientation Updated Command", description = "Commande pour mettre à jour une orientation existante")
public record OrientationUpdatedComboCommand(
        @NotNull(message = "Le code QR de l'orientation est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code QR doit contenir au moins 6 caractères alphanumériques en majuscules")
        @Schema(description = "Code QR unique identifiant l'orientation à mettre à jour", example = "OR30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String qr,

        @NotNull(message = "Le code de ligne est obligatoire") @Size(min = 1, message = "Le code de ligne ne peut pas être vide")
        @Pattern(regexp = "^[A-Z0-9]{3,}$", message = "Le lineCode doit contenir au moins 6 caractères alphanumériques en majuscules")
        @Schema(description = "Nouveau code de ligne d'orientation", example = "RCPT2025000123", requiredMode = Schema.RequiredMode.REQUIRED)
        String lineCode)
        implements Serializable {

}