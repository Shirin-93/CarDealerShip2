package org.yearup.dataStorage;

import org.yearup.models.Contract;
import org.yearup.models.LeaseContract;
import org.yearup.models.SalesContract;
import org.yearup.models.Vehicle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContractFileManager
{
    public ArrayList<Contract>loadContracts (String filePath)
    {
        ArrayList<Contract> contracts = new ArrayList<>();
        try(FileReader reader = new FileReader(filePath);
            Scanner scanner = new Scanner(reader))
        {
            ArrayList<Vehicle> vehicleSold;
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] columns = line.split("\\|");
                String type = columns[0].toUpperCase();
                String date = columns[1];
                String name = columns[2];
                String vin = columns[3];
                int year = Integer.parseInt(columns[4]);
                String make = columns[5];
                String model = columns[6];
                String vehicleType = columns[7];
                String color = columns[8];
                int odometer = Integer.parseInt(columns[9]);
                double price = Double.parseDouble(columns[10]);
                switch(type)
                {
                    case "sales":
                        vehicleSold = new ArrayList<>();
                        Vehicle vehicleSales = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
                        vehicleSold.add(vehicleSales);

                        double recordingFee = Double.parseDouble(columns[11]);
                        double processingFee = Double.parseDouble(columns[12]);
                        double totalCostSales = Double.parseDouble(columns[13]);
                        boolean financeOptions = Boolean.parseBoolean(columns[14]);
                        double monthlyPayment = Double.parseDouble(columns[15]);
                        contracts.add(new SalesContract(type,date,name,vehicleSold,financeOptions));
                        break;
                    case "Lease":
                        vehicleSold = new ArrayList<>();
                        Vehicle vehicleLease = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
                        vehicleSold.add(vehicleLease);
                        double expectedEndValue = Double.parseDouble(columns[11]);
                        double leaseFee = Double.parseDouble(columns[12]);
                        double totalLeaseCost = Double.parseDouble(columns[13]);
                        double monthlyPaymentLease = Double.parseDouble(columns[14]);
                        contracts.add(new LeaseContract(type,date,name,vehicleSold));
                        break;
                }
            }
        }catch(IOException e)
        {
            throw new RuntimeException(e);

        }
        return contracts;
    }

}
