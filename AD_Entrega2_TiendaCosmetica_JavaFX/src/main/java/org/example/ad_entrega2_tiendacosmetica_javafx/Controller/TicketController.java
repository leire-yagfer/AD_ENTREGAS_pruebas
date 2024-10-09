package org.example.ad_entrega2_tiendacosmetica_javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.utilidades;

public class TicketController {

    //ATRIBUTOS
    @FXML
    private Button botonSalir;

    @FXML
    private Button botonVolverInicio;

    @FXML
    private TextArea resumenCompra;


    //MÉTODOS
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