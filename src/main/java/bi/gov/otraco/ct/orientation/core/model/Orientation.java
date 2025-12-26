package bi.gov.otraco.ct.orientation.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@NoArgsConstructor@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
@Schema(name = "Orientation")
public class Orientation {

    @Id
    private String orientationId;
    @Field("orientation_code")    private String orientationCode;
    @Field("receipt_no")    private String receiptNo;
    @Field("payment_no")    private String paymentNo;
    @Field("chassis_no")    private String chassisNo;
    @Field("plate_no")    private String plateNo;
    @Field("tin_no")    private String tinNo;
    @Field("orientation_line_code")    private String orientationLineCode;
    @Field("orientation_line_name")    private String orientationLineName;
    @Field("orientation_status")    private String orientationStatus;
    @Field("branch_code")    private String branchCode;
    @Field("branch_name")    private String branchName;
    @Field("user_code")    private String userCode;
    @Field("user_name")    private String userName;
    @Field("log_created_at")    private String logCreatedAt;
    @Field("vehicle_type")    private String vehicleType;
    @Field("owner_name")    private String ownerName;
    @Field("owner_category")    private String ownerCategory;
    @Field("invoice_number")    private String invoiceNumber;
    @Field("payment_status")    private String paymentStatus;
}