package com.github.Dnkiss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;;

public class Reader {
    private Queue<Integer> X = new LinkedList<Integer>();
    private Queue<Integer> Y = new LinkedList<Integer>();
    private Queue<Long> time = new LinkedList<Long>();
    private Queue<Long> l = new LinkedList<Long>();
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
            Queue<Double> bpmt = new LinkedList<Double>();
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File(file)),
                            StandardCharsets.UTF_8));
            long t1 = 0;
            long tp = 0;

            while((str = br.readLine()) != null){
                double bpm = 0;
                if(str.equals("[TimingPoints]")){
                    str = br.readLine();
                    String substring = str.substring(0,str.length()-1);
                    String[] result = substring.split(",");
                    tp = (long)Double.parseDouble(result[1]);
                    t.add(Long.parseLong(result[0]));
                    bpm = 60000/tp;
                    while(!(str = br.readLine()).equals("")){
                        substring = str.substring(0,str.length()-1);
                        result = substring.split(",");
                        t.add(Long.parseLong(result[0]));
                        bpmt.add(100*bpm/Double.parseDouble(result[1]));
                    }
                }
                if(str.equals("[HitObjects]")){
                    Double n = (double) 0;
                    while((str = br.readLine()) != null){
                        String substring = str.substring(0,str.length()-1);
                        String[] result = substring.split("\\|");
                        String[] r = result[0].split(",");
                        X.add((int)((Float.parseFloat(r[0])+180)*2.2));
                        Y.add((int)((Float.parseFloat(r[1])+82)*2.2));
                        if(t1 == 0){
                            t1 = Long.parseLong(r[2]);
                        }
                        time.add(Long.parseLong(r[2]) - t1);
                        if(t.peek()!=null){
                            if(Long.parseLong(r[2]) >= t.peek()){
                                n = bpmt.poll();
                                t.poll();
                            }
                        }
                        if(r[5].equals("P")){
                            type.add('p');
                            result = substring.split(",");
                            l.add((long)(Double.parseDouble(result[7])*tp*-1 / n));
                        }
                        else if(r[5].equals("L")){
                            type.add('l');
                            result = substring.split(",");
                            String[] s = result[5].split("\\|");
                            for(int i = 0;i < s.length;i++){
                                String[] re = s[i].split(",");
                                if(re.length>2){
                                    l.add((long)(Double.parseDouble(re[2])*tp*-1 / n));
                                }
                            }
                        }
                        else{
                            type.add('n');
                            l.add((long) 10);
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