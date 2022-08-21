package one.diome.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.diome.parking.controller.dto.ParkingCreateDTO;
import one.diome.parking.controller.dto.ParkingDTO;
import one.diome.parking.controller.dto.ParkingUpdateDTO;
import one.diome.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkings")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService service;

    private ParkingController(ParkingService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Find All Parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<ParkingDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find Parking By Id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable("id") String id) {
        ParkingDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Create New Parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        ParkingDTO parkingDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Parking Entity")
    public ResponseEntity delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Parking Entity")
    public ResponseEntity<ParkingDTO> update(@PathVariable("id") String id, ParkingUpdateDTO dto) {
        ParkingDTO parkingDTO = service.update(id, dto);
        return ResponseEntity.ok(parkingDTO);
    }

    @GetMapping("/{id}/checkout")
    @ApiOperation("Parking Checkout")
    public ResponseEntity<ParkingDTO> checkout(@PathVariable("id") String id) {
        ParkingDTO parkingDTO = service.checkout(id);
        return ResponseEntity.ok(parkingDTO);
    }
}
