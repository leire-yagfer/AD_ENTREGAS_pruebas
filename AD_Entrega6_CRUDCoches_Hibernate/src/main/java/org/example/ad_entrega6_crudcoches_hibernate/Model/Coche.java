package org.example.ad_entrega6_crudcoches_hibernate.Model;

import javax.persistence.Entity; //importamos la anotacion Entity
import javax.persistence.Id; //importamos la anotacion Id
import javax.persistence.Table; //importamos la anotacion Table
import javax.persistence.Column; //importamos la anotacion Column

@Entity //indico que esta clase es una entidad de la BD
@Table(name = "Coche") //especifico el nombre de la tabla en la BD
public class Coche {

    //ATRIBUTOS
    @Id //especificamos que este atributo es la clave primaria
    @Column(name = "matricula", length = 15, nullable = false) //especificamos la columna
    private String matricula;

    @Column(name = "marca", length = 50, nullable = false) //especificamos la columna
    private String marca;

    @Column(name = "modelo", length = 50, nullable = false) //especificamos la columna
    private String modelo;

    @Column(name = "tipo", length = 50, nullable = false) //especificamos la columna
    private String tipo;


    //CONSTRUCTOR
    public Coche() {    } //constructor vacío requerido por Hibernate

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }


    //GET Y SET
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}//class