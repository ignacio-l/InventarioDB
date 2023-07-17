package productos;

import enums.Sabor;
import excepciones.CapacidadInvalidaException;
import enums.Envase;

import java.util.ArrayList;
import java.util.Arrays;

public class Lata extends Producto {
    private static ArrayList<Integer> capacidadesLata = new ArrayList<>(Arrays.asList(350, 493));

    public Lata(int capacidad, Sabor sabor, int cantidad) throws CapacidadInvalidaException {

        super(sabor, cantidad);
        super.envase = Envase.LATA;

        if (capacidadesLata.contains(capacidad)) {
            super.capacidad = capacidad;
        } else {
            throw new CapacidadInvalidaException();
        }
    }

    public static void agregarCapacidad(int capacidad) {
        capacidadesLata.add(capacidad);
    }

    public static void mostrarCapacidades(){
        for(Integer capacidadActual : capacidadesLata){
            System.out.print(capacidadActual + " ");
        }
        System.out.println(" ");
    }

}
