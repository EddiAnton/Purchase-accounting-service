package com.eddi;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        /*String operation = args[2];
        String inputFile = args[3];
        String outputFile = args[4];*/

        String type = "search";

        /*String json = null;
        try {
            json = new Scanner(new File("/home/eduard/projects/Purchase-accounting-service/input.json")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject myJsonobject = new JSONObject(json);
        System.out.println(myJsonobject.toString());*/

        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader("/home/eduard/projects/Purchase-accounting-service/input.json"));
            JSONObject jsonObject =  (JSONObject)object;

            List<Object> criteriaList = new ArrayList<>();

            JSONArray criterias = (JSONArray) jsonObject.get("criteria");
            Iterator criteriaIterator = criterias.iterator();
            while (criteriaIterator.hasNext()) {
                JSONObject criteria = (JSONObject) criteriaIterator.next();
                System.out.println(criteria);
                criteriaList.add(criteria);
            }
        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }


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

                String sql = "SELECT * FROM purchases";
                SQLQuery query = session.createSQLQuery(sql);
                query.addEntity(Purchase.class);
                List results = query.list();

                for (Object p: results
                     ) {
                    Purchase purchase = (Purchase) p;
                    System.out.println(purchase);
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

                Product productFromDB = session.get(Product.class, 1);
                System.out.println(productFromDB);

                session.getTransaction().commit();
            } finally {
                sessionFactory.close();
                if (session != null) {
                    session.close();
                }
            }

        }

        else {
            type = "error";
        }
    }
}
