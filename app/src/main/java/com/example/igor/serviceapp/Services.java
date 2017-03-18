package com.example.igor.serviceapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.igor.serviceapp.helpers.ApiConnection;
import com.example.igor.serviceapp.helpers.ApiRoutes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Services extends Activity {

  String line;
  String city;
  int cityId;
  String country;
  TextView tvTemperature;
  ApiConnection api;
  ApiRoutes apiRoutes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.services);
    this.initApi();
    city = this.readFromSharedPrefs("city");
    country = this.readFromSharedPrefs("country");
    tvTemperature = (TextView) this.findViewById(R.id.tvTemperature);
    //this.getWeatherInfo();
  }

  public void initApi() {
    this.apiRoutes = new ApiRoutes(getApplicationContext());
  }

  public String readFromFile(String file) {
    try {
      FileInputStream inputStream = openFileInput(file);
      BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
      this.line = r.readLine();
      r.close();
      inputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.line;
  }


  public void weatherStart(View v) {
    //Toast.makeText(getBaseContext(), "Weather clicked", Toast.LENGTH_SHORT).show();
    Intent weatherService = new Intent("com.example.igor.serviceapp.WEATHER");
    startActivity(weatherService);
  }

  public void getWeatherInfo() {
    this.apiRoutes.getWeatherInfo(792680, (result) -> {
      Log.i("RESPONSE1", "Response : " + result);
//			this.saveInfoLocal("weather", result);
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.aboutUs:

        break;
      case R.id.preferences:
        Intent p = new Intent("com.example.igor.serviceapp.PREFS");
        startActivity(p);
        break;
    }
    return false;
  }

  public String readFromSharedPrefs(String key) {
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
    String item = settings.getString(key, "");
    return item;
  }

  public void setSharedPrefs(String key, String value) {
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = settings.edit();
    editor.putString(key, value);
    editor.apply();
  }

  public void saveInfoLocal(String fileName, String info) {
    try {
      FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
      fos.write(info.getBytes());
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
