package fr.manu.picom_api.controller;

import fr.manu.picom_api.model.Hour;
import fr.manu.picom_api.service.HourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/hour")
@RequiredArgsConstructor
public class HourApiController {

    private final HourService hourService;

    @GetMapping("")
    public ResponseEntity<List<Hour>> getZones() {
        try {
            List<Hour> Hours = hourService.getHours();

            if (Hours.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(Hours, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hour> getAdvertisement(@PathVariable("id") long id) {
        Hour hour = hourService.findById(id);

        if (hour != null) {
            return new ResponseEntity<>(hour, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Hour> addAdvertisement(@RequestBody Hour hour) {
        try {
            Hour _hour = hourService.addHour(
                    new Hour(hour.getHourStart(), hour.getHourEnd())
            );
            return new ResponseEntity<>(_hour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hour> updateZone(@PathVariable("id") long id, @RequestBody Hour hour) {

        Hour hourData = hourService.findById(id);
        if (hourData != null) {
            hourData.setHourStart(hour.getHourStart());
            hourData.setHourEnd(hour.getHourEnd());
            return new ResponseEntity<>(hourService.updateHour(hourData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteZone(@PathVariable("id") long id) {
        try {
            hourService.deleteHour(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
