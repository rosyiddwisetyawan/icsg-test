package com.example.icsgtest.model;

import java.util.List;

public class MapData {

    private String date;
    private List<Data> data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MapData{" +
                "date='" + date + '\'' +
                ", data=" + data +
                '}';
    }
}
