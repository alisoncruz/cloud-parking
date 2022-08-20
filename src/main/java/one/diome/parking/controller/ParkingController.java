package one.diome.parking.controller;

import one.diome.parking.model.Parking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    @GetMapping
    public List<Parking> findAll(){
        var parking = new Parking();
        parking.setColor("preto");
        parking.setLicense("MSS-1111");
        parking.setModel("vw GOL");
        parking.setLicense("RJ");
        return Arrays.asList(parking,parking);
    }
}
