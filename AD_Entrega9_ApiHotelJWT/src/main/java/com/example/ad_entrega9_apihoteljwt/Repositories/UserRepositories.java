package com.example.ad_entrega9_apihoteljwt.Repositories;

import com.example.ad_entrega9_apihoteljwt.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositories extends JpaRepository<Usuario, String> {

    List<Usuario> getUsersByUsername(String username);
}
