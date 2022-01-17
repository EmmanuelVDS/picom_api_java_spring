package fr.manu.picom_api.service;

import fr.manu.picom_api.model.Hour;
import fr.manu.picom_api.model.Stop;

import java.util.List;

public interface HourService {
    List<Hour> getHours();

    Hour findById(long id);

    Hour addHour(Hour hour);

    Hour updateHour(Hour hour);

    void deleteHour(long id);
}
