/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sop_rmi.AdministradorImpl;
import sop_rmi.Archivo;
import sop_rmi.Usuario;

/**
 *
 * @author acer_acer
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Archivo arch=new Archivo();
        try {
            AdministradorImpl adm=new AdministradorImpl();
            System.out.println(""+adm.borrarUsuario(new Usuario("julian", "solarte", "pino", "1234")));
            Thread.sleep(4000);
        } catch (RemoteException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
