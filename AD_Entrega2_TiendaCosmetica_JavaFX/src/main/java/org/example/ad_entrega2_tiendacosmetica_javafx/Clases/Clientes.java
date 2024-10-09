package org.example.ad_entrega2_tiendacosmetica_javafx.Clases;

public class Clientes {

    //ATRIBUTOS
    private int idCliente;
    private String nombreCliente;
    private String email;


    //CONSTRUCTOR --> sin id porque es auto-increment
    public Clientes(String nombreCliente, String email) {
        this.nombreCliente = nombreCliente;
        this.email = email;
    }


    //GET Y SET
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}//class