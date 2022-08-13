package one.digitaninnoavtion.parking.controller;

import one.digitaninnoavtion.parking.controller.dto.ParkingDTO;
import one.digitaninnoavtion.parking.controller.mapper.ParkingMapper;
import one.digitaninnoavtion.parking.model.Parking;
import one.digitaninnoavtion.parking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findAll() {
        List<Parking> parkings = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkings);
        return result;
    }
}
