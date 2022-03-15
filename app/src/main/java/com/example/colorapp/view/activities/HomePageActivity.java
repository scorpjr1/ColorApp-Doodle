package com.example.colorapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.colorapp.MainActivity;
import com.example.colorapp.MosaicDemo;
import com.example.colorapp.R;
import com.example.colorapp.guide.DoodleGuideActivity;

public class HomePageActivity extends AppCompatActivity {

    CardView cvZiyouhua, cvZhaopianhua, cvMasaike, cvShezhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        cvZiyouhua = findViewById(R.id.cvZiyouhua);
        cvZiyouhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DoodleGuideActivity.class));
            }
        });

        cvZhaopianhua = findViewById(R.id.cvZhaopianhua);
        cvZhaopianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
            }
        });

        cvMasaike = findViewById(R.id.cvMasaike);
        cvMasaike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MosaicDemo.class));
            }
        });

        cvShezhe = findViewById(R.id.cvShezhe);
        cvShezhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePageActivity.this, "功能还未实现", Toast.LENGTH_SHORT).show();
            }
        });


    }
}