package bi.gov.otraco.ct.orientation.query.api.handler;


import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureUpdateCommand;
import bi.gov.otraco.ct.orientation.core.model.Nature;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface  NatureEventHandler {
    Mono<Void> create(NatureCreatedCommand command);
    Mono<ResponseEntity<Nature>> update(NatureUpdateCommand command);
}