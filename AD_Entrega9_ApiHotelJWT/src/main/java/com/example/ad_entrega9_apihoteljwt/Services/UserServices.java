package com.example.ad_entrega9_apihoteljwt.Services;

import com.example.ad_entrega9_apihoteljwt.Entities.Usuario;
import com.example.ad_entrega9_apihoteljwt.Repositories.UserRepositories;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepositories userRepositories;

    public UserServices(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }


    //obtengo el usuario a partir del nombre
    public Usuario getUser(String username) {
        return userRepositories.getById(username);
    }//getUser
}//class
