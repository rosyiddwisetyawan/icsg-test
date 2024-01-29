package com.example.icsgtest.model;

public class Data {

    private String id;
    private String name;
    private String absoluteMagnitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public void setAbsoluteMagnitude(String absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", absoluteMagnitude='" + absoluteMagnitude + '\'' +
                '}';
    }
}
