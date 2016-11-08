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
public interface ServidorJugadoresInt extends Remote {
    
    boolean registrarJugador(JugadorInt objcllbck,String usuario) throws RemoteException;
    void notificar() throws RemoteException;
    boolean desconectarJugador(JugadorInt objcllbck,String usuario) throws RemoteException;
    boolean ingresarJugador(String nickName,String claveIngreso) throws RemoteException;
    void empezarPartida(String nick1,String nick2) throws RemoteException;
    boolean verificarTablero(String nick) throws RemoteException;
    void posicionBarcos(String nick) throws RemoteException;
    int disparo(Coordenada coordenada,String nick) throws RemoteException;
    boolean barcosOponente(String nick) throws RemoteException;
    
    
}
