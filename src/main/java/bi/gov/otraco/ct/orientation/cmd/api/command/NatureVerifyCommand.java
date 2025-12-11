package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


@Schema(name = "Nature Verify Command", description = "Commande pour mettre à jour les informations d'une nature de véhicule")
public record NatureVerifyCommand(

    @NotBlank(message = "Le numéro de reçu est obligatoire")
    @Size(max = 50, message = "Le numéro de reçu ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de reçu associé à la nature", example = "RCPT2025010001", requiredMode = Schema.RequiredMode.REQUIRED)
    String receiptNo,

    @Schema(description = "État du système de freinage", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "L'état du système de freinage est obligatoire")
    String vehicleBreak,

    @Schema(description = "Niveau de compressibilité", example = "BON")
    String compressibility,

    @Schema(description = "Direction du véhicule", example = "BON")
    String direction,

    @Schema(description = "Type de document présenté", example = "BON")
    String document,

    @Schema(description = "État du moteur", example = "BON")
    String engine,

    @Schema(description = "État du système d’éclairage", example = "BON")
    String lighting,

    @Schema(description = "Charge maximale", example = "BON")
    String load,

    @Schema(description = "Nombre de sièges", example = "BON")
    String numberSeat,

    @Schema(description = "Présence de pare-brise", example = "BON")
    String parePrise,

    @Schema(description = "État de la fusée (rocket)", example = "BON")
    String rocket,

    @Schema(description = "État des amortisseurs", example = "BON")
    String shockAbsorber,

    @Schema(description = "Vitesse maximale testée", example = "BON")
    String speed,

    @Schema(description = "Type de suspension", example = "BON")
    String suspension,

    @Schema(description = "Type de transmission", example = "BON")
    String transmission,

    @Schema(description = "Type de roues", example = "BON")
    String wheels,

    @Schema(description = "Modèle des roues", example = "BON")
    String wheelsType,

    @Schema(description = "Pont utilisé", example = "BON")
    String bridge
)implements Serializable{
}
