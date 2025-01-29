package com.example.ad_entrega9_apihoteljwt.Controllers;

import com.example.ad_entrega9_apihoteljwt.Entities.Habitacion;
import com.example.ad_entrega9_apihoteljwt.Services.HabitacionServices;
import com.example.ad_entrega9_apihoteljwt.Services.HotelServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitacionleire") //como voy a acceder a la clase desde Postman
public class HabitacionController {
    private final HabitacionServices habitacionServices;


    //CONSTRUCTOR CREADO PQ SI NO DA ERROR
    public HabitacionController(HabitacionServices habitacionServices) {
        this.habitacionServices = habitacionServices;
    }


    //MÉTODOS
    //Búsqueda de habitaciones de un hotel por tamaño y precio (rango minimo→máximo). Solo mostrará aquellas habitaciones que estén marcadas como libres
    @GetMapping("/filtros/{id_hotel}")
    @Operation(summary = "Lista de las habitaciones que cumplen los filtros", description = "Lista de habitaciones que cumplen")
    public ResponseEntity<?> getAllHotelsPorTamanoYPrecio(@PathVariable int id_hotel,
                                                          @RequestParam int tamano,
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
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }//try-catch
    }//getAllHotelsPorTamanoYPrecio


    //método para cambiar de libre a ocupada
    @PutMapping("/cambiarAOcupada/{id_habitacion}")
    @Operation(summary = "Cambiar a ocupada las habitaciones libres", description = "Cambiar de libre a ocupado")
    public ResponseEntity<?> cambiarHabitacionAOcupada(@PathVariable int id_habitacion){
        try {
            habitacionServices.actualizarEstadoHabitacion(id_habitacion);
            return ResponseEntity.ok("Habitación ocupada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }//try-catch
    }//cambiarHabitacionAOcupada
}//class
