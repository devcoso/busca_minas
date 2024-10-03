package com.mycompany.p1_buscaminas.Cliente;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame {

    private String host = "";
    private int port = 0;

    public Inicio() {
        getContentPane().setLayout(null);

        Font fuente = new Font("Tahoma", Font.BOLD, 30);

        JLabel jl1 = new JLabel("Buscaminas");
        jl1.setBounds(150, 20, 200, 40);
        jl1.setFont(fuente);
        getContentPane().add(jl1);

        JLabel jl2 = new JLabel("Coloca el puerto y el host de tu servidor");
        jl2.setBounds(120, 65, 300, 40);
        getContentPane().add(jl2);

        JLabel jl3 = new JLabel("Host:");
        jl3.setBounds(125, 100, 50, 20);
        getContentPane().add(jl3);

        JTextField tf1 = new JTextField("localhost");
        tf1.setBounds(175, 100, 115, 20);
        getContentPane().add(tf1);

        JLabel jl4 = new JLabel("Puerto:");
        jl4.setBounds(125, 140, 50, 20);
        getContentPane().add(jl4);

        JTextField tf2 = new JTextField("8000");
        tf2.setBounds(175, 140, 115, 20);
        getContentPane().add(tf2);

        // Formulario
        JButton btn1 = new JButton("CONECTAR");
        btn1.setBounds(175, 180, 115, 40);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // VALIDAR DATOS
                if(tf2.getText().isEmpty() || tf1.getText().isEmpty()){
                    return;
                }
                if(Integer.parseInt(tf2.getText()) < 1024 || Integer.parseInt(tf2.getText()) > 49151){
                    return;
                }
                host = tf1.getText();
                port = Integer.parseInt(tf2.getText());
                dispose();
            }
        });
        getContentPane().add(btn1);
        

        this.setSize(500, 300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
