package bi.gov.otraco.ct.orientation.query.api.handler;
import bi.gov.otraco.ct.orientation.query.api.response.NatureResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface NatureQueryHandler {
    Flux<NatureResponse> findAll();
    Mono<NatureResponse> findByChassisNo(String orientationCode);
    Mono<NatureResponse> findByPlateNo(String chassis);
}