package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.AssignCrewException;
import com.epam.jwd.core_final.exception.CreateCrewException;
import com.epam.jwd.core_final.strategy.impl.ReadCrewMembersFromFileStrategy;

import java.util.Collection;
import java.util.List;

/**
 * All its implementations should be a singleton
 * You have to use streamAPI for filtering, mapping, collecting, iterating
 */
public interface CrewService {

    Collection<CrewMember> findAllCrewMembers();

    public List<CrewMember> findAllCrewMembersByRank(CrewMemberCriteria criteria);

    List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria);

    void findCrewMemberByCriteria(CrewMemberCriteria criteria);

    void updateCrewMemberDetails(CrewMember crewMember);

    // todo create custom exception for case, when crewMember is not able to be assigned
    void assignCrewMemberOnMission(CrewMember crewMember) throws AssignCrewException;

    public void freedCrewMembers(CrewMember crewMember);

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // crewmember unique criteria - only name!
    Collection<CrewMember> createCrewMember(ReadCrewMembersFromFileStrategy strategy) throws CreateCrewException;
}