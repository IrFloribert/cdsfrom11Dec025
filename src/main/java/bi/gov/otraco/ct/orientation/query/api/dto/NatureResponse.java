package bi.gov.otraco.ct.orientation.query.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "NatureResponse")
public class NatureResponse {
    private String id;
    private String receiptNo;
    private String plateNo;
    private String chassisNo;
    private String ownerTinNo;
    private String ownerName;
    private String vehicleBreak;
    private String compressibility;
    private String direction;
    private String document;
    private String engine;
    private String lighting;
    private String load;
    private String numberSeat;
    private String parePrise;
    private String rocket;
    private String shockAbsorber;
    private String speed;
    private String suspension;
    private String transmission;
    private String wheels;
    private String wheelsType;
    private String bridge;
    private String agencyCode;
    private String agencyName;
    private String logCreated;
    private String validStatus;


}
