package fr.manu.picom_api.dao;

import fr.manu.picom_api.model.ZoneHasHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneHasHourDao extends JpaRepository<ZoneHasHour, Long> {
}
