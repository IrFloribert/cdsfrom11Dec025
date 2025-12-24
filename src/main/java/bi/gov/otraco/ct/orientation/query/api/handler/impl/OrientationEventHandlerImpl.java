package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationStatusCommand;
import bi.gov.otraco.ct.orientation.core.common.LogCreated;
import bi.gov.otraco.ct.orientation.core.common.OrientationStatus;
import bi.gov.otraco.ct.orientation.core.feign.validator.command.ValidationCommand;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.core.payload.NaturesPayload;
import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationEventHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Service
@Slf4j
@RequiredArgsConstructor
public class OrientationEventHandlerImpl implements OrientationEventHandler {
    private final OrientationRepository orientationRepository;
    private final OrientationPayload orientationPayload;
    private final NaturesPayload naturesPayload;

    @Override
    public Mono<ResponseEntity<?>> create(OrientationCreatedCommand command) {
        return orientationPayload.getOrientationCode().flatMap(code -> {
                    // Déterminer le nom de la ligne en fonction du natureCode
                    String lineName = "";
                    if ("OL001".equals(command.orientationLineCode())) {
                        lineName = "LMV";
                    } else if ("OL002".equals(command.orientationLineCode())) {
                        lineName = "COMBO";
                    } else if ("OL003".equals(command.orientationLineCode())) {
                        lineName = "MOTO";
                    }
                    ValidationCommand validationCommand=  new ValidationCommand(
                            command.receiptNo(), command.tinNo(), command.plateNo(),command.chassisNo(),command.userCode());
                    Orientation o = Orientation.builder()
                            .orientationId(UUID.randomUUID().toString())
                            .orientationCode(code)
                            .receiptNo(command.receiptNo())
                            .paymentNo(command.paymentNo())
                            .chassisNo(command.chassisNo())
                            .plateNo(command.plateNo())
                            .ownerName(command.ownerName())
                            .tinNo(command.tinNo())
                            .orientationLineCode(command.orientationLineCode())
                            .orientationLineName(lineName)
                            .orientationStatus(OrientationStatus.enable())
                            .branchCode(command.branchCode())
                            .branchName(command.branchName())
                            .userCode(command.userCode())
                            .userName(command.userName())
                            .logCreatedAt(LogCreated.At())
                            .ownerCategory(command.ownerCategory())
                            .invoiceNumber(command.invoiceNumber())
                            .paymentStatus("PS001")
                            .vehicleType(command.vehicleType())
                            .build();

                    return orientationRepository.save(o).then(naturesPayload.validOrientation(validationCommand))
                            .map(co -> ResponseEntity.status(HttpStatus.CREATED).body(co))
                            .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
                });
                
    }
//
//    @Override
//    public Mono<ResponseEntity<Orientation>> updateTowardsCombo(OrientationUpdatedComboCommand command) {
//        return orientationRepository.findByOrientationCode(command.qr()).flatMap(o -> {
//
//                    o.setOrientationLineCode(command.lineCode());
//                    if (Objects.equals(command.lineCode(), "OL001"))
//                        o.setOrientationLineName("LMV");
//                    if (Objects.equals(command.lineCode(), "OL002"))
//                        o.setOrientationLineName("COMBO");
//                    if (Objects.equals(command.lineCode(), "OL003"))
//                        o.setOrientationLineName("MOTO");
//                    return orientationRepository.save(o).map(saved -> ResponseEntity.ok().body(saved));
//                }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
//                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
//    }


    @Override
    public Mono<ResponseEntity<Orientation>> disable(OrientationStatusCommand command) {
        return orientationRepository
                .findByOrientationCode(command.code())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")))
                .flatMap(orientation -> {
                    orientation.setOrientationStatus(OrientationStatus.disable());
                    return orientationRepository.save(orientation);
                })

                .map(saved -> ResponseEntity.ok(saved))
                .onErrorResume(ResponseStatusException.class,
                        ex -> Mono.just(ResponseEntity.status(ex.getStatusCode()).build()))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @Override
    public Mono<ResponseEntity<Orientation>> enable(OrientationStatusCommand command) {

        return orientationRepository
                .findByOrientationCode(command.code())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")))
                .flatMap(orientation -> {
                    orientation.setOrientationStatus(OrientationStatus.enable());
                    return orientationRepository.save(orientation);
                })
                .map(saved -> ResponseEntity.ok(saved))
                .onErrorResume(ResponseStatusException.class,
                        ex -> Mono.just(ResponseEntity.status(ex.getStatusCode()).build()))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }


}