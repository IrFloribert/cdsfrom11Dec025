package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.core.common.LogCreated;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationQueryHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationQRResponse;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class OrientationQueryHandlerImpl implements OrientationQueryHandler {
    private final OrientationRepository orientationRepository;


    @Override
    public Flux<OrientationResponse> findAll() {
        return orientationRepository.findAll().map(this::toOrientationDisplay);
    }

    @Override
    public Flux<OrientationResponse> findAllToday() {
        String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        return orientationRepository.findByLogCreatedAt(today).map(this::toOrientationDisplay);
    }

    @Override
    public Flux<OrientationResponse> findAllBybranchCode(FindByCode query) {
        return orientationRepository.findAllBybranchCode(query.code()).map(this::toOrientationDisplay);
    }


    @Override
    public Mono<OrientationResponse> findByOrientationCode(String code) {
        return orientationRepository.findByOrientationCode(code).flatMap(category -> Mono.just(toOrientationDisplay(category)));
    }

    @Override
    public Mono<OrientationQRResponse> findOrientationByQR(String code) {
        return orientationRepository.findByOrientationCode(code).flatMap(category -> Mono.just(toOrientationQRDisplay(category)));
    }


    @Override
    public Mono<OrientationResponse> findByOrientationId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("L'ID de l'orientation ne peut pas être vide"));
        }
        return orientationRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("Aucune orientation trouvée avec l'ID: " + id))).map(this::toOrientationDisplay).onErrorResume(e -> {
            // Log l'erreur si nécessaire
            // log.error("Erreur lors de la recherche de l'orientation avec l'ID: {}", id, e);
            return Mono.error(new RuntimeException("Erreur lors de la récupération de l'orientation", e));
        });
    }


    @Override
    public Flux<OrientationResponse> findAllBybranchCodeToday(FindByCode query) {
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        return orientationRepository.findByBranchCode(today, query.code())
                .map(this::toOrientationDisplay);
    }


    private OrientationResponse toOrientationDisplay(Orientation o) {
        return new OrientationResponse(o.getOrientationId(), o.getOrientationCode(), o.getReceiptNo(), o.getPaymentNo(), o.getChassisNo(),
                o.getPlateNo(), o.getOwnerName(), o.getTinNo(), o.getOrientationLineCode(), o.getOrientationLineName(), o.getBranchCode(),
                o.getBranchName(), o.getUserCode(), o.getUserName(), o.getLogCreatedAt(), o.getVehicleType(), o.getOwnerCategory(),
                o.getInvoiceNumber(), o.getPaymentStatus(),o.getOrientationCode()


        );
    }



    private OrientationQRResponse toOrientationQRDisplay(Orientation o) {
        return new OrientationQRResponse(
                o.getPlateNo(),
                o.getChassisNo(),
                o.getOwnerName(),
                o.getVehicleType(),
                o.getTinNo(),
                o.getReceiptNo(),
                o.getVehicleType(),
                o.getOwnerCategory(),
                o.getLogCreatedAt(),
                o.getInvoiceNumber(),
                o.getPaymentNo(),
                o.getPaymentStatus()
        );
    }
}
