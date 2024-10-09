package org.example.ad_entrega2_tiendacosmetica_javafx.DAO;

import javafx.scene.control.Alert;
import org.example.ad_entrega2_tiendacosmetica_javafx.Clases.Clientes;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.comprobaciones;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.conexionBBDD;
import org.example.ad_entrega2_tiendacosmetica_javafx.Util.utilidades;

import java.sql.*;

public class ClientesDAO {

    //método para insertar clientes --> Registrarse
    public static int insertarCliente(Clientes clientes) {
        //llamo al método existeEmail para comprobar si el email introducido ya existe en la base de datos --> si ya existe saco una alerta y sino, lo registra
        if (comprobaciones.existeEmail(clientes.getEmail())) {
            utilidades.mostrarAlerta("Este correo electrónico ya está en uso. Pruebe con otro.", Alert.AlertType.ERROR);
            return 0;
        }

        int columnasAfectadas = 0;
        String sql = "INSERT INTO Clientes(nombre_cliente, email) VALUES (?,?) ";
        try (Connection connection = conexionBBDD.conectar()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //preparo la declaración para la consulta sql, incluyendo el retorno de la clave generada
            //establezco los parámetros de la consulta
            statement.setString(1, clientes.getNombreCliente());
            statement.setString(2, clientes.getEmail());
            columnasAfectadas = statement.executeUpdate(); //ejecuto la actualización y guardo el número de columnas afectadas
            ResultSet rs = statement.getGeneratedKeys(); //obtengo el id generado por la inserción
            while (rs.next()) {
                int idGenerado = rs.getInt(1);
                clientes.setIdCliente(idGenerado); //establezco el id generado en el objeto cliente
            }
        } catch (Exception e) {
            System.out.println("Error al insertar el cliente: " + e.getMessage());
        }
        return columnasAfectadas;
    }//insertarCliente



    //método para buscar y comprobar que el usuario y gmail son correctos cuando se pulsa el botón entrar
    public static boolean verificacionDatos(String nombreCliente, String emailCliente) {
        //consulta sql que busca un cliente cuyo nombre e email coincidan
        String sql = "SELECT COUNT(*) FROM Clientes WHERE nombre_cliente = ? AND email = ?";

        //utilizo un bloque try-with-resources para asegurar que los recursos se cierren correctamente al finalizar
        try (Connection connection = conexionBBDD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.println(emailCliente);
            //establezco los parámetros de la consulta
            statement.setString(1, nombreCliente);
            statement.setString(2, emailCliente);

            //ejecuto la consulta y obtengo el resultado en rs
            ResultSet rs = statement.executeQuery();

            //si el resultado contiene algún registro, las credenciales son correctas
            if (rs.next()) {
                return rs.getInt(1) > 0; // retorna true si se encontró un usuario con esos datos
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar los datos. " + e.getMessage());
        }

        //si ocurre un error o no se encuentra el usuario, obtengo false
        return false;
    }//verificacionDatos
}//class