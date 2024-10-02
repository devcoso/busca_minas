package com.mycompany.p1_buscaminas.Servidor;
import java.net.ServerSocket;

import com.mycompany.p1_buscaminas.CampoObjeto;
import com.mycompany.p1_buscaminas.Dificultad;
import com.mycompany.p1_buscaminas.MostrarObjeto;
import com.mycompany.p1_buscaminas.Registros;

public class Servidor {

    public static void main(String[] args) {

        final int puerto = 8000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");

            Registros registros= Registros.fromCSV("registros.csv");

            while (true) {
                Conexion conexion = new Conexion(servidor.accept());

                String respuesta = conexion.getString();
                if(respuesta == null) {
                    conexion.close();
                    continue;
                }
                Dificultad dificultad = new Dificultad(respuesta);
                Campo campo = new Campo(dificultad.n, dificultad.m, dificultad.minas);
                campo.show();
                //Tiempo
                long startTime = System.currentTimeMillis();

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

                if(status == 1) {
                    long endTime = System.currentTimeMillis();
                    long tiempo = endTime - startTime;
                    System.out.println("Ganó en " + tiempo + "ms");
                    conexion.sendTime((int) tiempo);
                    String nombre = conexion.getString();
                    if(nombre == null) {
                        System.out.println("No se pudo guardar el registro");
                        conexion.close();
                        continue;
                    }
                    System.out.println("Guardando registro: " + nombre + " - " + tiempo + "ms" + " - " + dificultad.nombre);
                    // Guardar registro
                    registros.addRegistro(nombre, tiempo, Dificultad.fromNombre(dificultad.nombre));
                    registros.toCSV("registros.csv");
                } else if(status == -1) {
                    System.out.println("Perdió");
                }

                conexion.close();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
