package org.example.ad_entrega2_tiendacosmetica_javafx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.utilidades;

import java.net.URL;
import java.util.ResourceBundle;

public class ComprasController implements Initializable {

    //ATRIBUTOS
    @FXML
    private Button botonComprar;

    @FXML
    private Button botonAtras;

    @FXML
    private ComboBox<Integer> cantidadCB;

    @FXML
    private RadioButton cremaRB;

    @FXML
    private RadioButton labialRB;

    @FXML
    private ToggleGroup productos;

    @FXML
    private RadioButton serumRB;

    double precioCrema = 20.67;
    double precioLabial = 9.49;
    double precioSerum = 17.99;

    //MÉTODOS
    @FXML
    void onComprarClick(ActionEvent event) {
        utilidades.cambiarEscena(botonComprar, "Ticket.fxml");
    }//onComprarClick



    @FXML
    void onAtrasClick(ActionEvent event) {
        utilidades.cambiarEscena(botonAtras, "Principal.fxml");
    }//onAtrasClick



    //MÉTODO QUE SE EJECUTA CUANDO SE CARGA EL FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> cantidades = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); //creo una lista de números del 1 al 10
        cantidadCB.setItems(cantidades); //asigno la lista al ComboBox
        cantidadCB.setValue(1); ////establecer un valor predeterminado, el 1
    }//initialize
}//class