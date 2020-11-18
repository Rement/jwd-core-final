package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.CreateSpaceshipException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.strategy.ReadFromFileStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadSpaceshipsFromFileStrategy implements ReadFromFileStrategy {

    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Long id = 0L;

    @Override
    public void readFromFile(String path) throws FileNotFoundException {
        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        Map<String, Spaceship> tmpMap = new HashMap<>();
        Scanner scanner = new Scanner(new File(path));
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String delimitedBySemicolon = scanner.nextLine();
            String[] splitBySemicolon = delimitedBySemicolon.split(";");
            String name = splitBySemicolon[0];
            Long distance = Long.parseLong(splitBySemicolon[1]);
            String[] mapFields = splitBySemicolon[2].substring(1, splitBySemicolon[2].length() - 1).split(",");
            Map<Role, Short> crewMembers = new HashMap<>();
            for (String pairCounter : mapFields) {
                String[] roleCounter = pairCounter.split(":");
                crewMembers.put(Role.resolveRoleById(Integer.parseInt(roleCounter[0])), Short.parseShort(roleCounter[1]));
            }
            if (!tmpMap.containsKey(name)) {
                spaceships.add(spaceshipFactory.create(++id, name, distance, crewMembers));
                tmpMap.put(name, null);
            } else {
                try {
                    throw new CreateSpaceshipException("Spaceship with name " + name + " already exists!");
                } catch (CreateSpaceshipException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Collection<Spaceship> getSpaceships() {
        return spaceships;
    }
}
