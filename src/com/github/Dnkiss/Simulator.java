package com.github.Dnkiss;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Simulator {
    public void keySimulator(int i){
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
    public void mouseMoveSimulator(int x, int y){
        try {
            Robot robot = new Robot();
            robot.mouseMove(x,y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
