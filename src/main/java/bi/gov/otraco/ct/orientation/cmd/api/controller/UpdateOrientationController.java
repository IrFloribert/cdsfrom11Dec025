package bi.gov.otraco.ct.orientation.cmd.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedCommand;
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
@RequestMapping("bi/gov/otraco/ct/orientation/update")
@Tag(name = "Orientation")
public class UpdateOrientationController {
    private final OrientationPayload payload;
    private final OrientationEventHandler handler;

    @Operation(summary = "Update orientation")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody OrientationUpdatedCommand cmd) {
        return payload.updateException(cmd).then(handler.update(cmd))
            .map(s -> ResponseEntity.ok(new MessageResponse(true, MapUtils.update)))
            .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(false, ex.getMessage()))));
    }
}