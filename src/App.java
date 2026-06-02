import java.util.Arrays;
import java.util.concurrent.Callable;

import controles.SortPersonaMethods;
import models.Persona;
import models.Resultado;

public class App {

    public static void main(String[] args) throws Exception {

        int[] size = {10000, 50000, 100000};

        SortPersonaMethods metodos = new SortPersonaMethods();

        for (int i = 0; i < size.length; i++) {

            ejecutarEscenarioDesordenado(size[i], metodos);
            ejecutarEscenarioCasiOrdenado(size[i], metodos);

            System.out.println();
        }
    }

    public static void ejecutarEscenarioDesordenado(int size, SortPersonaMethods metodos) {

        Persona[] base = generarPersonas(size);

        Persona[] copiaInsercion = base.clone();
        Persona[] copiaQuickSort = base.clone();

        Callable<Void> insertion = () -> {
            metodos.insertionSort(copiaInsercion);
            return null;
        };

        Callable<Void> quick = () -> {
            metodos.quickSort(copiaQuickSort, 0, copiaQuickSort.length - 1);
            return null;
        };

        Resultado r1 = Benchmarking.medirTiempo(insertion, "Inserción","Desordenado",size);

        Resultado r2 = Benchmarking.medirTiempo(quick,"QuickSort","Desordenado",size);

        imprimirResultado(r1);
        imprimirResultado(r2);
    }

    public static void ejecutarEscenarioCasiOrdenado(int size, SortPersonaMethods metodos) {

    Persona[] base = generarPersonas(size);

    metodos.quickSort(base, 0, base.length - 1);

    Persona[] baseMasUno = Arrays.copyOf(base, base.length + 1);

    baseMasUno[baseMasUno.length - 1] = new Persona(
            "Persona nueva",
            (int) (Math.random() * 101)
    );

    Persona[] copiaInsercion = baseMasUno.clone();
    Persona[] copiaQuickSort = baseMasUno.clone();

    Callable<Void> insertion = () -> {
        metodos.insertionSort(copiaInsercion);
        return null;
    };

    Callable<Void> quick = () -> {
        metodos.quickSort(copiaQuickSort, 0, copiaQuickSort.length - 1);
        return null;
    };

    Resultado r1 = Benchmarking.medirTiempo(insertion,"Inserción","Casi ordenado + 1 persona",baseMasUno.length);

    Resultado r2 = Benchmarking.medirTiempo(
            quick,
            "QuickSort",
            "Casi ordenado + 1 persona",
            baseMasUno.length
    );

    imprimirResultado(r1);  
    imprimirResultado(r2);
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

    public static void imprimirResultado(Resultado resultado) {

    System.out.println(
            resultado.getEscenario() + " | " + resultado.getAlgoritmo() + " | "+ resultado.getSample() + " | "+ resultado.getTiempoMillis() + " ms"
    );
}
}