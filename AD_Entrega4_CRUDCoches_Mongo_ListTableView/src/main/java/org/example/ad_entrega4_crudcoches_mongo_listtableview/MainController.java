package org.example.ad_entrega4_crudcoches_mongo_listtableview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField MarcaTF;

    @FXML
    private Button cancelarBoton;

    @FXML
    private TableColumn<?, ?> colMarca;

    @FXML
    private TableColumn<?, ?> colMatricula;

    @FXML
    private TableColumn<?, ?> colModelo;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private Button eliminarBoton;

    @FXML
    private Button guadarCambiosBoton;

    @FXML
    private TextField matriculaTF;

    @FXML
    private TextField modeloTF;

    @FXML
    private Button nuevoBoton;

    @FXML
    private TableView<?> tableViewCoches;

    @FXML
    private ComboBox<?> tipoCB;

    @FXML
    void onCancelarClick(ActionEvent event) {

    }

    @FXML
    void onEliminarClick(ActionEvent event) {

    }

    @FXML
    void onGuardarCambiosClick(ActionEvent event) {

    }

    @FXML
    void onNuevoClick(ActionEvent event) {

    }

}
