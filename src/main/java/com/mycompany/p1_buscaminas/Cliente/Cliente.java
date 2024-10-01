package com.mycompany.p1_buscaminas.Cliente;

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
            Conexion conexion = new Conexion(puerto, host);

            // Pide dificultad
            Menu menu = new Menu(conexion);
            while (menu.getDificultadName().isEmpty()) {
                Thread.sleep(100);
            }
            // Envia dificultad
            String dificultadName = menu.getDificultadName();
            conexion.sendDificultad(dificultadName);
            // Crea dificultad en base a lo enviado
            Dificultad dificultad = new Dificultad(dificultadName);
            // Inicia juego
            new Juego(dificultad.minas, dificultad.n, dificultad.m, conexion);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
