package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.CreateCrewMemberException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.strategy.ReadFromFileStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadCrewMembersFromFileStrategy implements ReadFromFileStrategy {

    Collection<CrewMember> crewMembers = new ArrayList<>();
    Long counter = 0L;

    @Override
    public void readFromFile(String path) throws FileNotFoundException {
        Map<String, CrewMember> tmpMap = new HashMap<>();
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        Scanner scanner = new Scanner(new File(path));
        scanner.nextLine();
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            String dividedByComma = scanner.next();
            String[] splitByComma = dividedByComma.split(",");
            Role role = Role.resolveRoleById(Integer.parseInt(splitByComma[0]));
            String name = splitByComma[1];
            Rank rank = Rank.resolveRankById(Integer.parseInt(splitByComma[2]));
            Long id = ++counter;
            if (!tmpMap.containsKey(name)) {
                crewMembers.add(crewMemberFactory.create(id, role, name, rank));
                tmpMap.put(name, null);
            } else {
                try {
                    throw new CreateCrewMemberException("Member with name " + name + " already exists!");
                } catch (CreateCrewMemberException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Collection<CrewMember> getCrewMembers() {
        return crewMembers;
    }
}
