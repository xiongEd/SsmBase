package com.study.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    //private static Logger logger = LoggerFactory.getLogger(Test.class);

    static BufferedReader bufferedReader;

    public static int check () throws IOException{

        Logger logger = LoggerFactory.getLogger(Test.class);
        logger.debug("This is log");
        logger.info("This is info log");
        logger.info("This is info log");

        String address="www.baidu.com";
        int state=0;
        try {
            Process process=Runtime.getRuntime().exec("ping "+address+" -n 1");
            bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line="";
            String connectionStr=null;
            while ((line = bufferedReader.readLine())!=null) {

                connectionStr += line;
            }
            System.out.println(connectionStr);
            logger.info(connectionStr);
            if(connectionStr.indexOf("0% 丢失")!=-1){
                System.out.println("===the internet working===");
                logger.info("===the internet working===");
                state= 1;
            }
            else
            {
                System.out.println("else===the internet dont working ===");
                logger.info("else===the internet dont working===");
                state= 0;
            }
        } catch(IOException e){
            System.out.println("Exception===the internet dont working ===");
            logger.info("Exception===the internet dont working===");
            state= 0;
            e.printStackTrace();
        }finally{
            System.out.println("===finally ===");
            logger.info("===finally===");
            bufferedReader.close();
        }
        return state;
    }
}