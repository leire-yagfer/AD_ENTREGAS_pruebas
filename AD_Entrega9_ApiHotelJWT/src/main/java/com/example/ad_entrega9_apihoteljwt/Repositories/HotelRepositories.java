package com.example.ad_entrega9_apihoteljwt.Repositories;

import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepositories extends JpaRepository<Hotel, Integer> { //Integer pq la PK es Integer
    //CONSULTAS
    //Búsqueda de hotel por localidad
    List<Hotel> findHotelByLocalidad(String localidad);
    //equivalente pero con consulta JPQL
    /*
    @Query("SELECT h FROM Hotel h WHERE h.localidad = :localidadParam")
    List<Hotel> findHotelByLocalidad(@Param("localidadParam") String localidad);
    List<Hotel> findHotelByLocalidad(String localidad); //equivalente de lo anterior sin @Param
    */

    //Búsqueda de hotel por categoría
    List<Hotel> findHotelByCategoria(String categoria);
    //equivalente pero con consulta JPQL
    /*
    @Query("SELECT h FROM Hotel h WHERE h.categoria = :categoria")
    List<Hotel> findHotelByLocalidad(String categoria);
    */
}
