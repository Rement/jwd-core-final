package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.ui.StartUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Collection<CrewMember> crewMembers = null;
    private static Collection<Spaceship> spaceships = null;
    private static Collection<FlightMission> flightMissions = new ArrayList<>();
    private static Collection<Planet> planets = null;
    private static Logger logger = new JwdLogger(Main.class.getName(), "slf4j");
    private static ApplicationProperties applicationProperties = new ApplicationProperties();

    public static void main(String[] args) {
        ApplicationMenu applicationMenu = Application.start();
        logger.log(Level.INFO, "initialization was successful");
        ApplicationContext applicationContext = applicationMenu.getApplicationContext();
        Thread ui = new StartUI();
        ui.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (ui.isAlive()) {
                    i++;
                    logger.log(Level.INFO, "updating the cache number " + i);
                    crewMembers = applicationContext.retrieveBaseEntityList(CrewMember.class);
                    spaceships = applicationContext.retrieveBaseEntityList(Spaceship.class);
                    planets = applicationContext.retrieveBaseEntityList(Planet.class);
                    synchronized (currentThread()) {
                        try {
                            wait(applicationProperties.getFileRefreshRate());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    public static Collection<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public static Collection<Spaceship> getSpaceships() {
        return spaceships;
    }

    public static Collection<Planet> getPlanets(){return planets;}

    public static Collection<FlightMission> getFlightMissions(){return flightMissions;}

    public static void setFlightMissions(Collection<FlightMission> flightMissions) {
        Main.flightMissions = flightMissions;
    }
}