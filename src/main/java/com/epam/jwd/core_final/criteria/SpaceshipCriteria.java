package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;
import java.util.Objects;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private final Map<Role, Short> crew;
    private final Long flightDistance;
    private final Boolean isReadyForNextMissions;

    private SpaceshipCriteria(Long id, String name, Map<Role, Short> crew, Long flightDistance, boolean isReadyForNextMissions) {
        super(id, name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceshipCriteria that = (SpaceshipCriteria) o;
        return Objects.equals(crew, that.crew) &&
                Objects.equals(flightDistance, that.flightDistance) &&
                Objects.equals(isReadyForNextMissions, that.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightDistance, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "SpaceshipCriteria{" +
                "crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static SpaceshipCriteriaBuilder builder() {
        return new SpaceshipCriteriaBuilder();
    }

    public static class SpaceshipCriteriaBuilder extends Criteria.CriteriaBuilder<Spaceship> {
        private Map<Role, Short> crew;
        private Long flightDistance;
        private boolean isReadyForNextMissions;

        public SpaceshipCriteriaBuilder withCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        public SpaceshipCriteriaBuilder withFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public SpaceshipCriteriaBuilder isReadyForNextMissions(boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(super.getId(), super.getName(), crew, flightDistance, isReadyForNextMissions);
        }
    }
}
