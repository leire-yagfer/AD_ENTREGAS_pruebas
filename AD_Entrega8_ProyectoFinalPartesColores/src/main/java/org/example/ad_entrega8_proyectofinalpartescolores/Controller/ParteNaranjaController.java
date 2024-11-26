package org.example.ad_entrega8_proyectofinalpartescolores.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.ad_entrega8_proyectofinalpartescolores.DAO.ParteIncidenciasDAO;
import org.example.ad_entrega8_proyectofinalpartescolores.Model.ParteIncidencia;
import org.example.ad_entrega8_proyectofinalpartescolores.util.ComprobacionesYcambioEscena;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ParteNaranjaController implements Initializable {

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
    private Label nombre_profesor;

    @FXML
    private Label hora_actual;

    @FXML
    private TextArea txt_descripcion;

    @FXML
    private TextField txt_expedienteAlumno;

    @FXML
    private TextField txt_nombreGrupo;

    ParteIncidenciasDAO parteDAO = new ParteIncidenciasDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //obtengo la hora actual
        LocalTime horaActual = LocalTime.now();

        //formateo la hora al tipo hh:mm
        DateTimeFormatter horaFormateada = DateTimeFormatter.ofPattern("HH:mm");
        hora_actual.setText(horaActual.format(horaFormateada));
    }//initialize

    @FXML
    void onParteRojoClick(ActionEvent event) {
        ComprobacionesYcambioEscena.cambiarEscena(bt_parteRojo, "parteRojo.fxml");
    }//onParteRojoClick

    @FXML
    void onParteVerdeClick(ActionEvent event) {
        ComprobacionesYcambioEscena.cambiarEscena(bt_parteRojo, "parteVerde.fxml");
    }//onParteVerdeClick


    @FXML
    void onCrearParteNaranjaClick(ActionEvent event) {
        String numExpedienteAlumno = txt_expedienteAlumno.getText();
        String grupo = txt_nombreGrupo.getText();
        String nombreProfesor = nombre_profesor.getText();
        LocalDate diaParte = dp_fechaParte.getValue();
        LocalTime horaParte = LocalTime.parse(hora_actual.getText());
        String descripcion = txt_descripcion.getText();

        //comprobar que todos los campos estan rellenos -> solo en los que se pueden introducir datos
        if (numExpedienteAlumno.isEmpty() || diaParte == null || descripcion.isEmpty()){
            ComprobacionesYcambioEscena.mostrarAlerta(Alert.AlertType.ERROR, "Todos los campos deben estar rellenos.");
        } else { //si todos los campos están correctos -> creo el parte y lo introduzco en la BD
            ParteIncidencia parte = new ParteIncidencia();
            parteDAO.insertarParte(parte);
        }
        //poner el profesor en los label
        //comprobacion en tiempo real del expediente
    }//onCrearParteNaranjaClick
}//class
