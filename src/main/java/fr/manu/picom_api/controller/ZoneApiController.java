package fr.manu.picom_api.controller;

import fr.manu.picom_api.model.Zone;
import fr.manu.picom_api.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/zone")
@RequiredArgsConstructor
public class ZoneApiController {

    private final ZoneService zoneService;

    @GetMapping("")
    public ResponseEntity<List<Zone>> getZones() {
        try {
            List<Zone> zones = zoneService.getZones();

            if (zones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(zones, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getAdvertisement(@PathVariable("id") long id) {
        Zone zone = zoneService.findById(id);

        if (zone != null) {
            return new ResponseEntity<>(zone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Zone> addAdvertisement(@RequestBody Zone zone) {
        try {
            Zone _zone = zoneService.addZone(
                    new Zone(zone.getName(), zone.getStopNumber())
            );
            return new ResponseEntity<>(_zone, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable("id") long id, @RequestBody Zone zone) {

        Zone zoneData = zoneService.findById(id);
        if (zoneData != null) {
            zoneData.setName(zone.getName());
            zoneData.setStopNumber(zone.getStopNumber());
            return new ResponseEntity<>(zoneService.updateZone(zoneData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteZone(@PathVariable("id") long id) {
        try {
            zoneService.deleteZone(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
