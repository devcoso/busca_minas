package com.mycompany.p1_buscaminas.Servidor;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;

import com.mycompany.p1_buscaminas.CampoObjeto;
import com.mycompany.p1_buscaminas.MostrarObjeto;

public class Conexion {
    Socket sc;

    public Conexion(Socket sc) {
        this.sc = sc;
        System.out.println("Cliente Conectado");
    }

    public String getString() {
        try {
            DataInputStream in = new DataInputStream(sc.getInputStream());
            String respuesta = (String) in.readUTF();
            System.out.println("Dificultad seleccionada: " + respuesta);
            return respuesta;
        } catch (EOFException  e) {
            System.out.println("Fin de datos del cliente. Continuando...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MostrarObjeto getMostrar() {
        try {
            ObjectInputStream in = new ObjectInputStream(sc.getInputStream());
            MostrarObjeto respuesta = (MostrarObjeto) in.readObject();
            return respuesta;
        } catch (EOFException  e) {
            System.out.println("Fin de datos del cliente. Continuando...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendCampo(CampoObjeto campoObjeto) {    
        try {
            ObjectOutputStream out = new ObjectOutputStream(sc.getOutputStream());
            out.writeObject(campoObjeto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTime(int tiempo) {
        try {
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            out.writeInt(tiempo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            sc.close();
            System.out.println("Cliente Desconectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
