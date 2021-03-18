package com.epam.jwd.core_final.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class JwdLogger extends Logger {
    private FileHandler fh;

    public JwdLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
        try {
            fh = new FileHandler("src/main/resources/logs/log" + name, true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            this.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setUseParentHandlers(false);
    }
}
