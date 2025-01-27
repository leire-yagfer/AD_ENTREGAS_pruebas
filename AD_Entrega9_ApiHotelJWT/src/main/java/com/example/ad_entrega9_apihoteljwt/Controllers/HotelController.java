package com.example.ad_entrega9_apihoteljwt.Controllers;

import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import com.example.ad_entrega9_apihoteljwt.Services.HotelServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/hotelleire") //como voy a acceder a la clase desde Postman
public class HotelController {
    private final HotelServices hotelServices;

    //CONSTRUCTOR CREADO PQ SI NO DA ERROR
    public HotelController(HotelServices hotelServices) {
        this.hotelServices = hotelServices;
    }
}
