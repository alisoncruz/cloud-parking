package one.diome.parking.service;

import one.diome.parking.controller.dto.ParkingCreateDTO;
import one.diome.parking.controller.dto.ParkingDTO;
import one.diome.parking.controller.dto.ParkingUpdateDTO;
import one.diome.parking.controller.mapper.ParkingMapper;
import one.diome.parking.model.Parking;
import one.diome.parking.repository.ParkingRepository;
import one.diome.parking.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    private ParkingMapper parkingMapper;

    public ParkingService(ParkingMapper parkingMapper, ParkingRepository parkingRepository) {
        this.parkingMapper = parkingMapper;
        this.parkingRepository = parkingRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<ParkingDTO> findAll() {
        return parkingRepository.findAll()
                .stream()
                .map(parking -> parkingMapper.toParkingDTO(parking))
                .collect(Collectors.toList());
    }

    public ParkingDTO findById(String id) {
        Parking parking = find(id);
        return parkingMapper.toParkingDTO(parking);
    }

    public ParkingDTO create(ParkingCreateDTO dto) {
        Parking parking = parkingMapper.toParkingCreate(dto);
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parking = parkingRepository.save(parking);
        return parkingMapper.toParkingDTO(parking);
    }

    public void delete(String id) {
        Parking parking = find(id);
        parkingRepository.deleteById(id);
    }

    public ParkingDTO update(String id, ParkingUpdateDTO dto) {
        Parking parking = find(id);
        parking.setColor(dto.getColor());
        parking = parkingRepository.save(parking);
        return parkingMapper.toParkingDTO(parking);
    }

    private Parking find(String id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parking with Id: " + id + " not found"));
    }

    public ParkingDTO checkout(String id) {
        Parking parking = find(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));
        parking = parkingRepository.save(parking);
        return parkingMapper.toParkingDTO(parking);
    }
}
