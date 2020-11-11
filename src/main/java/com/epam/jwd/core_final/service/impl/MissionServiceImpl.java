package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.CreationOfMissionException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private Collection<FlightMission> flightMissions = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
    private CrewService crewService = new CrewServiceImpl();
    private SpaceshipService spaceshipService = new SpaceshipServiceImpl();
    private FlightMissionFactory factory = new FlightMissionFactory();
    private Long counter = 0L;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) flightMissions;
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
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) throws CreationOfMissionException {
        Long distance = flightMission.getDistance();
        List<Spaceship> availableSpaceships = spaceshipService.findAllSpaceshipsByCriteria(new SpaceshipCriteria.SpaceshipCriteriaBuilder().byFlightDistance(distance).build())
                .stream()
                .filter(spaceship -> spaceship.isReadyForNextMission())
                .collect(Collectors.toList());
        boolean isFound = false;
        List<CrewMember> possibleTeam = new ArrayList<>();
        for (int i = 0; i < availableSpaceships.size(); i++) {
            if (!isFound) {
                Map<Role, Short> needCrew = availableSpaceships.get(i).getCrew();
                isFound = isFoundCreMembers(needCrew, possibleTeam);
                if (isFound) {
                    flightMission.setAssignedSpaceship(availableSpaceships.get(i));
                    spaceshipService.assignSpaceshipOnMission(availableSpaceships.get(i));
                    flightMission.setAssignedCrew(possibleTeam);
                    possibleTeam.forEach(crewMember -> crewService.assignCrewMemberOnMission(crewMember));
                    setMissionResult(flightMission);
                    flightMission.setId(++counter);
                    flightMissions.add(flightMission);
                    if (flightMission.getMissionResult() == MissionResult.FAILED) {
                        NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class).remove(availableSpaceships.get(i));
                        NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class).removeAll(possibleTeam);
                    } else if (flightMission.getMissionResult() == MissionResult.CANCELLED && flightMission.getMissionResult() == MissionResult.COMPLETED) {
                        spaceshipService.freedSpaceships(availableSpaceships.get(i));
                        possibleTeam.forEach(crewMember -> crewService.freedCrewMembers(crewMember));
                    }
                }

            }
            if (isFound) {
                break;
            }
        }
        if (!isFound) {
            throw new CreationOfMissionException("You can't create this mission!");
        }
        return null;
    }

    private void setMissionResult(FlightMission flightMission) {
        while (flightMission.getMissionResult() != MissionResult.COMPLETED && flightMission.getMissionResult() != MissionResult.CANCELLED
                && flightMission.getMissionResult() != MissionResult.FAILED) {
            flightMission.setMissionResult(MissionResult.values()[new Random().nextInt(5)]);
            System.out.println(flightMission.toString());
        }
    }

    private boolean isFoundCreMembers(Map<Role, Short> needCrew, List<CrewMember> possibleTeam) {
        Set<Map.Entry<Role, Short>> entries = needCrew.entrySet();
        for (Map.Entry<Role, Short> entry : entries) {
            List<CrewMember> crewMembers = crewService.findAllCrewMembersByCriteria(new CrewMemberCriteria.CrewMemberCriteriaBuilder().byRole(entry.getKey()).build())
                    .stream()
                    .filter(crewMember -> crewMember.isReadyForNextMission())
                    .collect(Collectors.toList());
            if (crewMembers.size() < entry.getValue()) {
                possibleTeam.clear();
                return false;
            }
            for (int i = 0; i < entry.getValue(); i++) {
                possibleTeam.add(crewMembers.get(i));
            }
        }
        return true;
    }
}
