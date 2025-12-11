package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.query.api.response.CommonResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CommonQueryHandler {
    //
    Mono<List<CommonResponse>> getComboType();
}