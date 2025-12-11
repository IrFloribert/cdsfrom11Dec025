package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.core.model.Nature;
import bi.gov.otraco.ct.orientation.query.api.dto.NatureResponse;
import bi.gov.otraco.ct.orientation.query.api.handler.NatureQueryHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NatureQueryHandlerImpl implements NatureQueryHandler {

    private final NatureRepository repository;

    @Override
    public Flux<NatureResponse> findAll() {
        return repository.findAll()
                .map(this::toResponse)
                .switchIfEmpty(Flux.empty());
    }


    @Override
    public Mono<NatureResponse> findByChassisNo(String chassis) {
        return repository.findByChassisNo(chassis).map(this::toResponse);
    }
    @Override
    public Mono<NatureResponse> findByPlateNo(String chassis) {
        return repository.findByChassisNo(chassis).map(this::toResponse);
    }




    private NatureResponse toResponse(Nature n) {
        return NatureResponse.builder()
                .id(n.getNatureId())
                .receiptNo(n.getReceiptNo())
                .plateNo(n.getPlateNo())
                .chassisNo(n.getChassisNo())
                .ownerTinNo(n.getOwnerTinNo())
                .ownerName(n.getOwnerName())
                .vehicleBreak(n.getVehicleBreak())
                .compressibility(n.getCompressibility())
                .direction(n.getDirection())
                .document(n.getDocument())
                .engine(n.getEngine())
                .lighting(n.getLighting())
                .load(n.getLoad())
                .numberSeat(n.getNumberSeat())
                .parePrise(n.getParePrise())
                .rocket(n.getRocket())
                .shockAbsorber(n.getShockAbsorber())
                .speed(n.getSpeed())
                .suspension(n.getSuspension())
                .transmission(n.getTransmission())
                .wheels(n.getWheels())
                .wheelsType(n.getWheelsType())
                .bridge(n.getBridge())
                .branchCode(n.getBranchCode())
                .branchName(n.getBranchName())
                .logCreated(n.getLogCreated())
                .validStatus(n.getValidStatus())
                .build();
    }
}
