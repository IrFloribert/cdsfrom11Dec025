package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.OrientationUpdatedCommand;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.core.payload.OrientationPayload;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationEventHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrientationEventHandlerImpl implements OrientationEventHandler {
    private final OrientationRepository repository;
    private final OrientationPayload payload;

    @Override
    public Mono<ResponseEntity<Orientation>> create(OrientationCreatedCommand c) {
        return payload.getOrientationCode().flatMap(code -> {
            Orientation o = Orientation.builder()
                .orientationId(UUID.randomUUID().toString())
                .orientationCode(code)
                .receiptNo(c.receiptNo())
                .paymentNo(c.paymentNo())
                .chassisNo(c.chassisNo())
                .plateNo(c.plateNo())
                .ownerName(c.ownerName())
                .tinNo(c.tinNo())
                .orientationLineCode(c.orientationLineCode())
                .orientationLineName(c.orientationLineName())
                .orientationStatus(c.orientationStatus())
                .agencyCode(c.agencyCode())
                .agencyName(c.agencyName())
                .userCode(c.userCode())
                .userName(c.userName())
                .logCreated(c.logCreated())
                .build();
            return repository.save(o).map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
          .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @Override
    public Mono<ResponseEntity<Orientation>> update(OrientationUpdatedCommand c) {
        return repository.findByOrientationCode(c.orientationCode()).flatMap(o -> {
            o.setReceiptNo(c.receiptNo());
            o.setPaymentNo(c.paymentNo());
            o.setChassisNo(c.chassisNo());
            o.setPlateNo(c.plateNo());
            o.setOwnerName(c.ownerName());
            o.setTinNo(c.tinNo());
            o.setOrientationLineCode(c.orientationLineCode());
            o.setOrientationLineName(c.orientationLineName());
            o.setOrientationStatus(c.orientationStatus());
            o.setAgencyCode(c.agencyCode());
            o.setAgencyName(c.agencyName());
            o.setUserCode(c.userCode());
            o.setUserName(c.userName());
            o.setLogCreated(c.logCreated());
            return repository.save(o).map(saved -> ResponseEntity.ok().body(saved));
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
          .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}