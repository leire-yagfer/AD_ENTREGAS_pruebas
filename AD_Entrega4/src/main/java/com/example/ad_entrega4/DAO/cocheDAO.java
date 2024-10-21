package com.example.ad_entrega4.DAO;

import com.example.ad_entrega4.Model.coche;
import com.example.ad_entrega4.util.conexionBD;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class cocheDAO {
    static MongoClient con; //para establecer posteriormente la conexión con la base de datos
    static MongoCollection<Document> collection = null;

    static String json;
    static Document doc;

    //método en el que creo la BD y su colección (tabla) coches
    public static void crearBD() {
        try {
            con = conexionBD.conectar();
            //me conecto a la BD "Concesionario"
            MongoDatabase database = con.getDatabase("Concesionario");
            //creo una colección (tabla) llamada coches que va a guradar los atributos matrícula, marca, modelo y tipo
            database.createCollection("coches");
            collection = database.getCollection("coches");
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }//crearBD


    //metodo para mostrar los coches almacenados en la base de datos
    public static ArrayList<coche> mostrarCoches() {
        ArrayList<coche> cochesBD = new ArrayList<>();
        Gson gson = new Gson();
        for (Document doc : collection.find()) {
            coche coche = gson.fromJson(doc.toJson(), com.example.ad_entrega4.Model.coche.class);
            cochesBD.add(coche);
        }//for
        return cochesBD;
    } //mostrarCoches


    public static int insertarCoche(coche insertarCoche) { //le paso el coche creado con los datos introducidos en el método onNuevoClick
        Gson gson = new Gson();
        int semaforo = 0;
        if (insertarCoche != null) {
            json = gson.toJson(insertarCoche);
            doc = Document.parse(json);
            collection.insertOne(doc);
            semaforo = 1;
        }
        return semaforo;
    }//insertarCoche


    public static int eliminarCoche(coche eliminarCoche) {
        Gson gson = new Gson();
        int semaforo = 0;
        if (eliminarCoche != null) {
            json = gson.toJson(eliminarCoche);
            doc = Document.parse(json);
            collection.deleteOne(doc);
            semaforo = 1;
        }
        return semaforo;
    }//eliminarCoche


    public static int actualizarCoche(coche actualizarCoche) {
        int semaforo = 0;
        try {
            //uso Gson para convertir el objeto coche a JSON
            Gson gson = new Gson();
            String jsonCoche = gson.toJson(actualizarCoche);

            //parseo el JSON a un documento BSON
            Document docCoche = Document.parse(jsonCoche);

            //filtro: busco el coche en la colección por su matrícula
            Document filtro = new Document("matricula", actualizarCoche.getMatricula());

            //actualizaciones: solo se actualizan marca, modelo y tipo
            Document actualizaciones = new Document()
                    .append("marca", actualizarCoche.getMarca())
                    .append("modelo", actualizarCoche.getModelo())
                    .append("tipo", actualizarCoche.getTipo());

            //realizo la actualización (busca por matrícula y actualiza los demás campos)
            collection.updateOne(filtro, new Document("$set", actualizaciones));

            semaforo = 1; // Éxito
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());;
        }
        return semaforo;
    }//actualizarCoche


    //consulto en la BD la existencia de la mátricula introducida
    public static int existeMatricula(String matricula) {
        Document query = new Document("matricula", matricula);
        Document result = collection.find(query).first(); // Busca el primer documento que coincida con la matrícula
        return result != null ? 1 : 0; // Si hay un resultado, la matrícula ya existe (semaforo = 1)
    }
}//class
