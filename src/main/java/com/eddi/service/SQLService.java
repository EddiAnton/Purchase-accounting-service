package com.eddi.service;

import com.eddi.entity.Customer;
import com.eddi.entity.Product;
import com.eddi.entity.Purchase;
import com.eddi.jsonModel.Criteria;
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

import static com.eddi.service.SQLQuery.*;

public class SQLService {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    SearchCriteriaSet searchCriteriaSet;
    StatCriteriaSet statCriteriaSet;

    public void searchQuery(String inputFile, String outputFile) {
        try {
            searchCriteriaSet = gson.fromJson(new JsonReader(new FileReader
                            (new File("/home/eduard/projects/Purchase-accounting-service/" + inputFile))),
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

            List<Object> resultList = new ArrayList<>();

            for (Criteria criteria : searchCriteriaSet.criteria) {
                SQLQuery query = null;
                List<Customer> list = new ArrayList<>();

                    // Search by customer last name
                if (criteria.getCustomerLastName() != null) {
                    query = session.createSQLQuery(findByCustomerLastName);
                    query.setParameter("customerLastName", criteria.getCustomerLastName());

                    // Search by title of product and quantity of purchases
                } else if (criteria.getTitleProduct() != null && criteria.getQuantityPurchased() != null) {
                    query = session.createSQLQuery(findByTitleProductAndQuantityPurchased);
                    query.setParameter("titleProduct", criteria.getTitleProduct());
                    query.setParameter("quantityPurchased", criteria.getQuantityPurchased());

                    // Search by expenses
                //} else if (criteria.getMinExpenses() != null && criteria.getMaxExpenses() != null) {
                    //query = session.createSQLQuery(findByMinExpensesAndMaxExpenses);
                    //query.setParameter("minExpenses", criteria.getMinExpenses());
                    //query.setParameter("maxExpenses", criteria.getMaxExpenses());

                    // Search by bad customers
                //}else if (criteria.getBadCustomers() != null) {
                    //query = session.createSQLQuery(findByBadCustomers);
                    //query.setParameter("titleProduct", criteria.getBadCustomers());*/
                }
                query.addEntity(Customer.class);
                list = query.list();
                resultList.add(list);
            }

            try {
                gson.toJson(resultList,
                        new FileWriter("/home/eduard/projects/Purchase-accounting-service/" + outputFile));
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

    public void statQuery(String inputFile, String outputFile) {
        try {
            statCriteriaSet = gson.fromJson(new JsonReader(new FileReader
                            (new File("/home/eduard/projects/Purchase-accounting-service/" + inputFile))),
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

            SQLQuery query = session.createSQLQuery(findByDateBetween);
            //query.setParameter("startDate", statCriteriaSet.getStartDate());
            //query.setParameter("endDate", statCriteriaSet.getEndDate());
            query.addEntity(Purchase.class);
            List listFindByDateBetween = query.list();

            try {
                gson.toJson(listFindByDateBetween,
                        new FileWriter("/home/eduard/projects/Purchase-accounting-service/" + outputFile));
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

    public void exception(String outputFile) {
        String json = "\"type\": \"error\",\n\"message\": \"Invalid date format\"";

        try {
            gson.toJson(json,
                    new FileWriter("/home/eduard/projects/Purchase-accounting-service/" + outputFile));
            System.out.println("It works!");
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
