package inventario;

import enums.Sabor;
import excepciones.CapacidadInvalidaException;
import productos.Botella;
import productos.Lata;
import productos.Producto;

public interface IFabricaDeProductos {

    Botella crearBotella(int capacidad, Sabor sabor, int cantidad) throws CapacidadInvalidaException;

    Lata crearLata(int capacidad, Sabor sabor, int cantidad) throws CapacidadInvalidaException;

    Producto crearProducto(int id, String envase, String sabor, int cantidad, int capacidad) throws CapacidadInvalidaException;

}
