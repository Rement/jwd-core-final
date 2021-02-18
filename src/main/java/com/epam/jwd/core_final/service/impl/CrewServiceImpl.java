package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.AssignCrewException;
import com.epam.jwd.core_final.exception.CreateCrewMemberException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.strategy.impl.ReadCrewMembersFromFileStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl instance;
    Collection<CrewMember> crewMembers = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
            return instance;
        }
        return null;
    }

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
    public Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria) {
        return crewMembers.stream().filter(c -> c.getName().equals(criteria.getName())).findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        Optional<CrewMember> member = crewMembers.stream().filter(crewMember1 -> crewMember1.getName().equals(crewMember.getName()))
                .findFirst();
        if (member.isPresent()) {
            crewMembers.remove(member.get());
            crewMembers.add(crewMember);
        }
        return crewMember;
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
    public Collection<CrewMember> createCrewMember(ReadCrewMembersFromFileStrategy strategy) throws CreateCrewMemberException {
        return strategy.getCrewMembers();
    }
}
