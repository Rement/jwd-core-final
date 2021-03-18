package com.epam.jwd.core_final.cashcreator.impl;

import com.epam.jwd.core_final.cashcreator.BaseEntityCashCreatorFromDatabase;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlanetCashCreatorFromDatabase implements BaseEntityCashCreatorFromDatabase {
    private Planet planet = null;
    private static PlanetCashCreatorFromDatabase instance;

    private PlanetCashCreatorFromDatabase() {
    }

    public static PlanetCashCreatorFromDatabase getInstance() {
        if (instance == null) {
            instance = new PlanetCashCreatorFromDatabase();
        }
        return instance;
    }

    @Override
    public Collection<? extends AbstractBaseEntity> createCashFromDatabase(List<String> stringStreamFromFile) {
        Collection<Planet> planetMap = new ArrayList<>();
        PlanetCriteria.Builder planetCriteria = PlanetCriteria.newBuilder();
        int matrixWidth = stringStreamFromFile.get(0).split(",").length;
        int matrixHeight = stringStreamFromFile.size();
        String[][] spaceMap = new String[matrixHeight][matrixWidth];
        for (int i = 0; i < matrixHeight; i++) {
            spaceMap[i] = stringStreamFromFile.get(i).split(",");
        }

        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < spaceMap[i].length; j++) {
                if (!spaceMap[i][j].equals("null")) {
                    Planet.Point point = new Planet.Point(i, j);
                    Planet planet = planetCriteria
                            .setPoint(point)
                            .setName(spaceMap[i][j])
                            .build();
                    planetMap.add(planet);
                }
            }
        }
        return planetMap;
    }
}
