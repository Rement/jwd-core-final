package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.DefaultMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.scheduledjob.FileRefreshJob;

import static com.epam.jwd.core_final.util.InputUtil.readInt;

public interface Application {

    static void start() throws InvalidStateException {
        ApplicationMenu menu = new DefaultMenu();
        FileRefreshJob.getInstance().perform();
        while (true) {
            menu.handleUserInput(
                    readInt(menu.printAvailableOptions() + "\nEnter option: ")
            );
        }
    }
}
