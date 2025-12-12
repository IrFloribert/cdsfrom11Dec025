package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints
        .Pattern;

import java.io.Serializable;


@Schema(name = "Orientation Created Command", description = "Commande de création d'une orientation de véhicule")
public record OrientationCreatedCommand(
        @NotNull(message = "Le numéro de reçu est obligatoire")
        @Schema(description = "Numéro de reçu unique pour le paiement de l'orientation", example = "RCPT2025000123", requiredMode = Schema.RequiredMode.REQUIRED)
        String receiptNo,

        @NotNull(message = "Le numéro de paiement est obligatoire")
        @Pattern(regexp = "^[A-HJ-NPR-Z0-9-]{6,}$", message = "Format de numéro de paiement invalide. Doit contenir au moins 6 caractères alphanumériques")
        @Schema(description = "Référence unique du paiement effectué", example = "PAY2025000456", requiredMode = Schema.RequiredMode.REQUIRED)
        String paymentNo,

        @NotNull(message = "Le numéro de châssis est obligatoire")
        @Pattern(regexp = "^[A-HJ-NPR-Z0-9-]{6,}$", message = "Format de numéro de châssis invalide. Doit contenir au moins 6 caractères alphanumériques")
        @Schema(description = "Numéro d'identification du véhicule (VIN)", example = "VF3CR9HP0ES123456", requiredMode = Schema.RequiredMode.REQUIRED)
        String chassisNo,

        @NotNull(message = "Le numéro de plaque d'immatriculation est obligatoire")
        @Pattern(regexp = "^[A-Z0-9-]{4,15}$", message = "Format de plaque d'immatriculation invalide. Doit contenir entre 4 et 15 caractères alphanumériques")
        @Schema(description = "Plaque d'immatriculation du véhicule", example = "ABC-1234", requiredMode = Schema.RequiredMode.REQUIRED)
        String plateNo,

        @NotNull(message = "Le type de véhicule est obligatoire")
        @Pattern(regexp = "^[\\p{L}0-9 ._-]{2,30}$", message = "Le type de véhicule doit contenir entre 2 et 30 caractères")
        @Schema(description = "Catégorie du véhicule (ex: Voiture, Moto, Camion)", example = "VOITURE", requiredMode = Schema.RequiredMode.REQUIRED)
        String vehicleType,

        @NotNull(message = "Le nom du propriétaire est obligatoire")
        @Pattern(regexp = "^[\\p{L}0-9 .'-]{2,60}$", message = "Le nom du propriétaire doit contenir entre 2 et 60 caractères")
        @Schema(description = "Nom complet du propriétaire du véhicule", example = "Jean Dupont", requiredMode = Schema.RequiredMode.REQUIRED)
        String ownerName,

        @NotNull(message = "Le numéro d'identification fiscale (TIN) est obligatoire")
        @Schema(description = "Numéro d'identification fiscale (TIN) du propriétaire", example = "TIN123456789", requiredMode = Schema.RequiredMode.REQUIRED)
        String tinNo,

        @NotNull(message = "Le code d'agence est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code d'agence doit contenir au moins 6 caractères alphanumériques en majuscules")
        @Schema(description = "Code unique identifiant l'agence OTRACO", example = "AG30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String branchCode,

        @NotNull(message = "Le nom de l'agence est obligatoire")
        @Pattern(regexp = "^[\\p{L}0-9 .'-]{2,60}$", message = "Le nom de l'agence doit contenir entre 2 et 60 caractères")
        @Schema(description = "Nom complet de l'agence OTRACO", example = "Agence Centrale OTRACO", requiredMode = Schema.RequiredMode.REQUIRED)
        String branchName,

        @NotNull(message = "Le code utilisateur est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code utilisateur doit contenir au moins 6 caractères alphanumériques en majuscules")
        @Schema(description = "Identifiant unique de l'opérateur", example = "USR30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String userCode,

        @NotNull(message = "Le nom d'utilisateur est obligatoire")
        @Pattern(regexp = "^[\\p{L}0-9._-]{2,30}$", message = "Le nom d'utilisateur doit contenir entre 2 et 30 caractères")
        @Schema(description = "Identifiant de connexion de l'opérateur", example = "jane.dupont", requiredMode = Schema.RequiredMode.REQUIRED)
        String userName,


        @Schema(description = "Numéro de facture associé à l'opération", example = "INV2025000789", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String invoiceNumber,


        @Schema(description = "Catégorie du propriétaire (ex: PARTICULIER, ENTREPRISE)", example = "PARTICULIER", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String ownerCategory
) implements Serializable {
    private static final long serialVersionUID = 1L;
}


//        @NotNull(message ="ne doit pas être nul")
//
//        @Schema(description = "Statut du paiement", example = "PENDING", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
//
//        String paymentStatus


//        @NotNull(message = "Le nom d'utilisateur ne doit pas être nul")
//
//        @Pattern(regexp = "^[\\p{L}0-9._-]{2,30}$", message = "Le nom d'utilisateur doit contenir entre 2 et 30 caractères")
//
//        @Schema(description = "Nom d'utilisateur de l'opérateur", example = "jane.dupont", requiredMode = Schema.RequiredMode.REQUIRED)
//
//        String deliveredTo,