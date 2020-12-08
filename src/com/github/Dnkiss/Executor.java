package com.github.Dnkiss;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.util.Date;
import java.util.Timer;

public class Executor {
    public static Long getTimestamp(Date date){
        if (null == date) {
            return (long) 0;
        }
        String timestamp = String.valueOf(date.getTime());
        return Long.valueOf(timestamp);
    }

    private Reader reader = new Reader("D:/osu.txt");
    private Simulator simulator = new Simulator();
    private long time = 0;
    public void autoMove(){
        JIntellitype.getInstance().registerHotKey(1, 0,'S');
        JIntellitype.getInstance().registerHotKey(2, 0,'D');
        JIntellitype.getInstance().registerHotKey(3, 0,'R');
        System.out.println("热键注册成功!你可以使用了!");
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            public void onHotKey(int markCode) {
                switch (markCode){
                    case 1:
                        if(reader.getX().peek()!=null){
                            simulator.mouseMoveSimulator((int) reader.getX().poll(),(int) reader.getY().poll());
                        }
                        simulator.keySimulator('W',10);
                        break;
                    case 2:
                        if(reader.getX().peek()!=null){
                            simulator.mouseMoveSimulator((int) reader.getX().poll(),(int) reader.getY().poll());
                        }
                        simulator.keySimulator('E',10);
                        break;
                    case 3:
                        reader.setFile("D:/osu.txt");
                        System.out.println("重新录入完成");
                        break;
                }
            }
        });
    }

    public void autoClick(){
        JIntellitype.getInstance().registerHotKey(1, 0,'F');
        JIntellitype.getInstance().registerHotKey(2, 0,'R');
        JIntellitype.getInstance().registerHotKey(3, 0,'S');
        Timer timer = new Timer();
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int markCode) {
                switch (markCode){
                    case 1:
                        Thread td = new Thread(()->{
                            time = getTimestamp(new Date());
                            while(reader.getTime().peek()!=null){
                                long t= getTimestamp(new Date());
                                if((long) reader.getTime().peek()<=t - time){
                                    System.out.println(reader.getLong().peek());
                                    simulator.mouseClickSimulator((long) reader.getLong().poll());
                                    reader.getTime().poll();
                                    try {
                                        Thread.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                        td.start();
                        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
                            @Override
                            public void onHotKey(int i) {
                                if(i == 3){
                                    td.stop();
                                }
                            }
                        });
                        break;
                    case 2:
                        reader.setFile("D:/osu.txt");
                        if(reader.getTime().peek()!=null){
                            time  = (long) reader.getTime().poll();
                        }
                        else{
                            System.out.println("读取文件内时间失败");
                        }
                        System.out.println("重新录入完成");
                        break;
                }
            }
        });
    }

    public void autoClickAndMove(){
        JIntellitype.getInstance().registerHotKey(1, 0,'F');
        JIntellitype.getInstance().registerHotKey(2, 0,'R');
        JIntellitype.getInstance().registerHotKey(3, 0,'S');
        Timer timer = new Timer();
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int markCode) {
                switch (markCode){
                    case 1:
                        Thread td = new Thread(()->{
                            time = getTimestamp(new Date());
                            while(reader.getTime().peek()!=null){
                                long t= getTimestamp(new Date());
                                if((long) reader.getTime().peek()<=t - time){
                                    simulator.mouseMoveSimulator((int) reader.getX().poll(),(int) reader.getY().poll());
                                    simulator.keySimulator('W',10);
                                    reader.getTime().poll();
                                    try {
                                        Thread.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                        td.start();
                        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
                            @Override
                            public void onHotKey(int i) {
                                if(i == 3){
                                    td.stop();
                                }
                            }
                        });
                        break;
                    case 2:
                        reader.setFile("D:/osu.txt");
                        if(reader.getTime().peek()!=null){
                            time  = (long) reader.getTime().poll();
                        }
                        else{
                            System.out.println("读取文件内时间失败");
                        }
                        System.out.println("重新录入完成");
                        break;
                }
            }
        });
    }
}
