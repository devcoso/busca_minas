package com.mycompany.p1_buscaminas;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente {
    
    public static void main(String args[]) {

        final int puerto = 8000;
        final String host = "127.0.0.1";

        try {
            Socket sc = new Socket(host, puerto);
            Cliente obj = new Cliente();
            System.out.println("Conectado con el Servidor");

            Dificultad dif = new Dificultad();

            while (dif.getDificultad().isEmpty()) {
                Thread.sleep(100);
            }

            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            String dificultad = dif.getDificultad();
            System.out.println(dificultad);

            out.writeUTF(dif.getDificultad());
            
            DataInputStream in = new DataInputStream(sc.getInputStream());
            int venX = in.readInt();
            int venY = in.readInt();
            int mine = in.readInt();
            int tabX = in.readInt();
            int tabY = in.readInt();

            Juego inicio = new Juego(venX, venY, mine, tabX, tabY);
            
            sc.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
