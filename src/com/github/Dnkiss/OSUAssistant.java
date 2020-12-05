package com.github.Dnkiss;

import com.melloware.jintellitype.*;

import javax.swing.*;
import java.awt.*;

public class OSUAssistant extends JFrame{
    public static void main(String[] args){
        Simulater simulater = new Simulater();

        Read read = new Read("D:/osu.txt");


        JIntellitype.getInstance().registerHotKey(1, 0,'S');
        JIntellitype.getInstance().registerHotKey(2, 0,'D');
        JIntellitype.getInstance().registerHotKey(3, 0,'R');
        System.out.println("热键注册成功!你可以使用了!");
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            public void onHotKey(int markCode) {
                switch (markCode){
                    case 1:
                        if(read.getX().peek()!=null){
                            simulater.mouseMoveSimulater(
                                    (int)(((float)read.getX().poll()+180)*2.2),
                                    (int)(((float)read.getY().poll()+82)*2.2)
                            );
                        }
                        simulater.keySimulater('W');
                        break;
                    case 2:
                        if(read.getX().peek()!=null){
                            simulater.mouseMoveSimulater(
                                    (int)(((float)read.getX().poll()+180)*2.2),
                                    (int)(((float)read.getY().poll()+82)*2.2)
                            );
                        }
                        simulater.keySimulater('E');
                        break;
                    case 3:
                        read.setFile("D:/osu.txt");
                        System.out.println("重新录入完成");
                        break;
                }
            }
        });
    }
}
