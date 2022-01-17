package fr.manu.picom_api.dao;

import fr.manu.picom_api.model.Hour;
import fr.manu.picom_api.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourDao extends JpaRepository<Hour, Long> {
    Hour findById(long id);
}
