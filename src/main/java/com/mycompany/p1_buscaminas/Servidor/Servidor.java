package com.mycompany.p1_buscaminas.Servidor;
import java.net.ServerSocket;

import com.mycompany.p1_buscaminas.CampoObjeto;
import com.mycompany.p1_buscaminas.Dificultad;
import com.mycompany.p1_buscaminas.MostrarObjeto;

public class Servidor {

    public static void main(String[] args) {

        final int puerto = 8000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");

            while (true) {
                Conexion conexion = new Conexion(servidor.accept());

                String respuesta = conexion.getDificultad();
                if(respuesta == null) {
                    conexion.close();
                    continue;
                }
                Dificultad dificultad = new Dificultad(respuesta);
                System.out.println("Dificultad: " + dificultad.m + "x" + dificultad.n + " con " + dificultad.minas + " minas");
                Campo campo = new Campo(dificultad.n, dificultad.m, dificultad.minas);
                campo.show();

                int status = 0;
                while (status == 0) {
                    MostrarObjeto mostrar = conexion.getMostrar();
                    if (mostrar == null) {
                        conexion.close();
                        break;
                    }
                    CampoObjeto campoObjeto = campo.mostrar(mostrar.x, mostrar.y);
                    conexion.sendCampo(campoObjeto);
                    status = campoObjeto.getStatus();
                }
                //conexion.close();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
