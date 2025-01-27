package Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "HOTEL")
public class Hotel {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_hotel")
    private int id_hotel;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "piscina")
    private boolean piscina;

    @Column(name = "localidad")
    private String localidad;

    @OneToMany(mappedBy = "HOTEL", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Habitacion> listaHabitaciones;


    //CONSTRUCTOR
    public Hotel() {
    }

    public Hotel(String nombre, String descripcion, String categoria, boolean piscina, String localidad, List<Habitacion> listaHabitaciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.piscina = piscina;
        this.localidad = localidad;
        this.listaHabitaciones = listaHabitaciones;
    }


    //GETTER Y SETTER
    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isPiscina() {
        return piscina;
    }

    public void setPiscina(boolean piscina) {
        this.piscina = piscina;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }



    //TOSTRING
    @Override
    public String toString() {
        return "hotel{" +
                "idHotel=" + id_hotel +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", piscina=" + piscina +
                ", localidad='" + localidad + '\'' +
                ", listadoHabitaciones=" + listaHabitaciones +
                '}';
    }//toString
}//class