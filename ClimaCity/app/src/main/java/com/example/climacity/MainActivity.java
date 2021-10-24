package com.example.climacity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 123;
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    // App ID para usar o OpenWeather data
    final String APP_ID = "<bb9919b7aa9fbbc0359684f24d83a592>";
    // Tempo de atualização
    final long MIN_TIME = 200;
    // Distancia entre as atualizações
    final float MIN_DISTANCE = 1;
    // TODO: Set LOCATION_PROVIDER here:
    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    // pela rede de dados LocationManager.NETWORK_PROVIDER
// Tag de bebug
    final String LOGCAT_TAG = "Clima";

    TextView mTextViewCity;
    ImageView mImageViewSky;
    TextView mTextViewTemperature;
    // TODO: Declaração do LocationManager and e LocationListener here:
    LocationManager mLocationManager;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCity = (TextView) findViewById(R.id.textViewCity);
        mImageViewSky = (ImageView) findViewById(R.id.imageViewSky);
        mTextViewTemperature = (TextView) findViewById(R.id.textViewTemperature);
    }
    private void getWeatherForCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
// Some additional log statements to help you debug
        Log.d(LOGCAT_TAG, "Location Provider used: "
                + mLocationManager.getProvider(LOCATION_PROVIDER).getName());
        Log.d(LOGCAT_TAG, "Location Provider is enabled: "
                + mLocationManager.isProviderEnabled(LOCATION_PROVIDER));
        Log.d(LOGCAT_TAG, "Last known location (if any): "
                + mLocationManager.getLastKnownLocation(LOCATION_PROVIDER));
        Log.d(LOGCAT_TAG, "Requesting location updates");
        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.d("Clima", "onLocationChanged() callback received");
                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());
                Log.d("Clima","longitude is: "+longitude);
                Log.d("Clima","latitude is: "+latitude);
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d(LOGCAT_TAG, "onStatusChanged() callback received. Status: " + i);
                Log.d(LOGCAT_TAG, "2 means AVAILABLE, 1: TEMPORARILY_UNAVAILABLE, 0: OUT_OF_SERVICE");
            }
            @Override
            public void onProviderEnabled(String s) {
                Log.d(LOGCAT_TAG, "onProviderEnabled() callback received. Provider: " + s);
            }
            @Override
            public void onProviderDisabled(String s) {
                Log.d("Clima", "onProviderDisabled() callback received");
            }

        };
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOGCAT_TAG, "onRequestPermissionsResult(): Permission granted!");
// Pega dados do tempo somente se tiver permissão
                getWeatherForCurrentLocation();
            } else {
                Log.d(LOGCAT_TAG, "Permission denied =( ");
            }
        }
    }
    private void letsDoSomeNetworking(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL,params,new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Clima","Sucess! JSON: "+response.toString());
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                Log.e("Clima","Fail "+throwable.toString());
                Log.d("Clima","Status code "+ statusCode);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOGCAT_TAG, "onResume() called");
        getWeatherForCurrentLocation();
    }



}