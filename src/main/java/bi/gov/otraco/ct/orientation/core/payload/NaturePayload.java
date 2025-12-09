package bi.gov.otraco.ct.orientation.core.payload;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureUpdateCommand;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NaturePayload {
    private final NatureRepository repository;

    public Mono<Void> createException(NatureCreatedCommand cmd) {
        return repository.existsByChassisNo(cmd.getChassisNo())
            .flatMap(exists -> {
                if (exists) {
                    return Mono.error(new IllegalArgumentException("Nature with chassis number " + cmd.getChassisNo() + " already exists"));
                }
                return repository.existsByPlateNo(cmd.getPlateNo())
                    .flatMap(plateExists -> {
                        if (plateExists) {
                            return Mono.error(new IllegalArgumentException("Nature with plate number " + cmd.getPlateNo() + " already exists"));
                        }
                        return Mono.empty();
                    });
            });
    }

    public Mono<Void> updateException(NatureUpdateCommand cmd) {
        return repository.findByCode(cmd.code())
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Nature with code " + cmd.code() + " not found")))
            .then(Mono.empty());
    }
}
