package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.core.model.Nature;
import bi.gov.otraco.ct.orientation.query.api.response.NatureQRResponse;
import bi.gov.otraco.ct.orientation.query.api.response.NatureResponse;
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
                .map(this::toNatureDisplay)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<NatureResponse> findByChassisNo(String chassis) {
        return repository.findByChassisNo(chassis).map(this::toNatureDisplay);
    }

    @Override
    public Mono<NatureResponse> findByPlateNo(String plateNo) {
        return repository.findByPlateNo(plateNo).map(this::toNatureDisplay);
    }

    @Override
    public Mono<NatureResponse> findByPlateAndTinAndChassis(String plateNo, String tin, String chassis) {
        return repository.findByPlateNoAndOwnerTinNoAndChassisNo(plateNo,tin,chassis).map(this::toNatureDisplay);
    }


    private NatureResponse toNatureDisplay(Nature o) {
        return new NatureResponse(
                o.getNatureId(),
                o.getCode(),
                o.getReceiptNo(),
                o.getPlateNo(),
                o.getChassisNo(),
                o.getOwnerTinNo(),
                o.getOwnerName(),
                o.getVehicleBreak(),
                o.getCompressibility(),
                o.getDirection(),
                o.getDocument(),
                o.getEngine(),
                o.getLighting(),
                o.getLoad(),
                o.getNumberSeat(),
                o.getParePrise(),
                o.getRocket(),
                o.getShockAbsorber(),
                o.getSpeed(),
                o.getSuspension(),
                o.getTransmission(),
                o.getWheels(),
                o.getWheelsType(),
                o.getBridge(),
                o.getBranchCode(),
                o.getBranchName(),
                o.getLogCreatedAt(),
                o.getValidStatus(),
                o.getUserCode(),
                o.getUserName(),
                o.getReceiptNo()
        );
    }

    private NatureQRResponse toNatureQRDisplay(Nature n) {
        return new NatureQRResponse(
                n.getPlateNo(),
                n.getChassisNo(),
                n.getOwnerName(),
                n.getDocument(),  // équivalent à vehicleType dans l'exemple
                n.getOwnerTinNo(),
                n.getReceiptNo(),
                n.getDocument(),  // répété comme dans l'exemple
                n.getOwnerName(), // ou un autre champ pour ownerCategory
                n.getLogCreatedAt(),
                n.getReceiptNo(), // ou un autre champ pour invoiceNumber
                n.getReceiptNo(), // ou un autre champ pour paymentNo
                n.getValidStatus() // ou un autre champ pour paymentStatus
        );
    }



}
