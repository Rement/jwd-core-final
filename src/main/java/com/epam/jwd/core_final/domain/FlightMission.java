package com.epam.jwd.core_final.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private Long id;
    private String missionName;
    private LocalDateTime startOfMission;
    private LocalDateTime endOfMission;
    private Long distance;
    private Spaceship assignedSpaceship;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ApplicationProperties.DATE_TIME_FORMAT);

    public FlightMission(String missionName, LocalDateTime startOfMission, LocalDateTime endOfMission, Long distance) {
        this.missionName = missionName;
        this.startOfMission = startOfMission;
        this.endOfMission = endOfMission;
        this.distance = distance;
    }

    public LocalDateTime getStartOfMission() {
        return startOfMission;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEndOfMission() {
        return endOfMission;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getMissionName() {
        return missionName;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "ID = " + getId() +
                ", start of mission = " + getStartOfMission() +
                ", spaceship name = " + getAssignedSpaceship() +
                ", missionName = '" + getMissionName() + '\'' +
                ", distance = " + getDistance() +
                ", missionResult = " + getMissionResult() +
                '}';
    }
}
