package com.mycompany.p1_buscaminas;

import java.io.Serializable;

public class MostrarObjeto implements Serializable {
    public int x;
    public int y;

    public MostrarObjeto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void show() {
        System.out.println("MostrarObjeto: " + x + ", " + y);
    }

}
