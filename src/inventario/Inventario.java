package inventario;

import bd.MetodosBD;
import enums.Envase;
import enums.Sabor;
import excepciones.CantidadInvalidaException;
import excepciones.CapacidadInvalidaException;
import productos.Producto;

import java.sql.SQLException;

public class Inventario {
    public Producto buscarProductoPorID(int id) {
        Producto productoBuscado;
        productoBuscado = MetodosBD.getProductoByID(id);
        return productoBuscado;
    }

    public int existeProducto(Producto producto) {
        int id = -1;
        id = MetodosBD.existeProducto(producto);
        return id;
    }

    public void agregarProducto(Producto producto) {
        int cantidad = producto.getCantidad();
        int idExistente = existeProducto(producto);
        if (idExistente == -1) {
            MetodosBD.agregarProducto(producto);
            System.out.println("El producto fue creado con exito.");
            System.out.println("----------------------");
        } else {
            agregarCantidad(idExistente, cantidad);
            System.out.println("El producto ya existia, se ha cargado la cantidad.");
            System.out.println("-------------------------");
        }
        ;
    }

    public void agregarCantidad(int id, int cantidad) {
        Producto productoBuscado = this.buscarProductoPorID(id);
        if (productoBuscado != null) {
            productoBuscado.agregarCantidad(cantidad);
            MetodosBD.actualizarCantidadProducto(productoBuscado);
        } else {
            System.out.println("El producto no existe");
            System.out.println("-------------------------");
        }
    }

    public void retirarCantidad(int id, int cantidad) {
        Producto productoBuscado = this.buscarProductoPorID(id);
        if (productoBuscado != null) {
            try {
                productoBuscado.retirar(cantidad);
                MetodosBD.actualizarCantidadProducto(productoBuscado);
            } catch (CantidadInvalidaException e) {
                e.printStackTrace();
                System.out.println("La cantidad ingresada es invalida");
            }
        } else {
            System.out.println("El producto no existe");
            System.out.println("-------------------------");
        }
        System.out.println("La cantidad del producto se ha actualizado");
        System.out.println("-------------------------");

    }

    public void eliminarProducto(int id) {
        MetodosBD.eliminarProducto(id);
        System.out.println("El producto fue eliminado");
        System.out.println("-------------------------");
    }

    public void listarProductos() {
        System.out.println("==============================================");
        System.out.println("===============EXISTENCIAS====================");
        System.out.println("ID--ENVASE------SABOR-------CAPAC----CANT");
        System.out.println(MetodosBD.listarProductos());
        System.out.println("==============================================");
        System.out.println("==============================================");
    }

    public void crearProducto(Envase envase, Sabor sabor, int capacidad, int cantidad) {
        FabricaDeProductos fabricaDeProductos = new FabricaDeProductos();
        if (envase == Envase.LATA) {
            try {
                agregarProducto(fabricaDeProductos.crearLata(capacidad, sabor, cantidad));
            } catch (CapacidadInvalidaException e) {
                e.printStackTrace();
            }
        }
        if (envase == Envase.BOTELLA) {
            try {
                agregarProducto(fabricaDeProductos.crearBotella(capacidad, sabor, cantidad));
            } catch (CapacidadInvalidaException e) {
                e.printStackTrace();
            }
        }
    }

    public void filtrarYMostrar(int categoria, String valor){
        System.out.println("==============================================");
        System.out.println("ID--ENVASE------SABOR-------CAPAC----CANT");
        switch (categoria){
            case 1:
                System.out.println(MetodosBD.filtrar("tipoDeEnvase", valor));
                break;
            case 2:
                System.out.println(MetodosBD.filtrar("sabor",valor));
                break;
            case 3:
                System.out.println(MetodosBD.filtrar("capacidad",valor));
                break;
        }
        System.out.println("==============================================");
        System.out.println("==============================================");
    }
}

