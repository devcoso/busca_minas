package com.mycompany.p1_buscaminas;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

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

                
            	int n = 0, m = 0, minas = 0, venX = 0, venY = 0;

                if (respuesta.equals("Principiante")) {
                    n = 9;
                    m = 9;
                    minas = 10;
                    venX = 340;
                    venY = 360;

                } else if (respuesta.equals("Intermedio")) {
                    n = 16;
                    m = 16;
                    minas = 40;
                    venX = 550;
                    venY = 570;

                } else if (respuesta.equals("Experto")) {
                    n = 16;
                    m = 30;
                    minas = 90;
                    venX = 970;
                    venY = 570;
                }
                
                Campo campo = new Campo(n, m, minas);
                campo.show();
                
                ///////// Envio de parametros al cliente
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                out.writeInt(venX);
                out.flush();
                
                out.writeInt(venY);
                out.flush();
                
                out.writeInt(minas);
                out.flush();
                
                out.writeInt(n);
                out.flush();
                
                out.writeInt(m);
                out.flush();
                
                sc.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
