package bi.gov.otraco.ct.orientation.core.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Réponse d'erreur standard de l'API")
public record ErrorResponse(@Schema(description = "Horodatage de l'erreur") LocalDateTime timestamp,
                            @Schema(description = "Code d'erreur HTTP") int status,
                            @Schema(description = "Message d'erreur") String error,
                            @Schema(description = "Message détaillé") String message,
                            @Schema(description = "Chemin de la requête") String path) {
    // Constructeur, getters, builder...
}