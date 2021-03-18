package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class CrewMemberFindAllUI implements ApplicationMenu {
    public String ANSI_GREEN = "\u001B[32m";
    public String ANSI_RESET = "\u001B[0m";
    private static CrewMemberFindAllUI instance;
    private CrewService crewService = CrewServiceImpl.getInstance();
    private List<CrewMember> crewMembers;
    private static Logger logger = new JwdLogger(CrewMemberFindAllUI.class.getName(), "slf4j");

    private CrewMemberFindAllUI() {
    }

    public static CrewMemberFindAllUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberFindAllUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN +"wait a minute. we're looking for everyone."+ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        crewMembers = crewService.findAllCrewMembers();
        crewMembers.stream().forEach(System.out::println);
        return null;
    }
}
