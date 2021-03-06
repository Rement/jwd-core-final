package com.epam.jwd.core_final.cashcreator.impl;

import com.epam.jwd.core_final.cashcreator.BaseEntityCollectionCreatorFromFile;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CrewMemberCollectionCreatorFromFile implements BaseEntityCollectionCreatorFromFile {
    private CrewMember crewMember = null;
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private static CrewMemberCollectionCreatorFromFile instance;

    private CrewMemberCollectionCreatorFromFile() {
    }

    public static CrewMemberCollectionCreatorFromFile getInstance() {
        if (instance == null) {
            instance = new CrewMemberCollectionCreatorFromFile();
        }
        return instance;
    }

    @Override
    public Collection<CrewMember> createFromFile(  List<String> stringStreamFromFile ) {
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
