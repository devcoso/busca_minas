package com.mycompany.p1_buscaminas;
import java.util.Random;

public class Campo {
    private int[][] grilla = new int[0][0];
    private int[][] mostrar = new int[0][0];
    private int n = 0;
    private int m = 0;
    
    public Campo(int n, int m, int minas){
        this.n = n;
        this.m = m;
        this.grilla = new int[m][n];
        this.mostrar = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                 this.grilla[i][j] = 0;
                 this.mostrar[i][j] = 0;
            }
        }
        int contador_minas =0;
        while(contador_minas < minas){
            Random random = new Random();
            int y = random.nextInt(m);
            int x = random.nextInt(n);
            if(this.grilla[y][x] != -1){
               this.grilla[y][x] = -1;
               contador_minas++;
               //Suma las casillas alrededor
                boolean isy_gt_z = y-1 > -1;
                boolean isx_gt_z = x-1 > -1;
                boolean isy_lt_m = y+1 < m;
                boolean isx_lt_n = x+1 < n;

                if ( isy_gt_z && this.grilla[y-1][x] != -1 ) this.grilla[y-1][x]++;
                if ( isy_gt_z && isx_lt_n && this.grilla[y-1][x+1] != -1 ) this.grilla[y-1][x+1]++;
                if ( isx_lt_n && this.grilla[y][x+1] != -1 ) this.grilla[y][x+1]++;
                if ( isy_lt_m && isx_lt_n && this.grilla[y+1][x+1] != -1 ) this.grilla[y+1][x+1]++;
                if ( isy_lt_m && this.grilla[y+1][x] != -1 ) this.grilla[y+1][x]++;
                if ( isy_lt_m && isx_gt_z && this.grilla[y+1][x-1] != -1 ) this.grilla[y+1][x-1]++;
                if ( isx_gt_z && this.grilla[y][x-1] != -1 ) this.grilla[y][x-1]++;
                if ( isy_gt_z && isx_gt_z && this.grilla[y-1][x-1] != -1 ) this.grilla[y-1][x-1]++;
            }
        }
    }
    
    public void show() {
        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                System.out.print(this.grilla[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
    
}
