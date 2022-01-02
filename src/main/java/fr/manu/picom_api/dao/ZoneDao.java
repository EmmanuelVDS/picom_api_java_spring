package fr.manu.picom_api.dao;

import fr.manu.picom_api.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneDao extends JpaRepository<Zone, Long> {
}
