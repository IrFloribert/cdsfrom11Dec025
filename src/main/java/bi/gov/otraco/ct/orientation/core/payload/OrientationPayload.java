package bi.gov.otraco.ct.orientation.core.payload;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedCommand;
import reactor.core.publisher.Mono;

public interface OrientationPayload {
    Mono<Void> createException(OrientationCreatedCommand command);
    Mono<Void> updateException(OrientationUpdatedCommand command);
    Mono<String> getOrientationCode();
}