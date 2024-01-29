package com.example.icsgtest.service;

import com.example.icsgtest.model.Data;
import com.example.icsgtest.model.MapData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    @Value("${uri.feed}")
    private String uri;

    @Value("${api.key}")
    private String apiKey;

    public Map<String, List<Data>> getData(String startDate, String endDate){
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("start_date=").append(startDate)
                .append("&end_date=").append(endDate)
                .append("&api_key=").append(apiKey);
        RestTemplate rest = new RestTemplate();
        String response = rest.getForObject(sb.toString(),String.class);
        JSONObject jsonObject = new JSONObject(response);
        JSONObject jsNearEarth = jsonObject.getJSONObject("near_earth_objects");
        Map<String , List<Data>> listMap = new HashMap<>();
        for (String key: jsNearEarth.keySet()){
            List<Data> listData = new ArrayList<>();
            JSONArray js1 = jsNearEarth.getJSONArray(key);
            for (int i = 0 ; i < js1.length(); i++) {
                JSONObject obj = js1.getJSONObject(i);
                Data dt = new Data();
                dt.setId(obj.getString("id"));
                dt.setName(obj.getString("name"));
                dt.setAbsoluteMagnitude(String.valueOf(obj.getInt("absolute_magnitude_h")));
                listData.add(dt);
            }
            listMap.put(key,listData);
        }

        return listMap;
    }

    public List<MapData> sortNearData(Map<String, List<Data>> res){
        List<MapData> list = new ArrayList<>();
        for (var entry : res.entrySet()) {
            MapData mapData = new MapData();
            List<Data> listDt = entry.getValue();
            Collections.sort(listDt, (d1, d2) -> {
                return Integer.parseInt(d1.getAbsoluteMagnitude()) - Integer.parseInt(d2.getAbsoluteMagnitude());
            });

            List<Data> tenData = listDt.stream().limit(10).toList();
            mapData.setDate(entry.getKey());
            mapData.setData(tenData);
            list.add(mapData);
        }

        Collections.sort(list, (d1, d2) -> {
            return d1.getDate().compareTo(d2.getDate());
        });

        return list;
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}
