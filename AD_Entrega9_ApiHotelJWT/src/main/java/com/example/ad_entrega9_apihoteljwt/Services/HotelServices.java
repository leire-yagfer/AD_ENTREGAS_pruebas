package com.example.ad_entrega9_apihoteljwt.Services;

import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import com.example.ad_entrega9_apihoteljwt.Repositories.HotelRepositories;

import java.util.List;

public class HotelServices {
    private final HotelRepositories hotelRepositories;


    //CONSTRUCTOR CREADO PQ SI NO DA ERROR
    public HotelServices(HotelRepositories hotelRepositories) {
        this.hotelRepositories = hotelRepositories;
    }



    //LLAMADA A MÉTODOS CREADOS EN LA INTERFACE DE REPOSITORIES
    //Buscar hotel por localidad
    public List<Hotel> findHotelByLocalidad(String localidad) {
        return hotelRepositories.findHotelByLocalidad(localidad);
    }//findHotelByLocalidad


    //Buscar hotel por categoría
    public List<Hotel> findHotelByCategoria(String categoria) {
        return hotelRepositories.findHotelByCategoria(categoria);
    }//findHotelByCategoria



    //PROPIOS DE JPAREPOSITORY
    //Crear/Registrar un nuevo hotel
    public Hotel nuevoHotel(Hotel hotel) {
        return hotelRepositories.save(hotel);
    }//nuevoHotel
}//HotelServices