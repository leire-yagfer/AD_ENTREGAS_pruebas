package org.example.ad_entrega6_crudcoches_hibernate.Util;

import org.example.ad_entrega6_crudcoches_hibernate.Model.Coche;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory = null;

    static {
        try {
            // Cargar la configuración desde el archivo hibernate.cfg.xml
            Configuration cfg = new Configuration().configure(); // Solo carga el archivo en el classpath
            cfg.addAnnotatedClass(Coche.class); // Registra la clase Coche como entidad
            factory = cfg.buildSessionFactory(); // Construye el SessionFactory
        } catch (Throwable ex) {
            // Si algo sale mal, lanzar una excepción
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }
}