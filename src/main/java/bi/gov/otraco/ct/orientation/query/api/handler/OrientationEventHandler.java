package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedCommand;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface OrientationEventHandler {
    Mono<ResponseEntity<Orientation>> create(OrientationCreatedCommand command);
    Mono<ResponseEntity<Orientation>> update(OrientationUpdatedCommand command);
}