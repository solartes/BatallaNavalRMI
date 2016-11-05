package sop_rmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Archivo {

    private BufferedWriter archivoEscritura;
    private BufferedReader archivoLectura;

    public void abrirArchivo(String nombre, boolean escritura) throws IOException {
        if (escritura == true) {
            this.archivoEscritura = new BufferedWriter(new FileWriter(nombre, true));
        } else {
            this.archivoLectura = new BufferedReader(new FileReader(nombre));
        }
    }

    public void escribirArchivo(String datos) throws IOException {
        archivoEscritura.write(datos);
        archivoEscritura.newLine();
    }

    public String leerArchivo() throws IOException {
        return archivoLectura.readLine();
    }

    public void cerrarArchivo() throws IOException {
        if (archivoEscritura != null) {
            archivoEscritura.close();
        }
        if (archivoLectura != null) {
            archivoLectura.close();
        }
    }

    public boolean puedeLeer() throws IOException {
        return archivoLectura.ready();
    }

    // Cantidad de Lineas
    public int contarLineas(String nombre) throws IOException {
        abrirArchivo(nombre, false);
        int lineas = 0;
        while (puedeLeer()) {
            leerArchivo();
            lineas++;
        }
        cerrarArchivo();
        return lineas;
    }

    public String[] leerContenido(String archivo) throws FileNotFoundException, IOException {
        String[] contenido = new String[contarLineas(archivo)];
        String linea;
        int i = 0;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((linea = b.readLine()) != null) {
            contenido[i] = linea;
            i++;
        }
        return contenido;
    }

    public static String obtenerRuta() {
        File miDir = new File(".");
        String ruta = "";
        try {
            ruta = miDir.getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruta;
    }

    public void escribir(String contenido, String nick) throws IOException {

        //String ruta = " C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\batallaNavalRMI\\batallaNavalRMI\\src\\servidor\\infoArchivos\\archivosUsers\\jugador_" + nick + ".txt"; 
        String ruta = obtenerRuta()+"\\src\\servidor\\infoArchivos\\archivosUsers\\jugador_" + nick + ".txt";
        File archivo = new File(ruta);
        BufferedWriter bw;

        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contenido);
        } else {
            archivo.createNewFile();
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contenido);
        }
        bw.close();
    }

}
