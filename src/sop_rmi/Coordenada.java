/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.io.Serializable;

/**
 *
 * @author acer_acer
 */
public class Coordenada implements Serializable {

    int x;
    int y;
    boolean acerto;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        boolean igual = false;

        if (object != null && object instanceof Coordenada) {
            if (this.x == ((Coordenada) object).x && this.y == ((Coordenada) object).y) {
                igual = true;
            }
        }

        return igual;
    }
}
