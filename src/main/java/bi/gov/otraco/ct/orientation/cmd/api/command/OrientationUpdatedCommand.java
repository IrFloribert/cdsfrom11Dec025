package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

@Schema(name = "Orientation Updated Command")
public record OrientationUpdatedCommand(
        @NotNull(message = "Le code d'orientation ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code d'orientation doit contenir au moins 6 caractères alphanumériques majuscules")
        @Schema(description = "Code d'orientation", example = "OR30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationCode,

        @NotNull(message = "Le numéro de reçu ne doit pas être nul")
        @Schema(description = "Numéro de reçu associé à l'orientation", example = "RCPT2025000123", requiredMode = Schema.RequiredMode.REQUIRED)
        String receiptNo,

        @NotNull(message = "Le numéro de paiement ne doit pas être nul")
        @Schema(description = "Numéro de référence de paiement", example = "PAY2025000456", requiredMode = Schema.RequiredMode.REQUIRED)
        String paymentNo,

        @NotNull(message = "Le numéro de châssis ne doit pas être nul")
        @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Le numéro de châssis doit contenir 17 caractères alphanumériques valides")
        @Schema(description = "Numéro de châssis/VIN du véhicule", example = "VF3CR9HP0ES123456", requiredMode = Schema.RequiredMode.REQUIRED)
        String chassisNo,

        @NotNull(message = "Le numéro de plaque ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9-]{4,15}$", message = "Le numéro de plaque doit contenir entre 4 et 15 caractères")
        @Schema(description = "Numéro de plaque d'immatriculation du véhicule", example = "ABC-1234", requiredMode = Schema.RequiredMode.REQUIRED)
        String plateNo,

        @NotNull(message = "Le nom du propriétaire ne doit pas être nul")
        @Pattern(regexp = "^[\\p{L}0-9 .'-]{2,60}$", message = "Le nom du propriétaire doit contenir entre 2 et 60 caractères")
        @Schema(description = "Nom complet du propriétaire", example = "Jean Dupont", requiredMode = Schema.RequiredMode.REQUIRED)
        String ownerName,

        @NotNull(message = "Le numéro d'identification fiscale ne doit pas être nul")
        @Schema(description = "Numéro d'identification fiscale", example = "TIN123456789", requiredMode = Schema.RequiredMode.REQUIRED)
        String tinNo,

        @NotNull(message = "Le code de ligne d'orientation ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code de ligne d'orientation doit contenir au moins 6 caractères alphanumériques majuscules")
        @Schema(description = "Code de ligne d'orientation", example = "ORL30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationLineCode,

        @NotNull(message = "Le nom de la ligne d'orientation ne doit pas être nul")
        @Pattern(regexp = "^[\\p{L}0-9 .'-]{2,60}$", message = "Le nom de la ligne d'orientation doit contenir entre 2 et 60 caractères")
        @Schema(description = "Nom de la ligne d'orientation", example = "Ligne de Contrôle Technique A", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationLineName,

        @NotNull(message = "Le statut d'orientation ne doit pas être nul")
        @Schema(description = "Statut de l'orientation", example = "CREATED", requiredMode = Schema.RequiredMode.REQUIRED)
        String orientationStatus,

        @NotNull(message = "Le code d'agence ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code d'agence doit contenir au moins 6 caractères alphanumériques majuscules")
        @Schema(description = "Code d'agence", example = "AG30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String branchCode,

        @NotNull(message = "Le nom de l'agence ne doit pas être nul")
        @Pattern(regexp = "^[\\p{L}0-9 .'-]{2,60}$", message = "Le nom de l'agence doit contenir entre 2 et 60 caractères")
        @Schema(description = "Nom de l'agence", example = "Agence Centrale OTRACO", requiredMode = Schema.RequiredMode.REQUIRED)
        String branchName,

        @NotNull(message = "Le code utilisateur ne doit pas être nul")
        @Pattern(regexp = "^[A-Z0-9]{6,}$", message = "Le code utilisateur doit contenir au moins 6 caractères alphanumériques majuscules")
        @Schema(description = "Code utilisateur de l'opérateur", example = "USR30000000001", requiredMode = Schema.RequiredMode.REQUIRED)
        String userCode,

        @NotNull(message = "Le nom d'utilisateur ne doit pas être nul")
        @Pattern(regexp = "^[\\p{L}0-9._-]{2,30}$", message = "Le nom d'utilisateur doit contenir entre 2 et 30 caractères")
        @Schema(description = "Nom d'utilisateur de l'opérateur", example = "jane.dupont", requiredMode = Schema.RequiredMode.REQUIRED)
        String userName,

        @NotNull(message = "Le journal de création ne doit pas être nul")
        @Schema(description = "Journal de création ou commentaire", example = "Créé via API", requiredMode = Schema.RequiredMode.REQUIRED)
        String logCreated
) implements Serializable {}