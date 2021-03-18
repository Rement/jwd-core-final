package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrewMemberFindByCriteriaUI implements ApplicationMenu {
    public String ANSI_GREEN = "\u001B[32m";
    public String ANSI_RESET = "\u001B[0m";
    private static CrewMemberFindByCriteriaUI instance;
    private CrewService crewService = CrewServiceImpl.getInstance();
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private Criteria<CrewMember> criteria;
    private final Scanner scanner = new Scanner(System.in);
    private static Logger logger = new JwdLogger(CrewMemberFindByCriteriaUI.class.getName(), "slf4j");

    private CrewMemberFindByCriteriaUI() {
    }

    public static CrewMemberFindByCriteriaUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberFindByCriteriaUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN + "write name" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        if (scanner.hasNext()) {
            Object stringFromConsole = scanner.nextLine();
            crewMemberCriteria.setName((String) stringFromConsole).build();
            criteria = new CrewMemberCriteria(crewMemberCriteria);
            logger.log(Level.INFO, "the user entered a name ");
            Optional crewMember = crewService.findCrewMemberByCriteria(criteria);
            System.out.println(crewMember);
        }
        return null;
    }
}
