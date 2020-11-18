package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDateTime;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    @Override
    public FlightMission create(Object... args) {
        return new FlightMission((Long) args[0], (String) args[1], (LocalDateTime) args[2], (LocalDateTime) args[3], (Long) args[4]);
    }
}
