package com.mycompany.p1_buscaminas.Cliente;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

import com.mycompany.p1_buscaminas.MostrarObjeto;

public class Conexion {
    Socket sc;

    public Conexion(int puerto, String host) throws Exception {
        this.sc = new Socket(host, puerto);
        System.out.println("Conectado con el Servidor");
    }

    public void sendDificultad(String dificultadName) {
        try {
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(dificultadName);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMostrar(MostrarObjeto mostrar) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(sc.getOutputStream());
            out.writeObject(mostrar);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
