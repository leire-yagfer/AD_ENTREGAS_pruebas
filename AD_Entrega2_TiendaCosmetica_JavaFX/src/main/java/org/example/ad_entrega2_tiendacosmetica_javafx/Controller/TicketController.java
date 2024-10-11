package org.example.ad_entrega2_tiendacosmetica_javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.utilidades;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketController{

    //ATRIBUTOS
    @FXML
    private Button botonSalir;

    @FXML
    private Button botonVolverInicio;

    @FXML
    private TextArea resumenCompra;

    //creo un objeto de la clase ComprasController porque necesito un método de esa clase, el cual no es static



    //MÉTODOS
    //método que según se abre la pestaña muestra la compra
    public void mostrarTicket(ComprasController comprasController) {
        resumenCompra.setText("Ticket de compra \n"
                + comprasController.devolverNombreYCantidadProducto()
                + "\nPrecio total.................."
                + comprasController.cantidadComprada() + "€");
    }//initialize


    //método para volver a la página principal
    @FXML
    void onVolverInicioClick(ActionEvent event) {
        utilidades.cambiarEscena(botonVolverInicio, "Principal.fxml");
    }//onVolverInicioClick


    //método para salir de programa
    @FXML
    void onSalirClick(ActionEvent event) {
        utilidades.salir();
    }//onSalirClick
}//class