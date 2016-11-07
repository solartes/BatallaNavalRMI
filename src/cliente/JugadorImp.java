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
    GUIBarcos interfazBarcos;
    GUIJuego interfazJuego;
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
        barcos = new ArrayList<>();
        disparosRealizados = new ArrayList<>();
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
        jugadorOponente = oponente;
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
    public void posicionarBarcos() throws RemoteException {
        barcos = interfazBarcos.getBarcos().getPuntos();
    }

    @Override
    public String obtenerOponente() throws RemoteException {
        return jugadorOponente;
    }

    @Override
    public ArrayList<Coordenada> obtenerBarcos() throws RemoteException {
        return barcos;
    }

    @Override
    public ArrayList<Coordenada> obtenerDisparosRealizados() throws RemoteException {
        return disparosRealizados;
    }

    @Override
    public void setInterfazbarcos(GUIBarcos Interfazbarcos) {
        this.interfazBarcos = Interfazbarcos;
    }


    @Override
    public void setinterfazJuego(GUIJuego juego) throws RemoteException {
        interfazJuego=juego;
    }

    @Override
    public void repintar(Coordenada coordenada, boolean b) throws RemoteException {
        interfazJuego.repintarInterfaz(coordenada,b);
    }

}
