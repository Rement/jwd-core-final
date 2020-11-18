package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private String missionName;
    private LocalDateTime startOfMission;
    private LocalDateTime endOfMission;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    protected FlightMissionCriteria(final FlightMissionCriteriaBuilder builder) {
        super();
        this.missionName = builder.missionName;
        this.distance = builder.distance;
    }

    public String getMissionName() {
        return missionName;
    }

    public Long getDistance() {
        return distance;
    }

    public static class FlightMissionCriteriaBuilder extends Criteria.Builder<FlightMissionCriteria> {
        private String missionName;
        private LocalDateTime startOfMission;
        private LocalDateTime endOfMission;
        private Long distance;
        private Spaceship assignedSpaceShift;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;

        @Override
        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(this);
        }

        public FlightMissionCriteriaBuilder byName(String name) {
            this.missionName = name;
            return this;
        }

        public FlightMissionCriteriaBuilder byDistance(Long distance) {
            this.distance = distance;
            return this;
        }
    }
}
