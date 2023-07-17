package inventario;

import enums.Envase;
import enums.Sabor;
import excepciones.CapacidadInvalidaException;
import productos.Botella;
import productos.Lata;
import productos.Producto;

import static enums.Envase.LATA;

public class FabricaDeProductos implements IFabricaDeProductos {

    public Botella crearBotella(int capacidad, Sabor sabor, int cantidad) throws CapacidadInvalidaException {
        return new Botella(capacidad, sabor, cantidad);
    }

    public Lata crearLata(int capacidad, Sabor sabor, int cantidad) throws CapacidadInvalidaException {
        return new Lata(capacidad, sabor, cantidad);
    }

    public Producto crearProducto(int id, String envase, String sabor, int cantidad, int capacidad) throws CapacidadInvalidaException {
        Producto productoCreado;
        if(envase == LATA.name()){
            productoCreado = crearLata(capacidad, Sabor.valueOf(sabor), cantidad);
        } else {
            productoCreado = crearBotella(capacidad,Sabor.valueOf(sabor),cantidad);
        }
        productoCreado.setID(id);
        return productoCreado;
    }
}
