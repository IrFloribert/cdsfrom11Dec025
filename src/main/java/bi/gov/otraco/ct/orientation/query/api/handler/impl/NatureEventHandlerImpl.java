package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureVerifyCommand;
import bi.gov.otraco.ct.orientation.core.common.LogCreated;
import bi.gov.otraco.ct.orientation.core.model.Nature;
import bi.gov.otraco.ct.orientation.core.payload.NaturePayload;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NatureEventHandlerImpl implements NatureEventHandler {
    private final NatureRepository naturerepository;
    private final NaturePayload naturePayload;

    @Override
    public Mono<Void> create(NatureCreatedCommand cmd) {
        Nature nature = Nature.builder()
                .natureId(UUID.randomUUID().toString())
                .receiptNo(cmd.receiptNo())
                .plateNo(cmd.plateNo())
                .chassisNo(cmd.chassisNo())
                .ownerTinNo(cmd.ownerTinNo())
                .ownerName(cmd.ownerName())

                .vehicleBreak("")
                .compressibility("")
                .direction("")
                .document("")
                .engine("")
                .lighting("")
                .load("")
                .numberSeat("")
                .parePrise("")
                .rocket("")
                .shockAbsorber("")
                .speed("")
                .suspension("")
                .transmission("")
                .wheels("")
                .wheelsType("")
                .bridge("")

                .branchCode(cmd.branchCode())
                .branchName(cmd.branchName())
                .logCreatedAt(LogCreated.At())
                .validStatus(cmd.validStatus() != null ? cmd.validStatus() : "01")
                .build();

        return naturerepository.save(nature).then();
    }

    @Override
    public Mono<ResponseEntity<Nature>> verifyState(NatureVerifyCommand command) {
        return naturerepository.findByReceiptNo(command.receiptNo()).flatMap(nature -> {

            nature.setVehicleBreak(command.vehicleBreak());
            nature.setCompressibility(command.compressibility());
            nature.setDirection(command.direction());
            nature.setDocument(command.document());
            nature.setEngine(command.engine());
            nature.setLighting(command.lighting());
            nature.setLoad(command.load());
            nature.setNumberSeat(command.numberSeat());
            nature.setParePrise(command.parePrise());
            nature.setRocket(command.rocket());
            nature.setShockAbsorber(command.shockAbsorber());
            nature.setSpeed(command.speed());
            nature.setSuspension(command.suspension());
            nature.setTransmission(command.transmission());
            nature.setWheels(command.wheels());
            nature.setWheelsType(command.wheelsType());
            nature.setBridge(command.bridge());
            return naturerepository.save(nature).map(saved -> ResponseEntity.ok().body(saved));
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build())).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}