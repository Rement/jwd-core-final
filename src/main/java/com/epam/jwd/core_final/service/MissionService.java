package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.CreationOfMissionException;

import java.util.List;

public interface MissionService {

    List<FlightMission> findAllMissions();

    List<FlightMission> findAllMissionsByCriteria(FlightMissionCriteria criteria);

    void findMissionByCriteria(FlightMissionCriteria criteria);

    FlightMission updateSpaceshipDetails(FlightMission flightMission);

    FlightMission createMission(FlightMission flightMission) throws CreationOfMissionException;
}
