package bi.gov.otraco.ct.orientation.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;


@Schema(name = "NatureResponse")
public record NatureResponse (
    String natureId,
    String natureCode,
    String receiptNo,
    String plateNo,
    String chassisNo,
    String ownerTinNo,
    String ownerName,
    String vehicleBreak,
    String compressibility,
    String direction,
    String document,
    String engine,
    String lighting,
    String load,
    String numberSeat,
    String parePrise,
    String rocket,
    String shockAbsorber,
    String speed,
    String suspension,
    String transmission,
    String wheels,
    String wheelsType,
    String bridge,
    String branchCode,
    String branchName,
    String logCreated,
    String validStatus,
    String userCode,
    String userName,
    String qr)implements Serializable{


}
