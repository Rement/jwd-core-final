package com.epam.jwd.core_final.domain;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public CrewMember(final String name, final Role role, final Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
    }

    public CrewMember(final String name, final Role role, final Rank rank, final boolean isReadyForNextMissions) {
        super(name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return role == that.role &&
                rank == that.rank &&
                Objects.equals(isReadyForNextMissions, that.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, rank, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        String isReadyMsg = isReadyForNextMissions == null
                ? "Didn't participate in any mission"
                : isReadyForNextMissions().toString();

        return "CrewMember{"
                + "name=" + super.getName()
                + ", role=" + role
                + ", rank=" + rank
                + ", isReadyForNextMissions="
                + isReadyMsg
                + '}';
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }
}
