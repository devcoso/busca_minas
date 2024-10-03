package com.mycompany.p1_buscaminas.Cliente;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Final extends JFrame {

    public Final(int status, Conexion conexion) {
        getContentPane().setLayout(null);

        Font fuente = new Font("Tahoma", Font.BOLD, 30);

        JLabel jl1 = new JLabel("Buscaminas");
        jl1.setBounds(150, 20, 200, 40);
        jl1.setFont(fuente);
        getContentPane().add(jl1);

        long time = 0;

        if(status == 1) {
            time = conexion.getTime();
        }

        JLabel jl2 = new JLabel(status != 1 ? "Perdiste" : "Ganaste" + " con un tiempo de " + ((float)time / 1000)  + "s");
        jl2.setBounds(status == 1 ? 145 : 220, 65, 300, 40);
        getContentPane().add(jl2);

        if(status == 1 ) {
            JLabel jl4 = new JLabel("Nombre:");
            jl4.setBounds(145, 100, 50, 20);
            getContentPane().add(jl4);

            JTextField tf1 = new JTextField("");
            tf1.setBounds(195, 100, 115, 20);
            getContentPane().add(tf1);

            // Formulario
            JButton btn1 = new JButton("Guardar record");
            btn1.setBounds(145, 180, 200, 40);
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // VALIDAR DATOS
                    if(tf1.getText().isEmpty() || tf1.getText().isEmpty()){
                        return;
                    }
                    conexion.sendString(tf1.getText());
                    dispose();
                    System.exit(0);
                }
            });
            getContentPane().add(btn1);
        } else {
            //OK
            JButton btn1 = new JButton("OK");
            btn1.setBounds(150, 180, 200, 40);
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    System.exit(0);
                }
            });
            getContentPane().add(btn1);
        }

        this.setSize(500, 300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
