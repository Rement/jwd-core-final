package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;

public class CrewServiceImpl implements CrewService<CrewMember> {
    CrewMemberFactory factory = CrewMemberFactory.getInstance();
    private static CrewServiceImpl instance;
    PostProcessorImpl postProcessor;

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {


        return null;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {

        return null;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        Optional<CrewMember> crewMember= Optional.of(CrewMemberCriteria.newBuilder().build());
        return Optional.empty();
    }

    @Override
    public CrewMember deleteCrewMember(CrewMember crewMember) {
        return null;
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        postProcessor = new PostProcessorImpl(factory.create(crewMember));
        postProcessor.create(factory.create(crewMember));
        return null;
    }
}
