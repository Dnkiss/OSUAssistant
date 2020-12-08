package com.github.Dnkiss;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;

public class Simulator {

    public static Long getTimestamp(Date date){
        if (null == date) {
            return (long) 0;
        }
        String timestamp = String.valueOf(date.getTime());
        return Long.valueOf(timestamp);
    }

    public void keySimulator(int i,long time){
        try {
            Robot robot = new Robot();
            long t = getTimestamp(new Date());
            while(true){
                robot.keyPress(i);
                if(getTimestamp(new Date()) - t > time){
                    break;
                }
                robot.delay(15);
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
    public void mouseClickSimulator(long time){
        try {
            int t = (int)time;
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(t);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
