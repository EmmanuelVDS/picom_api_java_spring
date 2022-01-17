package fr.manu.picom_api.service;

import fr.manu.picom_api.model.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getZones();

    Zone findById(long id);

    Zone addZone(Zone zone);

    Zone updateZone(Zone zone);

    void deleteZone(long id);
}
