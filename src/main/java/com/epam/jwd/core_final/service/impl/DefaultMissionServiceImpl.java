package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.MissionAlreadyExistsException;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DefaultMissionServiceImpl implements MissionService {
    private List<FlightMission> missions;

    public DefaultMissionServiceImpl(final List<FlightMission> missions) {
        this.missions = missions;
    }

    public DefaultMissionServiceImpl(final ApplicationContext context) {
        this.missions = (List<FlightMission>) context.retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return missions;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(FlightMissionCriteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria must not be null!");
        }

        Stream<FlightMission> stream = missions.stream();

        if (criteria.getId() != null) {
            stream = stream.filter(crewMember -> crewMember.getId().equals(criteria.getId()));
        }
        if (criteria.getName() != null) {
            stream = stream.filter(crewMember -> crewMember.getName().equals(criteria.getName()));
        }
        if (criteria.getAssignedCrew() != null) {
            stream = stream.filter(crewMember -> crewMember.getAssignedCrew().equals(criteria.getAssignedCrew()));
        }
        if (criteria.getAssignedSpaceShip() != null) {
            stream = stream.filter(crewMember -> crewMember.getAssignedSpaceShip().equals(criteria.getAssignedSpaceShip()));
        }
        if (criteria.getDistance() != null) {
            stream = stream.filter(crewMember -> crewMember.getDistance().equals(criteria.getDistance()));
        }
        if (criteria.getStartDate() != null) {
            stream = stream.filter(crewMember -> crewMember.getStartDate().equals(criteria.getStartDate()));
        }
        if (criteria.getEndDate() != null) {
            stream = stream.filter(crewMember -> crewMember.getEndDate().equals(criteria.getEndDate()));
        }
        if (criteria.getMissionResult() != null) {
            stream = stream.filter(crewMember -> crewMember.getMissionResult().equals(criteria.getMissionResult()));
        }
        if (criteria.getMissionsName() != null) {
            stream = stream.filter(crewMember -> crewMember.getMissionsName().equals(criteria.getMissionsName()));
        }

        return stream.collect(toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(FlightMissionCriteria criteria) {
        List<FlightMission> missions = findAllMissionsByCriteria(criteria);

        return missions.isEmpty()
                ? Optional.empty()
                : Optional.of(missions.get(0));
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        FlightMission mission = missions.stream()
                .filter(mission1 -> mission1.getMissionsName().equals(flightMission.getMissionsName()))
                .findFirst().get();

        missions.remove(mission);
        missions.add(flightMission);

        return missions.get(missions.size() - 1);
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) throws MissionAlreadyExistsException {
        List<FlightMission> duplicates = missions.stream()
                .filter(mission -> mission.getMissionsName().equals(flightMission.getMissionsName()))
                .collect(toList());

        if (duplicates.isEmpty()) {
            missions.add(flightMission);
            return missions.get(missions.size() - 1);
        }

        throw new MissionAlreadyExistsException("Mission with this name already exists!");
    }
}
