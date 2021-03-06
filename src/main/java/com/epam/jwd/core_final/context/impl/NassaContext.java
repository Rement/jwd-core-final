package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
//<<<<<<< HEAD
import com.epam.jwd.core_final.cashcreator.BaseEntityCollectionCreatorFromFile;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.reader.ReaderFromFile;
import com.epam.jwd.core_final.cashcreator.impl.CrewMemberCollectionCreatorFromFile;
import com.epam.jwd.core_final.cashcreator.impl.PlanetCollectionCreatorFromFile;
import com.epam.jwd.core_final.reader.impl.ReaderFromFileImpl;
import com.epam.jwd.core_final.cashcreator.impl.SpaceShipCollectionCreatorFromFile;
//=======
//>>>>>>> f660db45b7e6aa57495c014461025917330f9e1f
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.*;

// todo
public class NassaContext implements ApplicationContext {
    BaseEntityCollectionCreatorFromFile createFromFile = null;
    ReaderFromFile readerFromFile = ReaderFromFileImpl.getInstance();
    // no getters/setters for them
    private Collection<AbstractBaseEntity> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();


    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        List<String> stringStreamFromFile = null;
        Collection<T> collection = null;

        if (tClass.equals(CrewMember.class)) {
            stringStreamFromFile = readerFromFile.readFromFile(tClass);
            createFromFile = CrewMemberCollectionCreatorFromFile.getInstance();
            crewMembers = (Collection<AbstractBaseEntity>) createFromFile.createFromFile(stringStreamFromFile);
            crewMembers.stream().forEach(System.out::println);
            collection = (Collection<T>) crewMembers;
        }

        if (tClass.equals(Spaceship.class)) {
            stringStreamFromFile = readerFromFile.readFromFile(tClass);
            createFromFile = SpaceShipCollectionCreatorFromFile.getInstance();
            spaceships = (Collection<Spaceship>) createFromFile.createFromFile(stringStreamFromFile);
            spaceships.stream().forEach(System.out::println);
            collection = (Collection<T>) spaceships;
        }


        if (tClass.equals(Planet.class)) {
            stringStreamFromFile = readerFromFile.readFromFile(tClass);
            createFromFile = PlanetCollectionCreatorFromFile.getInstance();
            planetMap = (Collection<Planet>) createFromFile.createFromFile(stringStreamFromFile);
            planetMap.stream().forEach(System.out::println);
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
        retrieveBaseEntityList(CrewMember.class);
        retrieveBaseEntityList(Spaceship.class);
        retrieveBaseEntityList(Planet.class);
        //   throw new InvalidStateException();
    }
}
