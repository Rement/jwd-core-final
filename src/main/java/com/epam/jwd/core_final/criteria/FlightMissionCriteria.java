package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private Planet fromPlanet;
    private Planet toPlanet;
    private MissionResult missionResult;

    public FlightMissionCriteria(Builder builder) {
        super(builder);
    }

    public FlightMissionCriteria(){};

    public static FlightMissionCriteria.Builder newBuilder() {
        return new FlightMissionCriteria.Builder();
    }

    public static final class Builder extends Criteria.Builder<FlightMission> {
        private LocalDate startDate;
        private LocalDate endDate;
        private Long distance;
        private Spaceship assignedSpaceshift;
        private List<CrewMember> assignedCrew;
        private Planet fromPlanet;
        private Planet toPlanet;
        private MissionResult missionResult;

        public Builder() {
        }

        public FlightMissionCriteria.Builder setStartDate(LocalDate var) {
            startDate = var;
            return this;
        }

        public FlightMissionCriteria.Builder setEndDate(LocalDate var) {
            endDate = var;
            return this;
        }

        public FlightMissionCriteria.Builder setDistance(Long var) {
            distance = var;
            return this;
        }

        public FlightMissionCriteria.Builder setSpaceship(Spaceship var) {
            assignedSpaceshift = var;
            return this;
        }

        public FlightMissionCriteria.Builder setCrewList(List<CrewMember> var) {
            assignedCrew = var;
            return this;
        }

        public FlightMissionCriteria.Builder setFromPlanet(Planet var) {
            fromPlanet = var;
            return this;
        }

        public FlightMissionCriteria.Builder setToPlanet(Planet var) {
            toPlanet = var;
            return this;
        }

        public FlightMissionCriteria.Builder setMissionResult(MissionResult var) {
            missionResult = var;
            return this;
        }

        public FlightMission build() {
            FlightMission flightMission = new FlightMission();
            flightMission.setId(super.getId());
            flightMission.setName(super.getName());
            flightMission.setStartDate(startDate);
            flightMission.setEndDate(endDate);
            flightMission.setDistance(distance);
            flightMission.setAssignedSpaceShift(assignedSpaceshift);
            flightMission.setAssignedCrew(assignedCrew);
            flightMission.setFromPlanet(fromPlanet);
            flightMission.setToPlanet(toPlanet);
            flightMission.setMissionResult(missionResult);
            return flightMission;
        }

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(Planet toPlanet) {
        this.toPlanet = toPlanet;
    }
}
