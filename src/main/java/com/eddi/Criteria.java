package com.eddi;

import java.util.Date;

public class Criteria {
    private String customerLastName;
    private String titleProduct;
    private Integer quantityPurchased;
    private Integer minExpenses;
    private Integer maxExpenses;
    private Integer badCustomers;
    private Date startDate;
    private Date endDate;

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public Integer getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(Integer minExpenses) {
        this.minExpenses = minExpenses;
    }

    public Integer getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(Integer maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    public Integer getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Integer badCustomers) {
        this.badCustomers = badCustomers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "customerLastName='" + customerLastName + '\'' +
                ", titleProduct='" + titleProduct + '\'' +
                ", quantityPurchased=" + quantityPurchased +
                ", minExpenses=" + minExpenses +
                ", maxExpenses=" + maxExpenses +
                ", badCustomers=" + badCustomers +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}


