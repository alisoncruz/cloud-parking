package one.diome.parking.service;

import one.diome.parking.controller.dto.ParkingDTO;
import one.diome.parking.controller.mapper.ParkingMapper;
import one.diome.parking.model.Parking;
import org.springframework.stereotype.Service;

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
        return parkingMapper.toParkingDTO(parking);
    }
}
