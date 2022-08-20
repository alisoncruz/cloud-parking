package one.diome.parking.controller;

import one.diome.parking.controller.dto.ParkingDTO;
import one.diome.parking.model.Parking;
import one.diome.parking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService service;

    private ParkingController(ParkingService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<ParkingDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable("id") String id){
        ParkingDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
