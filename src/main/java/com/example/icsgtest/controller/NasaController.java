package com.example.icsgtest.controller;

import com.example.icsgtest.model.Data;
import com.example.icsgtest.model.MapData;
import com.example.icsgtest.service.NasaService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class NasaController {

    @Autowired
    private NasaService nasaService;

    @GetMapping(value = "/getdata")
    public List<MapData> getData(@RequestParam("startdate") String startDate, @RequestParam("enddate") String endDate){

        Boolean st1 = nasaService.isValidDate(startDate);
        Boolean st2 = nasaService.isValidDate(endDate);
        if (st1 && st2){
            Map<String, List<Data>> res = nasaService.getData(startDate,endDate);
            return nasaService.sortNearData(res);
        }

        return null;

    }
}
