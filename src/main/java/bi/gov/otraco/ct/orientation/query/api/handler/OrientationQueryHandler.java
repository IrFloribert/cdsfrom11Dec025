package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrientationQueryHandler {
    Flux<OrientationResponse> findAll();

    Flux<OrientationResponse> findAllToday();

    Flux<OrientationResponse> findAllBybranchCode(FindByCode query);

    Mono<OrientationResponse> findByOrientationCode(String code);



    Mono<OrientationResponse> findByOrientationId(String id);

    Flux<OrientationResponse> findAllBybranchCodeToday(FindByCode query);

    Mono<OrientationResponse> findByPlateAndTinAndChassis(String plateNo, String tin, String chassis);
}