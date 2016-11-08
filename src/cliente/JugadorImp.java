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
    
    ////////////////////////
    int dispRestantes = 10;
    int meDerribaron = 0;
    int heDerribado = 0;
    int dispFallidos = 0;
    ///////////////////////
    boolean turno;
    int salida;
    boolean barcPu;

    public JugadorImp(GUIPanelSesion GUIC) throws RemoteException {
        this.GUIC = GUIC;
        barcos = new ArrayList<>();
        disparosRealizados = new ArrayList<>();
        barcPu = false;
        turno = true;
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
        interfazJuego = juego;
    }

    @Override
    public void repintar(Coordenada coordenada, boolean b,int a) throws RemoteException {
        interfazJuego.repintarInterfaz(coordenada, b,a);
    }

    @Override
    public void barcosListos() throws RemoteException {
        barcPu = true;
    }

    @Override
    public boolean obtenerPosBarc() throws RemoteException {
        return barcPu;
    }

    @Override
    public boolean obtenerTurno() {
        return turno;
    }

    @Override
    public void cambiarTurno() {
        if (turno) {
            turno = false;
        } else {
            turno = true;
        }
    }

    @Override
    public void heDerribado() throws RemoteException {
        heDerribado++;
    }

    @Override
    public void dispRestantes() throws RemoteException {
        dispRestantes--;
    }

    @Override
    public void meDerribaron() throws RemoteException {
        meDerribaron++;
    }

    @Override
    public int getMeDerribaron() throws RemoteException {
        return meDerribaron;
    }

    @Override
    public int getDisparosRestantes() throws RemoteException {
        return dispRestantes;
    }

    @Override
    public void dispFallidos() throws RemoteException {
        dispFallidos++;
    }

    @Override
    public void agregar(Coordenada coordenada) throws RemoteException {
        disparosRealizados.add(coordenada);
    }

    @Override
    public int getHeHundido() throws RemoteException {
        return heDerribado;
    }

    @Override
    public int getMeHundieron() throws RemoteException {
        return meDerribaron;
    }

    @Override
    public int getDisparosFallidos() throws RemoteException {
        return dispFallidos;
    }

}
