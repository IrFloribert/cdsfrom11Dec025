package bi.gov.otraco.ct.orientation.cmd.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.payload.NaturesPayload;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/otraco/certificate/nature/create")
@Tag(name = "Nature")
public class CreateNatureController {
    private final NaturesPayload payload;
    private final NatureEventHandler handler;

    @Operation(summary = "Create Nature"
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Nature créée avec succès",
//                            content = @Content(schema = @Schema(implementation = MessageResponse.class))),
//                    @ApiResponse(responseCode = "400", description = "Données invalides",
//                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
//                    @ApiResponse(responseCode = "500", description = "Erreur serveur",
//                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
//            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> create(@Valid @RequestBody NatureCreatedCommand cmd) {
        return payload.exist(cmd).flatMap(exists -> {
            if (Boolean.TRUE.equals(exists)) {
                return Mono.just(ResponseEntity.badRequest()
                        .body(new MessageResponse(false, "We zombie")));
            } else {
                return handler.create1(cmd);
            }
        });



    }
}
