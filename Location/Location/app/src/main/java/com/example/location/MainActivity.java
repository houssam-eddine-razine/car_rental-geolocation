package com.example.location;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText ipInput;
    String IP_ADDRESS;
    LinearLayout container;
    List<String> infoList = new ArrayList<>();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ipInput = findViewById(R.id.IpInput);
        this.container = findViewById(R.id.container);
        this.requestQueue = Volley.newRequestQueue(MainActivity.this);

        findViewById(R.id.button).setOnClickListener(v -> {
            IP_ADDRESS = ipInput.getText().toString();
            fetchLocationData();
        });
    }

    private void fetchLocationData() {
        String url = "https://ipinfo.io/" + IP_ADDRESS + "/geo";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String city = jsonObject.getString("city");
                        String region = jsonObject.getString("region");
                        String country = jsonObject.getString("country");
                        String LatLand = jsonObject.getString("loc");
                        String[] latLong = LatLand.split(",");
                        String latitude = latLong[0];
                        String longitude = latLong[1];

                        infoList.add("City : " + city);
                        infoList.add("Region : " + region);
                        infoList.add("Country : " + country);

                        displayLocationInfo();
                        sendLocationDataToServer(city, region, country, latitude, longitude);
                        addMapButton(LatLand);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                volleyError -> Log.e("API Err", volleyError.toString())
        );
        requestQueue.add(request);
    }


    private void displayLocationInfo() {
        container.removeAllViews();
        for (String text : infoList) {
            TextView toAdd = new TextView(MainActivity.this);
            toAdd.setText(text);
            toAdd.setTextSize(16);
            toAdd.setTypeface(null, Typeface.BOLD);
            toAdd.setTextColor(Color.WHITE);
            toAdd.setBackgroundColor(Color.parseColor("#62317E"));
            int paddingValue = 20;
            toAdd.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
            toAdd.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 20);
            toAdd.setLayoutParams(layoutParams);
            container.addView(toAdd);
        }
    }

    private void sendLocationDataToServer(String city, String region, String country, String latitude, String longitude) {
        String url = "http://10.0.2.2/API/location.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    // Handle response
                    Log.d("Response", response);
                },
                error -> {
                    Log.e("Post Err", error.toString());
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("city", city);
                params.put("region", region);
                params.put("country", country);
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                Log.d("Params", "city: " + city + ", region: " + region + ", country: " + country + ", latitude: " + latitude + ", longitude: " + longitude);
                return params;
            }
        };
        requestQueue.add(postRequest);
    }


    private void addMapButton(String LatLand) {
        Button mapBtn = new Button(MainActivity.this);
        mapBtn.setText("Show Map");
        int paddingY = 30;
        mapBtn.setPadding(0, paddingY, 0, paddingY);
        container.addView(mapBtn);

        mapBtn.setOnClickListener(view -> {
            Intent map = new Intent(view.getContext(), MapIntent.class);
            map.putExtra("LatLand", LatLand);
            startActivity(map);
        });
    }
}