package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Objects;

@JsonSerialize
public class StockData implements Serializable {
    @JsonProperty("Name")
    static String name;
    static String date;
    static String open;
    static String high;
    static String low;
    static String close;
    static String volume;

    public static StockData fromJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StockData stockData=objectMapper.readValue(jsonString, StockData.class);
            return stockData;
        } catch (Exception e) {
            // Handle the exception (e.g., log it or throw a RuntimeException)
            e.printStackTrace();
            return null;
        }
    }
    @JsonCreator
    public StockData(@JsonProperty("Name") String name, @JsonProperty("date") String date,@JsonProperty("open") String open,@JsonProperty("high") String high,@JsonProperty("low") String low,@JsonProperty("close") String close,@JsonProperty("volume") String volume) {
        this.name = name;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }

    public String getName() {
        return name != null ? name : "UNKNOWN";
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StockData other = (StockData) obj;
        return Objects.equals(date, other.date);    }
}
