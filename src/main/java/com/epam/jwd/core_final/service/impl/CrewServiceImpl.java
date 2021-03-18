package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.decorator.impl.PreProcessorImpl;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.reader.ReaderFromFile;
import com.epam.jwd.core_final.reader.impl.ReaderFromFileImpl;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.writer.BaseEntityWriter;
import com.epam.jwd.core_final.writer.impl.CrewMemberWriter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService<CrewMember> {
    private static CrewServiceImpl instance;
    private Collection<CrewMember> crewMembers;
    private ReaderFromFile readerFromFile = ReaderFromFileImpl.getInstance();
    private static Logger logger = new JwdLogger(CrewServiceImpl.class.getName(), "slf4j");
    private PostProcessorImpl postProcessor = new PostProcessorImpl(new PreProcessorImpl(CrewMemberFactory.getInstance()));
    private BaseEntityWriter writer = CrewMemberWriter.getInstance();

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
        crewMembers = Main.getCrewMembers();
        return (List<CrewMember>) crewMembers;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembersByCriteria = new ArrayList<>();
        if (criteria != null) {
            crewMembers = Main.getCrewMembers();
            CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;
            if (crewMemberCriteria.getRole() != null) {
                crewMembersByCriteria =
                        crewMembers
                                .stream()
                                .filter(c -> c.getRole().equals(crewMemberCriteria.getRole()))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the crewMember was found successfully " + crewMembersByCriteria);
            }
            if (crewMemberCriteria.getRank() != null) {
                crewMembersByCriteria =
                        crewMembers
                                .stream()
                                .filter(c -> c.getRank().equals((crewMemberCriteria.getRank())))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the crewMember was found successfully " + crewMembersByCriteria);

            }
            if (crewMemberCriteria.getReadyForNextMissions() != null) {
                crewMembersByCriteria =
                        crewMembers
                                .stream()
                                .filter(c -> c.getReadyForNextMissions().equals((crewMemberCriteria.getReadyForNextMissions())))
                                .collect(Collectors.toList());
                logger.log(Level.INFO, "the crewMember was found successfully " + crewMembersByCriteria);
            }
        }
        return crewMembersByCriteria;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        crewMembers = Main.getCrewMembers();
        Optional<CrewMember> crewMember = null;
        try {
            crewMember = Optional.of(
                    crewMembers
                            .stream()
                            .filter(c -> c.getName().equals(criteria.getName()))
                            .findFirst().orElseThrow(() -> new UnknownEntityException(criteria.getName())));
            logger.log(Level.INFO, "the crewMember was found successfully " + crewMember);
        } catch (UnknownEntityException unknownEntityException) {
            unknownEntityException.getMessage();
            logger.log(Level.WARNING, "the crewMember wasn't found by name " + criteria.getName());
        }
        return crewMember;
    }

    @Override
    public CrewMember deleteCrewMember(CrewMember crewMember) {
        List<String> stringFromFile = readerFromFile.readFromFile(crewMember.getClass());
        List<String> stringsToFile = stringFromFile.stream()
                .filter(s -> !s.contains(crewMember.getName()))
                .collect(Collectors.toList());
        writer.writeAllToDatabase(stringsToFile);
        return null;
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        return (CrewMember) postProcessor.create(crewMember);
    }
}
