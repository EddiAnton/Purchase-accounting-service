package com.eddi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        /*String type = args[2];
        String inputFile = args[3];
        String outputFile = args[4];*/
        String type = "search";

        String jsonInput = null;
        try {
            jsonInput = new Scanner(new File("/home/eduard/projects/Purchase-accounting-service/input_stat.json")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CriteriaSet criterias = gson.fromJson(jsonInput, CriteriaSet.class);
        System.out.println(criterias);



        if(type == "search") {
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
                SQLQuery query_1 = session.createSQLQuery(com.eddi.SQLQuery.findByCustomerLastName);
                query_1.addEntity(Purchase.class);
                List listFindByCustomerLastName = query_1.list();

                SQLQuery query_2 = session.createSQLQuery(com.eddi.SQLQuery.findByTitleProductAndQuantityPurchased);
                query_2.addEntity(Purchase.class);
                List listFindByTitleProductAndQuantityPurchased = query_2.list();

                /*SQLQuery query_3 = session.createSQLQuery(com.eddi.SQLQuery.findByMinExpensesAndMaxExpenses);
                query_2.addEntity(Purchase.class);
                List listFindByMinExpensesAndMaxExpenses = query_3.list();

                SQLQuery query_4 = session.createSQLQuery(com.eddi.SQLQuery.findByBadCustomers);
                query_2.addEntity(Purchase.class);
                List listFindByBadCustomers = query_4.list();*/

                List<Object> resultList = new ArrayList<>();
                resultList.add(listFindByCustomerLastName);
                resultList.add(listFindByTitleProductAndQuantityPurchased);
                //resultList.add(listFindByMinExpensesAndMaxExpenses);
                //resultList.add(listFindByBadCustomers);

                for (Object p: listFindByCustomerLastName
                     ) {
                    Purchase purchase = (Purchase) p;
                    System.out.println(purchase);
                }

                try {
                    gson.toJson(resultList,
                            new FileWriter("/home/eduard/projects/Purchase-accounting-service/output.json"));
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

        if(type == "stat") {
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
                SQLQuery query = session.createSQLQuery(com.eddi.SQLQuery.findByDateBetween);
                query.addEntity(Purchase.class);
                List listFindByDateBetween = query.list();

                try {
                    gson.toJson(listFindByDateBetween, new FileWriter("/home/eduard/projects/Purchase-accounting-service/output.json"));
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

        else {
            String json = "\"type\": \"error\",\n\"message\": \"Invalid date format\"";

            try {
                gson.toJson(json, new FileWriter("/home/eduard/projects/Purchase-accounting-service/output.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
