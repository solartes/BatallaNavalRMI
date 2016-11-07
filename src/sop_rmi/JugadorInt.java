/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lenovo
 */
public interface JugadorInt extends Remote{
    
   
    public void recibirContacto(String usuario) throws RemoteException;
    public String obtenerNombre() throws RemoteException;
    public void borrarListaDeContactos() throws RemoteException;
    public void modificarOponente(String oponente) throws RemoteException;
    public void mostrarPantalla() throws RemoteException;
    public boolean verificarTablero() throws RemoteException;//Camellarle aqui
    public void posicionarBarcos();
}

