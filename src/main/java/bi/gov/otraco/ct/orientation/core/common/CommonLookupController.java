
package bi.gov.otraco.ct.orientation.core.common;

import bi.gov.otraco.ct.orientation.query.api.dto.LookupCommonResponse;
import bi.gov.otraco.ct.orientation.query.api.handler.CommonQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/otraco/certificate/orientation/common/lookup-common/")
@Tag(name = "Common", description = "Data rest API for unite resource")
public class CommonLookupController {
    private final CommonQueryHandler commonQueryHandler;

    @Operation(summary = "Get combo status")
    @GetMapping(path = "get-combo-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupCommonResponse>> ownerType() {
        return commonQueryHandler.getComboType()
                .map(commonResponses ->
                        new LookupCommonResponse(true, commonResponses))
                .map(ResponseEntity::ok);
    }
}



































//
//
//
//
//
//
//
//    @Operation(summary = "Get period")
//    @GetMapping(path = "get-period", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<LookupCommonResponse>> period () {
//        return commonQueryHandler.getPeriod()
//                .map(commonResponses ->
//                        new LookupCommonResponse(true, commonResponses))
//                .map(ResponseEntity::ok);
//    }
//
//    @Operation(summary = "Get vehicle-category")
//    @GetMapping(path = "get-vehicle-category", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<LookupCommonResponse>> vehicleCategory () {
//        return commonQueryHandler.getCertVehicleCategory()
//                .map(commonResponses ->
//                        new LookupCommonResponse(true, commonResponses))
//                .map(ResponseEntity::ok);
//    }
//
//    @Operation(summary = "Get demand for reception")
//    @GetMapping(path = "get-demand-reception", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<LookupCommonResponse>> demand () {
//        return commonQueryHandler.getDemand()
//                .map(commonResponses ->
//                        new LookupCommonResponse(true, commonResponses))
//                .map(ResponseEntity::ok);
//    }
//
//    @Operation(summary = "Get vehicle-type")
//    @GetMapping(path = "get-vehicle-type", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<LookupCommonResponse>> vehicleType () {
//        return commonQueryHandler.getVehicleType()
//                .map(commonResponses ->
//                        new LookupCommonResponse(true, commonResponses))
//                .map(ResponseEntity::ok);
//    }
//
//    @Operation(summary = "Get card-type")
//    @GetMapping(path = "get-card-type", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<LookupCommonResponse>> cardType () {
//        return commonQueryHandler.getCardType()
//                .map(commonResponses ->
//                        new LookupCommonResponse(true, commonResponses))
//                .map(ResponseEntity::ok);
//    }
