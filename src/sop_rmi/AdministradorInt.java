/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author acer_acer
 */
public interface AdministradorInt extends Remote{
    boolean registrarUsuario(Usuario usuario) throws RemoteException;
    boolean ingresarAdministrador(String login,String claveIngreso) throws RemoteException;
    boolean modificarAdministador(String login) throws RemoteException;
    boolean modificarUsuario(String nickName) throws RemoteException;
    boolean borrarUsuario(Usuario usuario) throws RemoteException;
    ArrayList<Usuario> listarJugadores() throws RemoteException;
}
