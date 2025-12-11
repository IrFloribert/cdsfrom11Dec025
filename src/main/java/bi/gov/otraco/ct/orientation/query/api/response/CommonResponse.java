package bi.gov.otraco.ct.orientation.query.api.response;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(name = "Common response")
public record CommonResponse(String code, String name) implements Serializable {}
