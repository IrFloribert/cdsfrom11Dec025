package bi.gov.otraco.ct.orientation.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Type response")
public record TypeResponse(
    String typeId,
    String code,
    String name
) implements Serializable {}
