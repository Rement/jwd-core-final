package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private final String missionsName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long distance;
    private final Spaceship assignedSpaceShip;
    private final List<CrewMember> assignedCrew;
    private final MissionResult missionResult;

    private FlightMissionCriteria(Long id, String name, String missionsName, LocalDate startDate,
                                  LocalDate endDate, Long distance, Spaceship assignedSpaceShip,
                                  List<CrewMember> assignedCrew, MissionResult result) {
        super(id, name);
        this.missionsName = missionsName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShip = assignedSpaceShip;
        this.assignedCrew = assignedCrew;
        this.missionResult = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMissionCriteria criteria = (FlightMissionCriteria) o;
        return Objects.equals(missionsName, criteria.missionsName) &&
                Objects.equals(startDate, criteria.startDate) &&
                Objects.equals(endDate, criteria.endDate) &&
                Objects.equals(distance, criteria.distance) &&
                Objects.equals(assignedSpaceShip, criteria.assignedSpaceShip) &&
                Objects.equals(assignedCrew, criteria.assignedCrew) &&
                missionResult == criteria.missionResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionsName, startDate, endDate, distance, assignedSpaceShip, assignedCrew, missionResult);
    }

    @Override
    public String toString() {
        return "FlightMissionCriteria{" +
                "missionsName='" + missionsName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", assignedSpaceShip=" + assignedSpaceShip +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}';
    }

    public String getMissionsName() {
        return missionsName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public static FlightMissionCriteriaBuilder builder() {
        return new FlightMissionCriteriaBuilder();
    }

    public static class FlightMissionCriteriaBuilder extends Criteria.CriteriaBuilder<FlightMission> {
        private String missionsName;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long distance;
        private Spaceship assignedSpaceship;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;

        public FlightMissionCriteriaBuilder withMissionName(final String missionsName) {
            this.missionsName = missionsName;
            return this;
        }

        public FlightMissionCriteriaBuilder withFlightDistance(final Long flightDistance) {
            this.distance = flightDistance;
            return this;
        }

        public FlightMissionCriteriaBuilder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public FlightMissionCriteriaBuilder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public FlightMissionCriteriaBuilder withAssignedSpaceship(Spaceship spaceship) {
            this.assignedSpaceship = spaceship;
            return this;
        }

        public FlightMissionCriteriaBuilder withAssignedCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public FlightMissionCriteriaBuilder withMissionResult(final MissionResult result) {
            this.missionResult = result;
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(super.getId(), super.getName(), missionsName, startDate, endDate,
                    distance, assignedSpaceship, assignedCrew, missionResult);
        }
    }
}
