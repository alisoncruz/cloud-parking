package one.diome.parking.service;

import one.diome.parking.controller.dto.ParkingCreateDTO;
import one.diome.parking.controller.dto.ParkingDTO;
import one.diome.parking.controller.dto.ParkingUpdateDTO;
import one.diome.parking.controller.mapper.ParkingMapper;
import one.diome.parking.model.Parking;
import one.diome.parking.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    private ParkingMapper parkingMapper;

    public ParkingService(ParkingMapper parkingMapper){
        this.parkingMapper = parkingMapper;
    }

    static {
        var id = getUUID();
        var id2 = getUUID();
        Parking parking = new Parking(id,"DMS-1111","SC","CELTA","PRETO");
        Parking parking2 = new Parking(id2,"WAS-1111","SP","VW GOL","VERMELHO");
        parkingMap.put(id,parking);
        parkingMap.put(id2,parking2);

    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public List<ParkingDTO> findAll(){
        List<Parking> parkingList = parkingMap.values().stream().collect(Collectors.toList());
        return parkingMapper.toParkingDTOList(parkingList);
    }

    public ParkingDTO findById(String id){
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ResourceNotFoundException("Parking with Id: "+id + " not found");
        }
        return parkingMapper.toParkingDTO(parking);
    }

    public ParkingDTO create(ParkingCreateDTO dto){
        Parking parking = parkingMapper.toParkingCreate(dto);
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(parking.getId(),parking);
        return parkingMapper.toParkingDTO(parking);
    }

    public void delete(String id){
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ResourceNotFoundException("Parking with Id: "+id + " not found");
        }
        parkingMap.remove(id);
    }

    public ParkingDTO update(String id, ParkingUpdateDTO dto){
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ResourceNotFoundException("Parking with Id: "+id + " not found");
        }
        parking.setColor(dto.getColor());
        parkingMap.replace(id,parking);
        return parkingMapper.toParkingDTO(parking);
    }

}
