package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationStatusCommand;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@Component
public interface OrientationEventHandler {
    Mono<ResponseEntity<?>> create(OrientationCreatedCommand command);
    Mono<ResponseEntity<Orientation>> disable(OrientationStatusCommand command);
    Mono<ResponseEntity<Orientation>> enable(OrientationStatusCommand command);
}