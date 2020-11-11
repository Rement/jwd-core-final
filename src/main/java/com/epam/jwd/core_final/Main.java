package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.impl.ApplicationMenuImpl;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws InvalidStateException, FileNotFoundException {
        PropertyReaderUtil.loadProperties();
        NassaContext.getInstance().init();
        ApplicationMenuImpl applicationMenu = new ApplicationMenuImpl();
        applicationMenu.handleUserInput();
   }
}