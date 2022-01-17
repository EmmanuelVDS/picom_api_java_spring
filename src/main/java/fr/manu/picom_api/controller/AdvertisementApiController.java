package fr.manu.picom_api.controller;

import fr.manu.picom_api.AdvertisementType;
import fr.manu.picom_api.model.Advertisement;
import fr.manu.picom_api.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/app/advertisement")
@RequiredArgsConstructor
public class AdvertisementApiController {

    private final AdvertisementService advertisementService;

    @PostConstruct
    private void init() {

        if (advertisementService.getAdvertisements().isEmpty()) {

            advertisementService.addAdvertisement(new Advertisement(LocalDate.of(2022, 1, 16), LocalDate.of(2022, 1, 25), AdvertisementType.IMAGE, "test"));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Advertisement>> getAdvertisements() {
        try {
            List<Advertisement> advertisements = advertisementService.getAdvertisements();

            if (advertisements.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(advertisements, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advertisement> getAdvertisement(@PathVariable("id") long id) {
        Advertisement advertisement = advertisementService.findById(id);

        if (advertisement != null) {
            return new ResponseEntity<>(advertisement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement advertisement) {
        try {
            Advertisement _advertisement = null;
            if (advertisement.getType() == AdvertisementType.IMAGE) {
                _advertisement = advertisementService.addAdvertisement(
                        new Advertisement(advertisement.getDateStart(), advertisement.getDateEnd(), advertisement.getType(), advertisement.getUrl())
                );
            }
            if (advertisement.getType() == AdvertisementType.HTML) {
                _advertisement = advertisementService.addAdvertisement(
                        new Advertisement(advertisement.getDateStart(), advertisement.getDateEnd(), advertisement.getType(), advertisement.getContent())
                );
            }
            return new ResponseEntity<>(_advertisement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Advertisement> updateAdvertisementImage(@PathVariable("id") long id, @RequestBody Advertisement advertisement) {
        Advertisement advertisementData = advertisementService.findById(id);
        if (advertisementData != null) {
            if (advertisement.getType() == AdvertisementType.IMAGE) {
                advertisementData.setDateStart(advertisement.getDateStart());
                advertisementData.setDateEnd(advertisement.getDateEnd());
                advertisementData.setType(advertisement.getType());
                advertisementData.setUrl(advertisement.getUrl());
            }
            if (advertisement.getType() == AdvertisementType.HTML) {
                advertisementData.setDateStart(advertisement.getDateStart());
                advertisementData.setDateEnd(advertisement.getDateEnd());
                advertisementData.setType(advertisement.getType());
                advertisementData.setContent(advertisement.getContent());
            }
            return new ResponseEntity<>(advertisementData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAdvertisement(@PathVariable("id") long id) {
        try {
            advertisementService.deleteAdvertisement(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
