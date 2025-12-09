package bi.gov.otraco.ct.orientation.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Daily Display Response")
public record DailyDisplayResponse(
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

    @Schema(description = "Délivré à (Agence)")
    String deliveredTo,

    @Schema(description = "Date de création")
    String createdDate,

    @Schema(description = "Numéro d'orientation")
    String orientationNumber,

    @Schema(description = "Numéro de paiement")
    String paymentNumber,

    @Schema(description = "Ligne d'orientation")
    String orientationLine
) implements Serializable {
}
