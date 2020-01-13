package com.nvmvzv.kelimesoyle.recyclbinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    TextView txt;
    Handler handle = null;
    Runnable runnable = null;
    int zaman;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        zaman = 0;
        handle = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                zaman++;

                if (zaman == 4){

                    handle.removeCallbacks(runnable);

                    Intent intent = new Intent(getApplicationContext(), ScreenActivity.class);
                    startActivity(intent);




                }
                handle.postDelayed(runnable, 1000);

            }
        };
        runnable.run();



    }
}
