import enums.*;
import inventario.*;
import productos.*;

import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);
    static boolean ok = true;

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        int opcion = -1;

        while (opcion != 0 && ok == true) {
            System.out.println("Elija una opcion para continuar");
            System.out.println("1 - Listar Productos");
            System.out.println("2 - Buscar Producto");
            System.out.println("3 - Agregar Producto");
            System.out.println("4 - Agregar Stock");
            System.out.println("5 - Retirar Stock");
            System.out.println("7 - Filtrar Productos");
            System.out.println("0 - Salir ");

            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    listarProductos(inventario);
                    break;
                case 2:
                    buscarProductoPorId(inventario);
                    break;
                case 3:
                    agregarProducto(inventario);
                    break;
                case 4:
                    agregarStock(inventario);
                    break;
                case 5:
                    retirarStock(inventario);
                    break;
                case 6:
                    eliminarProducto(inventario);
                    break;
                case 7:
                    filtrar(inventario);
                    break;
                case 0:
                    System.out.println("Programa Terminado");
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    System.out.println(" ");
            }

        }
    }

    private static Sabor elegirSabor() {
        Sabor sabor = null;
        System.out.println("Ingrese el Sabor (1-NARANJA; 2-LIMA-LIMON ; 3-COLA)");
        int opcion = input.nextInt();
        switch (opcion) {
            case 1:
                sabor = Sabor.NARANJA;
                break;
            case 2:
                sabor = Sabor.LIMA_LIMON;
                break;
            case 3:
                sabor = Sabor.COLA;
                break;
            default:
                System.out.println("Sabor invalido");
                ok = false;
        }
        return sabor;
    }

    private static Envase elegirEnvase() {
        System.out.println("Ingrese el tipo de producto(1-Lata  2-Botella)");
        int opcion = input.nextInt();
        Envase envase = null;
        switch (opcion) {
            case 1:
                envase = Envase.LATA;
                break;
            case 2:
                envase = Envase.BOTELLA;
                break;
            default:
                System.out.println("Valor Invalido");
                ok = false;
        }
        return envase;
    }

    private static int elegirCapacidad(Envase envase) {
        if (envase == Envase.LATA) {
            System.out.print("Capacidades disponibles: ");
            Lata.mostrarCapacidades();
        } else if (envase == Envase.BOTELLA) {
            System.out.print("Capacidades disponibles: ");
            Botella.mostrarCapacidades();
        }
        int capacidadElegida = input.nextInt();
        return capacidadElegida;
    }

    private static int elegirCapacidad(){
        System.out.println("Capacidades Disponibles:");
        System.out.print("Latas :");
        Lata.mostrarCapacidades();
        System.out.print("Botellas :");
        Botella.mostrarCapacidades();
        int capacidadElegida = input.nextInt();
        return capacidadElegida;
    }

    private static int elegirCantidad() {
        System.out.println("Ingrese la cantidad: ");
        int cantidad = input.nextInt();
        return cantidad;
    }

    private static void agregarStock(Inventario inventario) {
        System.out.println("Ingrese id del producto: ");
        int id = input.nextInt();
        System.out.println("Ingrese la cantidad a agregar: ");
        int cantidad = input.nextInt();
        inventario.agregarCantidad(id, cantidad);
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();
    }

    private static void agregarProducto(Inventario inventario) {
        Envase envase = elegirEnvase();
        Sabor sabor = elegirSabor();
        int capacidad = elegirCapacidad(envase);
        int cantidad = elegirCantidad();
        if (ok) {
            inventario.crearProducto(envase, sabor, capacidad, cantidad);
        }
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();

    }

    private static void retirarStock(Inventario inventario) {
        System.out.println("Ingrese id del producto: ");
        int id = input.nextInt();
        System.out.println("Ingrese la cantidad a retirar ");
        int cantidad = input.nextInt();
        inventario.retirarCantidad(id, cantidad);
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();
    }

    private static void eliminarProducto(Inventario inventario) {
        System.out.println("Ingrese id del producto: ");
        int id = input.nextInt();
        inventario.eliminarProducto(id);
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();
    }

    private static void listarProductos(Inventario inventario) {
        inventario.listarProductos();
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();
    }

    private static void buscarProductoPorId(Inventario inventario){
        System.out.println("Ingrese id del producto: ");
        int id = input.nextInt();
        System.out.println("ID--ENVASE------SABOR-------CAPAC----CANT");
        System.out.println(inventario.buscarProductoPorID(id).toString());
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();
    }

    private static void filtrar(Inventario inventario){
        System.out.println("Ingrese por que categoria desea filtrar: 1-Envase 2-Sabor 3-Capacidad");
        int categoria = input.nextInt();
        switch(categoria){
            case 1:
                String envase = elegirEnvase().name();
                inventario.filtrarYMostrar(categoria,envase);
                break;
            case 2:
                String sabor = elegirSabor().name();
                inventario.filtrarYMostrar(categoria,sabor);
                break;
            case 3:
                String capacidad = String.valueOf(elegirCapacidad());
                inventario.filtrarYMostrar(categoria,capacidad);
                break;
            default:
                System.out.println("Valor Invalido");
        }
        System.out.println("Ingrese cualquier caracter para volver al menu");
        String aux = input.next();
    }
}




