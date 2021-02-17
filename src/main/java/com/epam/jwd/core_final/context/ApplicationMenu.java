package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.jwd.core_final.util.InputUtil.readMission;
import static com.epam.jwd.core_final.util.InputUtil.readMissionCriteria;
import static com.epam.jwd.core_final.util.LogUtil.logDebug;
import static com.epam.jwd.core_final.util.LogUtil.logError;
import static com.epam.jwd.core_final.util.PrintUtil.printCollectionToConsole;
import static com.epam.jwd.core_final.util.PrintUtil.printMsgToConsole;

// todo replace Object with your own types
public interface ApplicationMenu {
    Logger LOGGER = LoggerFactory.getLogger(ApplicationMenu.class);

    ApplicationContext getApplicationContext();

    void createMission(FlightMission mission);

    void writeAllMissionsToJson();

    void updateMission(FlightMission mission, FlightMissionCriteria criteria);

    default String printAvailableOptions() {
        return "\nAvailable options:\n"
                + "1: Get info about crew\n"
                + "2: Get info about spaceships\n"
                + "3: Create mission\n"
                + "4: Update mission\n"
                + "5: Select missions to write in output in json format\n"
                + "6: Exit.\n";
    }

    default void handleUserInput(int input) {
        printMsgToConsole(this.printAvailableOptions());
        try {


            switch (input) {
                case 1: // crew
                    logDebug(LOGGER, "User chose to get info about crew!");
                    printMsgToConsole("Info about crew:");
                    printCollectionToConsole(getApplicationContext().retrieveBaseEntityList(CrewMember.class));
                    break;
                case 2:
                    logDebug(LOGGER, "User chose to get info about spaceships!");
                    printMsgToConsole("Info about spaceships:");
                    printCollectionToConsole(getApplicationContext().retrieveBaseEntityList(Spaceship.class));
                    break;
                case 3:
                    logDebug(LOGGER, "User chose to create mission!");
                    FlightMission mission = readMission("Enter mission to create (assign spaceship and crew later)");
                    createMission(mission);
                    break;
                case 4:
                    logDebug(LOGGER, "User chose to update mission!");
                    FlightMissionCriteria criteria = readMissionCriteria("Enter mission criteria: ");
                    FlightMission mission1 = readMission("Enter new data for this mission: ");
                    updateMission(mission1, criteria);
                    break;
                case 5:
                    logDebug(LOGGER, "User chose to write missions in json format!");
                    writeAllMissionsToJson();
                    break;
                case 6:
                    logDebug(LOGGER, "User chose to exit!");
                    System.exit(0);
                default:
                    printMsgToConsole("Could not recognize input!");
                    break;
            }
        } catch (Throwable e) {
            logError(LOGGER, "Error occured!", e);
            this.printAvailableOptions();
        }
    }
}
