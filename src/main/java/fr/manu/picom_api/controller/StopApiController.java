package fr.manu.picom_api.controller;

import fr.manu.picom_api.model.Stop;
import fr.manu.picom_api.model.Zone;
import fr.manu.picom_api.service.StopService;
import fr.manu.picom_api.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/stop")
@RequiredArgsConstructor
public class StopApiController {
    private final StopService stopService;

    @GetMapping("")
    public ResponseEntity<List<Stop>> getZones() {
        try {
            List<Stop> stops = stopService.getStops();

            if (stops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(stops, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stop> getAdvertisement(@PathVariable("id") long id) {
        Stop stop = stopService.findById(id);

        if (stop != null) {
            return new ResponseEntity<>(stop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Stop> addAdvertisement(@RequestBody Stop stop) {
        try {
            Stop _stop = stopService.addStop(
                    new Stop(stop.getName(), stop.getZone())
            );
            return new ResponseEntity<>(_stop, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Stop> updateZone(@PathVariable("id") long id, @RequestBody Stop stop) {

        Stop stopData = stopService.findById(id);
        if (stopData != null) {
            stopData.setName(stop.getName());
            stopData.setZone(stop.getZone());
            return new ResponseEntity<>(stopService.updateStop(stopData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteZone(@PathVariable("id") long id) {
        try {
            stopService.deleteStop(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
