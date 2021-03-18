package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.decorator.impl.PreProcessorImpl;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.reader.ReaderFromFile;
import com.epam.jwd.core_final.reader.impl.ReaderFromFileImpl;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.writer.BaseEntityWriter;
import com.epam.jwd.core_final.writer.impl.SpaceshipWriter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private Collection<Spaceship> spaceships;
    private ReaderFromFile readerFromFile = ReaderFromFileImpl.getInstance();
    private static Logger logger = new JwdLogger(SpaceshipServiceImpl.class.getName(), "slf4j");
    private PostProcessorImpl postProcessor = new PostProcessorImpl(new PreProcessorImpl(SpaceshipFactory.getInstance()));
    private BaseEntityWriter writer = SpaceshipWriter.getInstance();
    private static SpaceshipServiceImpl instance;

    private SpaceshipServiceImpl() {
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        spaceships = Main.getSpaceships();
        return (List<Spaceship>) spaceships;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceshipsByCriteria = new ArrayList<>();
        if (criteria != null) {
            spaceships = Main.getSpaceships();
            SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
            if (spaceshipCriteria.getFlightDistance() != null) {
                try {
                    spaceshipsByCriteria =
                            spaceships
                                    .stream()
                                    .filter(s -> s.getFlightDistance() > (spaceshipCriteria.getFlightDistance()))
                                    .collect(Collectors.toList());
                    logger.log(Level.INFO, "the spaceships was found successfully " + spaceshipsByCriteria);
                } catch (UnknownEntityException unknownEntityException) {
                    unknownEntityException.getMessage();
                    logger.log(Level.WARNING, "the spaceships wasn't found by name " + ((SpaceshipCriteria) criteria).getFlightDistance());
                }
            }
            if (spaceshipCriteria.getReadyForNextMissions() != null) {
                try {
                    spaceshipsByCriteria =
                            spaceships
                                    .stream()
                                    .filter(s -> s.getReadyForNextMissions().equals((spaceshipCriteria.getReadyForNextMissions())))
                                    .collect(Collectors.toList());
                    logger.log(Level.INFO, "the crewMember was found successfully " + spaceshipsByCriteria);
                } catch (UnknownEntityException unknownEntityException) {
                    unknownEntityException.getMessage();
                    logger.log(Level.WARNING, "the crewMember wasn't found by name " + (spaceshipCriteria.getReadyForNextMissions()));
                }
                return spaceshipsByCriteria;
            }
        }
        return spaceshipsByCriteria;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        spaceships = Main.getSpaceships();
        Optional<Spaceship> spaceship = null;
        try {
            spaceship = Optional.of(
                    spaceships
                            .stream()
                            .filter(c -> c.getName().equals(criteria.getName()))
                            .findFirst().orElseThrow(() -> new UnknownEntityException(criteria.getName())));
            logger.log(Level.INFO, "the crewMember was found successfully " + spaceship);
        } catch (UnknownEntityException unknownEntityException) {
            unknownEntityException.getMessage();
            logger.log(Level.WARNING, "the crewMember wasn't found by name " + criteria.getName());
        }
        return spaceship;
    }

    @Override
    public Spaceship deleteSpaceship(Spaceship spaceship) {
        List<String> stringFromFile = readerFromFile.readFromFile(spaceship.getClass());
        List<String> stringsToFile = stringFromFile.stream()
                .filter(s -> !s.contains(spaceship.getName()))
                .collect(Collectors.toList());
        writer.writeAllToDatabase(stringsToFile);
        return null;
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return (Spaceship) postProcessor.create(spaceship);
    }
}
