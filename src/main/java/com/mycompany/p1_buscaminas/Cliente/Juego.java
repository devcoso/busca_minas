package com.mycompany.p1_buscaminas.Cliente;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Juego extends JFrame{

	public Juego(int venX, int venY, int mine, int n, int m) {
		
		getContentPane().setLayout(null);
		
		JButton [][] btn;
		btn = new JButton[venY][venX];
		int refX = 25;
		int refY = 25;
		int ancho = 30;
		int largo = 30;
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				btn[i][j] = new JButton();
				btn[i][j].setName(i + "," + j);
				
				if(i == 0 && j == 0) {
					btn[i][j].setBounds(refX, refY, ancho, largo);
				}else if(i == 0 && j != 0) {
					btn[i][j].setBounds(btn[i][j-1].getX() + btn[i][j-1].getWidth(), refY, ancho, largo);
				}else {
					btn[i][j].setBounds(btn[i-1][j].getX(), btn[i-1][j].getY() + btn[i-1][j].getHeight(), ancho, largo);
				}
				
				getContentPane().add(btn[i][j]);
			}
		}
		
		this.setSize(venX, venY);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		
	}

}
