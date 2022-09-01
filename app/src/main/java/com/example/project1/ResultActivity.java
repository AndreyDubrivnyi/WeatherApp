package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class  ResultActivity extends AppCompatActivity {
    private TextView result_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_info = (TextView) findViewById(R.id.result_info);
        Intent intent = getIntent();
        String name = intent.getStringExtra("city");
        String city = name;
        String key = "01ab921498495d8e9fbe958bf37b789b";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key + "&units=metric&lang=ua";
        new GetURLData().execute(url);

    }

    private class GetURLData extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            result_info.setText("Зачекайте...");

        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {

                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                JSONObject jsonWeather = jsonArray.getJSONObject(0);


                result_info.setText("Темпаратура: " + jsonObject.getJSONObject("main").getDouble("temp") + "\n" +
                        "Відчувається як: " + jsonObject.getJSONObject("main").getDouble("feels_like") + "\n" +
                        "Швидкість Вітра: " + jsonObject.getJSONObject("wind").getDouble("speed") + "м/c" + "\n" +
                        "Хмарнісь: " + jsonObject.getJSONObject("clouds").getInt("all") + "%" +"\n" + "Зараз: " +
                        jsonWeather.getString("description"));



                String  weather = jsonWeather.getString("main");

                if(weather.equals("Clouds")) {
                    LottieAnimationView cloudy = (LottieAnimationView) findViewById(R.id.сloudy);
                    cloudy.setVisibility(View.VISIBLE);
                }
                if(weather.equals("Clear")) {
                    LottieAnimationView sunny = (LottieAnimationView) findViewById(R.id.sunny);
                    sunny.setVisibility(View.VISIBLE);
                }
                if(weather.equals("Rain")) {
                    LottieAnimationView rain = (LottieAnimationView) findViewById(R.id.rain);
                    rain.setVisibility(View.VISIBLE);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}

