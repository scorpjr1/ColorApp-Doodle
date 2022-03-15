package com.example.colorapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import cn.forward.androids.utils.LogUtil;
import com.example.doodle.DoodleActivity;
import com.example.doodle.DoodleParams;
import com.example.doodle.DoodleView;

import com.example.colorapp.guide.DoodleGuideActivity;
import cn.hzw.imageselector.ImageLoader;
import cn.hzw.imageselector.ImageSelectorActivity;

import com.example.colorapp.github.dragon66.AnimatedGIFWriter;

public class MainActivity extends Activity {

    public static final int REQ_CODE_SELECT_IMAGE = 100;
    public static final int REQ_CODE_DOODLE = 101;
    private TextView mPath;

    Button btnShare, btnSticker;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShare = findViewById(R.id.btn_share);
        img = findViewById(R.id.img);
        btnSticker = findViewById(R.id.btn_sticker);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting drawable value from image
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                shareImageandText(bitmap);
            }
        });

        btnSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                //True for dither. Will need more memory and CPU
                AnimatedGIFWriter writer = new AnimatedGIFWriter(true);
                OutputStream os = null;
                File imagefolder = new File(getCacheDir(), "images");
                try {
                    imagefolder.mkdirs();
                    File file = new File(imagefolder, "output.gif");
                    os = new FileOutputStream(file);
                    writer.write(bitmap, os);
                    Toast.makeText(MainActivity.this, "生成表情包成功", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        findViewById(R.id.btn_select_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelectorActivity.startActivityForResult(REQ_CODE_SELECT_IMAGE, MainActivity.this, null, false);
            }
        });


        mPath = (TextView) findViewById(R.id.img_path);
    }

    private void shareImageandText(Bitmap bitmap) {
        Uri uri = getimageToShare(bitmap);
        Intent intent = new Intent(Intent.ACTION_SEND);
        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        // adding text to share
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");
        // Add subject Here
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        // setting type to image
        intent.setType("image/png");
        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    // Retrieving the url to share
    private Uri getimageToShare(Bitmap bitmap) {
        File imagefolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagefolder.mkdirs();
            File file = new File(imagefolder, "shared_image.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(this, "com.example.colorapp.fileprovider", file);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return uri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (data == null) {
                return;
            }
            ArrayList<String> list = data.getStringArrayListExtra(ImageSelectorActivity.KEY_PATH_LIST);
            if (list != null && list.size() > 0) {
                LogUtil.d("Doodle", list.get(0));

                // 涂鸦参数
                DoodleParams params = new DoodleParams();
                params.mIsFullScreen = true;
                // 图片路径
                params.mImagePath = list.get(0);
                // 初始画笔大小
                params.mPaintUnitSize = DoodleView.DEFAULT_SIZE;
                // 画笔颜色
                params.mPaintColor = Color.RED;
                // 是否支持缩放item
                params.mSupportScaleItem = true;
                // 启动涂鸦页面
                DoodleActivity.startActivityForResult(MainActivity.this, params, REQ_CODE_DOODLE);
            }
        } else if (requestCode == REQ_CODE_DOODLE) {
            if (data == null) {
                return;
            }
            if (resultCode == DoodleActivity.RESULT_OK) {
                String path = data.getStringExtra(DoodleActivity.KEY_IMAGE_PATH);
                if (TextUtils.isEmpty(path)) {
                    return;
                }
                ImageLoader.getInstance(this).display(findViewById(R.id.img), path);
                mPath.setText(path);
            } else if (resultCode == DoodleActivity.RESULT_ERROR) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
