package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.DAO.MultaDAOImpl;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Coche;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Multa;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Util.ComprobacionesAlertasCambioEscena;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultasController implements Initializable {


    //ATRIBUTOS
    @FXML
    private Button actualizarBoton;

    @FXML
    private Button atrasBoton;

    @FXML
    private Button borrarBoton;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colIdMulta;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private DatePicker idFechaDatePicker;

    @FXML
    private TextField idMultaTF;

    @FXML
    private TextField idPrecioTF;

    @FXML
    private Button insertarBoton;

    @FXML
    private Button limpiarBoton;

    @FXML
    private TextField matriculaTF;

    @FXML
    private TableView<Multa> tableViewMultas;


    Coche cocheSelected;

    MultaDAOImpl multasDAO = new MultaDAOImpl();

    ObservableList<Multa> listadoMultas;


    //MÉTODOS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TABLEVIEW --> inicializo las columnas
        colIdMulta.setCellValueFactory(new PropertyValueFactory<>("id_multa"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }//initialize


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


    public void datosCocheMulta(Coche coche) {
        cocheSelected = coche;
        matriculaTF.setText(coche.getMatricula()); //pongo la matricula del coche seleccionado en la clase MainController pasado a través del método del cambio de escena

        //muestro las listaMultas del coche seleccionado en la tabla
        List<Multa> listaMultas = multasDAO.mostrarMultasCocheSeleccionado(cocheSelected);
        listadoMultas = FXCollections.observableArrayList(listaMultas);
        tableViewMultas.setItems(listadoMultas);
    }//datosCocheMulta


    @FXML
    void onLimpiarClick(ActionEvent event) {

    }

    @FXML
    void onVoloverAtrasClick(ActionEvent event) {
        ComprobacionesAlertasCambioEscena.cambiarEscena(atrasBoton, "main.fxml");
    }
}//class