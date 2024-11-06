package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity //indica que esta clase es una entidad mapeada a una tabla en la base de datos
@Table(name = "multas") //especifica el nombre de la tabla en la base de datos
public class Multa {

    //ATRIBUTOS
    @Id //indica que id_multa es la clave primaria de la entidad
    @GeneratedValue(strategy = IDENTITY) //es autoincrement --> genera un id
    @Column(name="id_multa")
    private int id_multa;

    @Column(name = "precio")
    private String precio;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "matricula")
    private String matricula;

    @ManyToOne //utilizo Mto1 pq un coche (one) puede tener varias multas (many)
    @JoinColumn(name="matricula", referencedColumnName="matricula")
    //name es el atributo de la tabla multas que guarda la clave foránea (matricula)
    //referencedColumnName es el atributo en la tabla coches a la que apunta la clave foránea (matricula en coches).
    private Coche coche;


    //CONSTRUCTOR
    public Multa() {
    }

    public Multa(int id_multa, String precio, LocalDate fecha, String matricula, Coche coche) {
        this.id_multa = id_multa;
        this.precio = precio;
        this.fecha = fecha;
        this.matricula = matricula;
        this.coche = coche;
    }


    //GETTER Y SETTER
    public int getId_multa() {
        return id_multa;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}//class
