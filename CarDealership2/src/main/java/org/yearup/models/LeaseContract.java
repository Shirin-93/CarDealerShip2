package org.yearup.models;

import java.util.ArrayList;
import org.yearup.models.Vehicle;

public class LeaseContract extends Contract
{
    private static final double expectedEndValue = 0.5;//50% of original price
    private static final double leaseFee = 0.7;//7% of original price

    public LeaseContract(String type, String date, String name, ArrayList<Vehicle> vehicleSold) {
        super(type, date, name, vehicleSold);
    }
    public double getExpectedEndValue()
    {
        return expectedEndValue;
    }
    public double getLeaseFee()
    {
        return getLeaseFee();
    }


    @Override
    public double getTotalPrice()
    {
        double totalPrice = 0;

        for(Vehicle vehicle = getVehicleSold())
        {
            totalPrice += vehicle.getPrice();
        }
        totalPrice -=(totalPrice * getExpectedEndValue());
        totalPrice +=(totalPrice * getLeaseFee());
        return totalPrice;

    }

    @Override
    public double getMonthlyPayment()
    {
        double originalPrice = getTotalPrice();
        double leaseFee = originalPrice * getLeaseFee();
        double residualValue = originalPrice * getExpectedEndValue();

        double financedAmount = originalPrice -residualValue + leaseFee;
        double leaseFinancedInterest = 0.04;

        double monthlyInterestRate = leaseFinancedInterest / 12;
        int leaseTerm = 36;

        double monthlyPayment = financedAmount * monthlyInterestRate/ (1- Math.pow(1+ monthlyInterestRate, -leaseTerm));

        return monthlyPayment;

    }

}
