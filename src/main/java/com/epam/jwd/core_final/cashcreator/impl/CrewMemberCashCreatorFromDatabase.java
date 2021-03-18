package com.epam.jwd.core_final.cashcreator.impl;

import com.epam.jwd.core_final.cashcreator.BaseEntityCashCreatorFromDatabase;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CrewMemberCashCreatorFromDatabase implements BaseEntityCashCreatorFromDatabase {
    private CrewMember crewMember = null;
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private static CrewMemberCashCreatorFromDatabase instance;

    private CrewMemberCashCreatorFromDatabase() {
    }

    public static CrewMemberCashCreatorFromDatabase getInstance() {
        if (instance == null) {
            instance = new CrewMemberCashCreatorFromDatabase();
        }
        return instance;
    }

    @Override
    public Collection<CrewMember> createCashFromDatabase(List<String> stringStreamFromFile ) {
        Collection<CrewMember> crewMembers = new ArrayList<>();
        for (String s : stringStreamFromFile) {
        String[] stringsForObject = s.split(",");
        if (!stringsForObject[0].equals("role")) {
            Role role = Role.resolveRoleById(Integer.parseInt(stringsForObject[0]));
            Rank rank = Rank.resolveRankById(Integer.parseInt(stringsForObject[2]));
            crewMember = crewMemberCriteria
                    .setRole(role)
                    .setRank(rank)
                    .setReadyForNextMission(true)
                    .setName(stringsForObject[1])
                    .build();
        }
            crewMembers.add(crewMember);
        }
        return crewMembers;
    }
}
