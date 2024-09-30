package com.mycompany.p1_buscaminas.Cliente;

import java.net.Socket;
import java.io.DataOutputStream;

import com.mycompany.p1_buscaminas.Dificultad;


public class Cliente {
    
    public static void main(String args[]) {
        try {
            // Inicia cliente y pide host y puerto
            Inicio inicio = new Inicio();
            int puerto = 0;
            String host = "";
            while ( host.isEmpty() || puerto == 0) {
                puerto = inicio.getPort();
                host = inicio.getHost();
                Thread.sleep(100);
            }

            // Conecta con el servidor
            Socket sc = new Socket(host, puerto);
            System.out.println("Conectado con el Servidor");

            // Pide dificultad
            Menu menu = new Menu();
            while (menu.getDificultadName().isEmpty()) {
                Thread.sleep(100);
            }
            // Envia dificultad
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            String dificultadName = menu.getDificultadName();
            out.writeUTF(dificultadName);
            // Crea dificultad en base a lo enviado
            Dificultad dificultad = new Dificultad(dificultadName);

            // Inicia juego
            Juego juego = new Juego(dificultad.minas, dificultad.n, dificultad.m);

            int status = 0;
            while (status == 0) {
                status = juego.getStatus();
                Thread.sleep(100);
            }
            
            sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
