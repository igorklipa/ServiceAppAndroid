package com.example.igor.serviceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etCity;
    private EditText etState;
    private EditText etDate;
    private TextView tvError;
    private Button btnSaveInfo;
    private String nameText;
    private String cityText;
    private String stateText;
    private String dateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initComponents();
    }

    private void initComponents() {
        this.etName = (EditText) this.findViewById(R.id.etName);
        this.etCity = (EditText) this.findViewById(R.id.etCity);
        this.etState = (EditText) this.findViewById(R.id.etCountry);
        this.etDate = (EditText) this.findViewById(R.id.etDate);
        this.tvError = (TextView) this.findViewById(R.id.tvError);
        this.btnSaveInfo = (Button) this.findViewById(R.id.btnSaveInfo);
    }

    public void onClickSaveInfo(View v) {
        try {
            this.trimInputs();

            this.setSharedPrefs("name", this.etName.getText().toString());
            this.setSharedPrefs("city", this.etCity.getText().toString());
            this.setSharedPrefs("country", this.etState.getText().toString());
            this.setSharedPrefs("date", this.etDate.getText().toString());


        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            if(this.nameText.equals("") || this.cityText.equals("") || this.stateText.equals("") || this.dateText.equals("")) {
                this.tvError.setVisibility(View.VISIBLE);
            } else {
                this.tvError.setVisibility(View.INVISIBLE);
                Intent serviceActivity = new Intent("com.example.igor.serviceapp.SERVICES");
                startActivity(serviceActivity);
                finish();
            }
        }
    }

//    public void saveInfoLocal(String fileName, String info) {
//        try {
//            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
//            fos.write(info.getBytes());
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void trimInputs() {
        this.nameText = this.etName.getText().toString().trim();
        this.cityText = this.etCity.getText().toString().trim();
        this.stateText = this.etState.getText().toString().trim();
        this.dateText = this.etDate.getText().toString().trim();
    }

//    public String readFromFile(String file) {
//
//        String line = null;
//        try {
//            FileInputStream inputStream = openFileInput(file);
//            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
//            line = r.readLine();
//            r.close();
//            inputStream.close();
//            //Toast.makeText(getBaseContext(), line, Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return line;
//    }


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

}
