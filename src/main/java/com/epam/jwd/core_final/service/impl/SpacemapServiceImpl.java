package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpacemapServiceImpl implements SpacemapService {
    private Collection<Planet> planets;
    private static Logger logger = new JwdLogger(SpacemapServiceImpl.class.getName(), "slf4j");
    private static SpacemapServiceImpl instance;

    private SpacemapServiceImpl() {
    }

    public static SpacemapServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpacemapServiceImpl();
        }
        return instance;
    }

    @Override
    public Planet getRandomPlanet() {
        planets = Main.getPlanets();
        Planet randomPlanet = planets.stream().findAny().orElse(new Planet());
        logger.log(Level.INFO, "random planet found: " + randomPlanet);
        return randomPlanet;
    }

    public List<Planet> findAllPlanets() {
        return (List<Planet>) Main.getPlanets();
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        try {
            Planet firstPlanet = Main.getPlanets().stream()
                    .filter(planet -> planet.getName().equals(first.getName())).findAny().orElseThrow(() ->new UnknownEntityException(first.getName()));
            Planet secondPlanet = Main.getPlanets().stream()
                    .filter(planet -> planet.getName().equals(second.getName())).findAny().orElseThrow(() -> new UnknownEntityException(second.getName()));
            Planet.Point firstPoint = firstPlanet.getPoint();
            Planet.Point secondPoint = secondPlanet.getPoint();
            int distance = (int) Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2) + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
            logger.log(Level.INFO, "the distance between the planets is calculated. the first planet: " + firstPlanet + ", the second planet: " + secondPlanet + ", distance: " + distance);
            return distance;
        }catch (UnknownEntityException entityException){
            entityException.getMessage();
            logger.log(Level.WARNING, "the planet wasn't found by name " + entityException);
        }
        return 0;
    }

}
