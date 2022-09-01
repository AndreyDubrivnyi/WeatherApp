package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;

public class MainActivity extends AppCompatActivity  {

    private EditText user_field;
    private Button main_btn;
    private LottieAnimationView animationView;
    private static final String [] Cities = new String[]{

            "Kyiv", "Lviv", "Dnipro", "Kryvyi Rih", "Kharkiv", "Sumy", "Chernivtsi", "Vinnytsia"
            , "Odesa", "Zaporizhzhia", "Zaporizhzhia", "Mykolaiv", "Luhansk ", "Chernihiv", "Donetsk",
            "Mariupol", "Makiivka", "Kherson", "Poltava", "Khmelnytskyi","Cherkasy", "Zhytomyr", "Rivne",
            "Horlivka","Ivano-Frankivsk","Kamianske", "Ternopil", "Kropyvnytskyi", "Kremenchuk", "Lutsk",
            "Bila Tserkva", "Melitopol", "Kramatorsk", "Uzhhorod", "Brovary", "Berdiansk", "Nikopol",
            "Pavlohrad", "Kamianets-Podilskyi","Mukachevo","Konotop", "Uman", "Oleksandriia","Drohobych",
            "Berdychiv", "Shostka", "Kovel","Nizhyn", "Smila","Kalush","Chervonohrad","Boryspil","Irpin",
            "Korosten","Stryi","Chornomorsk","Lozova","Enerhodar","Pryluky","Novovolynsk","Okhtyrka",
            "Izium","Nova Kakhovka","Fastiv","Lubny","Bucha","Obukhiv", "Chuhuiv", "Novoiavorivsk","Kostopil",
            "Truskavets","Skvyra","Orikhiv","Berezan","Ovruch","Novyi Buh", "Pripyat","Bibrka", "Novyi Kalyniv",
            "Komarno","Skalat", "Khyriv","Dobromyl", "Vyzhnytsia"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView editText = findViewById(R.id.user_field);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cities);
        editText.setAdapter(adapter);
        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_btn);

        main_btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.clear, Toast.LENGTH_LONG).show();
                else {

                    Intent intent = new Intent();
                    intent.putExtra("city", user_field.getText().toString());
                    intent.setClass(MainActivity.this, ResultActivity.class);
                    startActivity(intent);



                }
            }
        }));



    }}