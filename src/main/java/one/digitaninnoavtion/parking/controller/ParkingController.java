package one.digitaninnoavtion.parking.controller;

import io.swagger.annotations.Api;
import one.digitaninnoavtion.parking.controller.dto.ParkingCreateDTO;
import one.digitaninnoavtion.parking.controller.dto.ParkingDTO;
import one.digitaninnoavtion.parking.controller.mapper.ParkingMapper;
import one.digitaninnoavtion.parking.model.Parking;
import one.digitaninnoavtion.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkings = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkings);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById (@PathVariable String id) {
        Parking parking = parkingService.findById(id);

        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok().body(result);

    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create (@RequestBody ParkingCreateDTO parkingCreateDTO) {
        Parking parkingCreate = parkingMapper.toParkingCreate(parkingCreateDTO);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update (@PathVariable String id, @RequestBody ParkingCreateDTO parkingCreateDTO) {
        Parking parkingCreate = parkingMapper.toParkingCreate(parkingCreateDTO);
        Parking parking = parkingService.update(id,parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> create (@PathVariable String id) {
        Parking parking = parkingService.checkOut(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
