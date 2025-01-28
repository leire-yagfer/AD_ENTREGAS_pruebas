package com.example.ad_entrega9_apihoteljwt.Controllers;

import com.example.ad_entrega9_apihoteljwt.Entities.Habitacion;
import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import com.example.ad_entrega9_apihoteljwt.Services.HabitacionServices;
import com.example.ad_entrega9_apihoteljwt.Services.HotelServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/hotelleire") //como voy a acceder a la clase desde Postman
public class HotelController {
    private final HotelServices hotelServices;
    private final HabitacionServices habitacionServices;

    //CONSTRUCTOR CREADO PQ SI NO DA ERROR
    public HotelController(HotelServices hotelServices, HabitacionServices habitacionServices) {
        this.hotelServices = hotelServices;
        this.habitacionServices = habitacionServices;
    }


    //DOS FORMAS DISTINTAS DE DEVOLVER LOS DATOS (1  ES POR LOCALIDAD Y 2 POR CATEGORÍA)
    //Búsqueda de hotel por localidad
    @GetMapping("/localidad/{localidad}")
    public List<Hotel> getAllHotelsPorLocalidad(@PathVariable String localidad) {
        try {
            return hotelServices.findHotelByLocalidad(localidad);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener los hoteles de " + localidad, e);
        }//try-catch
    }//getAllHotelsPorLocalidad


    //Búsqueda de hotel por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> getAllHotelsPorCategoria(@PathVariable int categoria) {
        try {
            //obtener los hoteles por la categoría expuesta
            List<Hotel> listaHotelCategoria = hotelServices.findHotelByCategoria(categoria);
            if (listaHotelCategoria.isEmpty()) {
                return new ResponseEntity<>("No se han encontrado hoteles con dicha categoría.", HttpStatus.NO_CONTENT);
            }
            //devuelvo la lista con los hoteles de la categoría
            return ResponseEntity.ok(listaHotelCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }//try-catch
    }//getAllHotelsPorLocalidad


    /*
    //Búsqueda de habitaciones de un hotel por tamaño y precio (rango minimo→máximo). Solo mostrará aquellas habitaciones que estén marcadas como libres
    @GetMapping("/filtros/{id_hotel}")
    public ResponseEntity<?> getAllHotelsPorTamanoYPrecio(@PathVariable int id_hotel,
                                                          @RequestParam  int tamano,
                                                          @RequestParam  double precioMin,
                                                          @RequestParam  double precioMax) {
        try {
            //obtener habitaciones del hotel con los filtros aplicados
            List<Habitacion> habitacionesHotel = habitacionServices.findHabitacionPorFiltros(id_hotel, tamano, precioMin, precioMax);
            if (habitacionesHotel.isEmpty()) {
                return new ResponseEntity<>("No se han encontrado habitaciones.", HttpStatus.NO_CONTENT);
            }
            //devuelvo la lista con la shabitaciones encontradas
            return ResponseEntity.ok(habitacionesHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }//try-catch
    }//getAllHotelsPorLocalidad
    */
}//class