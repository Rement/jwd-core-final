package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private final Role role;
    private final Rank rank;
    private final boolean isReadyForNextMissions;

    private CrewMemberCriteria(Long id, String name, Role role, Rank rank, boolean isReadyForNextMissions) {
        super(id, name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMemberCriteria that = (CrewMemberCriteria) o;
        return isReadyForNextMissions == that.isReadyForNextMissions &&
                role == that.role &&
                rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, rank, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "CrewMemberCriteria{" +
                "role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class CrewMemberCriteriaBuilder extends Criteria.CriteriaBuilder<CrewMember> {
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public CrewMemberCriteriaBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder withRank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public CrewMemberCriteriaBuilder withIsReadyForNextMissions(boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(super.getId(), super.getName(), role, rank, isReadyForNextMissions);
        }
    }
}
