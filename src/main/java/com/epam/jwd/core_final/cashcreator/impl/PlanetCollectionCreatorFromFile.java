package com.epam.jwd.core_final.cashcreator.impl;

import com.epam.jwd.core_final.cashcreator.BaseEntityCollectionCreatorFromFile;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlanetCollectionCreatorFromFile implements BaseEntityCollectionCreatorFromFile {
    private Planet planet = null;
    private Collection<Planet> planetMap = new ArrayList<>();
    private static PlanetCollectionCreatorFromFile instance;

    private PlanetCollectionCreatorFromFile() {
    }

    public static PlanetCollectionCreatorFromFile getInstance() {
        if (instance == null) {
            instance = new PlanetCollectionCreatorFromFile();
        }
        return instance;
    }
    @Override
    public Collection<? extends AbstractBaseEntity> createFromFile(List<String> stringStreamFromFile) {
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
