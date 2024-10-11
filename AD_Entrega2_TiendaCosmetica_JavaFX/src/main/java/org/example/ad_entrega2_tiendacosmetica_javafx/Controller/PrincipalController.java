package org.example.ad_entrega2_tiendacosmetica_javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.ad_entrega2_tiendacosmetica_javafx.Clases.Clientes;
import org.example.ad_entrega2_tiendacosmetica_javafx.DAO.ClientesDAO;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.utilidades;

public class PrincipalController {

    @FXML
    private Button botonEntrar;

    @FXML
    private Button botonRegistro;

    @FXML
    private Button botonSalir;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nombreTF;


    //MÉTODOS
    //método que cuando ya estoy registrado e introduzco las credenciales correctamente se pasa a la otra pantalla
    @FXML
    void onEntrarClick(ActionEvent event) {
        String nombre = nombreTF.getText();
        String email = emailTF.getText();

        //COMPRuebo QUE ESTAN TODOS LOS CAMPOS RELLENOS
        if (nombre.isEmpty() || email.isEmpty()) {
            utilidades.mostrarAlerta("Los campos han de estar rellenos.", Alert.AlertType.ERROR);
            return; //para que se reinicie el programa y no realice la comprobación siguiente
        }

        //COMPRruebo QUE LOS DATOS SON CORRECTOS CON LA BASE DE DATOS --> tengo un método con un boolean por lo que si es true, se añade correctamente y si es false salta una alerta
        if (ClientesDAO.verificacionDatos(nombre, email)) {
            //SI SE CUMPLE, PASo A LA OTRA VENTANA
            utilidades.cambiarEscena(botonEntrar, "Compras.fxml");
        } else {
            //si las credenciales no son correctas, muestro un mensaje de error
            utilidades.mostrarAlerta("Nombre de usuario o email incorrectos. Inténtelo de nuevo", Alert.AlertType.ERROR);
        }

        //BORRo LOS datos de los CAMPOS
        nombreTF.clear();
        emailTF.clear();
    }//onEntrarClick



    @FXML
    void onRegistrarseClick(ActionEvent event) {

        String nombre = nombreTF.getText();
        String email = emailTF.getText();

        //compruebo QUE TODOS LOS CAMPOS ESTÉN RELLENOS
        if (nombre.isEmpty() || email.isEmpty()) {
            utilidades.mostrarAlerta("Todos los campos deben estar rellenos.", Alert.AlertType.ERROR);
            return; //para que se reinicie el programa y no realice la comprobación siguiente
        }

        //compruebo QUE NO EXISTA UN EMAIL IGUAL --> en la base de datos ya es unique, por lo que da error, pero saco una alerta
        Clientes nuevoCliente = new Clientes(nombre, email);

        if (ClientesDAO.insertarCliente(nuevoCliente) > 0) { //llamo al metodo insertar cliente porque ahi se comprueba si existe ya el email o no insertado, y si se ha podido insertar el nuevo usuario, int columnasAfectadas será >0
            //pongo una alerta de registro correcto
            utilidades.mostrarAlerta("Cliente registrado correctamente.", Alert.AlertType.INFORMATION);
        }

        //BORRo LOS datos de los CAMPOS
        nombreTF.clear();
        emailTF.clear();
    }//onRegistrarseClick


    //método para salir de programa
    @FXML
    void onSalirClick(ActionEvent event) {
        utilidades.salir();
    }//onSalirClick
}//class
