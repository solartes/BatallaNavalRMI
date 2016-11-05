/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer_acer
 */
public class AdministradorImpl extends UnicastRemoteObject implements AdministradorInt {

    ArrayList<Usuario> jugadores;

    public AdministradorImpl() throws RemoteException {

        this.jugadores = new ArrayList<>();
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {

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
    public boolean ingresarAdministrador(String nickName, String claveIngreso) {
        System.out.println("Identificando administrador");
        String[] contenidoArchivo;
        boolean retorno=false;
        //String archivo = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\batallaNavalRMI\\batallaNavalRMI\\src\\servidor\\infoArchivos\\admin.txt";
        String archivo = Archivo.obtenerRuta()+"\\src\\servidor\\infoArchivos\\admin.txt";
        try {
            Archivo ObjArchivo = new Archivo();
            ObjArchivo.abrirArchivo(archivo, false);
            contenidoArchivo = ObjArchivo.leerContenido(archivo);
            if (nickName.equals(contenidoArchivo[0]) && claveIngreso.equals(contenidoArchivo[1])) {
                retorno=true;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error al leer el archivo");
        }
        return retorno;
    }

    @Override
    public boolean modificarAdministador(String login) {

        return false;

    }

    @Override
    public boolean modificarUsuario(String nickName) {

        boolean dato = true;
        for (int i = 0; i < jugadores.size(); i++) {

            if (jugadores.get(i).getNickName() == nickName) {

                //jugadores.set(i, "paola");
                dato = true;
            } else {
                dato = false;
            }
        }

        return dato;

    }

    @Override
    public boolean borrarUsuario(String nickName) {

        boolean dato = true;
        for (int i = 0; i < jugadores.size(); i++) {

            if (jugadores.get(i).getNickName() == nickName) {
                jugadores.remove(i);
                dato = true;
            } else {
                dato = false;
            }
        }

        return dato;
    }

    @Override
    public ArrayList<Usuario> listarJugadores() {
        return jugadores;
    }

}
