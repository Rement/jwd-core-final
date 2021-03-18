package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;

    public SpaceshipCriteria(Builder builder) {
        super(builder);
    }

    public SpaceshipCriteria() {

    }

    public static SpaceshipCriteria.Builder newBuilder() {
        return new SpaceshipCriteria.Builder();
    }

    public static final class Builder extends Criteria.Builder<Spaceship> {
        private Map<Role, Short> crew;
        private Long flightDistance;
        private Boolean isReadyForNextMissions;

        public Builder() {
        }

        public SpaceshipCriteria.Builder setCrew(Map var) {
            crew = var;
            return this;
        }

        public SpaceshipCriteria.Builder setFlightDistance(Long var) {
            flightDistance = var;
            return this;
        }

        public SpaceshipCriteria.Builder setReadyForNextMission(Boolean var) {
            isReadyForNextMissions = var;
            return this;
        }

        public Spaceship build() {
            Spaceship spaceship = new Spaceship();
            spaceship.setId(super.getId());
            spaceship.setName(super.getName());
            spaceship.setCrew(crew);
            spaceship.setFlightDistance(flightDistance);
            spaceship.setReadyForNextMissions(isReadyForNextMissions);
            return spaceship;
        }

    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }
}
