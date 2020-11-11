package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.populator.FileSourcePopulator;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.populator.impl.CrewMemberFilePopulator;
import com.epam.jwd.core_final.populator.impl.SpaceshipFilePopulator;
import com.epam.jwd.core_final.util.InitEnvironmentUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.epam.jwd.core_final.util.PropertyReaderUtil.loadProperties;
import static java.util.Collections.emptyList;

// todo
public class NassaContext implements ApplicationContext {
    private static NassaContext nassaContext;
    private static ApplicationProperties properties;
    private FileSourcePopulator fileSourcePopulator;

    static {
        properties = loadProperties();
        String prefix = "src/main/resources/";
        InitEnvironmentUtil.init(prefix, properties);
    }

    public static NassaContext getInstance() {
        if (nassaContext == null) {
            nassaContext = new NassaContext();
        }

        return nassaContext;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();

    private NassaContext() {
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class) && crewMembers != null) {
            return (Collection<T>) crewMembers;
        }

        if (tClass.equals(FlightMission.class) && flightMissions != null) {
            return (Collection<T>) flightMissions;
        }

        if (tClass.equals(Spaceship.class) && spaceships != null) {
            return (Collection<T>) spaceships;
        }

        return emptyList();
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        try {
            readResourcesFrom("src/main/resources/" + properties.getInputRootDir() + "/" + properties.getCrewFileName());
            readResourcesFrom("src/main/resources/" + properties.getInputRootDir() + "/" + properties.getSpaceshipsFileName());
        } catch (IOException e) {
            throw new InvalidStateException(e);
        }
    }

    private void readResourcesFrom(String filepath) throws IOException {
        if (filepath.contains("crew")) {
            fileSourcePopulator = CrewMemberFilePopulator.getInstance();
            crewMembers = new ArrayList<>((Collection<? extends CrewMember>) fileSourcePopulator.readResources(filepath));
        }

        if (filepath.contains("spaceships")) {
            fileSourcePopulator = SpaceshipFilePopulator.getInstance();
            spaceships = new ArrayList<>((Collection<? extends Spaceship>) fileSourcePopulator.readResources(filepath));
        }
    }

    @Override
    public ApplicationProperties getApplicationProperties() {
        return properties;
    }
}
