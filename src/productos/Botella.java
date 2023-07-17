package productos;

import enums.Sabor;
import excepciones.CapacidadInvalidaException;
import enums.Envase;

import java.util.ArrayList;
import java.util.Arrays;

public class Botella extends Producto {

    private static ArrayList<Integer> capacidadesBotella = new ArrayList<Integer>(Arrays.asList(500, 1750, 2500));

    public Botella(int capacidad, Sabor sabor, int cantidad) throws CapacidadInvalidaException {

        super(sabor, cantidad);
        super.envase = Envase.BOTELLA;

        if (capacidadesBotella.contains(capacidad)) {
            super.capacidad = capacidad;
        } else {
            throw new CapacidadInvalidaException();
        }
    }

    public static void agregarCapacidad(int capacidad) {
        capacidadesBotella.add(capacidad);
    }

    public static void mostrarCapacidades(){
        for(Integer capacidadActual : capacidadesBotella){
            System.out.print(capacidadActual + " ");
        }
        System.out.println(" ");
    }


}
