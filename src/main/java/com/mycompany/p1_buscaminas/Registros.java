package com.mycompany.p1_buscaminas;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.opencsv.CSVReader;

public class Registros implements Serializable {

    public class Registro implements Serializable {
        public String nombre;
        public long tiempo;
        public int dificultad;

        public Registro(String nombre, long tiempo, int dificultad) {
            this.nombre = nombre;
            this.tiempo = tiempo;
            this.dificultad = dificultad;
        }

        public void show() {
            System.out.println("Registro: " + nombre + " - " + tiempo + "ms");
        }
    }

    public List<Registro> registros = new ArrayList<>();

    public void addRegistro(String nombre, long tiempo, int dificultad) {
        // Agregar un nuevo registro ordenado
        for (int i = 0; i < registros.size(); i++) {
            if (tiempo < registros.get(i).tiempo) {
                registros.add(i, new Registro(nombre, tiempo, dificultad));
                return;
            }
        }
        registros.add(new Registro(nombre, tiempo, dificultad));
    }

    public void show() {
        for (Registro registro : registros) {
            registro.show();
        }
    }

    public static Registros fromCSV(String file) {
        File archivo = new File(file);

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            try {
                // Crear el archivo y escribir una línea de ejemplo
                archivo.createNewFile();
                try (FileWriter writer = new FileWriter(archivo)) {
                    writer.write("ID,Nombre,Tiempo,Dificultad\n");
                }
                System.out.println("Archivo creado: " + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Registros registros = new Registros();

        // Leer el archivo CSV con OpenCSV
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] siguienteLinea;
            while ((siguienteLinea = reader.readNext()) != null) {
                if(siguienteLinea[0].equals("ID")) continue;
                registros.addRegistro(siguienteLinea[1], Long.parseLong(siguienteLinea[2]), Integer.parseInt(siguienteLinea[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return registros;
    }


    public void toCSV(String file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("ID,Nombre,Tiempo,Dificultad\n");
            int id = 1;
            for (Registro registro : registros) {
                writer.write(id + "," + registro.nombre + "," + registro.tiempo + "," + registro.dificultad + "\n");
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
