/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sop_rmi.Archivo.obtenerRuta;

/**
 *
 * @author Lenovo
 */
public class ServidorJugadoresImpl extends UnicastRemoteObject implements ServidorJugadoresInt {

    List<JugadorInt> jugadoresEnLinea;
    List<JugadorInt> jugadoresJugando;
    List<String> datosJugadores;
    Archivo ObjArchivo;

    public ServidorJugadoresImpl() throws RemoteException {
        jugadoresEnLinea = new ArrayList<>();
        jugadoresJugando = new ArrayList<>();
        datosJugadores = new ArrayList<>();
        ObjArchivo = new Archivo();
    }

    @Override
    public boolean registrarJugador(JugadorInt objcllbck, String usuario) throws RemoteException {
        System.out.println("Registrando Jugador para iniciar partida ");
        boolean registro = true;
        if (!jugadoresEnLinea.contains(objcllbck)) {
            registro = jugadoresEnLinea.add(objcllbck);
            datosJugadores.add(usuario);
            notificar();
        }
        if (registro) {
            System.out.println("El usuario " + usuario + " se registro.");
        } else {
            System.out.println("El usuario " + usuario + " no se registro.");
        }
        return registro;
    }

    @Override
    public void notificar() throws RemoteException {
        System.out.println("Notificando Jugador para iniciar partida");
        for (JugadorInt jugador : jugadoresEnLinea) {
            jugador.borrarListaDeContactos();
            System.out.println("Jug" + jugador.obtenerNombre());
            for (JugadorInt usuario : jugadoresEnLinea) {
                if (usuario != jugador) {
                    System.out.println("Usu" + usuario.obtenerNombre());
                    jugador.recibirContacto(usuario.obtenerNombre());
                }
            }
        }

    }

    @Override
    public boolean desconectarJugador(JugadorInt objcllbck, String usuario) throws RemoteException {
        boolean registro = false;
        System.out.println("Desconectando el Jugador ");
        if (jugadoresEnLinea.contains(objcllbck) && datosJugadores.contains(usuario)) {
            jugadoresEnLinea.remove(objcllbck);
            datosJugadores.remove(usuario);
            notificar();
            registro = true;
        }
        return registro;

    }

    @Override
    public boolean ingresarJugador(String nickName, String claveIngreso) throws RemoteException {

        System.out.println("Iniciando sesion Usuario");

        boolean retorno = false;
        Usuario objUser = null;
        String[] contenidoArchivo;
        String ruta = obtenerRuta() + "\\src\\servidor\\infoArchivos\\archivosUsers";
        File directorio = new File(ruta);
        if (directorio.exists()) {
            File[] ficheros = directorio.listFiles();

            for (int x = 0; x < ficheros.length; x++) {
                try {
                    String nombreArch = ficheros[x].getName();
                    String rutaArchivo = obtenerRuta() + "\\src\\servidor\\infoArchivos\\archivosUsers\\" + nombreArch;
                    File archivo = new File(rutaArchivo);
                    ObjArchivo.abrirArchivo(rutaArchivo, false);
                    contenidoArchivo = ObjArchivo.leerContenido(rutaArchivo);

                    for (int i = 0; i < contenidoArchivo.length; i++) {
                        String[] contenido = contenidoArchivo[0].split(";");
                        if (nickName.equals(contenido[2]) && claveIngreso.equals(contenido[3])) {
                            retorno = true;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            System.out.println(" El directorio no Existe");
        }
        return retorno;

    }

    @Override
    public void empezarPartida(String nick1, String nick2) throws RemoteException {
        System.out.println("Jug1" + nick1);
        System.out.println("Jug2" + nick2);
        for (int i = 0; i < jugadoresEnLinea.size(); i++) {
            if (jugadoresEnLinea.get(i).obtenerNombre().equals(nick1)) {
                jugadoresJugando.add(jugadoresEnLinea.get(i));
                jugadoresEnLinea.get(i).modificarOponente(nick2);
                desconectarJugador(jugadoresEnLinea.get(i), jugadoresEnLinea.get(i).obtenerNombre());
            }
        }
        for (int i = 0; i < jugadoresEnLinea.size(); i++) {
            if (jugadoresEnLinea.get(i).obtenerNombre().equals(nick2)) {
                jugadoresEnLinea.get(i).modificarOponente(nick1);
                jugadoresJugando.add(jugadoresEnLinea.get(i));
                jugadoresEnLinea.get(i).mostrarPantalla();
                desconectarJugador(jugadoresEnLinea.get(i), jugadoresEnLinea.get(i).obtenerNombre());
            }
        }
        //Nick2 se le muestra la interfaz 
    }

    @Override
    public boolean verificarTablero(String nick) throws RemoteException {
        return true;
    }

    @Override
    public void posicionBarcos(String nick) throws RemoteException {
        for (int i = 0; i < jugadoresJugando.size(); i++) {
            if (jugadoresJugando.get(i).obtenerNombre().equals(nick)) {
                jugadoresJugando.get(i).posicionarBarcos();
            }
        }
    }

    @Override
    public int disparo(Coordenada coordenada, String nick) throws RemoteException {
        //Ver Turno
        JugadorInt oponente = null;
        JugadorInt jugador = null;
        try {
            for (int i = 0; i < jugadoresJugando.size(); i++) {
                //Quien tenga como oponente el que disparo
                if (jugadoresJugando.get(i).obtenerOponente().equals(nick)) {
                    oponente = jugadoresJugando.get(i);
                }
            }
            for (int i = 0; i < jugadoresJugando.size(); i++) {
                if (jugadoresJugando.get(i).obtenerNombre().equals(nick)) {
                    jugador = jugadoresJugando.get(i);
                }
            }
            //Error 1, coordenada repetida
            if (jugador.obtenerDisparosRealizados().contains(coordenada)) {
                return 1;
            } else if (oponente.obtenerBarcos().contains(coordenada)) {
                //Le dio al barco
                ArrayList<Coordenada> lista=oponente.obtenerBarcos();
                oponente.repintar(coordenada,true);
                jugador.obtenerDisparosRealizados().add(coordenada);
                return 0;                   
            } else {
                //no le dio 
                oponente.repintar(coordenada,false);
                jugador.obtenerDisparosRealizados().add(coordenada);
                return -1;
            }

        } catch (RemoteException ex) {
            Logger.getLogger(ServidorJugadoresImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

}
