package com.eddi;

import java.util.List;

public class Criteria {
    String customerLastName;
    String titleProduct;
    int quantityPurchased;
    Integer minExpenses;
    int maxExpenses;
    int badCustomers;

    @Override
    public String toString() {
        return "Criteria{" +
                "customerLastName='" + customerLastName + '\'' +
                ", titleProduct='" + titleProduct + '\'' +
                ", quantityPurchased=" + quantityPurchased +
                ", minExpenses=" + minExpenses +
                ", maxExpenses=" + maxExpenses +
                ", badCustomers=" + badCustomers +
                '}';
    }
}
