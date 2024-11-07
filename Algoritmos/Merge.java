import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Merge {

    public void ordenarM(int[] array) {
        mergesort(array, 0, array.length - 1);
    }

    private void mergesort(int[] numeros, int izq, int der) {
        if (izq >= der) return;

        int medio = (izq + der) / 2;

        mergesort(numeros, izq, medio);
        mergesort(numeros, medio + 1, der);

        mezclar(numeros, izq, medio, der);
    }

    private void mezclar(int[] numeros, int izq, int medio, int der) {
        int n1 = medio - izq + 1;
        int n2 = der - medio;

        int[] izqArray = new int[n1];
        int[] derArray = new int[n2];

        for (int i = 0; i < n1; i++) izqArray[i] = numeros[izq + i];
        for (int j = 0; j < n2; j++) derArray[j] = numeros[medio + 1 + j];

        int i = 0, j = 0, k = izq;

        while (i < n1 && j < n2) {
            if (izqArray[i] <= derArray[j]) {
                numeros[k] = izqArray[i];
                i++;
            } else {
                numeros[k] = derArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            numeros[k] = izqArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            numeros[k] = derArray[j];
            j++;
            k++;
        }
    }

    public int[] leerArchivo(String rutaArchivo) throws IOException {
        List<Integer> listaNumeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) listaNumeros.add(Integer.parseInt(linea.trim()));
        }
        return listaNumeros.stream().mapToInt(Integer::intValue).toArray();
    }

    public void escribirArchivo(String rutaArchivo, int[] array) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int num : array) bw.write(num + "\n");
        }
    }

    public void escribirTiempos(String rutaArchivo, List<String> tiempos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String tiempo : tiempos) bw.write(tiempo + "\n");
        }
    }

    public static void main(String[] args) {
        Merge merge = new Merge();

        String[] archivosEntrada = {
            "valores_aleatorios_100.txt",
            "valores_aleatorios_500.txt",
            "valores_aleatorios_1000.txt",
            "valores_aleatorios_10000.txt",
            "valores_aleatorios_20000.txt"
        };

        String carpetaEntrada = "ruta/de/la/carpeta/txt_files/";
        String carpetaSalida = "ruta/de/la/carpeta/salida/";
        String archivoTiempos = carpetaSalida + "tiempos_ejecucion.txt";

        List<String> tiempos = new ArrayList<>();

        for (String archivo : archivosEntrada) {
            try {
                String rutaArchivoEntrada = carpetaEntrada + archivo;
                int[] datos = merge.leerArchivo(rutaArchivoEntrada);

                long inicio = System.nanoTime();
                merge.ordenarM(datos);
                long fin = System.nanoTime();
                long duracion = fin - inicio;

                String rutaArchivoSalida = carpetaSalida + "ordenado_" + archivo;
                merge.escribirArchivo(rutaArchivoSalida, datos);

                tiempos.add("Archivo: " + archivo + " - Tiempo de ejecución: " + duracion + " nanosegundos");
                System.out.println("Archivo procesado: " + archivo);
            } catch (IOException e) {
                System.err.println("Error procesando el archivo " + archivo + ": " + e.getMessage());
            }
        }

        try {
            merge.escribirTiempos(archivoTiempos, tiempos);
            System.out.println("Tiempos de ejecución guardados en: " + archivoTiempos);
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo de tiempos: " + e.getMessage());
        }
    }
}
