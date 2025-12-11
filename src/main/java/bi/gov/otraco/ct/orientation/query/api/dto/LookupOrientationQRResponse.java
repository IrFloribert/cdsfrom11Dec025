package bi.gov.otraco.ct.orientation.query.api.dto;

import bi.gov.otraco.ct.orientation.query.api.response.OrientationQRResponse;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Orientation QR Response")
public record LookupOrientationQRResponse(
    Boolean success,
    OrientationQRResponse orientationQRResponse
) implements Serializable {}