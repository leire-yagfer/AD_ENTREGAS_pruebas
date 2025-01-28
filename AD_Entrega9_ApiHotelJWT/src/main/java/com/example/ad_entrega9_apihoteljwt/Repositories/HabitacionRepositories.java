package com.example.ad_entrega9_apihoteljwt.Repositories;

import com.example.ad_entrega9_apihoteljwt.Entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepositories extends JpaRepository<Habitacion, Integer> {
    //CONSULTAS
    //Búsqueda de habitaciones de un hotel por tamaño y precio (rango minimo→máximo). Solo mostrará aquellas habitaciones que estén marcadas como libres
        @Query("SELECT h FROM Habitacion h WHERE h.hotel.id_hotel = :idHotel AND h.ocupada = false AND h.tamanio = :tamanio AND h.precio BETWEEN :precioMin AND :precioMax ORDER BY h.precio ASC")
        List<Habitacion> findAvailableHabitaciones(int idhotel, float tamanio, double precioMin, double precioMax);
        /*
        1. h: Es un alias para la entidad Habitacion en la consulta JPQL.
        2. h.hotel: Representa la relación entre la entidad Habitacion y la entidad Hotel. La entidad Habitacion tiene un campo hotel que está relacionado con la entidad Hotel mediante la anotación @ManyToOne. Esto indica que cada habitación pertenece a un hotel.
        3. h.hotel.id_hotel: Dentro de la entidad Hotel, id_hotel es el identificador único del hotel. Esto hace referencia al campo id_hotel de la tabla Hotel en la base de datos.
        */
}//class
