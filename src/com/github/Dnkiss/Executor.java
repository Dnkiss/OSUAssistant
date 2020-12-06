package com.github.Dnkiss;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Executor {
    public static Long getTimestamp(Date date){
        if (null == date) {
            return (long) 0;
        }
        String timestamp = String.valueOf(date.getTime());
        return Long.valueOf(timestamp);
    }

    private Read read = new Read("D:/osu.txt");
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
                        if(read.getX().peek()!=null){
                            simulator.mouseMoveSimulator((int)read.getX().poll(),(int)read.getY().poll());
                        }
                        simulator.keySimulator('W');
                        break;
                    case 2:
                        if(read.getX().peek()!=null){
                            simulator.mouseMoveSimulator((int)read.getX().poll(),(int)read.getY().poll());
                        }
                        simulator.keySimulator('E');
                        break;
                    case 3:
                        read.setFile("D:/osu.txt");
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
                            System.out.println(time);
                            while(read.getTime().peek()!=null){
                                long t= getTimestamp(new Date());
                                if((long)read.getTime().peek()<=t - time){
                                    simulator.mouseMoveSimulator((int)read.getX().poll(),(int)read.getY().poll());
                                    simulator.keySimulator('W');
                                    read.getTime().poll();
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
                        read.setFile("D:/osu.txt");
                        if(read.getTime().peek()!=null){
                            time  = (long)read.getTime().poll();
                        }
                        else{
                            System.out.println("读取文件内时间失败");
                        }
                        read.getX().poll();
                        read.getY().poll();
                        System.out.println("重新录入完成");
                        break;
                }
            }
        });
    }
}
