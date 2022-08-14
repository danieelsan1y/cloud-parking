package one.digitaninnoavtion.parking.controller.mapper;

import one.digitaninnoavtion.parking.controller.dto.ParkingCreateDTO;
import one.digitaninnoavtion.parking.controller.dto.ParkingDTO;
import one.digitaninnoavtion.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {
    private static  final ModelMapper MODEL_MAPPER = new ModelMapper();

    public  ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking,ParkingDTO.class);
    }
    public List<ParkingDTO> toParkingDTOList(List<Parking> parkings) {
    return parkings.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }

    public Parking toParking(ParkingDTO parkingDTO) {
        return  MODEL_MAPPER.map(parkingDTO,Parking.class);
    }

    public Parking toParkingCreate(ParkingCreateDTO parkingCreateDTO) {
        return  MODEL_MAPPER.map(parkingCreateDTO,Parking.class);
    }
}
