package org.example.ad_entrega4_crudcoches_mongo_listtableview.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
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
import org.example.ad_entrega4_crudcoches_mongo_listtableview.util.ConexionBBDD;

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
    private Button nuevoBoton;

    @FXML
    private TableView<?> tableViewCoches;

    @FXML
    private ComboBox<String> tipoCB; //tipo de dato que almacena el ComboBox

    MongoClient con;



    //MÉTODOS
    //método que me carga los datos del comboBox
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tipoCoche = FXCollections.observableArrayList("SUV", "Monovolumen", "Deportivo", "Pick-up"); //creo una lista con los tipos de coch que hay
        tipoCB.setItems(tipoCoche); //asigno la lista al ComboBox


        //CONEXIÓN A LA BBDD
        try {
            con = ConexionBBDD.conectar();


            //La clase MongoDatabase nos ofrece el método getDatabase() que nos permite seleccionar la base de datos
            //con la que queremos trabajar
            // Me conecto a la BD "taller" si NO existe la crea.
            MongoDatabase database= con.getDatabase("Concesionario");

/*
            //Comencemos creando una Colección (equivalente a una tabla para MongoDB) para nuestra base de datos.

            //Me devuelve una coleccion si no existe la crea
            MongoCollection<Document> collection = database.getCollection("personajes");

            // Eliminar la colección y empezar de nuevo

            collection.drop();
            System.out.println("La coleccion se ha borrado Correctamente.\n");
            //creo una nueva coleccion
            database.createCollection("personajes");
            System.out.println("Coleccion creada Satisfactoriamente.\n");


            Document mickeyMouse = new Document();
            Document charlieBrown = new Document();
            //crear un documento con los valores que se insertarán usando el método append()
            mickeyMouse.append("_id", 1)
                    .append("nombre", "Mickey Mouse")
                    .append("creador", new Document("nombre", "Walt").append("apellido", "Disney"))
                    .append("mascota", "Pluto");

            charlieBrown.append("_id", 2)
                    .append("nombre", "Charlie Brown")
                    .append("creador", new Document("nombre", "Charles").append("apellido", "Shultz"))
                    .append("mascota", "Snoopy");

            try {
                //La función ".insertOne()" se utiliza para insertar el documento en la colección.
                collection.insertOne(mickeyMouse);
                collection.insertOne(charlieBrown);
                System.out.println("Documento Insertado Correctamente. \n");
            } catch (MongoWriteException mwe) {
                if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                    System.out.println("El documento con esa identificación ya existe");
                }
            }

            // Tamaño de l coleccion
            System.out.println("Tamaño Collection: " + collection.count() + " documentos. \n");

            // Crear e insertar multiples documentos
            List<Document> documents = new ArrayList<Document>();
            for (int i = 3; i < 51; i++) {
                documents.add(new Document("_id", i)
                        .append("nombre", "")
                        .append("creador", "")
                        .append("mascota", "")
                );
            }
            collection.insertMany(documents);

            // Datos básicos de la recogida
            System.out.println("Tamaño Coleccion: " + collection.count() + " documentos. \n");

            // Modificar un documento
            // imprimir el tercer documento antes de actualizar.
            Document third = collection.find(Filters.eq("_id", 3)).first();
            System.out.println("Original third document:");
            System.out.println(third.toJson()); //imprimir en formato Json

            collection.updateOne(new Document("_id", 3), //criterio de búsqueda que MongoDB utiliza para encontrar el documento a modificar
                    new Document("$set", //creo un nuevo documento para los cambios
                            new Document("nombre", "Dilbert")
                                    .append("creador", new Document("nombre", "Scott").append("apellido", "Adams"))
                                    .append("mascota", "Dogbert"))
            );

            System.out.println("\n Modificar el 3 documento:");
            Document dilbert = collection.find(Filters.eq("_id", 3)).first();
            System.out.println(dilbert.toJson());

            // Encuentre e imprima TODOS los documentos de la colección
            System.out.println("\n Imprime los documentos.");

            MongoCursor<Document> cursor = collection.find().iterator(); //iterator: permite recuperar los resultados de la consulta uno por uno
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }

            } finally {
                cursor.close();
            }

            //Borrar datos
            System.out.println("\n Eliminar documentos con un id mayor o igual a 4.");

            //borro todos los documentos con id>4
            collection.deleteMany(Filters.gte("_id", 4)); //gte = comparador "mas grande que"

            // Encuentre e imprima TODOS los documentos de la colección
            System.out.println("\nImprime los documentos.");

            MongoCursor<Document> cursor2 = collection.find().iterator();
            try {
                while (cursor2.hasNext()) {
                    System.out.println(cursor2.next().toJson());
                }

            } finally {
                cursor2.close();
            }

            //me desconecto de la BD
            ConexionBBDD.desconectar(con);
            System.out.println("\n Desconectado de la BD.");
*/
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }




    }//initialize


    @FXML
    void onNuevoClick(ActionEvent event) {

    }//onNuevoClick

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

    @FXML
    void onEliminarClick(ActionEvent event) {

    }//onEliminarClick


}//class