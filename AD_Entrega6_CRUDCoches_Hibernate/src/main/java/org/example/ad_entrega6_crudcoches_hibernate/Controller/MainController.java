package org.example.ad_entrega6_crudcoches_hibernate.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.ad_entrega6_crudcoches_hibernate.DAO.CocheDAOImpl;
import org.example.ad_entrega6_crudcoches_hibernate.Model.Coche;
import org.example.ad_entrega6_crudcoches_hibernate.Util.ComprobacionesYAlertas;
import org.example.ad_entrega6_crudcoches_hibernate.Util.HibernateUtil;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // ATRIBUTOS
    @FXML
    private TextField matriculaTF;
    @FXML
    private TextField marcaTF;
    @FXML
    private TextField modeloTF;
    @FXML
    private Button cancelarBoton;
    @FXML
    private TableColumn<Coche, String> colMatricula;
    @FXML
    private TableColumn<Coche, String> colMarca;
    @FXML
    private TableColumn<Coche, String> colModelo;
    @FXML
    private TableColumn<Coche, String> colTipo;
    @FXML
    private Button eliminarBoton;
    @FXML
    private Button guardarCambiosBoton;
    @FXML
    private Button nuevoBoton;
    @FXML
    private TableView<Coche> tableViewCoches;
    @FXML
    private ComboBox<String> tipoCB;

    private ObservableList<Coche> cochesOL; // ObservableList para almacenar coches

    Session session = HibernateUtil.getSession();
    CocheDAOImpl dao = new CocheDAOImpl();



    // MÉTODOS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarComboBox();
        inicializarTableView();
        cargarCoches(); // cargar coches al iniciar
    }

    private void inicializarComboBox() {
        ObservableList<String> tipoCoche = FXCollections.observableArrayList("SUV", "Monovolumen", "Deportivo", "Pick-up", "Familiar");
        tipoCB.setItems(tipoCoche);
    }

    private void inicializarTableView() {
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        cochesOL = FXCollections.observableArrayList();
        tableViewCoches.setItems(cochesOL); // Asignar la lista a la tabla
    }

    private void cargarCoches() {
        cochesOL.setAll(dao.mostrarCoches());
    }

    @FXML
    void onElegirCocheClick(MouseEvent event) {
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem();
        if (cocheSeleccionado != null) {
            matriculaTF.setText(cocheSeleccionado.getMatricula());
            marcaTF.setText(cocheSeleccionado.getMarca());
            modeloTF.setText(cocheSeleccionado.getModelo());
            tipoCB.getSelectionModel().select(cocheSeleccionado.getTipo());
        }
    }

    @FXML
    void onNuevoClick(ActionEvent event) {
        String matricula = matriculaTF.getText();
        String marca = marcaTF.getText();
        String modelo = modeloTF.getText();
        String tipo = tipoCB.getSelectionModel().getSelectedItem();

        if (validarDatos(matricula, marca, modelo, tipo)) {
            Coche cocheNuevo = new Coche(matricula, marca, modelo, tipo);
            if (dao.insertarCoche(cocheNuevo) > 0) {
                cargarCoches();
                limpiarCampos();
            } else {
                ComprobacionesYAlertas.mostrarAlerta("No se ha podido insertar el coche.");
            }
        }
    }

    private boolean validarDatos(String matricula, String marca, String modelo, String tipo) {
        if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipo == null) {
            ComprobacionesYAlertas.mostrarAlerta("Todos los campos deben estar rellenos.");
            return false;
        }
        if (!ComprobacionesYAlertas.validarMatricula(matricula)) {
            ComprobacionesYAlertas.mostrarAlerta("La matrícula debe tener 4 dígitos seguidos de 3 letras mayúsculas, excluyendo las letras de las matrículas de España.");
            return false;
        }
        return true;
    }

    @FXML
    void onEliminarClick(ActionEvent event) {
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem();
        if (cocheSeleccionado != null) {
            if (dao.eliminarCoche(cocheSeleccionado) > 0) {
                cargarCoches();
                limpiarCampos();
            } else {
                ComprobacionesYAlertas.mostrarAlerta("No se ha podido eliminar el coche.");
            }
        } else {
            ComprobacionesYAlertas.mostrarAlerta("No hay coche seleccionado.");
        }
    }

    @FXML
    void onGuardarCambiosClick(ActionEvent event) {
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem();
        if (cocheSeleccionado != null) {
            cocheSeleccionado.setMatricula(matriculaTF.getText());
            cocheSeleccionado.setMarca(marcaTF.getText());
            cocheSeleccionado.setModelo(modeloTF.getText());
            cocheSeleccionado.setTipo(tipoCB.getSelectionModel().getSelectedItem());

            if (dao.actualizarCoche(cocheSeleccionado) > 0) {
                cargarCoches();
                limpiarCampos();
            } else {
                ComprobacionesYAlertas.mostrarAlerta("No se ha podido actualizar el coche.");
            }
        } else {
            ComprobacionesYAlertas.mostrarAlerta("No hay coche seleccionado.");
        }
    }

    @FXML
    void onCancelarClick(ActionEvent event) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        matriculaTF.clear();
        marcaTF.clear();
        modeloTF.clear();
        tipoCB.getSelectionModel().clearSelection();
        tableViewCoches.getSelectionModel().clearSelection(); // limpiar selección de tabla
    }
}