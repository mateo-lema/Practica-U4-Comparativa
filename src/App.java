import java.util.Arrays;
import java.util.concurrent.Callable;

import controles.SortPersonaMethods;
import models.Persona;
import models.Resultado;

public class App {
    public static void main(String[] args) throws Exception {
        
        int size[] = { 10000, 50000, 100000 };

    }


    public static void ejecutarEscenarioDesordenado(int size, SortPersonaMethods metodos) {
    }

    public static void ejecutarEscenarioCasiOrdenado(int size, SortPersonaMethods metodos) {
        Persona[] base = generarPersonas(size);
        metodos.quickSort(base, 0, base.length - 1);

        Persona[] baseMasUno = Arrays.copyOf(base, base.length + 1);
        baseMasUno[baseMasUno.length - 1] = new Persona("Persona nueva", (int) (Math.random()));

        Persona[] copiaInsertion = base.clone();
        Persona[] copiaQuickSort = base.clone();

        Callable<Void> funcionInsertion = () -> {
            metodos.insertionSort(copiaQuickSort);
            return null;
        };

        Callable<Void> funcionQuickSort = () -> {
            metodos.quickSort(copiaQuickSort);
            return null;
        };


    }

    public static Persona[] generarPersonas(int cantidad) {
        Persona[] personas = new Persona[cantidad];

        for (int i = 0; i < cantidad; i++) {
            String nombre = "Persona " + (i + 1);
            int edad = (int) (Math.random() * 101);
            personas[i] = new Persona(nombre, edad);
        }
        return personas;
    }

}
