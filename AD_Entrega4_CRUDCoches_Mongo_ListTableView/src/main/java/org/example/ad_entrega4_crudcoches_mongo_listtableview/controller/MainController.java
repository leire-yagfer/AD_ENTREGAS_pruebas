package org.example.ad_entrega4_crudcoches_mongo_listtableview.controller;


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
import org.example.ad_entrega4_crudcoches_mongo_listtableview.model.Coche;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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
    private Button guadarCambiosBoton;

    @FXML
    private Button nuevoBoton;

    @FXML
    private TableView<Coche> tableViewCoches; //defino el tipo de datoq ue va a mostrar

    @FXML
    private ComboBox<String> tipoCB; //tipo de dato que almacena el ComboBox

    ObservableList<Coche> coches = FXCollections.observableArrayList(); //creo un ObservableList de tipo coche que va a almacenar todos los coches




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
        //añado coches
        Coche coche1 = new Coche("6666HHH", "Renault", "Clio", "Deportivo"); //creo el coche
        coches.add(coche1); //lo añado al ObservableList
        Coche coche2 = new Coche("5555BCD", "Ford", "SMax", "Familiar");
        coches.add(coche2);
        //agrego al tableView los coches creados
        tableViewCoches.setItems(coches);
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

    }//onNuevoClick


    //método en el que si se han realizado cambios en los datos de algún coche, lo actualizo
    @FXML
    void onGuardarCambiosClick(ActionEvent event) {

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
        Coche cocheSeleccionado = tableViewCoches.getSelectionModel().getSelectedItem(); //obtengo el coche seleccionado en el tableView y lo guardo en la variable cocheSeleccioando de tipo Coche
        if (cocheSeleccionado != null) { //si hay un coche seleccionado
            coches.remove(cocheSeleccionado);
        }//if

        //cuando elimino el coche, limpio los datos de los campos
        matriculaTF.clear();
        marcaTF.clear();
        modeloTF.clear();
        tipoCB.getSelectionModel().clearSelection();
    }//onEliminarClick


}//class