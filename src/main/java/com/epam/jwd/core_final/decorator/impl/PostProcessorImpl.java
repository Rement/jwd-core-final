package com.epam.jwd.core_final.decorator.impl;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.decorator.PostProcessor;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.writer.WriterToFile;
import com.epam.jwd.core_final.writer.impl.WriteCrewMemberToFile;
import com.epam.jwd.core_final.writer.impl.WriteSpaceshipToFile;

public class PostProcessorImpl extends PostProcessor {
    BaseEntity baseEntity = null;
    WriterToFile writer;

    public PostProcessorImpl(BaseEntity baseEntity) {
        super(baseEntity);
    }

    @Override
    public BaseEntity create(Object... args) {
        this.baseEntity = super.create(args);
        Class baseEntityClass = baseEntity.getClass();
        if (baseEntityClass.equals(CrewMember.class)) {
            writer= WriteCrewMemberToFile.getInstance();
        }
        if (baseEntityClass.equals(Spaceship.class)){
            writer= WriteSpaceshipToFile.getInstance();
        }
        writer.writeToFile(baseEntity);
        return baseEntity;
    }
}
