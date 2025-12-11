package bi.gov.otraco.ct.orientation.query.api.dto;

import bi.gov.otraco.ct.orientation.query.api.response.NatureResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "LookupNatureResponse")
public class LookupNatureResponse {
    private Boolean success;
    private NatureResponse data;
}
