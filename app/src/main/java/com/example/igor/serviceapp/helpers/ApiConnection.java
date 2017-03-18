package com.example.igor.serviceapp.helpers;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiConnection {

  private Context context;
  private String baseUrl;
  private static ApiConnection conn;
  private Map<String, String> header;
  private int socketTimeout;
  private RetryPolicy policy;
  private Class reflectClass;
  private GsonRequest<GsonSupport[]> request;
  private Callback callback;

  private ApiConnection() {
    this.baseUrl = "http://10.0.2.2:8080/app.php";
    this.header = new HashMap<String, String>();
    this.header.put("Secret", "service_app");
    this.socketTimeout = 15000; //TODO
    this.policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
  }

  public static synchronized ApiConnection apiFactory() {
    if (conn == null) {
      return new ApiConnection();
    } else {
      return conn;
    }
  }

  private com.android.volley.Response.Listener<GsonSupport[]> successListener(final Callback callback) {
    return new com.android.volley.Response.Listener<GsonSupport[]>() {
      @Override
      public void onResponse(GsonSupport[] response) {
        Log.i("RESPONSE1", "Response : " + response);
        callback.onSuccess(response);
      }
    };
  }

  private com.android.volley.Response.ErrorListener errorListener(final Callback callback) {
    return new com.android.volley.Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.i("ERROR", "Error : " + error.getLocalizedMessage());
        Toast.makeText(ApiConnection.this.context, "Check your internet connection", Toast.LENGTH_SHORT).show();
      }
    };
  }

  private void addToQueue() {
    RequestQueue requestQueue = Volley.newRequestQueue(this.context);
    this.request.setRetryPolicy(this.policy);
    requestQueue.add(this.request);
  }

  public void get(String url, final Callback callback) {
    this.request = new GsonRequest<GsonSupport[]>(this.baseUrl + url, Request.Method.GET, GsonSupport[].class, header, successListener(callback), errorListener(callback));
    this.addToQueue();
  }

  public void post(String url, JSONObject params, final Callback callback) {
    this.request = new GsonRequest<GsonSupport[]>(this.baseUrl + url, Request.Method.POST, GsonSupport[].class, header, successListener(callback), errorListener(callback), params);
    this.addToQueue();
  }

  public void addToHeader(String key, String value) {
    this.header.put(key, value);
  }

  public Context getContext() {
    return this.context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public interface Callback {
    void onSuccess(GsonSupport[] result);
  }
}
