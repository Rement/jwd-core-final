package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private String name;
    private Long flightDistance;
    private boolean isReadyForNextMission;

    protected SpaceshipCriteria(final SpaceshipCriteriaBuilder builder) {
        super();
        this.name = builder.name;
        this.flightDistance = builder.flightDistance;
    }

    public String getName() {
        return name;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public static class SpaceshipCriteriaBuilder extends Criteria.Builder<SpaceshipCriteria> {
        private String name;
        private Long flightDistance;
        private boolean isReadyForNextMission;

        @Override
        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }

        public SpaceshipCriteriaBuilder byName(String name) {
            this.name = name;
            return this;
        }

        public SpaceshipCriteriaBuilder byFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }
    }
}
