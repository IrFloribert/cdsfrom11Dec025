package bi.gov.otraco.ct.orientation.query.api.response;
import java.io.Serializable;
public record OrientationResponse(
        String orientationId,
        String orientationCode,
        String receiptNo,
        String paymentNo,
        String chassisNo,
        String plateNo,
        String ownerName,
        String tinNo,
        String orientationLineCode,
        String orientationLineName,
        String branchCode,
        String branchName,
        String userCode,
        String userName,
        String logCreated,
        String vehicleType,
        String ownerCategory,
        String invoiceNumber,
        String paymentStatus,
        String qr



) implements Serializable {
}
