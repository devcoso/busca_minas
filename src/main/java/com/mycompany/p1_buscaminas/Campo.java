/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1_buscaminas;
import java.util.Random;

/**
 *
 * @author cosoa
 */
public class Campo {
    private int[][] grilla = new int[0][0];
    private int n = 0;
    private int m = 0;
    public Campo(int n, int m, int minas){
        this.n = n;
        this.m = m;
        this.grilla = new int[m][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                 this.grilla[i][j] = 0;
            }
        }
        int contador_minas =0;
        while(contador_minas < minas){
            Random random = new Random();
            int x = random.nextInt(n);
            int y = random.nextInt(m);
            if(this.grilla[x][y] != -1){
               this.grilla[x][y] = -1;
               contador_minas++;
            }
        }
    }
    
    public void show() {
        for(int i=0; i<this.n; i++){
            for(int j=0; j<this.m; j++){
                System.out.print(this.grilla[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    
}
