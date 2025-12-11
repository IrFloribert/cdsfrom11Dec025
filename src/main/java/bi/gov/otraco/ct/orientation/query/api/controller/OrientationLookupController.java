package bi.gov.otraco.ct.orientation.query.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.query.api.dto.AllLookupOrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("bi/gov/otraco/ct/orientation/lookup")
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


    @Operation(summary = "Get today all orientations")
    @GetMapping(path = "get-today-orientations", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupOrientationResponse> getTodayOrientations() {
        return queryHandler.findAllToday()
            .collectList()
            .map(list -> new AllLookupOrientationResponse(true, list));
    }


    @Operation(summary = "Get all orientations by agency")
    @PutMapping(path = "get-orientations-by-agency", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupOrientationResponse> getOrientationsByAgency(@Valid @RequestBody FindByCode query) {
        return queryHandler.findAllBybranchCode(query)
            .collectList()
            .map(list -> new AllLookupOrientationResponse(true, list));
    }




    @Operation(summary = "Get all orientations by agency today")
    @PutMapping(path = "get-orientations-by-agency-today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupOrientationResponse> getOrientationsByAgencyToday(@Valid @RequestBody FindByCode query) {
        return queryHandler.findAllBybranchCodeToday(query)
            .collectList()
            .flatMap(list -> {
                if (list.isEmpty()) {
                    throw new RuntimeException("No orientations found for agency today");                  
                } else {
                    return Mono.just(new AllLookupOrientationResponse(true, list));
                }
            });
    }


}