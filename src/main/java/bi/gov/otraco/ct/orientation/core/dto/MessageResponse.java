package bi.gov.otraco.ct.orientation.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Message Response")
public record MessageResponse(Boolean success, String message) implements Serializable {}
