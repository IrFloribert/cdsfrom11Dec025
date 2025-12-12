package bi.gov.otraco.ct.orientation.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
@Schema(name = "Nature")
@Builder
@AllArgsConstructor
@Data
public class Nature {
    @Id
    private String natureId;
    @Field("code")    private String code;
    @Field("receipt_no")    private String receiptNo;
    @Field("plate_no")    private String plateNo;
    @Field("chassis_no")    private String chassisNo;
    @Field("owner_tin_no")    private String ownerTinNo;
    @Field("owner_name")    private String ownerName;



    @Field("vehicle_break")    private String vehicleBreak;
    @Field("compressibility")    private String compressibility;
    @Field("direction")    private String direction;
    @Field("document")    private String document;
    @Field("engine")    private String engine;
    @Field("lighting")    private String lighting;
    @Field("load")    private String load;
    @Field("number_seat")    private String numberSeat;
    @Field("pare_prise")    private String parePrise;
    @Field("rocket")    private String rocket;
    @Field("shock_absorber")    private String shockAbsorber;
    @Field("speed")    private String speed;
    @Field("suspension")    private String suspension;
    @Field("transmission")    private String transmission;
    @Field("wheels")    private String wheels;
    @Field("wheels_type")    private String wheelsType;
    @Field("bridge")    private String bridge;



    @Field("branch_code")    private String branchCode;
    @Field("branch_name")    private String branchName;
    @Field("log_created")    private String logCreatedAt;
    @Field("valid_status")    private String validStatus;

    @Field("user_code")    private String userCode;
    @Field("user_name")    private String userName;
}
