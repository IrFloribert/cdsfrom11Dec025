package bi.gov.otraco.ct.orientation.cmd.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.payload.NaturePayload;
import bi.gov.otraco.ct.orientation.core.payload.NaturePayload;
import bi.gov.otraco.ct.orientation.core.utils.MapUtils;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otraco/certificate/nature/create")
@Tag(name = "Nature")
public class CreateNatureController {
    private final NaturePayload payload;
    private final NatureEventHandler handler;

    @Operation(summary = "Create orientation")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody NatureCreatedCommand cmd) {
        return payload.createException(cmd).then(handler.create(cmd))
                .map(s -> ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, MapUtils.create)))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MessageResponse(false, ex.getMessage()))));
    }
}