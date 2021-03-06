package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria <Planet>{
    private Planet.Point point;
    private String name;

    protected PlanetCriteria(Builder builder) {
        super(builder);
    }

    public static PlanetCriteria.Builder newBuilder() {
        return new PlanetCriteria.Builder();
    }
    public static final class Builder extends Criteria.Builder<Planet> {
        private Planet.Point point;

        public Builder() {
        }

        public PlanetCriteria.Builder setPoint(Planet.Point var) {
            point = var;
            return this;
        }

        public Planet build() {
            Planet planet = new Planet();
            planet.setId(super.getId());
            planet.setName(super.getName());
            planet.setPoint(point);
            return planet;
        }

    }
}
