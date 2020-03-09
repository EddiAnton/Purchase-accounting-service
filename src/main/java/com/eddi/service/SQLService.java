package com.eddi.service;

import com.eddi.entity.Customer;
import com.eddi.entity.Product;
import com.eddi.entity.Purchase;
import com.eddi.jsonModel.SearchCriteriaSet;
import com.eddi.jsonModel.StatCriteriaSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SQLService {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    SearchCriteriaSet searchCriteriaSet;
    StatCriteriaSet statCriteriaSet;

    public void searchQuery() {
        try {
            searchCriteriaSet = gson.fromJson(new JsonReader(new FileReader
                            (new File("/home/eduard/projects/Purchase-accounting-service/input.json"))),
                    SearchCriteriaSet.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(searchCriteriaSet);

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Purchase.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            String customerLastName = "Ivanov";
            String sql = "SELECT * FROM purchases";
            SQLQuery query_1 = session.createSQLQuery(com.eddi.service.SQLQuery.findByCustomerLastName);
            query_1.setParameter("customerLastName", customerLastName);
            query_1.addEntity(Customer.class);
            List listFindByCustomerLastName = query_1.list();

            String titleProduct = "Milk";
            int quantityPurchased = 2;
            SQLQuery query_2 = session.createSQLQuery(com.eddi.service.SQLQuery.findByTitleProductAndQuantityPurchased);
            query_2.setParameter("titleProduct", titleProduct);
            query_2.setParameter("quantityPurchased", quantityPurchased);
            query_2.addEntity(Customer.class);
            List listFindByTitleProductAndQuantityPurchased = query_2.list();

            /*SQLQuery query_3 = session.createSQLQuery(com.eddi.service.SQLQuery.findByMinExpensesAndMaxExpenses);
            query_2.addEntity(Purchase.class);
            List listFindByMinExpensesAndMaxExpenses = query_3.list();

            SQLQuery query_4 = session.createSQLQuery(com.eddi.service.SQLQuery.findByBadCustomers);
            query_2.addEntity(Purchase.class);
            List listFindByBadCustomers = query_4.list();*/

            List<Object> resultList = new ArrayList<>();
            resultList.add(listFindByCustomerLastName);
            resultList.add(listFindByTitleProductAndQuantityPurchased);
            //resultList.add(listFindByMinExpensesAndMaxExpenses);
            //resultList.add(listFindByBadCustomers);

            try {
                gson.toJson(resultList, new FileWriter("/home/eduard/projects/Purchase-accounting-service/output.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void statQuery() {
        try {
            statCriteriaSet = gson.fromJson(new JsonReader(new FileReader
                            (new File("/home/eduard/projects/Purchase-accounting-service/input_stat.json"))),
                    StatCriteriaSet.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(statCriteriaSet);

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Purchase.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //String sql = "SELECT * FROM purchases";
            SQLQuery query = session.createSQLQuery(com.eddi.service.SQLQuery.findByDateBetween);
            query.addEntity(Purchase.class);
            List listFindByDateBetween = query.list();

            try {
                gson.toJson(statCriteriaSet, new FileWriter("/home/eduard/projects/Purchase-accounting-service/output.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void exception() {
        String json = "\"type\": \"error\",\n\"message\": \"Invalid date format\"";

        try {
            gson.toJson(json, new FileWriter("/home/eduard/projects/Purchase-accounting-service/output.json"));
            System.out.println("It works!");
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
