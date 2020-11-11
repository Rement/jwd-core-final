package com.epam.jwd.core_final.populator.impl;

import com.epam.jwd.core_final.populator.FileSourcePopulator;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static com.epam.jwd.core_final.domain.Role.resolveRoleById;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Short.parseShort;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.lines;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class SpaceshipFilePopulator implements FileSourcePopulator {
    private static SpaceshipFilePopulator instance;

    public static SpaceshipFilePopulator getInstance() {
        if (instance == null) {
            instance = new SpaceshipFilePopulator();
        }

        return instance;
    }

    private SpaceshipFilePopulator() { }

    @Override
    public Collection<? extends BaseEntity> readResources(String filepath) throws IOException {
        Path path = Path.of(filepath);
        List<String> entities;

        entities = lines(path, UTF_8)
                .filter(line -> line.charAt(0) != '#')
                .collect(toList());

        List<Spaceship> spaceships = new ArrayList<>();

        for (String info : entities) {
            String[] details = info.split(";");

            String crewDetails = details[2].substring(1, details[2].length() - 1);
            Map<Role, Short> crew = new LinkedHashMap<>();
            String[] entrances = crewDetails.split(",");
            for (String entrance : entrances) {
                String[] keyValue = entrance.split(":");
                crew.put(
                        resolveRoleById(parseInt(keyValue[0])), parseShort(keyValue[1])
                );
            }

            spaceships.add(new Spaceship(details[0], crew, parseLong(details[1])));
        }

        return spaceships;
    }
}
