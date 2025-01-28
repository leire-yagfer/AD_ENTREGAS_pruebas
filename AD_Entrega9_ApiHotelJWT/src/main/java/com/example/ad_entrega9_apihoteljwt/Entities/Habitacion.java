package com.example.ad_entrega9_apihoteljwt.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "HABITACION")
public class Habitacion {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_habitacion")
    private int id_habitacion;

    @Column(name = "tamano")
    private float tamano;

    @Column(name = "personas")
    private int personas;

    @Column(name = "precio_noche")
    private double precio_noche;

    @Column(name = "incluye_desayuno")
    private boolean incluye_desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;


    @ManyToOne
    @JoinColumn(name = "id_hotel", referencedColumnName="id_hotel")
    @JsonBackReference
    private Hotel hotel;


    //CONTRUCTOR
    public Habitacion() {
    }

    public Habitacion(float tamano, int personas, double precio_noche, boolean incluye_desayuno, boolean ocupada) {
        this.tamano = tamano;
        this.personas = personas;
        this.precio_noche = precio_noche;
        this.incluye_desayuno = incluye_desayuno;
        this.ocupada = ocupada;
    }


    //GETTER Y SETTER

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public float getTamano() {
        return tamano;
    }

    public void setTamano(float tamano) {
        this.tamano = tamano;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }

    public boolean isIncluye_desayuno() {
        return incluye_desayuno;
    }

    public void setIncluye_desayuno(boolean incluye_desayuno) {
        this.incluye_desayuno = incluye_desayuno;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}//class