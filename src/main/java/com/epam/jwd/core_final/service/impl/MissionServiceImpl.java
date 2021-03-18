package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.decorator.impl.PreProcessorImpl;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.writer.BaseEntityWriter;
import com.epam.jwd.core_final.writer.impl.FlightMissionWriter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    private static MissionServiceImpl instance;
    private Collection<FlightMission> flightMissions;
    private BaseEntityWriter<FlightMission> writer = FlightMissionWriter.getInstance();
    private PostProcessorImpl postProcessor = new PostProcessorImpl(new PreProcessorImpl(FlightMissionFactory.getInstance()));
    private static Logger logger = new JwdLogger(MissionServiceImpl.class.getName(), "slf4j");

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
        flightMissions = Main.getFlightMissions();
        return (List<FlightMission>) flightMissions;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> flightMissionsByCriteria = new ArrayList<>();
        if (criteria != null) {
            flightMissions = Main.getFlightMissions();
            FlightMissionCriteria flightMission = (FlightMissionCriteria) criteria;
            if (flightMission.getStartDate() != null) {
                flightMissionsByCriteria =
                        flightMissions
                                .stream()
                                .filter(c -> c.getStartDate().equals(flightMission.getStartDate()))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the flightMissions was found successfully " + flightMissionsByCriteria);
            }
            if (flightMission.getEndDate() != null) {
                flightMissionsByCriteria =
                        flightMissions
                                .stream()
                                .filter(c -> c.getEndDate().equals(flightMission.getEndDate()))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the flightMissions was found successfully " + flightMissionsByCriteria);
            }
            if (flightMission.getDistance() != null) {
                flightMissionsByCriteria =
                        flightMissions
                                .stream()
                                .filter(c -> c.getDistance().equals(flightMission.getDistance()))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the flightMissions was found successfully " + flightMissionsByCriteria);
            }
            if (flightMission.getFromPlanet() != null) {
                flightMissionsByCriteria =
                        flightMissions
                                .stream()
                                .filter(c -> c.getFromPlanet().equals(flightMission.getFromPlanet()))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the flightMissions was found successfully " + flightMissionsByCriteria);
            }
            if (flightMission.getToPlanet() != null) {
                flightMissionsByCriteria =
                        flightMissions
                                .stream()
                                .filter(c -> c.getToPlanet().equals(flightMission.getToPlanet()))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the flightMissions was found successfully " + flightMissionsByCriteria);
            }
        }
        return flightMissionsByCriteria;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        flightMissions = Main.getFlightMissions();
        Optional<FlightMission> flightMission = null;
        try {
            flightMission = Optional.of(
                    flightMission
                            .stream()
                            .filter(c -> c.getName().equals(criteria.getName()))
                            .findFirst().orElseThrow(() -> new UnknownEntityException(criteria.getName())));
            logger.log(Level.INFO, "the flightMission was found successfully " + flightMission);
        } catch (UnknownEntityException unknownEntityException) {
            unknownEntityException.getMessage();
            logger.log(Level.WARNING, "the flightMission wasn't found by name " + criteria.getName());
        }
        return flightMission;
    }

    @Override
    public FlightMission deleteFlightMission(FlightMission flightMission) {
        Collection<FlightMission> flightMissions = Main.getFlightMissions();
        Collection<FlightMission> newFlightMissions = flightMissions.stream()
                .filter(s -> !s.equals(flightMission))
                .collect(Collectors.toList());
        Main.setFlightMissions(newFlightMissions);
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        return (FlightMission) postProcessor.create(flightMission);
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {

    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {

    }

    @Override
    public void writeMissionToJsonFile(FlightMission f) {
        flightMissions = Main.getFlightMissions();
        Optional<FlightMission> flightMission = null;
        try {
            flightMission = Optional.of(
                    flightMissions
                            .stream()
                            .filter(c -> c.getName().equals(f.getName()))
                            .findFirst().orElseThrow(() -> new UnknownEntityException(f.getName())));
            logger.log(Level.INFO, "the flightMission was found successfully " + flightMission);
        } catch (UnknownEntityException unknownEntityException) {
            unknownEntityException.getMessage();
            logger.log(Level.WARNING, "the flightMission wasn't found by name " + f.getName());
        }
        writer.writeToJsonFile(flightMission.get());
    }
}
