package bi.gov.otraco.ct.orientation.query.api.handler;
import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import reactor.core.publisher.Flux;

public interface OrientationQueryHandler {

    Flux<OrientationResponse> findAll();
    Flux<OrientationResponse> findAllToday();
    Flux<OrientationResponse> findAllBybranchCode(FindByCode query) ;
    Flux<OrientationResponse> findAllBybranchCodeToday(FindByCode query) ;


  }