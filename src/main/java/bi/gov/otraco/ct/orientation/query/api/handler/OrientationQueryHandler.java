package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.response.PaymentDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.GlobalDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.DailyDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.AgencyGlobalDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.AgencyDailyDisplayResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrientationQueryHandler {
    Flux<OrientationResponse> findAll();
    Mono<OrientationResponse> findByCode(String orientationCode);
    Flux<Orientation> findAllOrientations();
    Mono<Orientation> findOrientationByCode(String orientationCode);
    
    PaymentDisplayResponse toPaymentDisplay(Orientation orientation);
    GlobalDisplayResponse toGlobalDisplay(Orientation orientation);
    DailyDisplayResponse toDailyDisplay(Orientation orientation);
    AgencyGlobalDisplayResponse toAgencyGlobalDisplay(Orientation orientation);
    AgencyDailyDisplayResponse toAgencyDailyDisplay(Orientation orientation);
}