package bd;

import enums.*;
import inventario.FabricaDeProductos;
import productos.Producto;
import java.sql.*;
public class MetodosBD {
    public static void agregarProducto(Producto producto) {

        String query = "INSERT INTO `prueba`.`producto` " +
                "(`idProducto`,`tipoDeEnvase`,`sabor`,`cantidad`,`capacidad`) " +
                "VALUES (?,?,?,?,?);";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.setInt(1, producto.getId());
            statement.setString(2, producto.getEnvase().name());
            statement.setString(3, producto.getSabor().name());
            statement.setInt(4, producto.getCantidad());
            statement.setInt(5, producto.getCapacidad());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");
        }
    }

    public static void eliminarProducto(int id) {

        String query = "DELETE FROM producto WHERE idProducto=?";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");
        }
    }

    public static String listarProductos() {

        String productos = "";
        String query = "SELECT * FROM producto";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productos = stringFormatter(set,productos);
            }
            if(!productos.isBlank()) productos = productos.substring(0, productos.length() - 1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");

        }
        return productos;
    }

    public static Producto getProductoByID(int id) {

        FabricaDeProductos fabrica = new FabricaDeProductos();
        Producto productoBuscado = null;
        String query = "SELECT * FROM producto WHERE idProducto = ?";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productoBuscado = fabrica.crearProducto(set.getInt("idProducto"),
                        set.getString("tipoDeEnvase"), set.getString("sabor"),
                        set.getInt("cantidad"),
                        set.getInt("capacidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");
        }
        return productoBuscado;
    }

    public static int getUltimoID() throws SQLException {

        int ultimoid = 0;
        String query = "SELECT max(idProducto) FROM producto";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                ultimoid = set.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");

        }
        return ultimoid;

    }

    public static void actualizarCantidadProducto(Producto producto) {

        String query = "UPDATE producto SET cantidad=? WHERE idProducto=?";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.setInt(1, producto.getCantidad());
            statement.setInt(2, producto.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");
        }

    }

    public static void limpiarInventario() {

        String query = "DELETE FROM producto";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");

        }
    }

    public static int existeProducto(Producto producto) {

        int idProductoExistente = -1;
        String query = "SELECT * FROM producto WHERE tipoDeEnvase=? AND sabor=? AND capacidad=?";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.setString(1, producto.getEnvase().name());
            statement.setString(2, producto.getSabor().name());
            statement.setInt(3, producto.getCapacidad());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                idProductoExistente = set.getInt("idProducto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");
        }
        return idProductoExistente;
    }
    public static String filtrar(String tipo, String valor){
        String productos = "";
        String query = "SELECT * FROM producto WHERE "+ tipo +" =?";
        try (Connection conexion = ConectarBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(query))
        {
            statement.setString(1,valor);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productos = stringFormatter(set,productos);
            }
            if(!productos.isBlank()){
                productos = productos.substring(0, productos.length() - 1);
            }else {
                productos = "No se han encontrado productos";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion a base de datos");

        }
        return productos;
    }
   private static String stringFormatter(ResultSet set,String base) throws SQLException {

       if (set.getString("sabor").equals("LIMA_LIMON")) {
           base += set.getInt("idProducto") + "\t" +
                   set.getString("tipoDeEnvase") +
                   "\t\t" + set.getString("sabor") +
                   "\t" + set.getInt("capacidad") +
                   "ml\t " + set.getInt("cantidad") + "\n";
       } else {
           base += set.getInt("idProducto") +
                   "\t" + set.getString("tipoDeEnvase") +
                   "\t\t" + set.getString("sabor") + "\t\t" +
                   set.getInt("capacidad") + "ml\t " +
                   set.getInt("cantidad") + "\n";
       }
        return base;
   }




}

