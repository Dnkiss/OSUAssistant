package com.github.Dnkiss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;;

public class Reader {
    private Queue<Integer> X = new LinkedList<>();
    private Queue<Integer> Y = new LinkedList<>();
    private Queue<Long> time = new LinkedList<>();
    private Queue<Long> l = new LinkedList<>();
    private Queue<Character> type = new LinkedList<>(); //n,p,l

    Reader(String file){
        setFile(file);
    }

    public Queue getX(){
        return X;
    }
    public Queue getY(){
        return Y;
    }
    public Queue getTime(){
        return time;
    }
    public Queue getLong() {
        return l;
    }

    public void setFile(String file){
        try{
            X.clear();
            Y.clear();
            time.clear();
            l.clear();
            type.clear();
            time.add((long) 0);


            String str;
            Queue<Long> t = new LinkedList<Long>();
            Queue<Double> percent = new LinkedList<Double>();
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File(file)),
                            StandardCharsets.UTF_8));
            long t1 = 0;
            Double tp = Double.valueOf(0);
            Double sv = Double.valueOf(0);

            while((str = br.readLine()) != null){
                double bpm = 0;
                if(str.equals("[Difficulty]")){
                    str = br.readLine();
                    str = br.readLine();
                    str = br.readLine();
                    str = br.readLine();
                    str = br.readLine();
                    String[] result = str.split(":");
                    sv = Double.parseDouble(result[1]);
                }
                if(str.equals("[TimingPoints]")){
                    str = br.readLine();
                    String substring = str.substring(0,str.length()-1);
                    String[] result = substring.split(",");
                    tp = Double.parseDouble(result[1]);
                    while(!(str = br.readLine()).equals("")){
                        substring = str.substring(0,str.length()-1);
                        result = substring.split(",");
                        t.add(Long.parseLong(result[0]));
                        percent.add(100/Double.parseDouble(result[1])*-1);
                    }
                }
                if(str.equals("[HitObjects]")){
                    Double n = (double) 1;
                    while((str = br.readLine()) != null){
                        String[] result = str.split("\\|");
                        String[] r = result[0].split(",");
                        X.add((int)((Float.parseFloat(r[0])+180)*2.2));
                        Y.add((int)((Float.parseFloat(r[1])+82)*2.2));
                        if(t1 == 0){
                            t1 = Long.parseLong(r[2]);
                        }
                        time.add(Long.parseLong(r[2]) - t1);
                        if(t.peek()!=null){
                            if(Long.parseLong(r[2]) >= t.peek()){
                                n = percent.poll();
                                t.poll();
                            }
                        }
                        if(r[5].equals("P")){
                            type.add('p');
                            result = str.split(",");
                            l.add((long) (Double.parseDouble(result[6])*Double.parseDouble(result[7])*tp/(100*sv*n)));
                        }
                        else if(r[5].equals("L")){
                            type.add('l');
                            result = str.split(",");
                            l.add((long) (Double.parseDouble(result[6])*Double.parseDouble(result[7])*tp/(100*sv*n)));
                        }
                        else if(r[5].equals("B")){
                            type.add('b');
                            result = str.split(",");
                            l.add((long) (Double.parseDouble(result[6])*Double.parseDouble(result[7])*tp/(100*sv*n)));
                        }
                        else{
                            type.add('n');
                            l.add((long) 1);
                        }
                    }
                }
            }
            br.close();
        }catch(Exception e){
            System.err.println("Reader errors :" + e);
        }
    }
}