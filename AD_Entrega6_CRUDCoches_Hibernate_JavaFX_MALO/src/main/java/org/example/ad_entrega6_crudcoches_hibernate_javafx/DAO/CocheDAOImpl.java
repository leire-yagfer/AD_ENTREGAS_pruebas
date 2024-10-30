package org.example.ad_entrega6_crudcoches_hibernate_javafx.DAO;

import org.example.ad_entrega6_crudcoches_hibernate_javafx.Model.Coche;
import org.example.ad_entrega6_crudcoches_hibernate_javafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CocheDAOImpl implements CocheDAO {

    //método para obtener todos los coches almacenados en la base de datos
    @Override
    public ArrayList<Coche> mostrarCoches() {
        ArrayList<Coche> cochesBD = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) { // Utilizar HibernateUtil para obtener la sesión
            Query<Coche> query = session.createQuery("from Coche", Coche.class);
            cochesBD.addAll(query.getResultList());
        } catch (Exception e) {
            System.err.println("Error al mostrar coches: " + e.getMessage());
        }
        return cochesBD;
    }//mostrarCoches


    //método para insertar un nuevo coche en la base de datos
    @Override
    public int insertarCoche(Coche insertarCoche) {
        int semaforo = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(insertarCoche);
            transaction.commit();
            semaforo = 1; // Operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al insertar coche: " + e.getMessage());
        }
        return semaforo;
    }//insertarCoche


    //método para eliminar un coche de la base de datos
    @Override
    public int eliminarCoche(Coche eliminarCoche) {
        int semaforo = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.delete(session.contains(eliminarCoche) ? eliminarCoche : session.merge(eliminarCoche));
            transaction.commit();
            semaforo = 1; // Operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al eliminar coche: " + e.getMessage());
        }
        return semaforo;
    }//eliminarCoche


    //método para actualizar un coche en la base de datos
    @Override
    public int actualizarCoche(Coche actualizarCoche) {
        int semaforo = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(actualizarCoche);
            transaction.commit();
            semaforo = 1; // Operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al actualizar coche: " + e.getMessage());
        }
        return semaforo;
    }//actualizarCoche


    //método para verificar si una matrícula ya existe en la base de datos
    @Override
    public int existeMatricula(String matricula) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Coche> query = session.createQuery("from Coche where matricula = :matricula", Coche.class);
            query.setParameter("matricula", matricula);
            return query.uniqueResult() != null ? 1 : 0;
        } catch (Exception e) {
            System.err.println("Error al verificar matrícula: " + e.getMessage());
        }
        return 0;
    }//existeMatricula
}//class