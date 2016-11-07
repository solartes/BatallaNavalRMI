/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import cliente.GUIBarcos;
import cliente.GUIJuego;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface JugadorInt extends Remote{
       
    public void recibirContacto(String usuario) throws RemoteException;
    public String obtenerNombre() throws RemoteException;
    public String obtenerOponente() throws RemoteException;
    public ArrayList<Coordenada> obtenerDisparosRealizados() throws RemoteException;
    public ArrayList<Coordenada> obtenerBarcos() throws RemoteException;
    public void borrarListaDeContactos() throws RemoteException;
    public void modificarOponente(String oponente) throws RemoteException;
    public void mostrarPantalla() throws RemoteException;
    public boolean verificarTablero() throws RemoteException;//Camellarle aqui
    public void posicionarBarcos() throws RemoteException;
    void setInterfazbarcos(GUIBarcos Interfazbarcos) throws RemoteException;
    void setinterfazJuego(GUIJuego juego) throws RemoteException;
    public void repintar(Coordenada coordenada, boolean b) throws RemoteException;
   
}

