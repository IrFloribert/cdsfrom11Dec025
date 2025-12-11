package bi.gov.otraco.ct.orientation.query.api.controller;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindNatureByCode;
import bi.gov.otraco.ct.orientation.cmd.api.command.FindNatureByPlateNo;
import bi.gov.otraco.ct.orientation.query.api.dto.AllNatureResponse;
import bi.gov.otraco.ct.orientation.query.api.dto.LookupNatureResponse;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/otraco/certificate/nature/lookup")
@Tag(name = "Nature")
@RequiredArgsConstructor
public class NatureLookupController {
    private final NatureQueryHandler queryHandler;

    @Operation(summary = "Get all natures")
    @GetMapping(path = "get-natures", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllNatureResponse> getNatures() {
        return queryHandler.findAll()
            .collectList()
            .map(list -> new AllNatureResponse(true, list));
    }

    @Operation(summary = "Get nature by chassis number")
    @PutMapping(path = "get-nature-by-chassis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupNatureResponse> getByChassisNo(@Valid @RequestBody FindNatureByCode query) {
        return queryHandler.findByChassisNo(query.chassisNo())
            .map(o -> new LookupNatureResponse(true, o))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Nature not found")));
    }

    @Operation(summary = "Get nature by plate number")
    @PutMapping(path = "get-nature-by-plate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupNatureResponse> getByPlateNo(@Valid @RequestBody FindNatureByPlateNo query) {
        return queryHandler.findByPlateNo(query.plateNo())
            .map(o -> new LookupNatureResponse(true, o))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Nature not found")));
    }
}
