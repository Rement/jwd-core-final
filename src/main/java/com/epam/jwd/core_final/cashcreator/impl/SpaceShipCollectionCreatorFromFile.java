package com.epam.jwd.core_final.cashcreator.impl;

import com.epam.jwd.core_final.cashcreator.BaseEntityCollectionCreatorFromFile;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.*;

public class SpaceShipCollectionCreatorFromFile implements BaseEntityCollectionCreatorFromFile {
    private Spaceship spaceship = null;
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private static SpaceShipCollectionCreatorFromFile instance;
    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();

    private SpaceShipCollectionCreatorFromFile() {
    }

    public static SpaceShipCollectionCreatorFromFile getInstance() {
        if (instance == null) {
            instance = new SpaceShipCollectionCreatorFromFile();
        }
        return instance;
    }

    @Override
    public Collection<? extends AbstractBaseEntity> createFromFile(List<String> stringStreamFromFile) {
        for (String s : stringStreamFromFile) {
            if (s.length() > 5) {
                String[] stringsForObject = s.split(";");
                if (!stringsForObject[0].equals("#name")) {
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

                    spaceship = spaceshipCriteria
                            .setCrew(crew)
                            .setFlightDistance(Long.valueOf(stringsForObject[1]))
                            .setReadyForNextMission(true)
                            .setName(stringsForObject[0])
                            .build();
                }
            }
            spaceships.add(spaceship);
        }
        return spaceships;
    }
}