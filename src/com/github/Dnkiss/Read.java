package com.github.Dnkiss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;;

public class Read {
    private Queue<String> hitObject = new LinkedList<String>();
    private Queue<Float> X = new LinkedList<Float>();
    private Queue<Float> Y = new LinkedList<Float>();
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
            X.clear();
            Y.clear();
            time.clear();
            while((str = br.readLine()) != null){
                hitObject.add(str);
                String substring = str.substring(0,str.length()-1);
                String[] result = substring.split(",");
                X.add(Float.valueOf(result[0]));
                Y.add(Float.valueOf(result[1]));
                time.add(Long.valueOf(result[2]));
            }
            br.close();
        }catch(Exception e){
            System.err.println("read errors :" + e);
        }
    }
}