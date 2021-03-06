/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sop_rmi.Archivo.obtenerRuta;

/**
 *
 * @author acer_acer
 */
public class AdministradorImpl extends UnicastRemoteObject implements AdministradorInt {

    ArrayList<Usuario> jugadores;
    Archivo ObjArchivo = new Archivo();

    public AdministradorImpl() throws RemoteException {

        this.jugadores = new ArrayList<>();
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        System.out.println("Registro de Usuario");
        Archivo objArchivo = new Archivo();
        String cadena = usuario.getNombre() + ";" + usuario.getApellidos() + ";" + usuario.getNickName() + ";" + usuario.getClaveIngreso();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNickName().equals(usuario.getNickName())) {
                return false;
            }
        }
        try {
            objArchivo.escribir(cadena, usuario.getNickName());
        } catch (IOException ex) {

            Logger.getLogger(AdministradorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        jugadores.add(usuario);
        return true;
    }

    @Override
    public boolean registrarAdministador(String login, String contrasenia) {
        System.out.println("Registro de Usuario");
        Archivo objArchivo = new Archivo();
        String cadena = login + ";" + contrasenia;
        try {
            objArchivo.escribirAdmin(cadena);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(AdministradorImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ingresarUsuario(String nickName, String claveIngreso) {
        System.out.println("Iniciando sesion Usuario");
        boolean retorno = false;
        Usuario objUser = null;
        String[] contenidoArchivo;
        String ruta = obtenerRuta() + "\\src\\servidor\\infoArchivos\\archivosUsers";
        File directorio = new File(ruta);
        if (directorio.exists()) {
            File[] ficheros = directorio.listFiles();

            for (int x = 0; x < ficheros.length; x++) {
                try {
                    String nombreArch = ficheros[x].getName();
                    String rutaArchivo = obtenerRuta() + "\\src\\servidor\\infoArchivos\\archivosUsers\\" + nombreArch;
                    File archivo = new File(rutaArchivo);
                    ObjArchivo.abrirArchivo(rutaArchivo, false);
                    contenidoArchivo = ObjArchivo.leerContenido(rutaArchivo);

                    for (int i = 0; i < contenidoArchivo.length; i++) {
                        String[] contenido = contenidoArchivo[0].split(";");
                        if (nickName.equals(contenido[2]) && claveIngreso.equals(contenido[2])) {
                            retorno = true;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            System.out.println(" El directorio no Existe");
        }
        return retorno;
    }

    @Override
    public boolean ingresarAdministrador(String nickName, String claveIngreso) {
        System.out.println("Identificando administrador");
        String[] contenidoArchivo;
        boolean retorno = false;
        //String archivo = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\batallaNavalRMI\\batallaNavalRMI\\src\\servidor\\infoArchivos\\admin.txt";
        String archivo = Archivo.obtenerRuta() + "\\src\\servidor\\infoArchivos\\admin.txt";
        try {
            ObjArchivo.abrirArchivo(archivo, false);

            contenidoArchivo = ObjArchivo.leerContenido(archivo);
            for (int i = 0; i < contenidoArchivo.length; i++) {
                String[] contenido = contenidoArchivo[0].split(";");
                if (nickName.equals(contenido[0]) && claveIngreso.equals(contenido[1])) {
                    retorno = true;
                }
            }

        } catch (Exception e) {
            System.err.println("Ocurrio un error al leer el archivo");
        }
        return retorno;
    }

    @Override
    public boolean modificarAdministador(String login, String contrasenia) {

        System.out.println("Modificar Administrador");
        boolean modificar = false;
        System.out.println("Modificando Datos de un Administrador");
        File archivo = new File(obtenerRuta() + "\\src\\servidor\\infoArchivos\\admin.txt");
        System.out.println("Borra datos del archivo para modificar el administrador");

        if (ObjArchivo.borrarFichero(archivo)) {
            if (registrarAdministador(login, contrasenia)) {

                modificar = true;

            } else {
                modificar = false;
            }
        } else {
            modificar = false;
        }
        return modificar;

    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        System.out.println("Modificando Usuario");
        boolean modificar = false;
        System.out.println("Modificando Datos de un Usuario");
        File archivo = new File(obtenerRuta() + "\\src\\servidor\\infoArchivos\\archivosUsers\\jugador_" + usuario.getNickName() + ".txt");
        System.out.println("Borra datos de un archivo");

        if (ObjArchivo.borrarFichero(archivo)) {
            if (registrarUsuario(usuario)) {
                modificar = true;
            } else {
                modificar = false;
            }
        } else {
            modificar = false;
        }
        return modificar;
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) {
        File archivo = new File(obtenerRuta() + "\\src\\servidor\\infoArchivos\\archivosUsers\\jugador_" + usuario.getNickName() + ".txt");
        if (ObjArchivo.borrarFichero(archivo)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> listarJugadores() {
        return ObjArchivo.listar();
    }

}
