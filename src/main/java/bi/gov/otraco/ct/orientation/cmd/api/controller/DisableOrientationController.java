// package bi.gov.otraco.ct.orientation.cmd.api.controller;

// import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationStatusCommand;
// import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
// import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
// import bi.gov.otraco.ct.orientation.core.utils.MapUtils;
// import bi.gov.otraco.ct.orientation.query.api.handler.OrientationEventHandler;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import reactor.core.publisher.Mono;

// @RestController
// @RequestMapping("bi/gov/otraco/ct/orientation/disable")
// @Tag(name = "Orientation")
// @RequiredArgsConstructor  // This will generate a constructor for final fields
// public class DisableOrientationController {
//     private final OrientationPayload orientationPayload;
//     private final OrientationEventHandler handler;

//     @Operation(summary = "disable orientation")
//     @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//     public Mono<ResponseEntity<MessageResponse>> disable(@Valid @RequestBody OrientationStatusCommand cmd) {
//         return orientationPayload.statusException(cmd).then(handler.disable(cmd))
//             .map(s -> ResponseEntity.ok(new MessageResponse(true, MapUtils.disable)))
//             .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(new MessageResponse(false, ex.getMessage()))));
//     }
// }