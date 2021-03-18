package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
//<<<<<<< HEAD
import com.epam.jwd.core_final.cashcreator.BaseEntityCashCreatorFromDatabase;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.reader.ReaderFromFile;
import com.epam.jwd.core_final.cashcreator.impl.CrewMemberCashCreatorFromDatabase;
import com.epam.jwd.core_final.cashcreator.impl.PlanetCashCreatorFromDatabase;
import com.epam.jwd.core_final.reader.impl.ReaderFromFileImpl;
import com.epam.jwd.core_final.cashcreator.impl.SpaceShipCashCreatorFromDatabase;
//=======
//>>>>>>> f660db45b7e6aa57495c014461025917330f9e1f
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// todo
public class NassaContext implements ApplicationContext {
    BaseEntityCashCreatorFromDatabase createFromFile = null;
    ReaderFromFile readerFromFile = ReaderFromFileImpl.getInstance();
    private static Logger logger = new JwdLogger(NassaContext.class.getName(), "slf4j");
    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();


    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        List<String> stringStreamFromFile = null;
        Collection<T> collection = null;

        stringStreamFromFile = readerFromFile.readFromFile(tClass);

        if (tClass.equals(CrewMember.class)) {
            createFromFile = CrewMemberCashCreatorFromDatabase.getInstance();
            crewMembers = (Collection<CrewMember>) createFromFile.createCashFromDatabase(stringStreamFromFile);
            logger.log(Level.INFO, crewMembers.toString());
            collection = (Collection<T>) crewMembers;
        }

        if (tClass.equals(Spaceship.class)) {
            createFromFile = SpaceShipCashCreatorFromDatabase.getInstance();
            spaceships = (Collection<Spaceship>) createFromFile.createCashFromDatabase(stringStreamFromFile);
            logger.log(Level.INFO, spaceships.toString());
            collection = (Collection<T>) spaceships;
        }


        if (tClass.equals(Planet.class)) {
            createFromFile = PlanetCashCreatorFromDatabase.getInstance();
            planetMap = (Collection<Planet>) createFromFile.createCashFromDatabase(stringStreamFromFile);
            logger.log(Level.INFO, planetMap.toString());
            collection = (Collection<T>) planetMap;
        }
        return collection;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        crewMembers = retrieveBaseEntityList(CrewMember.class);
        spaceships = retrieveBaseEntityList(Spaceship.class);
        planetMap = retrieveBaseEntityList(Planet.class);
        if (crewMembers.isEmpty()) {
            throw new InvalidStateException(CrewMember.class.getName());
        }
        if (spaceships.isEmpty()) {
            throw new InvalidStateException(Spaceship.class.getName());
        }
        if (planetMap.isEmpty()) {
            throw new InvalidStateException(Planet.class.getName());
        }
    }
}
