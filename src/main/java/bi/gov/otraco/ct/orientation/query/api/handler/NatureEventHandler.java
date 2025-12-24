package bi.gov.otraco.ct.orientation.query.api.handler;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface  NatureEventHandler {

    Mono<ResponseEntity<MessageResponse>> create1(NatureCreatedCommand command);
}