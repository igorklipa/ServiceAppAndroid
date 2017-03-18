package com.example.igor.serviceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(this.checkFiles())
		{
			Intent serviceActivity = new Intent("com.example.igor.serviceapp.SERVICES");
			startActivity(serviceActivity);
			finish();
		} else {

			this.setContentView(R.layout.splash);

			new Thread() {
				public void run() {
					try {
						sleep(2000);
					} catch (Exception e) {
						System.out.println("Error: " + e);
					} finally {
						Intent startActivity = new Intent("com.example.igor.serviceapp.START");
						startActivity(startActivity);
						finish();
					}
				}
			}.start();
		}
	}

	public String readFromFile(String file) {

		String line = null;
		try {
			FileInputStream inputStream = openFileInput(file);
			BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
			line = r.readLine();
			r.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return line;
	}

	public String readFromSharedPrefs(String key) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		String item = settings.getString(key, "");
		return item;
	}

	public boolean checkFiles() {
		if(this.readFromSharedPrefs("city") != null || this.readFromSharedPrefs("country") != null) {
			return true;
		} else {
			return false;
		}
	}

}
