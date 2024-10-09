package org.example.ad_entrega2_tiendacosmetica_javafx.Clases;

import java.time.LocalDateTime;

public class Ventas {

    //ATRIBUTOS
    private int idVenta;
    private int idCliente;
    private int idProducto;
    private LocalDateTime fecha_venta; //fecha con hora
    private int cantidad;
    private double total;


    //CONSTRUCTOR
    public Ventas(int idVenta, int idCliente, int idProducto, LocalDateTime fecha_venta, int cantidad, double total) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.fecha_venta = fecha_venta;
        this.cantidad = cantidad;
        this.total = total;
    }


    //GET Y SET
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}//class
