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
        Parking parking = new Parking(id,"DMS-1111","SC","CELTA","PRETO");
        parkingMap.put(id,parking);
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public List<ParkingDTO> findAll(){
        List<Parking> parkingList = parkingMap.values().stream().collect(Collectors.toList());
        return parkingMapper.toParkingDTOList(parkingList);
    }
}
