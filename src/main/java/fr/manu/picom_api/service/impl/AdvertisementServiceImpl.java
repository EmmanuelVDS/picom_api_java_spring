package fr.manu.picom_api.service.impl;

import fr.manu.picom_api.dao.AdvertisementDao;
import fr.manu.picom_api.model.Advertisement;
import fr.manu.picom_api.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementDao advertisementDao;

    @Override
    public List<Advertisement> getAdvertisements() {
        return advertisementDao.findAll();
    }

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        return advertisementDao.save(advertisement);
    }

    @Override
    public Advertisement findById(long id) {
        return advertisementDao.findById(id);
    }

    @Override
    public Advertisement updateAdvertisement(Advertisement advertisement) {
        return advertisementDao.save(advertisement);
    }

    @Override
    public void deleteAdvertisement(long id) {
        Advertisement advertisement = findById(id);
        advertisementDao.delete(advertisement);
    }

}
