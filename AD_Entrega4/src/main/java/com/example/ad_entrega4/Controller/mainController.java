package com.example.ad_entrega4.Controller;

import com.example.ad_entrega4.DAO.cocheDAO;
import com.example.ad_entrega4.Model.coche;
import com.example.ad_entrega4.util.ComprobacionesYAlertas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    //ATRIBUTOS
    @FXML
    private TextField matriculaTF;

    @FXML
    private TextField marcaTF;

    @FXML
    private TextField modeloTF;

    @FXML
    private Button cancelarBoton;

    @FXML
    private TableColumn<coche, String> colMatricula;

    @FXML
    private TableColumn<coche, String> colMarca;

    @FXML
    private TableColumn<coche, String> colModelo;

    @FXML
    private TableColumn<coche, String> colTipo;

    @FXML
    private Button eliminarBoton;

    @FXML
    private Button guadarCambiosBoton;

    @FXML
    private Button nuevoBoton;

    @FXML
    private TableView<coche> tableViewCoches; //defino el tipo de datoq ue va a mostrar

    @FXML
    private ComboBox<String> tipoCB; //tipo de dato que almacena el ComboBox

    ObservableList<coche> cochesOL; //creo un ObservableList de tipo coche que va a almacenar todos los coches


    //MÉTODOS
    //método que me carga los datos del comboBox
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


        //llamo al método que se conecta a la BD --> crea el dataBase y la tabla coches
        cocheDAO.crearBD();

        //llamo al método que muestra los coches almacenados en la base de datos y los añado a la tableView
        ArrayList<coche> listarCoches = cocheDAO.mostrarCoches(); //creo un ArrayList para convertir a ObservableList
        cochesOL = FXCollections.observableArrayList(listarCoches);
        tableViewCoches.setItems(cochesOL);
    }//initialize


    //método que muestra los datos del coche seleccionado del tableView en los distintos TextFields y comboBox
    @FXML
    void onElegirCocheClick(MouseEvent event) {
        coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche
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

        if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipo.isEmpty()) {
            System.out.println("Todos los campos han de estar rellenos.");
        } else {
            //compruebo que la matrícula cumple con el formato
            if (!ComprobacionesYAlertas.matriculaValida(matricula)) {
                ComprobacionesYAlertas.mostrarAlerta("La matrícula debe tener el formato nnnnLLL.");
            } else { //si cumple
                coche cocheNuevo = new coche(matricula, marca, modelo, tipo);
                if (cocheDAO.insertarCoche(cocheNuevo) > 0) {
                    actualizarTabla(); //llamo al método que actualiza la tabla después de haber realizado la inserción
                    System.out.println("Coche insertado correctamente.");
                    //cuando elimino el coche, limpio los datos de los campos
                    matriculaTF.clear();
                    marcaTF.clear();
                    modeloTF.clear();
                    tipoCB.getSelectionModel().clearSelection();
                } else {
                    System.out.println("No se ha podido insertar el coche.");
                }//if-else
            }//if-else
        }//if-else
    }//onNuevoClick


    //método en el que si se han realizado cambios en los datos de algún coche, lo actualizo
    @FXML
    void onGuardarCambiosClick(ActionEvent event) {
        coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche

        //compruebo si hay algún coche seleccionado
        if (cocheSeleccionado == null) {
            ComprobacionesYAlertas.mostrarAlerta("No se ha seleccionado ningún coche.");
            return; //si no hay coche seleccionado, salgo del método
        }

        String matricula = matriculaTF.getText();
        String marca = marcaTF.getText();
        String modelo = modeloTF.getText();
        String tipo = tipoCB.getSelectionModel().getSelectedItem();

        //compruebo que la matricula introducida no se haya modificado, porque se debe mantener constante
        if (!Objects.equals(cocheSeleccionado.getMatricula(), matricula)) {
            ComprobacionesYAlertas.mostrarAlerta("La matrícula no puede ser modificada.");
            matriculaTF.setText(cocheSeleccionado.getMatricula()); //cambio a la matricula original en caso de modificarse
        }

        //verifico que los otros campos no estén vacíos
        if (marca.isEmpty() || modelo.isEmpty() || tipo == null) {
            ComprobacionesYAlertas.mostrarAlerta("Todos los campos deben estar rellenos.");
            return;
        }

        //actualizo los campos que pueden ser modificados
        cocheSeleccionado.setMarca(marca);
        cocheSeleccionado.setModelo(modelo);
        cocheSeleccionado.setTipo(tipo);

        if (cocheDAO.actualizarCoche(cocheSeleccionado) > 0) {
            actualizarTabla();
            System.out.println("Coche actualizado correctamente.");
        } else System.out.println("Error al actualizar los datos del coche.");
    }//onGuardarCambiosClick


    //método que limpia todos los campos, ya que se cancela lo que se estuviese queriendo hacer
    @FXML
    void onCancelarClick(ActionEvent event) {
        matriculaTF.clear();
        marcaTF.clear();
        modeloTF.clear();
        tipoCB.getSelectionModel().clearSelection();
    }//onCancelarClick


    //método que elimina el coche seleccionado
    @FXML
    void onEliminarClick(ActionEvent event) {
        coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche
        if (cocheDAO.eliminarCoche(cocheSeleccionado) > 0) { //si hay un coche seleccionado
            actualizarTabla();
            System.out.println("Coche eliminado correctamente.");
        } else System.out.println("No se ha podido eliminar el coche.");

        //cuando elimino el coche, limpio los datos de los campos
        matriculaTF.clear();
        marcaTF.clear();
        modeloTF.clear();
        tipoCB.getSelectionModel().clearSelection();
    }//onEliminarClick


    //método que actualiza los datos de la tabla después de realizar cambios
    void actualizarTabla() {
        ArrayList<coche> listarCoches = cocheDAO.mostrarCoches();
        tableViewCoches.getItems().setAll(listarCoches);
    }//actualizarTabla


}//class