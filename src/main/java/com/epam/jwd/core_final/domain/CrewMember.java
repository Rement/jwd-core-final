package com.epam.jwd.core_final.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    private Long id;
    private Role role;
    private String name;
    private Rank rank;
    private boolean isReadyForNextMission;


    public CrewMember(Long id, Role role, String name, Rank rank) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.rank = rank;
        this.isReadyForNextMission = true;
    }

    @Override
    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setReadyForNextMission(boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                " ID " + getId() +
                " role = " + getRole() +
                ", name = '" + getName() + '\'' +
                ", rank = " + getRank() +
                ", isReadyForNextMission = " + isReadyForNextMission() +
                '}';
    }
}
