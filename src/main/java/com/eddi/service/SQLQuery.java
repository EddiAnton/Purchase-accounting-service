package com.eddi.service;

public class SQLQuery {
    static String findByCustomerLastName = "SELECT * FROM customers WHERE last_name=:customerLastName";
    static String findByTitleProductAndQuantityPurchased = "SELECT * FROM customers WHERE id IN (SELECT customer_id FROM (SELECT customer_id, COUNT(*) AS orders FROM purchases INNER JOIN purchase_list ON purchases.id=purchase_list.purchase_id INNER JOIN products ON purchase_list.product_id=products.id WHERE products.title=:titleProduct GROUP BY customer_id) AS l WHERE orders >:quantityPurchased)";
    static String findByMinExpensesAndMaxExpenses = "";
    static String findByBadCustomers = "";
    static String findByDateBetween = "";
}
