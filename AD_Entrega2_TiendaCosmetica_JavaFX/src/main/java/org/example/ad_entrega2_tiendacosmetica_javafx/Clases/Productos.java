package org.example.ad_entrega2_tiendacosmetica_javafx.Clases;

public class Productos {

    //ATRIBUTOS
    private int idProducto;
    private String nombreProducto;
    private String categoria;
    private double precio;
    private int stock;


    //CONSTRUCTOR --> sin id porque es auto-increment
    public Productos(String nombreProducto, String categoria, double precio, int stock) {
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }


    //GET Y SET
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    //TOSTRING para
}//class