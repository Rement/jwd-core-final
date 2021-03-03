package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// todo
public class NassaContext implements ApplicationContext {
    ApplicationProperties applicationProperties = new ApplicationProperties();
    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        String dir = applicationProperties.getRootDir();
        String dirName = applicationProperties.getInputRootDir();
        List<String> stringStreamFromFile = null;

        if (tClass.equals(CrewMember.class)) {
            String fileName = applicationProperties.getCrewFileName();
            String index = applicationProperties.getIndexForCrewFileName();
            stringStreamFromFile = readFromFile(dir + "/" + dirName + "/" + fileName, index);
            CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
            for (String s : stringStreamFromFile) {
                String[] stringsForObject = s.split(",");
                if (!stringsForObject[0].equals("role")) {
                    Role role = Role.resolveRoleById(Integer.parseInt(stringsForObject[0]));
                    Rank rank = Rank.resolveRankById(Integer.parseInt(stringsForObject[2]));
                    CrewMember crewMember = crewMemberCriteria
                            .setRole(role)
                            .setRank(rank)
                            .setReadyForNextMission(true)
                            .setName(stringsForObject[1])
                            .build();
                    crewMembers.add(crewMember);
                }
            }
            crewMembers.stream().forEach(System.out::println);
        }

        if (tClass.equals(Spaceship.class)) {
            String filename = applicationProperties.getSpaceshipsFileName();
            String index = applicationProperties.getIndexForSpaceshipFileName();
            stringStreamFromFile = readFromFile(dir + "/" + dirName + "/" + filename, index);
            SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();

            for (String s : stringStreamFromFile) {
                if (s.length()>3) {
                    String[] stringsForObject = s.split(";");
                    if (!stringsForObject[0].equals("name") && !stringsForObject[0].equals("#name")) {
                        String notRemuvedString = stringsForObject[2];
                        String remuvedString = notRemuvedString.substring(1, notRemuvedString.length() - 1);
                        String[] stringsForCrew = remuvedString.split(",");
                        Map<Role, Short> crew = new HashMap<>();

                        for (String s1 : stringsForCrew) {
                            String[] role_count = s1.split(":");
                            Role role = Role.resolveRoleById(Integer.parseInt(role_count[0]));
                            Short count = Short.parseShort(role_count[1]);
                            crew.put(role, count);
                        }

                        Spaceship spaceship = spaceshipCriteria
                                .setCrew(crew)
                                .setFlightDistance(Long.valueOf(stringsForObject[1]))
                                .setReadyForNextMission(true)
                                .setName(stringsForObject[0])
                                .build();
                        spaceships.add(spaceship);
                        System.out.println(spaceship);
                    }
                }
            }
            spaceships.stream().forEach(System.out::println);
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        retrieveBaseEntityList(CrewMember.class);
        retrieveBaseEntityList(Spaceship.class);
        //   throw new InvalidStateException();
    }

    public List<String> readFromFile(String fileName, String index) {
        List<String> stringStreamFromFile = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.read() != -1) {
                stringStreamFromFile = bufferedReader
                        .lines()
                        .flatMap(s -> Stream.of(s.split(index)))
                        .map(String::valueOf)
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringStreamFromFile;
    }
}
