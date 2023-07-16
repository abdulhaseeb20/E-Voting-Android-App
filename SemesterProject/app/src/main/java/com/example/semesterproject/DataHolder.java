package com.example.semesterproject;

public class DataHolder {
    private static DataHolder instance;
    private String textValue;

    private DataHolder() {
        // Private constructor to prevent instantiation
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
}
