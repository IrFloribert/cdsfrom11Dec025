package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationStatusCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedCommand;
import bi.gov.otraco.ct.orientation.core.common.LogCreated;
import bi.gov.otraco.ct.orientation.core.common.OrientationStatus;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationEventHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;
import java.util.UUID;
@Component
@Service
@RequiredArgsConstructor
public class OrientationEventHandlerImpl implements OrientationEventHandler {
    private final OrientationRepository orientationRepository;
    private final OrientationPayload orientationPayload;

    @Override
    public Mono<ResponseEntity<Orientation>> create(OrientationCreatedCommand command) {
        return orientationPayload.getOrientationCode().flatMap(code -> {
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
                .orientationLineName(command.orientationLineName())
                .orientationStatus(command.orientationStatus())
                .branchCode(command.branchCode())
                .branchName(command.branchName())
                .userCode(command.userCode())
                .userName(command.userName())
                .logCreated(LogCreated.At())
                .vehicleType(command.vehicleType())
                .build();
            return orientationRepository.save(o).map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
          .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @Override
    public Mono<ResponseEntity<Orientation>> update(OrientationUpdatedCommand command) {
        return orientationRepository.findByOrientationCode(command.orientationCode()).flatMap(o -> {
            o.setReceiptNo(command.receiptNo());
            o.setPaymentNo(command.paymentNo());
            o.setChassisNo(command.chassisNo());
            o.setPlateNo(command.plateNo());
            o.setOwnerName(command.ownerName());
            o.setTinNo(command.tinNo());
            o.setOrientationLineCode(command.orientationLineCode());
            o.setOrientationLineName(command.orientationLineName());
            o.setOrientationStatus(command.orientationStatus());
            o.setBranchCode(command.branchCode());
            o.setBranchName(command.branchName());
            o.setUserCode(command.userCode());
            o.setUserName(command.userName());
            o.setLogCreated(LogCreated.At());
            return orientationRepository.save(o).map(saved -> ResponseEntity.ok().body(saved));
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
          .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }


    @Override
    public Mono<ResponseEntity<Orientation>> disable(OrientationStatusCommand command) {
        return orientationRepository
            .findByOrientationCode(command.code())
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientation not found")))
            .flatMap(orientation -> {
                orientation.setOrientationStatus(OrientationStatus.disable());
                return orientationRepository.save(orientation);})
                
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