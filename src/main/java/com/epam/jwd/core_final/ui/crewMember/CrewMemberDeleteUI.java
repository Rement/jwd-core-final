package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrewMemberDeleteUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static CrewMemberDeleteUI instance;
    private CrewService crewService = CrewServiceImpl.getInstance();
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private static Logger logger = new JwdLogger(CrewMemberDeleteUI.class.getName(), "slf4j");
    private final Scanner scanner = new Scanner(System.in);

    private CrewMemberDeleteUI() {
    }

    public static CrewMemberDeleteUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberDeleteUI();
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
            String name = scanner.nextLine();
            CrewMember crewMember = crewMemberCriteria
                    .setName(name)
                    .build();
            crewService.deleteCrewMember(crewMember);
            logger.log(Level.INFO, "crewMember with name " + name + " tried to delete ");
        }
        return null;
    }
}
