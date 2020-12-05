package com.github.Dnkiss;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class Executor {
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
                            simulator.mouseMoveSimulator(
                                    (int)(((float)read.getX().poll()+180)*2.2),
                                    (int)(((float)read.getY().poll()+82)*2.2)
                            );
                        }
                        simulator.keySimulator('W');
                        break;
                    case 2:
                        if(read.getX().peek()!=null){
                            simulator.mouseMoveSimulator(
                                    (int)(((float)read.getX().poll()+180)*2.2),
                                    (int)(((float)read.getY().poll()+82)*2.2)
                            );
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
        if(read.getTime().peek()!=null){
            time  = (long)read.getTime().poll();
        }
        else{
            System.out.println("读取文件内时间失败");
        }
        JIntellitype.getInstance().registerHotKey(1, 0,'F');
        JIntellitype.getInstance().registerHotKey(2, 0,'R');
        JIntellitype.getInstance().registerHotKey(3, 0,'S');
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int markCode) {
                switch (markCode){
                    case 1:
                        Thread td = new Thread(()->{
                            while(read.getTime().peek()!=null){
                                simulator.keySimulator('W');
                                if((long)read.getTime().peek()<=99999){
                                    try {
                                        Thread.sleep(((long)read.getTime().peek() - time));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    time = (long) read.getTime().poll();
                                }
                                else{
                                    try {
                                        Thread.sleep(((long)read.getTime().peek()/10000*60 + (long)read.getTime().peek()%10000 - time));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    time = (long)read.getTime().peek()/10000*60 + (long)read.getTime().poll()%10000;
                                }
                                simulator.mouseMoveSimulator(
                                        (int)(((float)read.getX().poll()+180)*2.2),
                                        (int)(((float)read.getY().poll()+82)*2.2)
                                );
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
                        System.out.println("重新录入完成");
                        break;
                }
            }
        });
    }
}
