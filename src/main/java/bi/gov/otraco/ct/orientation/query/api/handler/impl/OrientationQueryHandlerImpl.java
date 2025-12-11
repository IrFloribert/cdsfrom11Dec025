package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.FindByCode;
import bi.gov.otraco.ct.orientation.core.model.Orientation;
import bi.gov.otraco.ct.orientation.query.api.handler.OrientationQueryHandler;
import bi.gov.otraco.ct.orientation.query.api.repository.OrientationRepository;
import bi.gov.otraco.ct.orientation.query.api.response.OrientationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class OrientationQueryHandlerImpl implements OrientationQueryHandler {
    private final OrientationRepository repository;


     @Override
    public Flux<OrientationResponse> findAll() {
        return repository.findAll()
            .map(this::toAgencyDailyDisplay);
    }

    @Override
    public Flux<OrientationResponse> findAllToday() {
        String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        return repository.findAllToday(today)
        .map(this::toAgencyDailyDisplay);
    }
    @Override
    public Flux<OrientationResponse> findAllBybranchCode(FindByCode query) {
        return repository.findAllBybranchCode(query.code())
            .map(this::toAgencyDailyDisplay);
    }


   @Override
public Flux<OrientationResponse> findAllBybranchCodeToday(FindByCode query) {
    String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    return repository.findAllTodayBybranchCode(today, query.code())
        .map(this::toAgencyDailyDisplay);
}

  

    

    private OrientationResponse toAgencyDailyDisplay(Orientation o) {
        return new OrientationResponse(

            o.getOrientationCode(),
            o.getPlateNo(),
            o.getChassisNo(),
            o.getOwnerName(),
     
        
            o.getTinNo(),
            o.getReceiptNo(),
            o.getVehicleType(),
            o.getLogCreated(),
            o.getBranchName(),
        
            o.getOrientationNumber(),
            o.getPaymentNo(),
            o.getOrientationLineName(),
            o.getOrientationStatus()
        

            
        );
    }
}
