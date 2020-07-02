package com.study.service;

import com.alibaba.fastjson.JSON;
import com.study.DataCollection.MyCollection;
import com.study.mapper.CountyMapper;
import com.study.mapper.ProvinceMapper;
import com.study.model.City;
import com.study.model.Province;
import org.springframework.stereotype.Service;
import com.study.DataCollection.MyCollection;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProvinceServinceImpl implements ProvinceServiceInf {
    @Resource
    private ProvinceMapper provinceMapper;

    @Resource
    private City cityMapper;

    @Resource
    private CountyMapper countyMapper;

    public int Insert() {
        System.out.println("123131");
        String url = "http://guolin.tech/api/china";
        String result = analysisUrl(url);
        System.out.println(result);

        List<Province> provinceList = JSON.parseArray(result, Province.class);
        for (Province province : provinceList) {
            try {
                this.provinceMapper.insert(province);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        return 1;
    }



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
