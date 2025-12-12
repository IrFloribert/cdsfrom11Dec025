package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "La compressibilite est obligatoire")
    String compressibility,

    @Schema(description = "Direction du véhicule", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "L'état de la direction est obligatoire")
    String direction,

    @Schema(description = "Type de document présenté", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "L'état du document est obligatoire")
    String document,

    @Schema(description = "État du moteur", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "L'état de la moteur est obligatoire")
    String engine,

    @Schema(description = "État du système d’éclairage", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "L'état de systeme d eclairage est obligatoire")
    String lighting,

    @Schema(description = "Charge maximale", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "L'état de la charge est obligatoire")
    String load,

    @Schema(description = "Nombre de sièges", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Le nombre de siège est obligatoire")
    String numberSeat,

    @Schema(description = "Présence de pare-brise", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Présence de pare-brise est obligatoire")
    String parePrise,

    @Schema(description = "État de la fusée (rocket)", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "État de la fusée est obligatoire")
    String rocket,

    @Schema(description = "État des amortisseurs", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "État d'amortisseur est obligatoire")
    String shockAbsorber,

    @Schema(description = "Vitesse maximale testée", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Vitesse maximale est obligatoire")
    String speed,

    @Schema(description = "Type de suspension", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Type de suspension est obligatoire")
    String suspension,

    @Schema(description = "Type de transmission", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Type de transmission est obligatoire")
    String transmission,

    @Schema(description = "Type de roues", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Type de roues est obligatoire")
    String wheels,

    @Schema(description = "Modèle des roues", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Modèle des roues est obligatoire")
    String wheelsType,

    @Schema(description = "Pont utilisé", example = "BON")
    @Pattern(regexp = "^(BON|MAUVAIS)$", message = "La valeur doit être 'BON' ou 'MAUVAIS'")
    @NotBlank(message = "Pont utilisé est obligatoire")
    String bridge
)implements Serializable{
}
