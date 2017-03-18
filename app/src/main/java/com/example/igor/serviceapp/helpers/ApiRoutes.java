package com.example.igor.serviceapp.helpers;


import android.content.Context;
import android.util.Log;

public class ApiRoutes {

    private ApiConnection conn;

    public ApiRoutes(Context context) {
        this.conn = ApiConnection.apiFactory();
        this.conn.setContext(context);
    }

    public void getCityId(String cityName, String countryName, final ApiConnection.Callback callback) {
        String url = "?city=" + cityName + "&country=" + countryName;
        this.conn.get(url, (result) -> {
            Log.i("RESPONSE1", "Response : " + result);
        });
    }

    public void getWeatherInfo(int cityId, final ApiConnection.Callback callback) {
        String url = "?cityId=" + cityId;
        this.conn.get(url, (result) -> {
            Log.i("RESPONSE2", "Response : " + result);
        });
    }

}
