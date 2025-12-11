package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Schema(name = "NatureCreatedCommand", description = "Représente les données d'entrée pour une nature de véhicule")
public record NatureCreatedCommand (

    @Size(max = 50, message = "Le numéro de reçu ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de reçu associé à la nature", example = "RCPT2025010001", requiredMode = Schema.RequiredMode.REQUIRED)
    String receiptNo,

    @Size(max = 20, message = "Le numéro de plaque ne doit pas dépasser 20 caractères")
    @Schema(description = "Numéro d’immatriculation du véhicule", example = "AB123CD", requiredMode = Schema.RequiredMode.REQUIRED)
    String plateNo,

    @Size(max = 50, message = "Le numéro de châssis ne doit pas dépasser 50 caractères")
    @Schema(description = "Numéro de châssis du véhicule", example = "CHASSIS987654321", requiredMode = Schema.RequiredMode.REQUIRED)
    String chassisNo,

    @Size(max = 30, message = "Le numéro TIN ne doit pas dépasser 30 caractères")
    @Schema(description = "Numéro TIN du propriétaire", example = "TIN55667788", requiredMode = Schema.RequiredMode.REQUIRED)
    String ownerTinNo,

    @Size(max = 100, message = "Le nom du propriétaire ne doit pas dépasser 100 caractères")
    @Schema(description = "Nom complet du propriétaire", example = "Jean-Pierre Nduwimana", requiredMode = Schema.RequiredMode.REQUIRED)
    String ownerName,



    @Size(max = 20, message = "Le code de l'agence ne doit pas dépasser 20 caractères")
    @Schema(description = "Code de l'agence", example = "AGC001", requiredMode = Schema.RequiredMode.REQUIRED)
    String branchCode,

    @Size(max = 100, message = "Le nom de l'agence ne doit pas dépasser 100 caractères")
    @Schema(description = "Nom de l'agence", example = "Agence centrale de Gitega", requiredMode = Schema.RequiredMode.REQUIRED)
    String branchName,

    @Schema(description = "Date de création de l'enregistrement", example = "2025-01-15 14:33:22")
    String logCreated,

    @Schema(description = "Statut de validité", example = "VALID")
    String validStatus,


    @Schema(description = "Nombre de sièges", example = "GOOD")
    String number)implements Serializable{





}
