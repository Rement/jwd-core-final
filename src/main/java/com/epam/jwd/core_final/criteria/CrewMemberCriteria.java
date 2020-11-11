package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private Role role;
    private String name;
    private Rank rank;
    private boolean isReadyForNextMission;

    protected CrewMemberCriteria(final CrewMemberCriteriaBuilder builder) {
        super();
        this.name = builder.name;
        this.role = builder.role;
        this.rank = builder.rank;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public static class CrewMemberCriteriaBuilder extends Criteria.Builder<CrewMemberCriteria> {

        private Role role;
        private String name;
        private Rank rank;
        private boolean isReadyForNextMission;

        @Override
        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this);
        }

        public CrewMemberCriteriaBuilder byName(String name) {
            this.name = name;
            return this;
        }

        public CrewMemberCriteriaBuilder byRole(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder byRank(Rank rank) {
            this.rank = rank;
            return this;
        }
    }
}
