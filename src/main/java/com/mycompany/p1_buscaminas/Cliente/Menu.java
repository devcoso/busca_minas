package com.mycompany.p1_buscaminas.Cliente;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private JLabel jl1, jl2;
    private JButton btn1, btn2, btn3;
    private String dificultadName = "";

    public Menu() {
         
        getContentPane().setLayout(null);

        Font fuente = new Font("Tahoma", Font.BOLD, 30);

        jl1 = new JLabel("Buscaminas");
        jl1.setBounds(150, 20, 200, 40);
        jl1.setFont(fuente);
        getContentPane().add(jl1);

        jl2 = new JLabel("Selecciona un Modo de Juego");
        jl2.setBounds(155, 65, 200, 40);
        getContentPane().add(jl2);

        btn1 = new JButton("Principiante");
        btn1.setBounds(30, 135, 115, 40);
        getContentPane().add(btn1);

        btn2 = new JButton("Intermedio");
        btn2.setBounds(175, 135, 115, 40);
        getContentPane().add(btn2);

        btn3 = new JButton("Experto");
        btn3.setBounds(320, 135, 115, 40);
        getContentPane().add(btn3);

        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dificultadName = "Principiante";
                dispose();
            }
        };
        btn1.addActionListener(act1);

        ActionListener act2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dificultadName = "Intermedio";
                dispose();

            }
        };
        btn2.addActionListener(act2);

        ActionListener act3 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dificultadName = "Experto";
                dispose();
            }
        };
        btn3.addActionListener(act3);

        this.setSize(500, 300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public String getDificultadName() {
        return dificultadName;
    }

}
