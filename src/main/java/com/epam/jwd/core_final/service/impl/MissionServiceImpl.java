package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;

public class MissionServiceImpl implements MissionService {
    CrewMemberFactory factory = CrewMemberFactory.getInstance();
    PostProcessorImpl postProcessor;
    private static MissionServiceImpl instance;

    private MissionServiceImpl() {
    }

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return null;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return null;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return Optional.empty();
    }

    @Override
    public FlightMission deleteFlightMission(FlightMission flightMission) {
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        // postProcessor = new PostProcessorImpl(factory.create(flightMission));
        //postProcessor.create(factory.create(flightMission));
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {

    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }
}
