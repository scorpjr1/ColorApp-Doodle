package com.example.colorapp.view.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.colorapp.MainActivity;
import com.example.colorapp.R;
import com.example.colorapp.view.fragments.SplashScreenFragment;
import com.example.colorapp.view.fragments.SplashScreenFragment1;
import com.example.colorapp.view.fragments.SplashScreenFragment2;
import com.example.colorapp.view.fragments.SplashScreenFragment3;

public class SplashScreenActivity extends AppCompatActivity {

    Button btnNextSC, btnNextSC1, btnNextSC2, btnNextSC3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.splashScreenFrameLayout, new SplashScreenFragment())
                .commit();

        btnNextSC = findViewById(R.id.btnNextSC);
        btnNextSC1 = findViewById(R.id.btnNextSC1);
        btnNextSC2 = findViewById(R.id.btnNextSC2);
        btnNextSC3 = findViewById(R.id.btnNextSC3);

//        btnNextSC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.splashScreenFrameLayout, new SplashScreenFragment1())
//                        .commit();
//            }
//        });
//        btnNextSC1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.splashScreenFrameLayout, new SplashScreenFragment2())
//                        .commit();
//            }
//        });
//        btnNextSC2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.splashScreenFrameLayout, new SplashScreenFragment3())
//                        .commit();
//            }
//        });
//        btnNextSC3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
//            }
//        });
    }
}