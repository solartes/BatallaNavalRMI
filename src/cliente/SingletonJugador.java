/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Naming;
import java.rmi.Remote;
import sop_rmi.ServidorJugadoresInt;

/**
 *
 * @author Lenovo
 */
public class SingletonJugador {
    
    //modifico pao
     private static ServidorJugadoresInt jugador;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    public static void jugadorRemoto(String dirIP, int puerto, String nameObjReg) {
        if (jugador == null) {
            jugador = (ServidorJugadoresInt) Singleton.ObtenerObjRemoto(dirIP, puerto, nameObjReg);
        }
    }

    public static ServidorJugadoresInt jugadorRemoto() {
        return jugador;
    }

    
    //termino de modificar pao
    
    

    public static Remote ObtenerObjRemoto(int puerto, String dirIP, String nameObjReg) {
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
