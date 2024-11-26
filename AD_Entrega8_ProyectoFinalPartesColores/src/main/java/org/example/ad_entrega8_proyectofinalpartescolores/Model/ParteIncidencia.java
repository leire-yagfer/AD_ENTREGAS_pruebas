package org.example.ad_entrega8_proyectofinalpartescolores.Model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "gestionpartes") //especifica el nombre de la tabla en la base de datos
public class ParteIncidencia {

    //ATRIBUTOS
    @Id // indica que id_multa es la clave primaria de la entidad
    @GeneratedValue(strategy = IDENTITY) // es autoincrement --> genera un id
    @Column(name="id_parte")
    private int id_parte;

    @Column(name="id_profesor")
    private int id_profesor;

    @Column(name="id_grupo")
    private int id_grupo;

    @Column(name="id_alum")
    private int id_alum;

    @Column(name="id_alum")
    private String descripcion;

    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="hora")
    private Time hora;

    @Column(name="sancion")
    private int sancion;

    /*
    @Column(name="id_punt_partes")
    private int id_punt_partes;
    */


    //CONSTRUCTOR
    public ParteIncidencia() {
    }

    public ParteIncidencia(int id_alum, int id_grupo, int id_profesor, LocalDate fecha, Time hora, int sancion, String descripcion) {
        this.id_alum = id_alum;
        this.id_grupo = id_grupo;
        this.id_profesor = id_profesor;
        this.fecha = fecha;
        this.hora = hora;
        this.sancion = sancion;
        this.descripcion = descripcion;
    }

    //GETTER Y SETTER
    public int getId_parte() {
        return id_parte;
    }

    public void setId_parte(int id_parte) {
        this.id_parte = id_parte;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_alum() {
        return id_alum;
    }

    public void setId_alum(int id_alum) {
        this.id_alum = id_alum;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getSancion() {
        return sancion;
    }

    public void setSancion(int sancion) {
        this.sancion = sancion;
    }
}//class
