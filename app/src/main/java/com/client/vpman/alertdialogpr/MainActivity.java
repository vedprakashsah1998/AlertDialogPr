package com.client.vpman.alertdialogpr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int splash=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.IndigoTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -20;
        params.height = 500;
        params.width = 500;
        params.y = -10;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.setColorMode(Color.TRANSPARENT);
        }
        this.getWindow().setAttributes(params);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();

            }
        },splash);



    }
}
