package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface  NatureEventHandler {
    Mono<ResponseEntity<?>> create(NatureCreatedCommand command);

}