package com.mycompany.p1_buscaminas.Cliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalButtonUI;

import com.mycompany.p1_buscaminas.CampoObjeto;
import com.mycompany.p1_buscaminas.MostrarObjeto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
public class Juego extends JFrame{
	public int status = 0;
	JButton [][] btn = new JButton[0][0];
	Conexion conexion;
	private int banderas = 0;
	private int minas = 0;
	
	public Juego(int minas, int n, int m, Conexion conexion) {
		
		getContentPane().setLayout(null);
		
		this.conexion = conexion;
		this.btn = new JButton[m][n];

		int refX = 25;
		int refY = 25;
		int ancho = 50;
		int largo = 50;

		this.minas = minas;
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				btn[i][j] = new JButton();
				btn[i][j].setName(j + "," + i);
				
				if(i == 0 && j == 0) {
					btn[i][j].setBounds(refX, refY, ancho, largo);
				}else if(i == 0 && j != 0) {
					btn[i][j].setBounds(btn[i][j-1].getX() + btn[i][j-1].getWidth(), refY, ancho, largo);
				}else {
					btn[i][j].setBounds(btn[i-1][j].getX(), btn[i-1][j].getY() + btn[i-1][j].getHeight(), ancho, largo);
				}
				btn[i][j].setFont(new java.awt.Font("Tahoma", 0, 11));
				btn[i][j].setText("");
				//Click derecho
				btn[i][j].addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						JButton btn = (JButton) evt.getSource();
						String[] pos = btn.getName().split(",");
						int x = Integer.parseInt(pos[0]);
						int y = Integer.parseInt(pos[1]);
						mostrar(x, y);
					}
				});
				//Click izquierdo
				btn[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						if(evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
							JButton btn = (JButton) evt.getSource();
							toggleBander(btn);
						}
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
		try {
			MostrarObjeto mostrar = new MostrarObjeto(x, y);
			conexion.sendMostrar(mostrar);
			CampoObjeto campo = conexion.getCampo();
			if(campo == null) {
				JOptionPane.showMessageDialog(null, "Error al recibir el campo", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int status = campo.getStatus();
			int grilla[][] = campo.getGrilla();
			for(int i = 0; i < grilla.length; i++) {
				for(int j = 0; j < grilla[0].length; j++) {
					if(grilla[i][j] > 8) continue;
					btn[i][j].setEnabled(false);
					if(grilla[i][j] == -1) {
						btn[i][j].setText("X");
						btn[i][j].setUI(new MetalButtonUI() {
							protected java.awt.Color getDisabledTextColor() {
								return new java.awt.Color(255, 0, 0);
							}
						});
					}else {
						btn[i][j].setText(String.valueOf(grilla[i][j]));
						//Color de los numeros
						if(grilla[i][j] == 1) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(0, 0, 255);
								}
							});
						}else if(grilla[i][j] == 2) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(0, 128, 0);
								}
							});
						}else if(grilla[i][j] == 3) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(255, 128, 0);
								}
							});
						}else if(grilla[i][j] == 4) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(128, 0, 128);
								}
							});
						}else if(grilla[i][j] == 5) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(128, 64, 0);
								}
							});
						}else if(grilla[i][j] == 6) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(0, 255, 255);
								}
							});
						}else if(grilla[i][j] == 7) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(0, 0, 0);
								}
							});
						}else if(grilla[i][j] == 8) {
							btn[i][j].setUI(new MetalButtonUI() {
								protected java.awt.Color getDisabledTextColor() {
									return new java.awt.Color(128, 128, 128);
								}
							});
						}
					}
				}
			}

			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Ganaste", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
				conexion.close();
				System.exit(0);
			}else if(status == -1) {
				JOptionPane.showMessageDialog(null, "Perdiste", "Fin del juego", JOptionPane.ERROR_MESSAGE);
				conexion.close();
				System.exit(0);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

 	private void toggleBander(JButton btn) {
		if(btn.getText().equals("M")) {
			btn.setText("");
			btn.setEnabled(true);
			banderas--;
		}else {
			if(banderas < minas) {
				btn.setText("M");
				btn.setUI(new MetalButtonUI() {
					protected java.awt.Color getDisabledTextColor() {
						return new java.awt.Color(255, 0, 0);
					}
				});
				btn.setEnabled(false);
				banderas++;
			}
		}
	}
	
	public int getStatus() {
		return this.status;
	}

	

}
