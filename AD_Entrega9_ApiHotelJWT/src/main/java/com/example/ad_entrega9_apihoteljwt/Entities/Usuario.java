package com.example.ad_entrega9_apihoteljwt.Entities;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    //ATRIUTOS
    @Id
    @Column(name="username")
    private String username;

    @Column(name = "pswd")
    private String pswd;



    //CONSTRUCTOR
    public Usuario() {

    }

    public Usuario(String username, String pswd) {
        this.username = username;
        this.pswd = pswd;
    }


    //GETTER Y SETTER
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = this.pswd;
    }
}