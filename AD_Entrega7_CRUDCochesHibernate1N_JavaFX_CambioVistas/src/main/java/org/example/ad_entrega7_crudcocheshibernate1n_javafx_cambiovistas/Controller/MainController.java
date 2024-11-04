package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private Button actualizarBoton;

    @FXML
    private Button borrarBoton;

    @FXML
    private TableColumn<?, ?> colMarca;

    @FXML
    private TableColumn<?, ?> colMatricula;

    @FXML
    private TableColumn<?, ?> colModelo;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private Button insertarBoton;

    @FXML
    private Button limpiarBoton;

    @FXML
    private TextField marcaTF;

    @FXML
    private TextField matriculaTF;

    @FXML
    private TextField modeloTF;

    @FXML
    private TableView<?> tableViewCoches;

    @FXML
    private ComboBox<?> tipoCB;

    @FXML
    private Button verMultasBoton;

    @FXML
    void onActualizarClick(ActionEvent event) {

    }

    @FXML
    void onBorrarClick(ActionEvent event) {

    }

    @FXML
    void onElegirCocheClick(MouseEvent event) {

    }

    @FXML
    void onInsertarClick(ActionEvent event) {

    }

    @FXML
    void onLimpiarClick(ActionEvent event) {

    }

    @FXML
    void onVerMultasClick(ActionEvent event) {
        //comprobar que hay un coche seleccionado
        //cambiar de vista

    }

}