package fr.manu.picom_api.service.impl;

import fr.manu.picom_api.dao.ZoneDao;
import fr.manu.picom_api.model.Zone;
import fr.manu.picom_api.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ZoneServiceImpl implements ZoneService {
    private final ZoneDao zoneDao;

    @Override
    public List<Zone> getZones() {
        return zoneDao.findAll();
    }

    @Override
    public Zone findById(long id) {
        return zoneDao.findById(id);
    }

    @Override
    public Zone addZone(Zone zone) {
        return zoneDao.save(zone);
    }

    @Override
    public Zone updateZone(Zone zone) {
        return zoneDao.save(zone);
    }

    @Override
    public void deleteZone(long id) {
        Zone zone = findById(id);
        zoneDao.delete(zone);
    }
}
