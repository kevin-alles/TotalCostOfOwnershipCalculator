package de.kevin_alles.totalcostofownershipcalculator.logic;

import de.kevin_alles.totalcostofownershipcalculator.TotalCostOfOwnershipCalculatorApplication;
import de.kevin_alles.totalcostofownershipcalculator.configuration.ConfigurationHandler;

public class TotalCost {
    private final double dieselCostPerLiter;
    private final double petrolCostPerLiter;
    private final double dieselConsumption;
    private final double petrilConsumption;

    private String name;
    private boolean isDiesel = false;
    private double leasingCostPerMonth;
    private double maintenanceCostPerMonth;
    private double wearCostPerMonth;
    private double insuranceCostPerYear;
    private double mileagePerYear;
    private double durationInMonths;

    private double totalCost;

    public TotalCost(TotalCostOfOwnershipCalculatorApplication main, String name, boolean isDiesel, double leasingCostPerMonth, double maintenanceCostPerMonth, double wearCostPerMonth, double insuranceCostPerYear, double mileagePerYear, double durationInMonths) {
        ConfigurationHandler configurationHandler = main.getConfigurationHandler();

        dieselCostPerLiter = Double.parseDouble(configurationHandler.get("tcooc.values.diesel.costPerLiter"));
        petrolCostPerLiter = Double.parseDouble(configurationHandler.get("tcooc.values.petrol.costPerLiter"));
        dieselConsumption = Double.parseDouble(configurationHandler.get("tcooc.values.diesel.consumption"));
        petrilConsumption = Double.parseDouble(configurationHandler.get("tcooc.values.petrol.consumption"));

        this.totalCost = calculateTotalCost();
    }

    private Double calculateCostPerMonth() {
        return leasingCostPerMonth + maintenanceCostPerMonth + wearCostPerMonth;
    }

    private Double calculateCostPerYear() {
        return insuranceCostPerYear + (calculateCostPerMonth() * 12) + calculateFuelCostPerYear();
    }

    private Double calculateFuelCostPerYear() {
        return (isDiesel ? dieselConsumption : petrilConsumption) * (isDiesel ? dieselCostPerLiter : petrolCostPerLiter) * (mileagePerYear / 100);
    }

    private Double calculateTotalCost() {
        return calculateCostPerYear() * (Double)(durationInMonths / 12);
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString(){
        return "Total cost of ownership for " + name + " is " + totalCost + "€";
    }
}