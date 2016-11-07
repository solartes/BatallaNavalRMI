package cliente;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import sop_rmi.Coordenada;

public class GUITableroDisparos extends JPanel {

    private JButton[][] matrizColores;
    private int n;
    ImageIcon agua = new ImageIcon(GUIPrincipal.class.getResource("agua.gif"));
    ImageIcon barco = new ImageIcon(GUIPrincipal.class.getResource("barco1.gif"));
    ArrayList<Coordenada> puntos = new ArrayList();

    //creando los botones 
    public GUITableroDisparos(int n, boolean color) {
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
                if (color) {
                    matrizColores[i][j].setBackground(new Color(255, 223, 223));
                } else {
                    matrizColores[i][j].setBackground(new Color(223, 255, 240));
                    matrizColores[i][j].setEnabled(false);                    
                    
                }
                matrizColores[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                this.add(matrizColores[i][j]);
            }
        }
        this.addListeners(); // me sirve cuando le  doy clic al boton (para darme informacion de el )
        this.setSize(new Dimension(400, 400));
    }

    // mi dani este metodo se puede omitir solo lo puse para que mirara en q pos esta cuando se le de clic a un boton 
    public void addListeners() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizColores[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        JButton evento = (JButton) evt.getSource();
                        String pos[] = evento.getActionCommand().split(",");
                        matrizColores[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])].setIcon(barco);
                        puntos.add(new Coordenada(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
                        System.out.println("valor de la posicion: " + evento.getActionCommand());

                    }
                });
            }
        }
    }

    public ArrayList<Coordenada> getPuntos() {
        return puntos;
    }

}
