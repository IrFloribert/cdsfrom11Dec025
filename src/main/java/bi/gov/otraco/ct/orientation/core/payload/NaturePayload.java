package bi.gov.otraco.ct.orientation.core.payload;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureUpdateCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureVerifyCommand;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NaturePayload {
    private final NatureRepository repository;

    public Mono<Void> createException(NatureCreatedCommand cmd) {
        return validateChassisNumber(cmd.chassisNo())
                .then(validatePlateNumber(cmd.plateNo()))
                .then(validateReceiptNumber(cmd.receiptNo()))
                .then();
    }

    private Mono<Void> validateChassisNumber(String chassisNo) {
        return repository.existsByChassisNo(chassisNo)
                .flatMap(exists -> exists
                        ? Mono.error(new IllegalArgumentException("Un véhicule avec le numéro de châssis " + chassisNo + " existe déjà"))
                        : Mono.empty()
                );
    }

    private Mono<Void> validatePlateNumber(String plateNo) {
        return repository.existsByPlateNo(plateNo)
                .flatMap(exists -> exists
                        ? Mono.error(new IllegalArgumentException("Un véhicule avec le numéro d'immatriculation " + plateNo + " existe déjà"))
                        : Mono.empty()
                );
    }

    private Mono<Void> validateReceiptNumber(String receiptNo) {
        return repository.existsByReceiptNo(receiptNo)
                .flatMap(exists -> exists
                        ? Mono.error(new IllegalArgumentException("Un reçu avec le numéro " + receiptNo + " existe déjà"))
                        : Mono.empty()
                );
    }

    public Mono<Void> updateException(NatureVerifyCommand cmd) {
        return repository.findByReceiptNo(cmd.receiptNo())
            .switchIfEmpty(Mono.error(new IllegalArgumentException("ReceiptNo with code " + cmd.receiptNo() + " not found")))
            .then(Mono.empty());
    }
}
