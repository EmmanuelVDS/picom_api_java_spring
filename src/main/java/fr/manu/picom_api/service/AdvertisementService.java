package fr.manu.picom_api.service;

import fr.manu.picom_api.model.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<Advertisement> getAdvertisements();

    Advertisement addAdvertisement(Advertisement advertisement);

    Advertisement findById(long id);

    Advertisement updateAdvertisement(Advertisement advertisement);

    void deleteAdvertisement(long id);
}
