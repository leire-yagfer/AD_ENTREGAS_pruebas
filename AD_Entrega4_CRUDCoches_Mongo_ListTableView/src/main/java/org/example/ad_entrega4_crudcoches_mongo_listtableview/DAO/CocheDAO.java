package org.example.ad_entrega4_crudcoches_mongo_listtableview.DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.ad_entrega4_crudcoches_mongo_listtableview.util.ConexionBBDD;

public class CocheDAO {
    static MongoClient con; //para establecer posteriormente la conexión con la base de datos
    static MongoCollection<Document> collection = null;

    //método en el que creo la BD y su colección (tabla) coches
    public static void crearBD(){
        try {
            con = ConexionBBDD.conectar();
            //me conecto a la BD "Concesionario"
            MongoDatabase database= con.getDatabase("Concesionario");
            //creo una colección (tabla) llamada coches que va a guradar los atributos matrícula, marca, modelo y tipo
            database.createCollection("coches");
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }//crearBD


    //metodo para mostrar los dos primeros coche
}//class