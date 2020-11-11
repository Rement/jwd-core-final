package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.MissionAlreadyExistsException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.DefaultMissionServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static com.epam.jwd.core_final.util.JsonWriter.writeCollectionToJson;
import static com.epam.jwd.core_final.util.LogUtil.logError;

public class DefaultMenu implements ApplicationMenu {
    private MissionService missionService = new DefaultMissionServiceImpl(getApplicationContext());

    public DefaultMenu() {
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return NassaContext.getInstance();
    }

    @Override
    public void createMission(FlightMission mission) {
        try {
            missionService.createMission(mission);
        } catch (MissionAlreadyExistsException e) {
            logError(LOGGER, e.getMessage(), e);
        }
    }

    @Override
    public void writeAllMissionsToJson() {
        ApplicationProperties properties = getApplicationContext().getApplicationProperties();
        try {
            writeCollectionToJson(getApplicationContext().retrieveBaseEntityList(FlightMission.class),
                    "src/main/resources/" + properties.getOutputRootDir() + "/" + properties.getMissionsFileName());
        } catch (IOException e) {
            logError(LOGGER, "Couldn't write missions to json!", e);
        }
    }

    @Override
    public void updateMission(FlightMission mission, FlightMissionCriteria criteria) {
        Optional<FlightMission> flightMission = missionService.findMissionByCriteria(criteria);

        if (flightMission.isPresent()) {
            missionService.updateMissionDetails(mission);
        } else {
            throw new UnknownEntityException("Can't find any mission by criteria " + criteria.toString());
        }
    }
}
