package org.example.ad_entrega8_proyectofinalpartescolores.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.ad_entrega8_proyectofinalpartescolores.util.comprobacionesYcambioEscena;

import java.net.URL;
import java.util.ResourceBundle;

public class ParteVerdeController implements Initializable {

    //ATRIBUTOS
    @FXML
    private Button bt_crear;

    @FXML
    private Button bt_parteNaranja;

    @FXML
    private Button bt_parteRojo;

    @FXML
    private Button bt_parteVerde;

    @FXML
    private DatePicker dp_fechaParte;

    @FXML
    private AnchorPane fondoVerde;

    @FXML
    private TextArea txt_descripcion;

    @FXML
    private TextField txt_expedienteAlumno;

    @FXML
    private TextField txt_nombreGrupo;

    @FXML
    private TextField txt_profesor;


    //MÉTODOS
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }//initialize


    @FXML
    void onParteNaranjaClick(ActionEvent event) {
        comprobacionesYcambioEscena.cambiarEscena(bt_parteNaranja, "parteNaranja.fxml");
    }//onParteNaranjaClick


    @FXML
    void onParteRojoClick(ActionEvent event) {
        comprobacionesYcambioEscena.cambiarEscena(bt_parteRojo, "parteRojo.fxml");
    }//onParteRojoClick


    public void onCrearClick(){

    }//onCrearClick
}//class
