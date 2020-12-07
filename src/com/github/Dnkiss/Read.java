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
            X.clear();
            Y.clear();
            time.clear();
            time.add((long) 0);
            String str;
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File(file)),
                            StandardCharsets.UTF_8));
            long t1 = 0;
            while((str = br.readLine()) != null){
                hitObject.add(str);
                String substring = str.substring(0,str.length()-1);
                String[] result = substring.split(",");
                X.add((int)((Float.parseFloat(result[0])+180)*2.2));
                Y.add((int)((Float.parseFloat(result[1])+82)*2.2));
                if(t1 == 0){
                    t1 = Long.parseLong(result[2]);
                }
                time.add(Long.parseLong(result[2]) - t1);
            }
            br.close();
        }catch(Exception e){
            System.err.println("read errors :" + e);
        }
    }
}