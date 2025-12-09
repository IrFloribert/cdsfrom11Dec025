package bi.gov.otraco.ct.orientation.query.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.query.api.dto.AllLookupOrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.dto.LookupOrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationQueryHandler;
import bi.gov.otraco.ct.orientation.query.api.response.PaymentDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.GlobalDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.DailyDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.AgencyGlobalDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.AgencyDailyDisplayResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/smu/orientation/lookup")
@Tag(name = "Orientation")
@RequiredArgsConstructor
public class OrientationLookupController {
    private final OrientationQueryHandler queryHandler;

    @Operation(summary = "Get all orientations")
    @GetMapping(path = "get-orientations", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupOrientationResponse> getOrientations() {
        return queryHandler.findAll()
            .collectList()
            .map(list -> new AllLookupOrientationResponse(true, list));
    }

    @Operation(summary = "Get orientation by code")
    @PutMapping(path = "get-orientation-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupOrientationResponse> getByCode(@Valid @RequestBody FindByCode query) {
        return queryHandler.findByCode(query.code())
            .map(o -> new LookupOrientationResponse(true, o))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }

    // Payment Display Endpoints
    @Operation(summary = "Get all orientations for payment display")
    @GetMapping(path = "payment-display", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PaymentDisplayResponse> getPaymentDisplay() {
        return queryHandler.findAllOrientations()
            .map(queryHandler::toPaymentDisplay);
    }

    @Operation(summary = "Get orientation by code for payment display")
    @PutMapping(path = "payment-display-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PaymentDisplayResponse> getPaymentDisplayByCode(@Valid @RequestBody FindByCode query) {
        return queryHandler.findOrientationByCode(query.code())
            .map(queryHandler::toPaymentDisplay)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }

    // Global Display Endpoints
    @Operation(summary = "Get all orientations for global display")
    @GetMapping(path = "global-display", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<GlobalDisplayResponse> getGlobalDisplay() {
        return queryHandler.findAllOrientations()
            .map(queryHandler::toGlobalDisplay);
    }

    @Operation(summary = "Get orientation by code for global display")
    @PutMapping(path = "global-display-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GlobalDisplayResponse> getGlobalDisplayByCode(@Valid @RequestBody FindByCode query) {
        return queryHandler.findOrientationByCode(query.code())
            .map(queryHandler::toGlobalDisplay)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }

    // Daily Display Endpoints
    @Operation(summary = "Get all orientations for daily display")
    @GetMapping(path = "daily-display", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<DailyDisplayResponse> getDailyDisplay() {
        return queryHandler.findAllOrientations()
            .map(queryHandler::toDailyDisplay);
    }

    @Operation(summary = "Get orientation by code for daily display")
    @PutMapping(path = "daily-display-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DailyDisplayResponse> getDailyDisplayByCode(@Valid @RequestBody FindByCode query) {
        return queryHandler.findOrientationByCode(query.code())
            .map(queryHandler::toDailyDisplay)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }

    // Agency Global Display Endpoints
    @Operation(summary = "Get all orientations for agency global display")
    @GetMapping(path = "agency-global-display", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AgencyGlobalDisplayResponse> getAgencyGlobalDisplay() {
        return queryHandler.findAllOrientations()
            .map(queryHandler::toAgencyGlobalDisplay);
    }

    @Operation(summary = "Get orientation by code for agency global display")
    @PutMapping(path = "agency-global-display-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AgencyGlobalDisplayResponse> getAgencyGlobalDisplayByCode(@Valid @RequestBody FindByCode query) {
        return queryHandler.findOrientationByCode(query.code())
            .map(queryHandler::toAgencyGlobalDisplay)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }

    // Agency Daily Display Endpoints
    @Operation(summary = "Get all orientations for agency daily display")
    @GetMapping(path = "agency-daily-display", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AgencyDailyDisplayResponse> getAgencyDailyDisplay() {
        return queryHandler.findAllOrientations()
            .map(queryHandler::toAgencyDailyDisplay);
    }

    @Operation(summary = "Get orientation by code for agency daily display")
    @PutMapping(path = "agency-daily-display-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AgencyDailyDisplayResponse> getAgencyDailyDisplayByCode(@Valid @RequestBody FindByCode query) {
        return queryHandler.findOrientationByCode(query.code())
            .map(queryHandler::toAgencyDailyDisplay)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }
}