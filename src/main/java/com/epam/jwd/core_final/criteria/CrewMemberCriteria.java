package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;
    private CrewMember crewMember;

    public CrewMemberCriteria(Builder builder) {
        super(builder);
    }

    public CrewMemberCriteria() {
    }

    public static Builder newBuilder() {
        return new CrewMemberCriteria.Builder();
    }

    public static final class Builder extends Criteria.Builder<CrewMember> {
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;


        public Builder() {
        }

        public Builder setRole(Role var) {
            role = var;
            return this;
        }

        public Builder setRank(Rank var) {
            rank = var;
            return this;
        }

        public Builder setReadyForNextMission(Boolean var) {
            isReadyForNextMissions = var;
            return this;
        }

        public CrewMember build() {
            CrewMember crewMember = new CrewMember();
            crewMember.setId(super.getId());
            crewMember.setName(super.getName());
            crewMember.setRole(role);
            crewMember.setRank(rank);
            crewMember.setReadyForNextMissions(isReadyForNextMissions);
            return crewMember;
        }
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }
}
