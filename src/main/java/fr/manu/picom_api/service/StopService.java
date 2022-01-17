package fr.manu.picom_api.service;

import fr.manu.picom_api.model.Stop;

import java.util.List;

public interface StopService {

    List<Stop> getStops();

    Stop findById(long id);

    Stop addStop(Stop stop);

    Stop updateStop(Stop stop);

    void deleteStop(long id);

}
