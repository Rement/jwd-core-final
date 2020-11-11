package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.AssignCrewException;
import com.epam.jwd.core_final.exception.CreateCrewException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.strategy.impl.ReadCrewMembersFromFileStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    Collection<CrewMember> crewMembers = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    private Long id = 0L;

    @Override
    public Collection<CrewMember> findAllCrewMembers() {
        return crewMembers;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria) {
        return crewMembers.stream().filter(crewMember -> crewMember.getRole().equals(criteria.getRole())).collect(Collectors.toList());
    }

    @Override
    public List<CrewMember> findAllCrewMembersByRank(CrewMemberCriteria criteria) {
        return crewMembers.stream().filter(crewMember -> crewMember.getRank().equals(criteria.getRank())).collect(Collectors.toList());
    }

    @Override
    public void findCrewMemberByCriteria(CrewMemberCriteria criteria) {
        Optional.of(crewMembers.stream().filter(c -> c.getName().equals(criteria.getName()))).ifPresent(crewMember -> System.out.println(crewMember.findAny().toString()));
    }

    @Override
    public void updateCrewMemberDetails(CrewMember crewMember) {
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws AssignCrewException {
        crewMember.setReadyForNextMission(false);
    }

    @Override
    public void freedCrewMembers(CrewMember crewMember) {
        crewMember.setReadyForNextMission(true);
    }

    @Override
    public Collection<CrewMember> createCrewMember(ReadCrewMembersFromFileStrategy strategy) throws CreateCrewException {
        return strategy.getCrewMembers();
    }
}
