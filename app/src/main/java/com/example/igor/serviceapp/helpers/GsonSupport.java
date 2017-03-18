package com.example.igor.serviceapp.helpers;


public class GsonSupport {
    public CityId result;
    public Test test;
    public Weather weather;

    public class CityId extends GsonSupport {
        public int cityId;
    }

    public class Test extends GsonSupport {
        public int cityName;
    }

    public class Weather extends GsonSupport {
        public GsonWeather.City city;
        public String cood;
        public double message;
        public int cnt;
        public GsonWeather.WeatherList list[];
    }
}
