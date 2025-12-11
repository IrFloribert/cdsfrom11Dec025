package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.cmd.api.command.NatureUpdateCommand;
import bi.gov.otraco.ct.orientation.core.model.Nature;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureEventHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class NatureEventHandlerImpl implements NatureEventHandler {
    private final NatureRepository naturerepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Mono<Void> create(NatureCreatedCommand cmd) {
        Nature nature = Nature.builder()
                .receiptNo(cmd.getReceiptNo())
                .plateNo(cmd.getPlateNo())
                .chassisNo(cmd.getChassisNo())
                .ownerTinNo(cmd.getOwnerTinNo())
                .ownerName(cmd.getOwnerName())
                .vehicleBreak(cmd.getVehicleBreak())
                .compressibility(cmd.getCompressibility())
                .direction(cmd.getDirection())
                .document(cmd.getDocument())
                .engine(cmd.getEngine())
                .lighting(cmd.getLighting())
                .load(cmd.getLoad())
                .numberSeat(cmd.getNumberSeat())
                .parePrise(cmd.getParePrise())
                .rocket(cmd.getRocket())
                .shockAbsorber(cmd.getShockAbsorber())
                .speed(cmd.getSpeed())
                .suspension(cmd.getSuspension())
                .transmission(cmd.getTransmission())
                .wheels(cmd.getWheels())
                .wheelsType(cmd.getWheelsType())
                .bridge(cmd.getBridge())
                .branchCode(cmd.getBranchCode())
                .branchName(cmd.getBranchName())
                .logCreated(cmd.getLogCreated() != null ? cmd.getLogCreated() : LocalDateTime.now().format(formatter))
                .validStatus(cmd.getValidStatus() != null ? cmd.getValidStatus() : "ACTIVE")
                .build();

        return naturerepository.save(nature).then();
    }



    @Override
    public Mono<ResponseEntity<Nature>> update(NatureUpdateCommand command) {
        return naturerepository.findByCode(command.code()).flatMap(nature -> {
            nature.setReceiptNo(command.receiptNo());
            nature.setPlateNo(command.plateNo());
            nature.setChassisNo(command.chassisNo());
            nature.setOwnerTinNo(command.ownerTinNo());
            nature.setOwnerName(command.ownerName());
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
            nature.setBranchCode(command.branchCode());
            nature.setBranchName(command.branchName());
            nature.setValidStatus(command.validStatus());
            return naturerepository.save(nature).map(saved -> ResponseEntity.ok().body(saved));
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build())).onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}