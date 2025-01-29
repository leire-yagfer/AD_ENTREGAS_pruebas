package com.example.ad_entrega9_apihoteljwt.DTO;

//clase sin las PK ni relaciones
public record HotelDTO(String nombre, String descripcion, int categoria, boolean piscina,
                       String localidad) {

}
