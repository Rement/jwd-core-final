package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.CrewMemberException;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DefaultCrewServiceImpl implements CrewService {
    private List<CrewMember> crewMembers;

    public DefaultCrewServiceImpl(final List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public DefaultCrewServiceImpl(final ApplicationContext context) {
        this.crewMembers = (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return crewMembers;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria must not be null!");
        }

        Stream<CrewMember> stream = crewMembers.stream();

        if (criteria.getId() != null) {
            stream = stream.filter(crewMember -> crewMember.getId().equals(criteria.getId()));
        }
        if (criteria.getName() != null) {
            stream = stream.filter(crewMember -> crewMember.getName().equals(criteria.getName()));
        }
        if (criteria.getRank() != null) {
            stream = stream.filter(crewMember -> crewMember.getRank().equals(criteria.getRank()));
        }
        if (criteria.getRole() != null) {
            stream = stream.filter(crewMember -> crewMember.getRank().equals(criteria.getRank()));
        }
        if (criteria.isReadyForNextMissions() != null) {
            stream = stream.filter(crewMember -> crewMember.isReadyForNextMissions().equals(criteria.isReadyForNextMissions()));
        }

        return stream.collect(toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria) {
        List<CrewMember> crewMembers = findAllCrewMembersByCriteria(criteria);

        return crewMembers.isEmpty()
                ? Optional.empty()
                : Optional.of(crewMembers.get(0));
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {

        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws CrewMemberException {
        assert crewMember != null;
        if (crewMember.isReadyForNextMissions() == null || !crewMember.isReadyForNextMissions()) {
            throw new CrewMemberException("Crew member "+crewMember.toString()+" already assigned to mission!");
        }

        crewMember.setReadyForNextMissions(true);
    }

    @Override
    public CrewMember createCrewMember(CrewMember spaceship) throws RuntimeException {
        return null;
    }
}
