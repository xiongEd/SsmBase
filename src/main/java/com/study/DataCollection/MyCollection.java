package com.study.DataCollection;

import com.alibaba.fastjson.JSON;
import com.study.mapper.CityMapper;
import com.study.mapper.CountyMapper;
import com.study.mapper.ProvinceMapper;
import com.study.model.Province;
import com.study.service.ProvinceServiceInf;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component("myCollection")
public class MyCollection {

    public String analysisUrl(String url){
        HttpURLConnection httpConnection = null;
        String output = "";
        try {
            URL targetUrl = new URL(url);
            httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type",
                    "application/json");
            InputStreamReader isr = new InputStreamReader(httpConnection
                    .getInputStream(),"utf-8");
            BufferedReader responseBuffer = new BufferedReader(isr);
            output = responseBuffer.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpConnection.disconnect();
        }
        return output;
    }
}
