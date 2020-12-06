package com.github.Dnkiss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;;

public class Read {
    private Queue<String> hitObject = new LinkedList<String>();
    private Queue<Integer> X = new LinkedList<Integer>();
    private Queue<Integer> Y = new LinkedList<Integer>();
    private Queue<Long> time = new LinkedList<Long>();

    Read(String file){
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

    public void setFile(String file){
        try{
            String str;
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File(file)),
                            StandardCharsets.UTF_8));
            long t1 = 0;
            X.clear();
            Y.clear();
            time.clear();
            while((str = br.readLine()) != null){
                hitObject.add(str);
                String substring = str.substring(0,str.length()-1);
                String[] result = substring.split(",");
                X.add((int)((Float.parseFloat(result[0])+180)*2.2));
                Y.add((int)((Float.parseFloat(result[1])+82)*2.2));
                if(Long.parseLong(result[2])<=99999) {
                    if(t1 == 0){
                        time.clear();
                        t1 = Long.parseLong(result[2]);
                    }
                    time.add(Long.parseLong(result[2]) - t1);
                }
                else{
                    time.add(Long.parseLong(result[2])/10000*60 + Long.parseLong(result[2])%10000 - t1);
                    t1 = Long.parseLong(result[2])/10000*60 + Long.parseLong(result[2])%10000;
                }
            }
            br.close();
        }catch(Exception e){
            System.err.println("read errors :" + e);
        }
    }
}