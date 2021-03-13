package com.dailyinspirationalquotes.lifequotes.models;

import java.util.ArrayList;

public class ResultsApiQuotes {
    private float status;
    private String message;
    private float count;
    private ArrayList<Quotes> quotes = new ArrayList<Quotes>();

    public ArrayList<Quotes> getQuotes() {
        return quotes;
    }

    public void setQuotes(ArrayList<Quotes> quotes) {
        this.quotes = quotes;
    }

// Getter Methods

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public float getCount() {
        return count;
    }

    // Setter Methods

    public void setStatus(float status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(float count) {
        this.count = count;
    }
}