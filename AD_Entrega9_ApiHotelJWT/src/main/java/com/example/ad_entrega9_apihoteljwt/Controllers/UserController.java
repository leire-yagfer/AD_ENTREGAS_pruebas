package com.example.ad_entrega9_apihoteljwt.Controllers;

import com.example.ad_entrega9_apihoteljwt.Entities.Usuario;
import com.example.ad_entrega9_apihoteljwt.Services.UserServices;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
//@RequestMapping("/api/login") //como voy a acceder a la clase desde Postman
public class UserController {

    final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }



    //método para logearse
    @PostMapping("/user")
    public String login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        //obtengo el usuario de Services
        Usuario usuario = userServices.getUser(username);

        if(usuario != null){
            if (usuario.getPassword().equals(pwd)) {
                System.out.println("Me crea el token");
                String token = getJWTToken(username);

                return token;
            }
            else{
                return null;
            }
        } else {
            return null;
        }
    }




    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
