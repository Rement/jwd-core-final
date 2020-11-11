package com.epam.jwd.core_final.populator.impl;

import com.epam.jwd.core_final.populator.FileSourcePopulator;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.epam.jwd.core_final.domain.Rank.resolveRankById;
import static com.epam.jwd.core_final.domain.Role.resolveRoleById;
import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.lines;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class CrewMemberFilePopulator implements FileSourcePopulator {
    private static CrewMemberFilePopulator instance;

    public static CrewMemberFilePopulator getInstance() {
        if (instance == null) {
            instance = new CrewMemberFilePopulator();
        }
        return instance;
    }

    private CrewMemberFilePopulator() { }


    @Override
    public Collection<? extends BaseEntity> readResources(String filepath) throws IOException {
        Path path = Path.of(filepath);
        List<String> entities;

        entities = lines(path, UTF_8)
                .filter(line -> line.charAt(0) != '#')
                .map(str -> asList(str.split(";")))
                .collect(toList())
                .get(0);

        List<CrewMember> crewMembers = new ArrayList<>();

        for (String info : entities) {
            List<String> details = asList(info.split(","));
            crewMembers.add(
                    new CrewMember(details.get(1), resolveRoleById(parseInt(details.get(0))), resolveRankById(parseInt(details.get(2))))
            );
        }

        return crewMembers;
    }
}
