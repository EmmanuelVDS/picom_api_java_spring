package fr.manu.picom_api.service.impl;

import fr.manu.picom_api.dao.HourDao;
import fr.manu.picom_api.model.Hour;
import fr.manu.picom_api.service.HourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HourServiceImpl implements HourService {
    public final HourDao hourDao;

    @Override
    public List<Hour> getHours() {
        return hourDao.findAll();
    }

    @Override
    public Hour findById(long id) {
        return hourDao.findById(id);
    }

    @Override
    public Hour addHour(Hour hour) {
        return hourDao.save(hour);
    }

    @Override
    public Hour updateHour(Hour hour) {
        return hourDao.save(hour);
    }

    @Override
    public void deleteHour(long id) {
        Hour hour = findById(id);
        hourDao.delete(hour);
    }
}
