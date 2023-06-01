package org.yearup.models;

import java.util.ArrayList;

public class SalesContract extends Contract
{
    private static final double salesTaxAmount = 0.05;
    private static final double recordingFee = 100;
    private static final double processingFeeUnder_1000 = 295;
    private static final double processingFeeAbove_1000 = 495;
    private boolean financeOptions;

    public SalesContract(String type, String date, String name, ArrayList<Vehicle> vehicleSold,boolean financeOptions) {
        super(type, date, name, vehicleSold);
        this.financeOptions = financeOptions;
    }

    public static double getSalesTaxAmount()
    {
        return salesTaxAmount;
    }
    public static double getRecordingFee()
    {
        return recordingFee;
    }
    public static double getProcessingFeeUnder_1000()
    {
        return processingFeeUnder_1000;
    }
    public static double getProcessingFeeAbove_1000()
    {
        return processingFeeAbove_1000;
    }
    public boolean isFinanceOptions() {
        return financeOptions;
    }
    public void setFinanceOptions(boolean financeOptions)
    {
        this.financeOptions = financeOptions;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0;
        for(Vehicle vehicle : getVehicleSold())
        {
            totalPrice+= vehicle.getPrice();
        }
        totalPrice += salesTaxAmount + recordingFee;
        if(totalPrice < 1000)
        {
            totalPrice += processingFeeUnder_1000;
        }
        else
        {
            totalPrice += processingFeeAbove_1000;
        }
        return totalPrice;

    }

    @Override
    public double getMonthlyPayment() {
        if(!financeOptions)
        {
            return 0;
        }
        double loanAmount = getTotalPrice();
        double interestRate;
        int loanTerm;
        if(loanAmount >+ 10000)
        {
            interestRate = 0.0425;
            loanTerm =48;
        }
        else {
            interestRate = 0.0525;
            loanTerm = 24;

        }
        double monthlyInterestRate = interestRate / 12;
        double monthlyPayment = loanAmount * (monthlyInterestRate * Math.pow(1+monthlyInterestRate,loanTerm))
                /(Math.pow(1+ monthlyInterestRate,loanTerm)-1);
        return  monthlyPayment;
    }
}
