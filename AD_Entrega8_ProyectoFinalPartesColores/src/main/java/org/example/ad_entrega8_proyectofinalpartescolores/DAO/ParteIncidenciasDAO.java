package org.example.ad_entrega8_proyectofinalpartescolores.DAO;

import javafx.scene.control.Alert;
import org.example.ad_entrega8_proyectofinalpartescolores.Model.Alumnos;
import org.example.ad_entrega8_proyectofinalpartescolores.Model.ParteIncidencia;
import org.example.ad_entrega8_proyectofinalpartescolores.util.ComprobacionesYcambioEscena;
import org.example.ad_entrega8_proyectofinalpartescolores.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.example.ad_entrega8_proyectofinalpartescolores.util.HibernateUtil.factory;

public class ParteIncidenciasDAO {
    SessionFactory factory = HibernateUtil.getSessionFactory();


    /*
    //metodo para buscar un Alumno por expediente
    public Alumnos buscarAlumnoPorExpediente(String numeroExpediente) {
        Transaction transaction = null;
        Alumnos alumno = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Alumno a WHERE a.numeroExpediente = :numeroExpediente";
            alumno = session.createQuery(hql, Alumnos.class)
                    .setParameter("numeroExpediente", numeroExpediente)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            ComprobacionesYcambioEscena.mostrarAlerta(Alert.AlertType.ERROR, "Error al buscar el alumno.");
        }

        return alumno;
    }//buscarAlumnoPorExpediente

     */


    //método para insertar un nuevo parte en la BD
    public void insertarParte(ParteIncidencia parteIncidencia) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.save(parteIncidencia);

            transaction.commit();
        } catch (Exception e) {
            ComprobacionesYcambioEscena.mostrarAlerta(Alert.AlertType.ERROR, "Error al ñadir el parte. Inténtelo de nuevo.");
        }
    }//insertarParte

}//class