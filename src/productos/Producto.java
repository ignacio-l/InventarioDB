package productos;
import bd.MetodosBD;
import enums.Sabor;
import excepciones.CantidadInvalidaException;
import enums.Envase;

import java.sql.SQLException;

public abstract class Producto {

    protected static int contador;
    protected int id;
    protected Envase envase;
    protected int capacidad;
    protected Sabor sabor;
    protected int cantidad;

    public Producto(Sabor sabor, int cantidad) {
        this.sabor = sabor;
        this.cantidad = cantidad;
        this.id = contador;
        contador++;
    }

    static {
        try {
            contador = MetodosBD.getUltimoID() + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return this.id;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public Envase getEnvase() {
        return envase;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setID(int id){
        this.id = id;
    }

    public void agregarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public void retirar(int cantidad) throws CantidadInvalidaException {
        if (cantidad <= this.cantidad) {
            this.cantidad -= cantidad;
        } else {
            System.out.println("Cantidad Invalida");
            throw new CantidadInvalidaException();
        }
    }

    public String toString(){
        String s = "";
        if(this.sabor.equals("LIMA_LIMON")){
            s += this.id + "\t" + this.envase + "\t\t" + this.sabor + "\t" + this.capacidad + "ml\t " + this.cantidad + "\n";
        }else {
            s += this.id + "\t" + this.envase + "\t\t" + this.sabor + "\t\t" + this.capacidad + "ml\t " + this.cantidad + "\n";
        }
        return s;
    }

}
