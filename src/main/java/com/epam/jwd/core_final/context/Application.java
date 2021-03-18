package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::new; // todo
        final NassaContext nassaContext = new NassaContext();
        try {
            nassaContext.init();
        } catch (InvalidStateException e) {
            e.getMessage();
        }
        return applicationContextSupplier::get;
    }
}
