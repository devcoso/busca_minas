package com.mycompany.p1_buscaminas.Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.mycompany.p1_buscaminas.Dificultad;

public class Servidor {

    public static void main(String[] args) {

        final int puerto = 8000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");

            while (true) {
                Socket sc = servidor.accept();
                System.out.println("Cliente Conectado");

                DataInputStream in = new DataInputStream(sc.getInputStream());

                String respuesta = in.readUTF();

                System.out.println("Dificultad seleccionada: " + respuesta);

                Dificultad dificultad = new Dificultad(respuesta);
                
                Campo campo = new Campo(dificultad.n, dificultad.m, dificultad.minas);
                campo.show();

                sc.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
