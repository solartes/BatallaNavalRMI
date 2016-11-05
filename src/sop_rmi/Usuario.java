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
//Poner serializable
public class Usuario implements Serializable{
    String nombre;
    String apellidos;
    String nickName;
    String claveIngreso;

    public Usuario() {
        nombre="";
        apellidos="";
        nickName="";
        claveIngreso="";
    }

    public Usuario(String nombre, String apellidos, String nickName, String claveIngreso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nickName = nickName;
        this.claveIngreso = claveIngreso;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getClaveIngreso() {
        return claveIngreso;
    }

    public void setClaveIngreso(String claveIngreso) {
        this.claveIngreso = claveIngreso;
    }
    
    
}
