package cliente;

import static cliente.GUILoginJugador.getNombre;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sop_rmi.Coordenada;

public class GUITableroDisparos extends JPanel {

    private JButton[][] matrizColores;
    private JLabel[][] matrizColores2;
    private int n;
    ImageIcon barco = new ImageIcon(GUIPrincipal.class.getResource("hundido4.gif"));
    ImageIcon explosion = new ImageIcon(GUIPrincipal.class.getResource("explosion2.gif"));
    ImageIcon fondo = new ImageIcon(GUIPrincipal.class.getResource("fondo.jpg"));
    ArrayList<Coordenada> puntos = new ArrayList();

    //creando los botones 
    public GUITableroDisparos(int n, boolean color) {
        if (color) {
            this.n = n;
            matrizColores = new JButton[n][n];
            this.setLayout(new GridLayout(n, n));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrizColores[i][j] = new JButton();
                    String nombre = new Integer(i).toString();
                    nombre += ",";
                    nombre += new Integer(j).toString();
                    //  el nombre me dice en que posicion estoy 
                    matrizColores[i][j].setActionCommand(nombre);
                    matrizColores[i][j].setBackground(new Color(255, 223, 223));
                    matrizColores[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    this.add(matrizColores[i][j]);
                }
            }
            this.addListeners(); // me sirve cuando le  doy clic al boton (para darme informacion de el )
            this.setSize(new Dimension(400, 400));
        } else {
            this.n = n;
            matrizColores2 = new JLabel[n][n];
            this.setLayout(new GridLayout(n, n));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrizColores2[i][j] = new JLabel();
                    String nombre = new Integer(i).toString();
                    nombre += ",";
                    nombre += new Integer(j).toString();
                    //  el nombre me dice en que posicion estoy 
                    matrizColores2[i][j].setIcon(fondo);
                    matrizColores2[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    this.add(matrizColores2[i][j]);
                }
            }
            this.setSize(new Dimension(400, 400));
        }

    }

    // mi dani este metodo se puede omitir solo lo puse para que mirara en q pos esta cuando se le de clic a un boton 
    public void addListeners() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizColores[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JButton evento = (JButton) evt.getSource();
                        String pos[] = evento.getActionCommand().split(",");
                        int resul = 5;
                        try {
                            resul = SingletonJugador.jugadorRemoto().disparo(new Coordenada(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])), getNombre());
                        } catch (RemoteException ex) {
                            Logger.getLogger(GUITableroDisparos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (resul == 1) {
                            JOptionPane.showMessageDialog(null, "Coordenada repetida", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (resul == 0) {
                            matrizColores[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])].setIcon(barco);
                        } else if (resul == -1) {
                            matrizColores[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])].setIcon(explosion);
                        }
                        //matrizColores[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])].setIcon(barco);
                        //puntos.add(new Coordenada(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
                        //System.out.println("valor de la posicion: " + evento.getActionCommand());

                    }
                });
            }
        }
    }

    void repint(Coordenada coordenada, boolean b) {
        if(b){
            matrizColores2[coordenada.getX()][coordenada.getY()].setIcon(barco);
        }else{
            matrizColores2[coordenada.getX()][coordenada.getY()].setIcon(explosion);
        }
    }

}
