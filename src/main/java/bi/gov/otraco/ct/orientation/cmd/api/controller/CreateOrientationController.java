package bi.gov.otraco.ct.orientation.cmd.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
import bi.gov.otraco.ct.orientation.core.utils.MapUtils;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationEventHandler;
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
@RequestMapping("bi/gov/otraco/ct/orientation/create")
@Tag(name = "Orientation")
public class CreateOrientationController {
    private final OrientationPayload payload;
    private final OrientationEventHandler handler;

    @Operation(summary = "Create orientation")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody OrientationCreatedCommand cmd) {
        return payload.createException(cmd).then(handler.create(cmd))
            .map(s -> ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, MapUtils.create)))
            .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(false, ex.getMessage()))));
    }
}