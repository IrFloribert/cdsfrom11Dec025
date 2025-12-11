package bi.gov.otraco.ct.orientation.query.api.response;
import java.io.Serializable;
public record OrientationResponse(
    String orientationCode,
    String plateNo,
    String chassisNo,
    String ownerName,

    String tinNo,
    String receiptNo,
    String vehicleType,
    String createdDate,
    String branchName,


    String orientationNumber,
    String paymentNumber,
    String orientationLine,
    String orientationStatus



) implements Serializable {
}
