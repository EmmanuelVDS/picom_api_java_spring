package fr.manu.picom_api.dao;

import fr.manu.picom_api.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopDao extends JpaRepository<Stop, Long> {
}
