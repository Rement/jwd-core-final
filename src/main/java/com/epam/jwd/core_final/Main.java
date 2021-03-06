package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.ui.StartUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) throws InvalidStateException {
        ApplicationMenu applicationMenu = Application.start();
        logger.log(Level.INFO, "this is from Main");

        StartUI startUI = new StartUI();
        startUI.printAvailableOptions();
        startUI.handleUserInput(applicationMenu);
    }
}