package bi.gov.otraco.ct.orientation.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Payment Display Response")
public record PaymentDisplayResponse(
    @Schema(description = "Numéro de plaque")
    String plateNo,

    @Schema(description = "Numéro de châssis")
    String chassisNo,

    @Schema(description = "Nom complet du propriétaire")
    String ownerName,

    @Schema(description = "Catégorie du véhicule (Ligne d'orientation)")
    String vehicleCategory,

    @Schema(description = "NIF (Numéro d'Identification Fiscale)")
    String tinNo,

    @Schema(description = "Numéro de réception")
    String receiptNo,

    @Schema(description = "Type du véhicule")
    String vehicleType,

    @Schema(description = "Catégorie du propriétaire")
    String ownerCategory,

    @Schema(description = "Date de création")
    String createdDate,

    @Schema(description = "Numéro de facture")
    String invoiceNumber,

    @Schema(description = "Numéro de paiement")
    String paymentNumber,

    @Schema(description = "Statut de paiement")
    String paymentStatus
) implements Serializable {
}
