package com.github.Dnkiss;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows extends JFrame {
    JFrame jf = new JFrame("OSUAssistant by.Dnkiss");;
    JButton moveButton = new JButton();
    JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    public Windows(){
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,300);
        jf.setLocationRelativeTo(null);

        moveButton.setText("移动模式");
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Executor executor = new Executor();
                executor.autoMove();
                jf.setVisible(false);
                jl.setText("按S和D执行程序，按R复位");
                jl.setHorizontalAlignment(SwingConstants.CENTER);
                JFrame jfmove = new JFrame("OSUAssistant by,Dnkiss");
                jfmove.add(jl);
                jfmove.setLocationRelativeTo(null);
                jfmove.setSize(500,300);
                jfmove.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jfmove.setVisible(true);
            }
        });

        jp.add(moveButton);
        jf.setContentPane(jp);
        jf.setVisible(true);
    }
}
