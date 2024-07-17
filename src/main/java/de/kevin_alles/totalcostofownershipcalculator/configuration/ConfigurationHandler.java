package de.kevin_alles.totalcostofownershipcalculator.configuration;

import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationHandler {
    private final String CONFIGURATION_FILE = "application.properties";
    private final String CONFIGURATION_FILE_PATH = "src/main/resources/" + CONFIGURATION_FILE;

    private final HashMap<String, String> DEFAULT_CONFIGURATION = new HashMap<>() {
        {
            // MainApplication
            put("tcooc.application.title", "TotalCostOfOwnershipCalculator");
            put("tcooc.application.abbreviation", "TCOOC");

            // Values
            put("tcooc.values.diesel.costPerLiter", "1.8");
            put("tcooc.values.petrol.costPerLiter", "2");
            put("tcooc.values.diesel.consumption", "5.5");
            put("tcooc.values.petrol.consumption", "7.5");

            // Logging

            // Configuration

            // Database

        }
    };

    private Properties properties = new Properties();

    private static ConfigurationHandler instance = new ConfigurationHandler();

    private ConfigurationHandler() {
        // Check if configuration file exists
        File configFile = new File(CONFIGURATION_FILE_PATH);

        if (!configFile.exists()) {
            try {
                // Create the configuration file
                configFile.createNewFile();

                FileWriter writer = new FileWriter(configFile, true);

                // Write the default configuration to the file
                for (String key : DEFAULT_CONFIGURATION.keySet()) {
                    String value = DEFAULT_CONFIGURATION.get(key);
                    // Write key-value pair to the file
                    writer.write(key + "=" + value + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIGURATION_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationHandler getInstance() {
        return instance;
    }

    public String get(String key) {
        if (!properties.containsKey(key)) {
            set(key, DEFAULT_CONFIGURATION.get(key));
        }
        return properties.getProperty(key);
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }
}