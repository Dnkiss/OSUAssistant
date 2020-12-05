package com.github.Dnkiss;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Simulater {
    public void keySimulater(int i){
        try {
            Robot robot = new Robot();
            robot.keyPress(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(i);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
    public void mouseMoveSimulater(int x, int y){
        try {
            Robot robot = new Robot();
            robot.mouseMove(x,y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
