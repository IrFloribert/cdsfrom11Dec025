package bi.gov.otraco.ct.orientation.core.feign.validator.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record ValidationCommand(
        @NotNull(message = "Receipt No must not be null")
        @Pattern(regexp = "^[A-Za-z0-9-]{3,20}$", message = "Receipt No must have 3 to 20 characters")
        String receiptNo,

        @NotNull(message = "Tin No must not be null")
        @Pattern(regexp = "^[0-9]{3,20}$", message = "Tin No must have 3 to 20 characters, numbers only")
        String ownerTinNo,

        @NotNull(message = "Plate No must not be null")
        @Pattern(regexp = "^[A-Z0-9]{3,8}$", message = "Plate No must have 3 to 8 characters")
        String plateNo,

        @NotNull(message = "Chassis No must not be null")
        @Pattern(regexp = "^[A-Z0-9- *]{5,}$", message = "Chassis No must have 3 to 20 characters")
        String chassisNo,

        @NotNull(message = "User must not be null")
        @Pattern(regexp = "^[A-Z0-9]{3,90}$", message = "User must have 3 to 90 characters")
        String user
) implements Serializable {
}