package fr.manu.picom_api.dao;

import fr.manu.picom_api.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementDao extends JpaRepository<Advertisement, Long> {

    Advertisement findById(long id);

}
