package com.example.ad_entrega9_apihoteljwt.Controllers;

import com.example.ad_entrega9_apihoteljwt.Services.HotelServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habitacionleire") //como voy a acceder a la clase desde Postman
public class HabitacionController {
    private final HotelServices hotelServices;


    //CONSTRUCTOR CREADO PQ SI NO DA ERROR
    public HabitacionController(HotelServices hotelServices) {
        this.hotelServices = hotelServices;
    }
}
