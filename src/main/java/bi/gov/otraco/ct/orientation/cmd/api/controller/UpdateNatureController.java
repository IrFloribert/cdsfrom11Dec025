package bi.gov.otraco.ct.orientation.cmd.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureUpdateCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureVerifyCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.payload.NaturePayload;
import bi.gov.otraco.ct.orientation.core.utils.MapUtils;
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
@RequestMapping("/api/v1/otraco/certificate/nature/update-cp")
@Tag(name = "Nature")
public class UpdateNatureController {
    private final NaturePayload payload;
    private final NatureEventHandler handler;

    @Operation(summary = "Update nature")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody NatureVerifyCommand cmd) {
        return payload.updateException(cmd).then(handler.verifyState(cmd))
            .map(s -> ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, MapUtils.update)))
            .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(false, ex.getMessage()))));
    }
}
