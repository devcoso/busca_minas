package com.mycompany.p1_buscaminas;

public class Dificultad {

    public String nombre = "";
    public int n = 0;
    public int m = 0;
    public int minas = 0;
    public int venX = 0;
    public int venY = 0;

    public Dificultad(String dificultad) {
        switch (dificultad) {
            case "Principiante":
                this.n = 9;
                this.m = 9;
                this.minas = 10;
                break;
                
            case "Intermedio":
                this.n = 16;
                this.m = 16;
                this.minas = 40;
                break;
                
            case "Experto":
                this.n = 30;
                this.m = 16;
                this.minas = 90;
                break;
                
            default:
                throw new IllegalArgumentException("Nivel de dificultad no reconocido: " + dificultad);
        }
    }

    public Dificultad getDificultad() {
        return this;
    }

}
