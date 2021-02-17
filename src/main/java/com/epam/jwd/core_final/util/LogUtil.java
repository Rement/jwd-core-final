package com.epam.jwd.core_final.util;

import org.slf4j.Logger;

public final class LogUtil {
    private LogUtil() { }

    public static void logInfo(Logger logger, String msg, Object... args) {
        logger.info(msg, args);
    }

    public static void logDebug(Logger logger, String msg, Object... args) {
        logger.debug(msg, args);
    }

    public static void logError(Logger logger, String msg, Object... args) {
        logger.error(msg, args);
    }

    public static void logError(Logger logger, String msg, Throwable e) {
        logger.error(msg, e);
    }
}
