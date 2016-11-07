/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import sop_rmi.AdministradorImpl;
import sop_rmi.ServidorJugadoresImpl;

/**
 *
 * @author Lenovo
 */
public class Servidor {

    public static void main(String[] args) {
        try {
            args = new String[2];
            args[0] = "localhost";
            args[1] = "2020";
            arrancarNS((Integer.parseInt(args[1])));
            AdministradorImpl obj = new AdministradorImpl();
            System.out.println("Objeto instanciado:" + obj);
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/Administrador", obj);
            System.out.println("servidor Administrador registrado");
            // servidor de Jugadores 
            ServidorJugadoresImpl objServidorJug = new ServidorJugadoresImpl();
            System.out.println("Objeto instanciado del servidor de jugadores:" + objServidorJug);
            Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/ServidorJugadores", objServidorJug);
            System.out.println("servidor Jugadores registrado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void arrancarNS(int numPuertoRMI) throws RemoteException {
        try {
            Registry registro = LocateRegistry.getRegistry(numPuertoRMI);
            registro.list();
            System.out.println("El registro se ha obtenido y se encuentra escuchando por el puerto: " + numPuertoRMI);
        } catch (RemoteException e) {
            System.out.println("El registro RMI no se localizo en el puerto: " + numPuertoRMI);
            Registry registro = LocateRegistry.createRegistry(numPuertoRMI);
            System.out.println("El registro se ha creado en el puerto: " + numPuertoRMI);
        }
    }
}
