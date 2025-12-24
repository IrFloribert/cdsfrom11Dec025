package bi.gov.otraco.ct.orientation.cmd.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.exception.ErrorResponse;
import bi.gov.otraco.ct.orientation.core.exception.RemoteApiException;
import bi.gov.otraco.ct.orientation.core.payload.NaturesPayload;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otraco/certificate/nature/create")
@Tag(name = "Nature")
public class CreateNatureController {
    private final NaturesPayload payload;
    private final NatureEventHandler handler;

    @Operation(summary = "Create Nature", responses = {@ApiResponse(responseCode = "201", description = "Nature créée avec succès", content = @Content(schema = @Schema(implementation = MessageResponse.class))), @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))), @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Object>> create(@Valid @RequestBody NatureCreatedCommand cmd) {
        return payload.createException(cmd).then(handler.create1(cmd))
                .map(nature -> ResponseEntity.status(HttpStatus.CREATED)
                        .body((Object) new MessageResponse(true, "Nature créée avec succès")))
                .onErrorResume(RemoteApiException.class, ex -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body((Object) ErrorResponse.builder().timestamp(LocalDateTime.now()).status(HttpStatus.BAD_REQUEST.value())
                                .error("Erreur de validation").message(ex.getMessage()).build())))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body((Object) ErrorResponse.builder().timestamp(LocalDateTime.now())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).error("Erreur serveur")
                                .message("Une erreur est survenue lors du traitement de votre demande").build())));
    }
}