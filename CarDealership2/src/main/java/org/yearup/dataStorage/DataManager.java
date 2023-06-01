package org.yearup.dataStorage;

import org.yearup.models.Contract;
import org.yearup.models.LeaseContract;
import org.yearup.models.SalesContract;
import org.yearup.models.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataManager {
    private final ArrayList<Contract> contracts;

    public DataManager() {
        ContractFileManager fileManager = new ContractFileManager();
        contracts = fileManager.loadContracts("Dealership.csv");
    }

    public void run() {
        for (Contract contract : contracts) {
            display(contract);
        }
    }

    public void saveContract(Contract contract) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Dealership.csv", true))) {
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                writer.printf("%s|%s|%s", contract.getType(), contract.getDate(), contract.getName());

                for (Vehicle vehicle : salesContract.getVehicleSold()) {
                    writer.printf("%s|%d|%s|%s|%s|%s|%d|%.2f\n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                            vehicle.getType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());

                }
            } else if (contract instanceof LeaseContract)
            {
                LeaseContract leaseContract = (LeaseContract) contract;
                writer.printf("%s|%s|%s", contract.getType(), contract.getDate(), contract.getName());
                for (Vehicle vehicle : leaseContract.getVehicleSold()) {
                    writer.printf("%s|%d|%s|%s|%s|%s|%d|%.2f\n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                            vehicle.getType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public void display(Contract contract) {
        System.out.println();
    }
}
