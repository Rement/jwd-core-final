package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    private CrewMember crewMember = null;
    private CrewMember enteredCrewMember = null;
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private static CrewMemberFactory instance;

    private CrewMemberFactory() {
    }

    public static CrewMemberFactory getInstance() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }

    @Override
    public CrewMember create(Object... args) {
        for (Object s : args) {
            try {
                Class n = s.getClass();
                if (n.equals(CrewMember.class)) {
                    enteredCrewMember = (CrewMember) s;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        crewMember = crewMemberCriteria
                .setRole(enteredCrewMember.getRole())
                .setRank(enteredCrewMember.getRank())
                .setReadyForNextMission(true)
                .setName(enteredCrewMember.getName())
                .build();
        return crewMember;
    }
}
