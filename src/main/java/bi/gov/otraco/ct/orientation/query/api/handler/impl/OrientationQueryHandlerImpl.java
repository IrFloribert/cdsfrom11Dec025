package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationQueryHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import bi.gov.otraco.ct.orientation.query.api.response.PaymentDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.GlobalDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.DailyDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.AgencyGlobalDisplayResponse;
import bi.gov.otraco.ct.orientation.query.api.response.AgencyDailyDisplayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrientationQueryHandlerImpl implements OrientationQueryHandler {
    private final OrientationRepository repository;

    @Override
    public Flux<OrientationResponse> findAll() {
        return repository.findAll().map(this::toResponse);
    }

    @Override
    public Mono<OrientationResponse> findByCode(String orientationCode) {
        return repository.findByOrientationCode(orientationCode).map(this::toResponse);
    }

    @Override
    public Flux<Orientation> findAllOrientations() {
        return repository.findAll();
    }

    @Override
    public Mono<Orientation> findOrientationByCode(String orientationCode) {
        return repository.findByOrientationCode(orientationCode);
    }

    private OrientationResponse toResponse(Orientation o) {
        return new OrientationResponse(
            o.getOrientationCode(),
            o.getReceiptNo(),
            o.getPaymentNo(),
            o.getChassisNo(),
            o.getPlateNo(),
            o.getOwnerName(),
            o.getTinNo(),
            o.getOrientationLineCode(),
            o.getOrientationLineName(),
            o.getOrientationStatus(),
            o.getAgencyCode(),
            o.getAgencyName(),
            o.getUserCode(),
            o.getUserName(),
            o.getLogCreated()
        );
    }

    // Payment Display Mapper
    public PaymentDisplayResponse toPaymentDisplay(Orientation o) {
        return new PaymentDisplayResponse(
            o.getPlateNo(),
            o.getChassisNo(),
            o.getOwnerName(),
            o.getOrientationLineName(),
            o.getTinNo(),
            o.getReceiptNo(),
            o.getVehicleType(),
            o.getOwnerCategory(),
            o.getLogCreated(),
            o.getInvoiceNumber(),
            o.getPaymentNo(),
            o.getPaymentStatus()
        );
    }

    // Global Display Mapper
    public GlobalDisplayResponse toGlobalDisplay(Orientation o) {
        return new GlobalDisplayResponse(
            o.getPlateNo(),
            o.getChassisNo(),
            o.getOwnerName(),
            o.getOrientationLineName(),
            o.getTinNo(),
            o.getReceiptNo(),
            o.getVehicleType(),
            o.getAgencyName(),
            o.getLogCreated(),
            o.getOrientationCode(),
            o.getPaymentNo(),
            o.getOrientationLineName()
        );
    }

    // Daily Display Mapper
    public DailyDisplayResponse toDailyDisplay(Orientation o) {
        return new DailyDisplayResponse(
            o.getPlateNo(),
            o.getChassisNo(),
            o.getOwnerName(),
            o.getOrientationLineName(),
            o.getTinNo(),
            o.getReceiptNo(),
            o.getVehicleType(),
            o.getAgencyName(),
            o.getLogCreated(),
            o.getOrientationCode(),
            o.getPaymentNo(),
            o.getOrientationLineName()
        );
    }

    // Agency Global Display Mapper
    public AgencyGlobalDisplayResponse toAgencyGlobalDisplay(Orientation o) {
        return new AgencyGlobalDisplayResponse(
            o.getPlateNo(),
            o.getChassisNo(),
            o.getOwnerName(),
            o.getOrientationLineName(),
            o.getTinNo(),
            o.getReceiptNo(),
            o.getVehicleType(),
            o.getAgencyName(),
            o.getLogCreated(),
            o.getOrientationCode(),
            o.getPaymentNo(),
            o.getOrientationLineName()
        );
    }

    // Agency Daily Display Mapper
    public AgencyDailyDisplayResponse toAgencyDailyDisplay(Orientation o) {
        return new AgencyDailyDisplayResponse(
            o.getPlateNo(),
            o.getChassisNo(),
            o.getOwnerName(),
            o.getOrientationLineName(),
            o.getTinNo(),
            o.getReceiptNo(),
            o.getVehicleType(),
            o.getAgencyName(),
            o.getLogCreated(),
            o.getOrientationCode(),
            o.getPaymentNo(),
            o.getOrientationLineName()
        );
    }
}