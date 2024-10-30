package org.example.ad_entrega6_crudcoches_hibernate_javafx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.ad_entrega6_crudcoches_hibernate_javafx.DAO.CocheDAO;
import org.example.ad_entrega6_crudcoches_hibernate_javafx.DAO.CocheDAOImpl;
import org.example.ad_entrega6_crudcoches_hibernate_javafx.Model.Coche;
import org.example.ad_entrega6_crudcoches_hibernate_javafx.Util.ComprobacionesYAlertas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final CocheDAO cocheDAO = new CocheDAOImpl();

    //ATRIBUTOS
    @FXML
    private TextField matriculaTF;
    @FXML
    private TextField marcaTF;
    @FXML
    private TextField modeloTF;
    @FXML
    private TableColumn<Coche, String> colMatricula;
    @FXML
    private TableColumn<Coche, String> colMarca;
    @FXML
    private TableColumn<Coche, String> colModelo;
    @FXML
    private TableColumn<Coche, String> colTipo;
    @FXML
    private TableView<Coche> tableViewCoches;
    @FXML
    private ComboBox<String> tipoCB;

    private ObservableList<Coche> cochesOL;

    //MÉTODOS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //COMBOBOX --> le inicializo con los tipos de coche que hay
        ObservableList<String> tipoCoche = FXCollections.observableArrayList("SUV", "Monovolumen", "Deportivo", "Pick-up", "Familiar"); //creo una lista con los tipos de coche
        tipoCB.setItems(tipoCoche); //asigno la lista al ComboBox

        //TABLEVIEW --> inicializo las columnas
        //inicializo las columnas del tableView (lo que hay entre "" es el getter de cada propiedad de la clase coche)
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        //cargo los coches de la BD en la tabla
        ArrayList<Coche> listarCoches = cocheDAO.mostrarCoches();
        cochesOL = FXCollections.observableArrayList(listarCoches);
        tableViewCoches.setItems(cochesOL);
    }//initialize


    //método que muestra los datos del coche seleccionado del tableView en los distintos TextFields y comboBox
    @FXML
    void onElegirCocheClick(MouseEvent event) {
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche
        if (cocheSeleccionado != null) { //si hay un coche seleccionado
            //pongo los datos del coche en los diferentes TextFields y comboBox
            matriculaTF.setText(cocheSeleccionado.getMatricula());
            marcaTF.setText(cocheSeleccionado.getMarca());
            modeloTF.setText(cocheSeleccionado.getModelo());
            tipoCB.getSelectionModel().select(cocheSeleccionado.getTipo());
        }//if
    }//onElegirCocheClick



    //método que añade el nuevo coche creado
    @FXML
    void onNuevoClick(ActionEvent event) {
        String matricula = matriculaTF.getText();
        String marca = marcaTF.getText();
        String modelo = modeloTF.getText();
        String tipo = tipoCB.getSelectionModel().getSelectedItem();

        if (validarCampos(matricula, marca, modelo, tipo) && validarMatriculaUnica(matricula)) {
            Coche cocheNuevo = new Coche(matricula, marca, modelo, tipo);
            if (cocheDAO.insertarCoche(cocheNuevo) > 0) {
                actualizarTabla(); //llamo al método que actualiza la tabla después de haber realizado la inserción
                limpiarCampos();
                System.out.println("Coche insertado correctamente.");
            } else {
                System.out.println("No se ha podido insertar el coche.");
            }//if-else
        }//if-else
    }//onNuevoClick



    //método en el que si se han realizado cambios en los datos de algún coche, lo actualizo
    @FXML
    void onGuardarCambiosClick(ActionEvent event) {
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche

        //compruebo si hay algún coche seleccionado
        if (cocheSeleccionado == null) {
            ComprobacionesYAlertas.mostrarAlerta("No se ha seleccionado ningún coche.");
            return; //si no hay coche seleccionado, salgo del método
        }//if

        String marca = marcaTF.getText();
        String modelo = modeloTF.getText();
        String tipo = tipoCB.getSelectionModel().getSelectedItem();

        //compruebo que la matricula introducida no se haya modificado, porque se debe mantener constante
        if (!Objects.equals(cocheSeleccionado.getMatricula(), matriculaTF.getText())) {
            ComprobacionesYAlertas.mostrarAlerta("La matrícula no puede ser modificada.");
            matriculaTF.setText(cocheSeleccionado.getMatricula()); //cambio a la matricula original en caso de modificarse
        } else if (validarCampos(cocheSeleccionado.getMatricula(), marca, modelo, tipo)) {
            //actualizo los campos que pueden ser modificados
            cocheSeleccionado.setMarca(marca);
            cocheSeleccionado.setModelo(modelo);
            cocheSeleccionado.setTipo(tipo);

            if (cocheDAO.actualizarCoche(cocheSeleccionado) > 0) {
                actualizarTabla();
                System.out.println("Coche actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar los datos del coche.");
            }//if-else
        }//if-elseif
    }//onGuardarCambiosClick



    //método que limpia todos los campos, ya que se cancela lo que se estuviese queriendo hacer
    @FXML
    void onCancelarClick(ActionEvent event) {
        limpiarCampos();
    }



    //método que elimina el coche seleccionado
    @FXML
    void onEliminarClick(ActionEvent event) {
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche
        //verifico que hay un coche seleccionado y si el método eliminar coche devuelve un 1, que indica que se va a proceder a la eliminación del coche
        if (cocheSeleccionado != null && cocheDAO.eliminarCoche(cocheSeleccionado) > 0) {
            actualizarTabla(); //actualizo la tabla
            limpiarCampos(); //limpio todos los campos
            System.out.println("Coche eliminado correctamente.");
        } else {
            System.out.println("No se ha podido eliminar el coche.");
        }
    }

    //validar campos de entrada
    private boolean validarCampos(String matricula, String marca, String modelo, String tipo) {
        if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipo == null) {
            ComprobacionesYAlertas.mostrarAlerta("Todos los campos han de estar rellenos.");
            return false;
        }
        if (!ComprobacionesYAlertas.validarMatricula(matricula)) {
            ComprobacionesYAlertas.mostrarAlerta("La matrícula debe tener el formato nnnnLLL.");
            return false;
        }
        return true;
    }

    //verifación de si la matrícula es única
    private boolean validarMatriculaUnica(String matricula) {
        if (cocheDAO.existeMatricula(matricula) > 0) {
            ComprobacionesYAlertas.mostrarAlerta("Esa matrícula ya está en uso. Pruebe con otra.");
            return false;
        }
        return true;
    }

    //método para actualizar la tabla después de realizar cambios
    private void actualizarTabla() {
        ArrayList<Coche> listarCoches = cocheDAO.mostrarCoches();
        tableViewCoches.getItems().setAll(listarCoches);
    }

    //método que limpia los campos de entrada
    private void limpiarCampos() {
        matriculaTF.clear();
        marcaTF.clear();
        modeloTF.clear();
        tipoCB.getSelectionModel().clearSelection();
    }
}