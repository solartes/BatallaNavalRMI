/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Naming;
import java.rmi.Remote;
import sop_rmi.AdministradorInt;

/**
 *
 * @author Lenovo
 */
public class Singleton {

    private static AdministradorInt admin;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    public static void adminRemoto(String dirIP, int puerto, String nameObjReg) {
        if (admin == null) {
            admin = (AdministradorInt) Singleton.ObtenerObjRemoto(dirIP, puerto, nameObjReg);
        }
    }

    public static AdministradorInt adminRemoto() {
        return admin;
    }

    // metodos getter y setter
    public static Remote ObtenerObjRemoto(String dirIP, int puerto, String nameObjReg) {
        String URLRegistro;
        URLRegistro = "rmi://" + dirIP + ":" + puerto + "/" + nameObjReg;
        try {
            return Naming.lookup(URLRegistro);
        } catch (Exception e) {
            System.out.println("Excepcion en obtención del objeto Remoto");
            return null;

        }
    }

}
