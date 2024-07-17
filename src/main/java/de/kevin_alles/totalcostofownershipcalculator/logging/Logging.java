package de.kevin_alles.totalcostofownershipcalculator.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {
    private static final Logger logger = LogManager.getLogger("TCOOC-Logger");
    private static final Logging instance = new Logging();

    private Logging() {
    }

    public static Logging getInstance() {
        return instance;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void trace(String message) {
        logger.trace(message);
    }

    public void fatal(String message) {
        logger.fatal(message);
    }

}
