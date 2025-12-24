package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.core.common.LogCreated;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.feign.validator.command.ValidationCommand;
import bi.gov.otraco.ct.orientation.core.model.Nature;
import bi.gov.otraco.ct.orientation.core.payload.NaturesPayload;
import bi.gov.otraco.ct.orientation.core.utils.MapUtils;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class NatureEventHandlerImpl implements NatureEventHandler {
    private final NatureRepository naturerepository;
    private final NaturesPayload naturePayload;

    @Override
    public Mono<ResponseEntity<MessageResponse>> create1(NatureCreatedCommand command) {
        return naturePayload.getNatureCode().flatMap(code -> {
            // Vérification de l'unicité avant la création
            return naturerepository.existsByChassisNoOrPlateNo(command.chassisNo(), command.plateNo())
                    .flatMap(exists -> {
                        if (exists) {
                            return Mono.just(ResponseEntity
                                    .status(HttpStatus.CONFLICT)
                                    .body(new MessageResponse(false,
                                            MapUtils.failed + ":Un véhicule avec ce numéro de châssis ou d'immatriculation existe déjà"
                                    )));
                        }


                        // Vérification des champs
                        String[] fields = {
                                command.vehicleBreak(), command.compressibility(), command.direction(),
                                command.document(), command.engine(), command.lighting(),
                                command.load(), command.numberSeat(), command.parePrise(),
                                command.rocket(), command.shockAbsorber(), command.speed(),
                                command.suspension(), command.transmission(), command.wheels(),
                                command.wheelsType(), command.bridge()
                        };

                        boolean allFieldsValid = Arrays.stream(fields)
                                .allMatch("BON"::equalsIgnoreCase);

                        ValidationCommand validationCommand = new ValidationCommand(
                                command.receiptNo(),
                                command.ownerTinNo(),
                                command.plateNo(),
                                command.chassisNo(),
                                command.userCode()
                        );

                        // Construction de l'objet Nature
                        Nature nature = Nature.builder()
                                .natureId(UUID.randomUUID().toString())
                                .code(code)
                                .receiptNo(command.receiptNo())
                                .plateNo(command.plateNo().toUpperCase())
                                .chassisNo(command.chassisNo().toUpperCase())
                                .ownerTinNo(command.ownerTinNo())
                                .ownerName(command.ownerName())
                                .vehicleBreak(command.vehicleBreak())
                                .compressibility(command.compressibility())
                                .direction(command.direction())
                                .document(command.document())
                                .engine(command.engine())
                                .lighting(command.lighting())
                                .load(command.load())
                                .numberSeat(command.numberSeat())
                                .parePrise(command.parePrise())
                                .rocket(command.rocket())
                                .shockAbsorber(command.shockAbsorber())
                                .speed(command.speed())
                                .suspension(command.suspension())
                                .transmission(command.transmission())
                                .wheels(command.wheels())
                                .wheelsType(command.wheelsType())
                                .bridge(command.bridge())
                                .userCode(command.userCode())
                                .userName(command.userName())
                                .branchCode(command.branchCode())
                                .branchName(command.branchName())
                                .logCreatedAt(LogCreated.At())
                                .validStatus(allFieldsValid ? "01" : "00")
                                .build();

                        return naturePayload.validNature(validationCommand)
                                .flatMap(validationResponse ->
                                        naturerepository.save(nature)
                                                .thenReturn(ResponseEntity
                                                        .status(HttpStatus.CREATED)
                                                        .body(validationResponse))
                                )
                                .onErrorResume(e -> {
                                    log.error("Erreur lors de la validation ou de la sauvegarde : {}", e.getMessage(), e);
                                    return Mono.just(ResponseEntity
                                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                            .body(new MessageResponse(false, "Une erreur est survenue lors du traitement de votre demande"
                                            )));
                                });
                    });
        }).onErrorResume(ex -> {
            log.error("Erreur lors de la création : {}", ex.getMessage(), ex);
            return Mono.just(ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(false, "Une erreur est survenue lors du traitement de votre demande"
                    )));
        });
    }
}