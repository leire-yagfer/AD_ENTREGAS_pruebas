package org.example.ad_entrega8_proyectofinalpartescolores.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ParteRojoController implements Initializable {

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
    private AnchorPane fondoRojo;

    @FXML
    private TextArea txt_descripcion;

    @FXML
    private TextField txt_expedienteAlumno;

    @FXML
    private TextField txt_nombreGrupo;


    //MÉTODOS
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fondoRojo.setStyle("-fx-background-color: #fa5757");
        bt_parteVerde.setStyle("-fx-background-color: #1ec703");
        bt_parteNaranja.setStyle("-fx-background-color: #fda22e");
        bt_parteRojo.setStyle("-fx-background-color: #ee0606");
    }//initialize
}//class
