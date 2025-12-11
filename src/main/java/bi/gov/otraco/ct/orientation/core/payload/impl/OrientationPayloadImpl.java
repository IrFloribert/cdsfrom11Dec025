package bi.gov.otraco.ct.orientation.core.payload.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationStatusCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedComboCommand;
import bi.gov.otraco.ct.orientation.core.common.OrientationModelCode;
import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Component
@Service
@RequiredArgsConstructor
public class OrientationPayloadImpl implements OrientationPayload {
    private final OrientationRepository repository;

    @Override
    public Mono<Void> createException(OrientationCreatedCommand command) {
        // Add uniqueness checks if needed (e.g., chassisNo, plateNo)
        return Mono.empty();
    }
    @Override
    public Mono<Void> updateComboException(OrientationUpdatedComboCommand command) {
        return repository.existsByOrientationCode(command.qr())
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new IllegalArgumentException("Orientation not found with qr: " + command.qr()));
                    }
                    if (!"OL001".equals(command.lineCode()) &&
                            !"OL002".equals(command.lineCode()) &&
                            !"OL003".equals(command.lineCode())) {
                        return Mono.error(new IllegalArgumentException(
                                "Code de ligne non valide. Les valeurs autorisées sont : OL001, OL002, OL003"
                        ));
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<String> getOrientationCode() {
        return repository.count().flatMap(count -> {
            if (count == 0) return Mono.just("OR30000000001");
            return repository.findByOrientationCodeDesc().take(1).single().map(last -> OrientationModelCode.generate(last.getOrientationCode()));
        });
    }


    @Override
    public Mono<Void> statusException(OrientationStatusCommand command) {
        return repository.existsByOrientationCode(command.code()).flatMap(exists -> {
            if (!exists) {
                return Mono.error(new IllegalArgumentException("Orientation not found with code: " + command.code()));
            }
            return Mono.empty();
        });
    }
}