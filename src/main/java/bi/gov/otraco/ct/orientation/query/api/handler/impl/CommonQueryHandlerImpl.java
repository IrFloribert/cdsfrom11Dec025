package bi.gov.otraco.ct.orientation.query.api.handler.impl;

import bi.gov.otraco.ct.orientation.core.common.VehicleCategory;
import bi.gov.otraco.ct.orientation.query.api.handler.CommonQueryHandler;
import bi.gov.otraco.ct.orientation.query.api.response.CommonResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonQueryHandlerImpl implements CommonQueryHandler {

    //
    @Override
    public Mono<List<CommonResponse>> getComboType() {
        List<CommonResponse> commonResponses = new ArrayList<>();
        commonResponses.add(new CommonResponse(VehicleCategory.COMBO_LMV.getCode(), VehicleCategory.COMBO_LMV.getName()));
        commonResponses.add(new CommonResponse(VehicleCategory.COMBO_CC.getCode(), VehicleCategory.COMBO_CC.getName()));
        commonResponses.add(new CommonResponse(VehicleCategory.COMBO_MOTO.getCode(), VehicleCategory.COMBO_MOTO.getName()));
        return Mono.just(commonResponses);
    }




////
//   @Override
//   public Mono<List<CommonResponse>> getCertVehicleCategory() {
//       List<CommonResponse> commonResponses = new ArrayList<>();
//       commonResponses.add(new CommonResponse(VehicleCategory.MOTOS.getCode(), VehicleCategory.MOTOS.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.VOITURE.getCode(), VehicleCategory.VOITURE.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.JEEP.getCode(), VehicleCategory.JEEP.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.BUS.getCode(), VehicleCategory.BUS.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.MINI_BUS.getCode(), VehicleCategory.MINI_BUS.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.CAMMIONNETTE.getCode(), VehicleCategory.CAMMIONNETTE.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.CAMION.getCode(), VehicleCategory.CAMION.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.GRAND_CAMMION.getCode(), VehicleCategory.GRAND_CAMMION.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.VEHICLE_TRACTEUR.getCode(), VehicleCategory.VEHICLE_TRACTEUR.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.VEHICLE_SPECIAUX.getCode(), VehicleCategory.VEHICLE_SPECIAUX.getName()));
//       commonResponses.add(new CommonResponse(VehicleCategory.VEHICLE_TRANSPORT.getCode(), VehicleCategory.VEHICLE_TRANSPORT.getName()));
////
//       return Mono.just(commonResponses);
////   }
//////
//   @Override
//   public Mono<List<CommonResponse>> getDemand() {
//       List<CommonResponse> commonResponses = new ArrayList<>();
//       commonResponses.add(new CommonResponse(Demand.RENEWAL_CERTIFICATE.getCode(), Demand.RENEWAL_CERTIFICATE.getName()));
//       commonResponses.add(new CommonResponse(Demand.NEW_CARD.getCode(), Demand.NEW_CARD.getName()));
//       commonResponses.add(new CommonResponse(Demand.RENEWAL_CARD.getCode(), Demand.RENEWAL_CARD.getName()));
//       commonResponses.add(new CommonResponse(Demand.LOSING_CARD.getCode(), Demand.LOSING_CARD.getName()));
//       commonResponses.add(new CommonResponse(Demand.BAD_CARD.getCode(), Demand.BAD_CARD.getName()));
//       commonResponses.add(new CommonResponse(Demand.CHANGE_PURPOSE.getCode(), Demand.CHANGE_PURPOSE.getName()));
//       commonResponses.add(new CommonResponse(Demand.CHANGE_COLOR.getCode(), Demand.CHANGE_COLOR.getName()));
//       commonResponses.add(new CommonResponse(Demand.CHANGE_PLATE.getCode(), Demand.CHANGE_PLATE.getName()));
//       commonResponses.add(new CommonResponse(Demand.TRANSFER.getCode(), Demand.TRANSFER.getName()));
////
//       return Mono.just(commonResponses);
//   }
////

//
//   @Override
//   public Mono<List<CommonResponse>> getCardType() {
//       List<CommonResponse> commonResponses = new ArrayList<>();
//       commonResponses.add(new CommonResponse(CardType.NEW.getCode(), CardType.NEW.getName()));
//       commonResponses.add(new CommonResponse(CardType.DUPLICATE.getCode(), CardType.DUPLICATE.getName()));
//       return Mono.just(commonResponses);
//   }
}
