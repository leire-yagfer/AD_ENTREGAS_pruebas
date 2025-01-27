package Entities;


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

    @Column(name = "tamanio")
    private int tamanio;

    @Column(name = "personas")
    private int personas;

    @Column(name = "precio")
    private double precio;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;


    @ManyToOne
    @JoinColumn(name = "id_hotel", referencedColumnName="id_hotel")
    @JsonBackReference
    private Hotel hotel;


    //CONTRUCTOR
    public Habitacion() {
    }

    public Habitacion(int tamanio, int personas, double precio, boolean desayuno, boolean ocupada) {
        this.tamanio = tamanio;
        this.personas = personas;
        this.precio = precio;
        this.desayuno = desayuno;
        this.ocupada = ocupada;
    }


    //GETTER Y SETTER
    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
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