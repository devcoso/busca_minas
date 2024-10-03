package com.mycompany.p1_buscaminas.Cliente;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mycompany.p1_buscaminas.CampoObjeto;
import com.mycompany.p1_buscaminas.MostrarObjeto;

public class Conexion {
    Socket sc;

    public Conexion(int puerto, String host) throws Exception {
        this.sc = new Socket(host, puerto);
        System.out.println("Conectado con el Servidor");
    }

    public void sendString(String string) {
        try {
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(string);
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

    public CampoObjeto getCampo() {
        try {
            ObjectInputStream in = new ObjectInputStream(sc.getInputStream());
            CampoObjeto respuesta = (CampoObjeto) in.readObject();
            return respuesta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTime() {
        try {
            DataInputStream in = new DataInputStream(sc.getInputStream());
            int respuesta = (int) in.readInt();
            return respuesta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void close() {
        try {
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
