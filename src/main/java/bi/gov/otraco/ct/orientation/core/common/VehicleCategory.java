package bi.gov.otraco.ct.orientation.core.common;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum VehicleCategory {
    COMBO_LMV("LMV","OL001"),
    COMBO_CC("COMBO","OL002"),
    COMBO_MOTO("MOTO","OL003"),


    MOTOS("MOTOS", "VCC001"),
    VOITURE("VOITURE", "VCC002"),
    JEEP("JEEP", "VCC003"),
    BUS("BUS", "VCC004"),
    MINI_BUS("MINI BUS", "VCC005"),
    CAMMIONNETTE("CAMMIONNETTE", "VCC006"),
    CAMION("CAMION/TYPE FUSO", "VCC007"),
    GRAND_CAMMION("GRAND CAMMION", "VCC008"),
    VEHICLE_TRACTEUR("VEHICLE TRACTEUR (TRAILLER)", "VCC009"),
    VEHICLE_SPECIAUX("VEHICLE SPECIAUX", "VCC010"),
    VEHICLE_TRANSPORT("VEHICLE DE TRANSPORT DE MATIERES DANGEREUSES", "VCC011"),
    PENALTY("PENALITE", "VCC012");

    private final String name;
    private final String code;

    private static final Map<String, VehicleCategory> BY_CODE =
            Stream.of(values()).collect(Collectors.toUnmodifiableMap(VehicleCategory::getCode, vehicleCategory -> vehicleCategory));

    public static VehicleCategory fromCode(String code) {
        return BY_CODE.getOrDefault(code, VOITURE);
    }
}
