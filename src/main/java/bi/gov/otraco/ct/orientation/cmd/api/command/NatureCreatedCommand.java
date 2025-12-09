package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "NatureCreatedCommand", description = "Représente les données d'entrée pour une nature de véhicule")
public class NatureCreatedCommand {

    @Size(max = 50, message = "Le numéro de reçu ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de reçu associé à la nature", example = "RCPT2025010001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String receiptNo;

    @Size(max = 20, message = "Le numéro de plaque ne doit pas dépasser 20 caractères")
    @Schema(description = "Numéro d’immatriculation du véhicule", example = "AB123CD", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateNo;

    @Size(max = 50, message = "Le numéro de châssis ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de châssis du véhicule", example = "CHASSIS987654321", requiredMode = Schema.RequiredMode.REQUIRED)
    private String chassisNo;

    @Size(max = 30, message = "Le numéro TIN ne doit pas dépasser 30 caractères")
    @Schema(description = "Numéro TIN du propriétaire", example = "TIN55667788", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ownerTinNo;

    @Size(max = 100, message = "Le nom du propriétaire ne doit pas dépasser 100 caractères")
    @Schema(description = "Nom complet du propriétaire", example = "Jean-Pierre Nduwimana", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ownerName;

    @Schema(description = "État du système de freinage", example = "OK")
    private String vehicleBreak;

    @Schema(description = "Niveau de compressibilité", example = "Faible")
    private String compressibility;

    @Schema(description = "Direction du véhicule", example = "Hydraulique")
    private String direction;

    @Schema(description = "Type de document présenté", example = "Carte grise")
    private String document;

    @Schema(description = "État du moteur", example = "Bon")
    private String engine;

    @Schema(description = "État du système d’éclairage", example = "OK")
    private String lighting;

    @Schema(description = "Charge maximale", example = "2 tonnes")
    private String load;

    @Schema(description = "Nombre de sièges", example = "5")
    private String numberSeat;

    @Schema(description = "Présence de pare-brise", example = "Oui")
    private String parePrise;

    @Schema(description = "État de la fusée (rocket)", example = "OK")
    private String rocket;

    @Schema(description = "État des amortisseurs", example = "OK")
    private String shockAbsorber;

    @Schema(description = "Vitesse maximale testée", example = "160 km/h")
    private String speed;

    @Schema(description = "Type de suspension", example = "Hydraulique")
    private String suspension;

    @Schema(description = "Type de transmission", example = "Automatique")
    private String transmission;

    @Schema(description = "Type de roues", example = "Alliage")
    private String wheels;

    @Schema(description = "Modèle des roues", example = "R16")
    private String wheelsType;

    @Schema(description = "Pont utilisé", example = "Simple")
    private String bridge;

    @Size(max = 20, message = "Le code de l'agence ne doit pas dépasser 20 caractères")
    @Schema(description = "Code de l'agence", example = "AGC001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String agencyCode;

    @Size(max = 100, message = "Le nom de l'agence ne doit pas dépasser 100 caractères")
    @Schema(description = "Nom de l'agence", example = "Agence centrale de Gitega", requiredMode = Schema.RequiredMode.REQUIRED)
    private String agencyName;

    @Schema(description = "Date de création de l'enregistrement", example = "2025-01-15 14:33:22")
    private String logCreated;

    @Schema(description = "Statut de validité", example = "VALID")
    private String validStatus;
}
