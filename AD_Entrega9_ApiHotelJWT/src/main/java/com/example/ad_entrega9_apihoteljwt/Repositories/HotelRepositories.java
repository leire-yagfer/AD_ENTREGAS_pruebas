package com.example.ad_entrega9_apihoteljwt.Repositories;

import com.example.ad_entrega9_apihoteljwt.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepositories extends JpaRepository<Hotel, Integer> { //Integer pq la PK es Integer
    //CONSULTAS
}
