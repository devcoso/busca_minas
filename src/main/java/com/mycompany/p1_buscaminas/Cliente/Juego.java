package com.mycompany.p1_buscaminas.Cliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mycompany.p1_buscaminas.MostrarObjeto;

import javax.swing.JButton;
public class Juego extends JFrame{
	public int status = 0;
	JButton [][] btn = new JButton[0][0];
	Conexion conexion;
	
	public Juego(int mine, int n, int m, Conexion conexion) {
		
		getContentPane().setLayout(null);
		
		this.conexion = conexion;
		this.btn = new JButton[m][n];

		int refX = 25;
		int refY = 25;
		int ancho = 40;
		int largo = 40;
		
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
				btn[i][j].setFont(new java.awt.Font("Tahoma", 0, 11));
				btn[i][j].setText("");
				btn[i][j].addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						JButton btn = (JButton) evt.getSource();
						String[] pos = btn.getName().split(",");
						int x = Integer.parseInt(pos[0]);
						int y = Integer.parseInt(pos[1]);
						mostrar(x, y);
					}
				});
				getContentPane().add(btn[i][j]);
			}
		}
		
		this.setSize((n + 6) * ancho, (m + 2) * largo);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//Cerrar conenxion cuando se cierre la ventana
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Desea salir del juego?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(resp == JOptionPane.YES_OPTION) {
					conexion.close();
					System.exit(0);
				}
			}
		});
	}

	private void mostrar(int x, int y) {
		System.out.println("Posicion: " + x + "," + y);
		try {
			MostrarObjeto mostrar = new MostrarObjeto(x, y);
			conexion.sendMostrar(mostrar);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getStatus() {
		return this.status;
	}

	

}
