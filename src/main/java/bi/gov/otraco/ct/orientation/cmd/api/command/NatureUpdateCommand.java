package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;


@Schema(name = "NatureUpdateCommand", description = "Commande pour mettre à jour les informations d'une nature de véhicule")
public record NatureUpdateCommand (
    @NotBlank(message = "Le numéro de reçu est obligatoire")
    @Size(max = 50, message = "Le numéro de reçu ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de reçu associé à la nature", example = "RCPT2025010001", requiredMode = Schema.RequiredMode.REQUIRED)
    String code,


    @NotBlank(message = "Le numéro de reçu est obligatoire")
    @Size(max = 50, message = "Le numéro de reçu ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de reçu associé à la nature", example = "RCPT2025010001", requiredMode = Schema.RequiredMode.REQUIRED)
    String receiptNo,

    @NotBlank(message = "Le numéro de plaque est obligatoire")
    @Size(max = 20, message = "Le numéro de plaque ne doit pas dépasser 20 caractères")
    @Schema(description = "Numéro d’immatriculation du véhicule", example = "AB123CD", requiredMode = Schema.RequiredMode.REQUIRED)
    String plateNo,

    @NotBlank(message = "Le numéro de châssis est obligatoire")
    @Size(max = 50, message = "Le numéro de châssis ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de châssis du véhicule", example = "CHASSIS987654321", requiredMode = Schema.RequiredMode.REQUIRED)
    String chassisNo,

    @NotBlank(message = "Le numéro TIN du propriétaire est obligatoire")
    @Size(max = 30, message = "Le numéro TIN ne doit pas dépasser 30 caractères")
    @Schema(description = "Numéro TIN du propriétaire", example = "TIN55667788", requiredMode = Schema.RequiredMode.REQUIRED)
    String ownerTinNo,

    @NotBlank(message = "Le nom du propriétaire est obligatoire")
    @Size(max = 100, message = "Le nom du propriétaire ne doit pas dépasser 100 caractères")
    @Schema(description = "Nom complet du propriétaire", example = "Jean-Pierre Nduwimana", requiredMode = Schema.RequiredMode.REQUIRED)
    String ownerName,

    @Schema(description = "État du système de freinage", example = "OK")
    String vehicleBreak,

    @Schema(description = "Niveau de compressibilité", example = "Faible")
    String compressibility,

    @Schema(description = "Direction du véhicule", example = "Hydraulique")
    String direction,

    @Schema(description = "Type de document présenté", example = "Carte grise")
    String document,

    @Schema(description = "État du moteur", example = "Bon")
    String engine,

    @Schema(description = "État du système d’éclairage", example = "OK")
    String lighting,

    @Schema(description = "Charge maximale", example = "2 tonnes")
    String load,

    @Schema(description = "Nombre de sièges", example = "5")
    String numberSeat,

    @Schema(description = "Présence de pare-brise", example = "Oui")
    String parePrise,

    @Schema(description = "État de la fusée (rocket)", example = "OK")
    String rocket,

    @Schema(description = "État des amortisseurs", example = "OK")
    String shockAbsorber,

    @Schema(description = "Vitesse maximale testée", example = "160 km/h")
    String speed,

    @Schema(description = "Type de suspension", example = "Hydraulique")
    String suspension,

    @Schema(description = "Type de transmission", example = "Automatique")
    String transmission,

    @Schema(description = "Type de roues", example = "Alliage")
    String wheels,

    @Schema(description = "Modèle des roues", example = "R16")
    String wheelsType,

    @Schema(description = "Pont utilisé", example = "Simple")
    String bridge,

    @NotBlank(message = "Le natureCode de l'agence est obligatoire")
    @Size(max = 20, message = "Le natureCode de l'agence ne doit pas dépasser 20 caractères")
    @Schema(description = "Code de l'agence", example = "AGC001", requiredMode = Schema.RequiredMode.REQUIRED)
    String branchCode,

    @NotBlank(message = "Le nom de l'agence est obligatoire")
    @Size(max = 100, message = "Le nom de l'agence ne doit pas dépasser 100 caractères")
    @Schema(description = "Nom de l'agence", example = "Agence centrale de Gitega", requiredMode = Schema.RequiredMode.REQUIRED)
    String branchName,

    @Schema(description = "Statut de validité", example = "VALID")
    String validStatus)implements Serializable{
}
