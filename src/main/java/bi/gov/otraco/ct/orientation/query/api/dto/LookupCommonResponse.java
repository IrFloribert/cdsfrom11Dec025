package bi.gov.otraco.ct.orientation.query.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Orientation Response")
public record LookupCommonResponse(
        Boolean success,
        java.util.List<bi.gov.otraco.ct.orientation.query.api.response.CommonResponse> orientation
) implements Serializable {}