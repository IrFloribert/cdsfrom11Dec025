package bi.gov.otraco.ct.orientation.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Nature QR Response")
public record NatureQRResponse(

        String plaque,
        String chassisNo,
        String ownerName,
        String vehicleCategory,
        String tinNo,
        String receiptNo,
        String vehicleType,
        String ownerCategory,
        String createdAt,
        String invoiceNumber,
        String paymentNo,
        String paymentStatus


) implements Serializable {
}