package com.example.ad_entrega9_apihoteljwt.Controllers;

import com.example.ad_entrega9_apihoteljwt.DTO.HabitacionDTO;
import com.example.ad_entrega9_apihoteljwt.DTO.HotelDTO;
import com.example.ad_entrega9_apihoteljwt.Entities.Habitacion;
import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import com.example.ad_entrega9_apihoteljwt.Services.HabitacionServices;
import com.example.ad_entrega9_apihoteljwt.Services.HotelServices;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Obtener todos los autos", description = "Obtiene una lista de todos los autos")
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




    //método para crear un nuevo hotel
    @PostMapping("/nuevoHotel")
    public ResponseEntity<?> crearHotel(@RequestBody HotelDTO hoteldto) {
        try {
            //creo un nuevo hotel de tipo hotel que le paso los datos sin las habitaciones
            Hotel hotel = new Hotel();
            //le paso los datos de hoteldto a hotel
            hotel.setNombre(hoteldto.nombre());
            hotel.setDescripcion(hoteldto.descripcion());
            hotel.setCategoria(hoteldto.categoria());
            hotel.setLocalidad(hoteldto.localidad());
            hotel.setPiscina(hoteldto.piscina());

            //creo el nuevo hotel
            hotelServices.nuevoHotel(hotel);
            return new ResponseEntity<>("Hotel creado con éxito",HttpStatus.CREATED);
        } catch (Exception e) {
            //si existe algún error, muestro mensaje
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al guardar el hotel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }//try-catch
    }//crearHotel


    //método para crear una nueva habitación de un hotel
    @PostMapping("/nuevaHabitacion/{id_hotel}")
    public ResponseEntity<?> nuevaHabitacionHotel(@PathVariable int id_hotel, @RequestBody HabitacionDTO habitaciondto) {
        try {
            //creo la habitacion, que no habitacionDTO
            Habitacion habitacion = new Habitacion();
            //le paso los parámetros de habitaciondto a habitacion
            habitacion.setTamano(habitaciondto.tamano());
            habitacion.setPersonas(habitaciondto.personas());
            habitacion.setPrecio_noche(habitaciondto.precio_noche());
            habitacion.setIncluye_desayuno(habitaciondto.incluye_desayuno());
            habitacion.setOcupada(habitaciondto.ocupada());

            //le paso el id y la habitacion
            habitacionServices.registrarNuevaHabitacion(id_hotel, habitacion);
            return new ResponseEntity<>("Habitación creada con éxito." ,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al guardar el habitacion.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//nuevaHabitacionHotel


    //método para eliminar una habitación de hotel
    @DeleteMapping("/eliminarHabitacionHotel/{id_hotel}/{id_habitacion}")
    public ResponseEntity<?> eliminarHabitacionHotel(@PathVariable int id_hotel, @PathVariable int id_habitacion) {
        try {
            //llamo al metodo directamente
            habitacionServices.eliminarHabitacionDeHotel(id_hotel, id_habitacion);
            return new ResponseEntity<>("Habitación eliminada con éxito del hotel." ,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al eliminar la habitacion.", HttpStatus.INTERNAL_SERVER_ERROR);
        }//try-catch
    }//nuevaHabitacionHotel
}//class