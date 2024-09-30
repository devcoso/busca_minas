package com.mycompany.p1_buscaminas.Cliente;

import java.net.Socket;

import com.mycompany.p1_buscaminas.Dificultad;

import java.io.DataOutputStream;

public class Cliente {
    
    public static void main(String args[]) {
        try {
            Inicio inicio = new Inicio();
            int puerto = 0;
            String host = "";
            while ( host.isEmpty() || puerto == 0) {
                puerto = inicio.getPort();
                host = inicio.getHost();
                Thread.sleep(100);
            }

            Socket sc = new Socket(host, puerto);
            System.out.println("Conectado con el Servidor");

            Menu menu = new Menu();
            while (menu.getDificultadName().isEmpty()) {
                Thread.sleep(100);
            }

            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            String dificultadName = menu.getDificultadName();
            System.out.println(dificultadName);

            out.writeUTF(dificultadName);
            
            Dificultad dificultad = new Dificultad(dificultadName);

            Juego juego = new Juego(dificultad.venX, dificultad.venY, dificultad.minas, dificultad.n, dificultad.m);
            
            sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
