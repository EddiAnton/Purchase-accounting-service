package com.eddi;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApplication {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .buildSessionFactory();



        sessionFactory.close();
    }
}
