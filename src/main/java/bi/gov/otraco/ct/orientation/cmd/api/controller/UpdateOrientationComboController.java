//package bi.gov.otraco.ct.orientation.cmd.api.controller;
//
//import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedComboCommand;
//import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
//import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
//import bi.gov.otraco.ct.orientation.core.utils.MapUtils;
//import bi.gov.otraco.ct.orientation.query.api.handler.OrientationEventHandler;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/otraco/certificate/orientation/update-combo")
//@Tag(name = "Orientation")
//public class UpdateOrientationComboController {
//    private final OrientationPayload orientationPayload;
//    private final OrientationEventHandler handler;
//
//    @Operation(summary = "Update orientation")
//    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody OrientationUpdatedComboCommand cmd) {
//        return orientationPayload.statusException(cmd).then(handler.updateTowardsCombo(cmd))
//            .map(s -> ResponseEntity.ok(new MessageResponse(true, MapUtils.update)))
//            .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new MessageResponse(false, ex.getMessage()))));
//    }
//}