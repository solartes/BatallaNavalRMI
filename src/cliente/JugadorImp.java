/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sop_rmi.Archivo;
import static sop_rmi.Archivo.obtenerRuta;
import sop_rmi.Coordenada;
import sop_rmi.JugadorInt;
import sop_rmi.Usuario;

/**
 *
 * @author Lenovo
 */
public class JugadorImp extends UnicastRemoteObject implements JugadorInt {

    GUIPanelSesion GUIC;//implicitamente incluido el atributo nombr
    GUIBarcos Interfazbarcos;
    String jugadorOponente;
    ArrayList<Coordenada> barcos;
    ArrayList<Coordenada> disparosRealizados;
    int meDerribaron;
    int heDerribado;
    int dispFallidos;
    int dispRestantes;
    boolean turno;
    int salida;
    
    public JugadorImp(GUIPanelSesion GUIC) throws RemoteException {
        this.GUIC = GUIC;
    }

    @Override
    public void recibirContacto(String usuario) throws RemoteException {
        GUIC.fijarContacto(usuario);

    }

    @Override
    public String obtenerNombre() throws RemoteException {
        return GUIC.obtenerNombre();
    }

    @Override
    public void borrarListaDeContactos() throws RemoteException {
        GUIC.borrarLista();
    }

    @Override
    public void modificarOponente(String oponente) throws RemoteException {
        jugadorOponente=oponente;
    }

    @Override
    public void mostrarPantalla() throws RemoteException {
        GUIC.posisionarBarcos();
    }

    @Override
    public boolean verificarTablero() throws RemoteException {        
        return true;
    }

    @Override
    public void posicionarBarcos() {
        barcos=Interfazbarcos.getBarcos().getPuntos();
    }

}
