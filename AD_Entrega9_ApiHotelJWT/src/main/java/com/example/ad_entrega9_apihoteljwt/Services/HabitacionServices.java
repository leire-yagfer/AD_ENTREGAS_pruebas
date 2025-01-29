package com.example.ad_entrega9_apihoteljwt.Services;

import com.example.ad_entrega9_apihoteljwt.Entities.Habitacion;
import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import com.example.ad_entrega9_apihoteljwt.Repositories.HabitacionRepositories;
import com.example.ad_entrega9_apihoteljwt.Repositories.HotelRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServices {
    private final HabitacionRepositories habitacionRepositories;
    private final HotelRepositories hotelRepositories;

    //CONSTRUCTOR CREADO PQ SI NO DA ERROR
    public HabitacionServices(HabitacionRepositories habitacionRepositories, HotelRepositories hotelRepositories) {
        this.habitacionRepositories = habitacionRepositories;
        this.hotelRepositories = hotelRepositories;
    }


    //LLAMADA A MÉTODOS CREADOS EN LA INTERFACE DE REPOSITORIES
    //método que obtiene las habitaciones disponibles en orden ascendente del precio
    public List<Habitacion> findHabitacionPorFiltros(int id_hotel, float tamanio, double precioMin, double precioMax) {
        //busco el hotel con el id proporcionado
        Optional<Hotel> hotel = hotelRepositories.findById(id_hotel);
        //si el hotel no existe, excepción
        if (!hotel.isPresent()) {
            throw new RuntimeException("El hotel con ID " + id_hotel + " no existe.");
        }//if
        //si el hotel existe, devuelvo las habitaciones disponibles
        return habitacionRepositories.findAvailableHabitaciones(id_hotel, tamanio, precioMin, precioMax);
    }//findHabitacionPorFiltros



    //método que cambia de libre a ocupada, pero no al revés
    public Habitacion actualizarEstadoHabitacion(int idHabitacion) {
        //busco la habitación por su id
        Optional<Habitacion> optionalHabitacion = habitacionRepositories.findById(idHabitacion);

        //si la habitación existe
        if (optionalHabitacion.isPresent()) {
            //obtengo la habitación
            Habitacion habitacion = optionalHabitacion.get();
            //modifico el estado de la habitación de libre --> ocupada
            if (!habitacion.isOcupada()) {
                habitacion.setOcupada(true);  //cambio a ocupada
                habitacionRepositories.save(habitacion); //actualizo la habitación en la BD
            } else {
                //si la habitación ya está ocupada o si el parámetro es false, no se hace nada
                throw new RuntimeException("La habitación ya está ocupada.");
            }//if-else
        } else {
            //si no se encuentra la habitación
            throw new RuntimeException("Habitación con id " + idHabitacion + " no encontrada.");
        }
        return null;
    }//actualizarEstadoHabitacion



    //método para crear una nueva habitación en un hotel
    public Habitacion registrarNuevaHabitacion(int idHotel, Habitacion nuevaHabitacion) {
        //busco el hotel en el que se va a crear loa habitación
        Optional<Hotel> optionalHotel = hotelRepositories.findById(idHotel);

        //si existe el hotel
        if (optionalHotel.isPresent()) {
            //l eobtengo
            Hotel hotel = optionalHotel.get();

            //asocio la nueva habitación al hotel --> llamo directamente al atributo hotel de Habitación que engloba a un Hotel
            nuevaHabitacion.setHotel(hotel);

            //guardo la nueva habitación en la base de datos
            return habitacionRepositories.save(nuevaHabitacion);
        } else {
            //si no se encuentra el hotel, lanzo una excepción
            throw new RuntimeException("Hotel con id " + idHotel + " no encontrado.");
        }//if-else
    }//registrarNuevaHabitacion



    //PROPIOS DE JPAREPOSITORY
    //método para eliminar una habitación específica de un hotel
    public void eliminarHabitacionDeHotel(int id_hotel, int idHabitacion) {
        //buso la habitación por su id
        Optional<Habitacion> optionalHabitacion = habitacionRepositories.findById(idHabitacion);

        //si la encuentro
        if (optionalHabitacion.isPresent()) {
            //la obtengo
            Habitacion habitacion = optionalHabitacion.get();
            //verifico si la habitación está asociada al id_hotel pasado por parametro
            Hotel hotel = habitacion.getHotel(); //obtengo el hotel al que pertenece esa habitación
            //si está asociada a un hotel
            if (hotel != null && hotel.getId_hotel() == id_hotel) {
                habitacionRepositories.delete(habitacion); //elimino la habitación
                System.out.println("Habitación eliminada con éxito del hotel: " + hotel.getNombre());
            } else {
                throw new RuntimeException("La habitación no está asociada a ningún hotel.");
            }//if-else
        } else {
            throw new RuntimeException("Habitación no encontrada.");
        }//if-else
    }//eliminarHabitacionDeHotel
}
