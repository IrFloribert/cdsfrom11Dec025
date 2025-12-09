package bi.gov.otraco.ct.orientation.query.api.dto;

import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Lookup Orientation Response")
public record LookupOrientationResponse(
    Boolean success,
    OrientationResponse orientation
) implements Serializable {}