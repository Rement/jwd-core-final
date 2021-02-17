package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    private String missionsName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(String missionsName, LocalDate startDate, LocalDate endDate, Long distance,
                         Spaceship assignedSpaceShip, List<CrewMember> assignedCrew) {
        super(missionsName);
        this.missionsName = missionsName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShip = assignedSpaceShip;
        this.assignedCrew = assignedCrew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMission mission = (FlightMission) o;
        return Objects.equals(missionsName, mission.missionsName) &&
                Objects.equals(startDate, mission.startDate) &&
                Objects.equals(endDate, mission.endDate) &&
                Objects.equals(distance, mission.distance) &&
                Objects.equals(assignedSpaceShip, mission.assignedSpaceShip) &&
                Objects.equals(assignedCrew, mission.assignedCrew) &&
                missionResult == mission.missionResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionsName, startDate, endDate, distance, assignedSpaceShip, assignedCrew, missionResult);
    }

    @Override
    public String toString() {
        return "FlightMission{" +
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

    public void setMissionsName(String missionsName) {
        this.missionsName = missionsName;
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

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }
}
