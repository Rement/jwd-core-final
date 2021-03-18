package com.epam.jwd.core_final.decorator.impl;

import com.epam.jwd.core_final.decorator.BaseEntityDecorator;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.decorator.PostProcessor;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.writer.BaseEntityWriter;
import com.epam.jwd.core_final.writer.impl.CrewMemberWriter;
import com.epam.jwd.core_final.writer.impl.FlightMissionWriter;
import com.epam.jwd.core_final.writer.impl.SpaceshipWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PostProcessorImpl extends BaseEntityDecorator implements PostProcessor {
    private static Logger logger = new JwdLogger(PostProcessorImpl.class.getName(), "slf4j");
    BaseEntity baseEntity = null;
    BaseEntityWriter writer;

    public PostProcessorImpl(EntityFactory factory) {
        super(factory);
    }

    @Override
    public BaseEntity create(Object... args) {
        this.baseEntity = super.create(args);
        postProcess();
        return baseEntity;
    }

    public void postProcess() {
        if (baseEntity != null) {
            Class baseEntityClass = baseEntity.getClass();
            if (baseEntityClass.equals(CrewMember.class)) {
                writer = CrewMemberWriter.getInstance();
            }
            if (baseEntityClass.equals(Spaceship.class)) {
                writer = SpaceshipWriter.getInstance();
            }
            if (baseEntityClass.equals(FlightMission.class)) {
                writer = FlightMissionWriter.getInstance();
            }
            writer.writeOneEntityToDatabase(baseEntity);
            logger.log(Level.INFO, "the entity was written to the file successfully: " + baseEntity);
        }
    }
}
