import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class merge {

    public void ordenarQ(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private void quicksort(int[] numeros, int izq, int der) {
        if (izq >= der) return;

        int pivoteIndex = (izq + der) / 2;
        int pivote = numeros[pivoteIndex];

        int indiceParticion = particionar(numeros, izq, der, pivote);

        quicksort(numeros, izq, indiceParticion - 1);
        quicksort(numeros, indiceParticion, der);
    }

    private int particionar(int[] numeros, int izq, int der, int pivote) {
        while (izq <= der) {
            while (numeros[izq] < pivote) izq++;
            while (numeros[der] > pivote) der--;
            if (izq <= der) {
                int aux = numeros[izq];
                numeros[izq] = numeros[der];
                numeros[der] = aux;
                izq++;
                der--;
            }
        }
        return izq;
    }

    public int[] leerArchivo(String nombreArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        List<Integer> numeros = new ArrayList<>();

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("\\s+|,");
            for (String parte : partes) {
                numeros.add(Integer.parseInt(parte.trim()));
            }
        }
        br.close();

        int[] arreglo = new int[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arreglo[i] = numeros.get(i);
        }
        return arreglo;
    }

    public static void main(String[] args) {
        Ordenador ordenador = new Ordenador();
        String[] archivos = {
            "valores_aleatorios_100.txt",
            "valores_aleatorios_500.txt",
            "valores_aleatorios_1000.txt",
            "valores_aleatorios_10000.txt",
            "valores_aleatorios_20000.txt"
        };

        double[] tiempos = new double[archivos.length];

        for (int i = 0; i < archivos.length; i++) {
            String archivo = archivos[i];
            try {
                int[] arreglo = ordenador.leerArchivo(archivo);

                long inicio = System.nanoTime();
                ordenador.ordenarQ(arreglo);
                long fin = System.nanoTime();

                tiempos[i] = (fin - inicio) / 1e9;
            } catch (IOException e) {
                System.err.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
                tiempos[i] = -1;
            }
        }

        System.out.println("Tiempos de ejecución en segundos:");
        for (int i = 0; i < tiempos.length; i++) {
            System.out.println(archivos[i] + ": " + (tiempos[i] >= 0 ? tiempos[i] : "Error al procesar"));
        }
    }
}