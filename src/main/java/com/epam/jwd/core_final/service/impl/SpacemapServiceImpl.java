package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

public class SpacemapServiceImpl implements SpacemapService {
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
        return null;
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return 0;
    }
}
