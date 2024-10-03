package com.mycompany.p1_buscaminas;

import java.io.Serializable;

public class CampoObjeto implements Serializable {
    public int[][] grilla = new int[0][0];
    private int n = 0;
    private int m = 0;
    private int status = 0;
    
    public CampoObjeto(int[][] grilla, int status){
        this.grilla = grilla;
        this.n = grilla[0].length;
        this.m = grilla.length;
        this.status = status;
    }

    public void show() {
        System.out.println("Status:" + this.status);
        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                System.out.print(this.grilla[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
    
    public int getStatus(){
        return this.status;
    }

    public int[][] getGrilla(){
        return this.grilla;
    }

}
