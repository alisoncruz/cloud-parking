package one.diome.parking.controller;

import one.diome.parking.model.Parking;
import one.diome.parking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Parking> findAll(){
        return service.findAll();
    }
}
