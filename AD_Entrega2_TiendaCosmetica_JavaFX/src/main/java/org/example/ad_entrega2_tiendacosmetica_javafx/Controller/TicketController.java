package org.example.ad_entrega2_tiendacosmetica_javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.utilidades;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {

    //ATRIBUTOS
    @FXML
    private Button botonSalir;

    @FXML
    private Button botonVolverInicio;

    @FXML
    private TextArea resumenCompra;

    //creo un objeto de la clase ComprasController porque necesito un método de esa clase, el cual no es static
    ComprasController comprasController;


    //MÉTODOS
    //método que según se abre la pestaña muestra la compra
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double precioCompra = comprasController.cantidadComprada();
        resumenCompra.setText(String.valueOf(precioCompra)); //en el textArea no puedo poner un texto con valor double, sino que tiene que ser String, por lo que lo "casteo"
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