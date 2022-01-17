package fr.manu.picom_api.service.impl;

import fr.manu.picom_api.dao.StopDao;
import fr.manu.picom_api.model.Stop;
import fr.manu.picom_api.service.StopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StopServiceImpl implements StopService {

    public final StopDao stopDao;

    @Override
    public List<Stop> getStops() {
        return stopDao.findAll();
    }

    @Override
    public Stop findById(long id) {
        return stopDao.findById(id);
    }

    @Override
    public Stop addStop(Stop stop) {
        return stopDao.save(stop);
    }

    @Override
    public Stop updateStop(Stop stop) {
        return stopDao.save(stop);
    }

    @Override
    public void deleteStop(long id) {
        Stop stop = stopDao.findById(id);
        stopDao.delete(stop);
    }
}
