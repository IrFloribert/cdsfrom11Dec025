package bi.gov.otraco.ct.orientation.query.api.dto;

import bi.gov.otraco.ct.orientation.query.api.response.NatureResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "AllNatureResponse")
public class AllNatureResponse {
    private Boolean success;
    private List<NatureResponse> data;
}
