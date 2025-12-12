package bi.gov.otraco.ct.orientation.core.payload;

import org.springframework.stereotype.Service;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationStatusCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedComboCommand;
import reactor.core.publisher.Mono;
@Service

public interface OrientationPayload {
    Mono<Void> createException(OrientationCreatedCommand command);

    Mono<String> getOrientationCode();


    Mono<Void> statusException(OrientationUpdatedComboCommand command);
}