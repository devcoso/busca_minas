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

                
            	int n = 0, m = 0, banderas = 0, venX = 0, venY = 0;

                if (respuesta.equals("Principiante")) {
                    n = 9;
                    m = 9;
                    banderas = 10;
                    venX = 340;
                    venY = 360;

                } else if (respuesta.equals("Intermedio")) {
                    n = 16;
                    m = 16;
                    banderas = 40;
                    venX = 550;
                    venY = 570;

                } else if (respuesta.equals("Experto")) {
                    n = 16;
                    m = 30;
                    banderas = 90;
                    venX = 970;
                    venY = 570;
                }

                int[][] matriz = new int[n][m];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        matriz[i][j] = 0;
                    }
                }

                Random r = new Random();
                int banderasColocadas = 0;

                while (banderasColocadas < banderas) {
                    int r1 = r.nextInt(n);
                    int r2 = r.nextInt(m);

                    if (matriz[r1][r2] != -1) {
                        matriz[r1][r2] = -1;
                        banderasColocadas++;
                    }
                }

                System.out.println("Tablero con minas generado:");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println();
                }

                System.out.println("\n\n");

                int[][] tablero = new int[n][m];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {

                    	int minas = 0;

                    	for (int x = -1; x <= 1; x++) {
                    		for (int y = -1; y <= 1; y++) {
                    			if (x == 0 && y == 0) {
                    				continue; 
                    			}

                    			int newX = i + x;
                    			int newY = j + y;

                    			if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    				if (matriz[newX][newY] == -1) {
                    					minas++;
                    				}
                    			}
                    		}
                    	}

                    	tablero[i][j] = minas; 
                    }
                }

                System.out.println("Tablero final con conteo de minas:");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                            System.out.print(tablero[i][j] + " ");
                    }
                    System.out.println();
                }
              
                
                ///////// Envio de parametros al cliente
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                out.writeInt(venX);
                out.flush();
                
                out.writeInt(venY);
                out.flush();
                
                out.writeInt(banderas);
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
