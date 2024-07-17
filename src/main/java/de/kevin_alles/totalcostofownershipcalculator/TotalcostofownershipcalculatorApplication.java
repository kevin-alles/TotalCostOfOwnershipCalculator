package de.kevin_alles.totalcostofownershipcalculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.kevin_alles.totalcostofownershipcalculator.configuration.ConfigurationHandler;
import de.kevin_alles.totalcostofownershipcalculator.logging.Logging;

@SpringBootApplication
public class TotalCostOfOwnershipCalculatorApplication {
	private Logging logging = Logging.getInstance();
	private ConfigurationHandler configurationHandler = ConfigurationHandler.getInstance();

	public static void main(String[] args) {
		SpringApplication.run(TotalCostOfOwnershipCalculatorApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			logging.info("TotalCostOfOwnershipCalculatorApplication started");

			logging.info("Initializing Configuration");
			logging.info("Title: " +
					configurationHandler.get("tcooc.application.title"));
			logging.info("Abbreviation: " +
					configurationHandler.get("tcooc.application.abbreviation"));

			logging.info("Initializing Database");

			logging.info("TotalCostOfOwnershipCalculatorApplication finished");
		};
	}

	public ConfigurationHandler getConfigurationHandler() {
		return configurationHandler;
	}

	public Logging getLogging() {
		return logging;
	}
}
