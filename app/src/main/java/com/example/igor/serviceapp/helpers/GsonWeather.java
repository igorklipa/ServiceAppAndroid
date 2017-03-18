package com.example.igor.serviceapp.helpers;


public class GsonWeather {

    public class WeatherMain {
        public double temp;
        public double temp_min;
        public double temp_max;
        public double pressure;
        public double sea_level;
        public double grnd_level;
        public int humidity;
        public double temp_kf;
    }

    public class WeatherInfo {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public class WeatherClouds {
        public int all;
    }

    public class WeatherWind {
        public double speed;
        public double deg;
    }

    public class WeatherRain {
        //... TODO different intervals
        public double _3h;
    }

    public class WeatherSys {
        public String pod;
    }

    public class WeatherList {
        public int dt;
        public WeatherMain main;
        public WeatherInfo weather[];
        public WeatherClouds clouds;
        public WeatherWind wind;
        public WeatherRain rain;
        public WeatherSys sys;
        public String dt_txt;
    }

    public class City {
        public int id;
        public String name;
        public Coord coord;
        public String country;
        public Sys sys;

        public class Coord {
            public double lon;
            public double lat;
        }

        public class Sys {
            public int population;
        }
    }
}
