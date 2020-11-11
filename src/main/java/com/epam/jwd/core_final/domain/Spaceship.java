package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    private Long id;
    private String name;
    private Long flightDistance;
    private Map<Role, Short> crew;
    private boolean isReadyForNextMission;

    public Spaceship(Long id, String name, Long flightDistance, Map<Role, Short> crew) {
        this.id = id;
        this.name = name;
        this.flightDistance = flightDistance;
        this.crew = crew;
        this.isReadyForNextMission = true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReadyForNextMission(boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "ID = " + getId() +
                " name = '" + getName() + '\'' +
                ", flightDistance = " + getFlightDistance() +
                ", crew = " + getCrew() +
                ", isReadyForNextMission = " + isReadyForNextMission() +
                '}';
    }
}
