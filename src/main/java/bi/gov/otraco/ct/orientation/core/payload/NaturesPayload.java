package bi.gov.otraco.ct.orientation.core.payload;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureUpdateCommand;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.feign.validator.command.ValidationCommand;
import reactor.core.publisher.Mono;

public interface NaturesPayload  {
    Mono<Boolean> exist(NatureCreatedCommand command);

    Mono<Boolean> existUp(NatureUpdateCommand command);

    Mono<Void> createException(NatureCreatedCommand cmd);

    Mono<String> getNatureCode();

    Mono<MessageResponse> validNature(ValidationCommand command);

    Mono<?> validOrientation(ValidationCommand command);
}