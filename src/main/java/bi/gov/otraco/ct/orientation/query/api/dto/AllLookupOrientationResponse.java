package bi.gov.otraco.ct.orientation.query.api.dto;

import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Orientation Response")
public record AllLookupOrientationResponse(
        Boolean success,
        List<OrientationResponse> orientations
) implements Serializable {
}