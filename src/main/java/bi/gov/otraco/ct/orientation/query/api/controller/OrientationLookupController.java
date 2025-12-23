package bi.gov.otraco.ct.orientation.query.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCodes;
import bi.gov.otraco.ct.orientation.cmd.api.command.FindById;
import bi.gov.otraco.ct.orientation.query.api.dto.AllLookupOrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.dto.LookupOrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Collections;
@Slf4j
@RestController
@RequestMapping("/api/v1/otraco/certificate/orientation/lookup")
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


    @Operation(summary = "Get orientation by natureCode")
    @PutMapping(path = "get-orientation-by-natureCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupOrientationResponse> getOrintationByCode(@Valid@RequestBody FindByCode query) {
        return queryHandler.findByOrientationCode(query.code())
                .map(category -> new LookupOrientationResponse(true, category))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "orientation not found")));
    }





    @Operation(summary = "Get orientation by natureCode")
    @PutMapping(path = "get-orientation-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupOrientationResponse> getOrintationById(@Valid@RequestBody FindById query) {
        return queryHandler.findByOrientationId(query.id())
                .map(category -> new LookupOrientationResponse(true, category))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "orientation not found")));
    }





    @Operation(summary = "Get all orientations by agency today")
    @PutMapping(path = "get-orientations-by-agency-today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupOrientationResponse> getOrientationsByAgencyToday(
            @Valid @RequestBody FindByCode query) {

        return queryHandler.findAllBybranchCodeToday(query)
                .collectList()
                .flatMap(list -> {
                    if (list.isEmpty()) {
                        log.warn("Aucune orientation trouvée pour l'agence {} aujourd'hui", query.code());
                        return Mono.just(new AllLookupOrientationResponse(false, Collections.emptyList()));
                    }
                    return Mono.just(new AllLookupOrientationResponse(true, list));
                })
                .onErrorResume(e -> {
                    log.error("Erreur lors de la récupération des orientations", e);
                    return Mono.just(new AllLookupOrientationResponse(false, Collections.emptyList()));
                });
    }

    @Operation(summary = "Get orientation by |plate-tin-chassis| number")
    @PutMapping(path = "get-orientation-by-plate-and-tin-and-chassis-number", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupOrientationResponse> getByPlateNoAndTCNo(@Valid @RequestBody FindByCodes query) {
        return queryHandler.findByPlateAndTinAndChassis(query.plateNo(), query.tinNo(), query.chassisNo())
                .map(o -> new LookupOrientationResponse(true, o))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")));
    }





}