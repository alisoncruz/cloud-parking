package one.diome.parking.controller.mapper;

import one.diome.parking.controller.dto.ParkingCreateDTO;
import one.diome.parking.controller.dto.ParkingDTO;
import one.diome.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public Parking toParkingEntity(ParkingDTO dto){
        return MODEL_MAPPER.map(dto,Parking.class);
    }

    public Parking toParkingCreate(ParkingCreateDTO dto){
        return MODEL_MAPPER.map(dto,Parking.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> list) {
        return list.stream().map(parking -> toParkingDTO(parking))
                .collect(Collectors.toList());
    }
}
