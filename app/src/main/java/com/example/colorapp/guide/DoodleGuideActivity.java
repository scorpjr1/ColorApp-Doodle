package com.example.colorapp.guide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.colorapp.R;

public class DoodleGuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // 中级涂鸦
        ViewGroup middleContainer = (ViewGroup) findViewById(R.id.container_middle_doodle);
        MiddleDoodleView middleDoodleView = new MiddleDoodleView(this);
        middleContainer.addView(middleDoodleView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }
}
