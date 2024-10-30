package org.example.ad_entrega6_crudcoches_hibernate_javafx.DAO;

import org.example.ad_entrega6_crudcoches_hibernate_javafx.Model.Coche;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CocheDAOImpl implements CocheDAO {

    private static SessionFactory sessionFactory; // objeto para gestionar la sesión de Hibernate

    // Bloque estático para inicializar la configuración de Hibernate
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Coche.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error en la configuración de Hibernate: " + e.getMessage());
        }
    }

    private static Session obtenerSesion() {
        return sessionFactory.openSession(); // método para abrir una sesión
    }

    @Override
    public ArrayList<Coche> mostrarCoches() {
        ArrayList<Coche> cochesBD = new ArrayList<>();
        try (Session session = obtenerSesion()) { // uso de try-with-resources
            Query<Coche> query = session.createQuery("from Coche", Coche.class);
            cochesBD.addAll(query.getResultList());
        } catch (Exception e) {
            System.err.println("Error al mostrar coches: " + e.getMessage());
        }
        return cochesBD;
    }

    @Override
    public int insertarCoche(Coche insertarCoche) {
        int semaforo = 0;
        Transaction transaction = null;
        try (Session session = obtenerSesion()) {
            transaction = session.beginTransaction();
            session.save(insertarCoche);
            transaction.commit();
            semaforo = 1; // operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al insertar coche: " + e.getMessage());
        }
        return semaforo;
    }

    @Override
    public int eliminarCoche(Coche eliminarCoche) {
        int semaforo = 0;
        Transaction transaction = null;
        try (Session session = obtenerSesion()) {
            transaction = session.beginTransaction();
            session.delete(session.contains(eliminarCoche) ? eliminarCoche : session.merge(eliminarCoche));
            transaction.commit();
            semaforo = 1; // operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al eliminar coche: " + e.getMessage());
        }
        return semaforo;
    }

    @Override
    public int actualizarCoche(Coche actualizarCoche) {
        int semaforo = 0;
        Transaction transaction = null;
        try (Session session = obtenerSesion()) {
            transaction = session.beginTransaction();
            session.update(actualizarCoche);
            transaction.commit();
            semaforo = 1; // operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al actualizar coche: " + e.getMessage());
        }
        return semaforo;
    }

    @Override
    public int existeMatricula(String matricula) {
        try (Session session = obtenerSesion()) {
            Query<Coche> query = session.createQuery("from Coche where matricula = :matricula", Coche.class);
            query.setParameter("matricula", matricula);
            return query.uniqueResult() != null ? 1 : 0;
        } catch (Exception e) {
            System.err.println("Error al verificar matrícula: " + e.getMessage());
        }
        return 0;
    }
}//class