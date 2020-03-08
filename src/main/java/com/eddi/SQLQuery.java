package com.eddi;

public class SQLQuery {
    final static String findByCustomerLastName = "SELECT * FROM customers WHERE last_name LIKE '%\" + customerLastName + \"%'";
    final static String findByTitleProductAndQuantityPurchased = "SELECT * FROM customers WHERE id IN (SELECT customer_id FROM (SELECT customer_id, COUNT(*) AS orders FROM purchases INNER JOIN purchase_list ON purchases.id=purchase_list.purchase_id INNER JOIN products ON purchase_list.product_id=products.id WHERE products.title LIKE '%\" + titleProduct +\"%' GROUP BY customer_id) AS l WHERE orders > 1)";
    final static String findByMinExpensesAndMaxExpenses = "";
    final static String findByBadCustomers = "";
    final static String findByDateBetween = "";
}
