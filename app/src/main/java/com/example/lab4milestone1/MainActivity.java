package com.example.lab4milestone1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private static Button startButton;
    private static volatile boolean stopThread = false;
    private static TextView textview;
    private static int downloadprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startbutton);
        startButton.setText("Testing");
        textview = findViewById(R.id.textView);
    }

    public void mockFileDownloader(){
        runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  startButton.setText("DOWNLOADING...");
              }
          });

        for(int downloadProgress = 0; downloadProgress <= 100; downloadProgress += 10) {
            MainActivity.downloadprogress = downloadProgress;
            if(stopThread) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startButton.setText("Start");
                    }
                });
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      textview.setText("Download Stopped");
                  }});
                return;
            }

            Log.d(TAG, "Download Progress: " + downloadProgress + "%");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textview.setText("Download Progress: " + downloadprogress + "%");
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("Start");
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textview.setText("");
            }});
    }

    public void startDownload(View view) {
        ExampleRunnable exampleRunnable = new ExampleRunnable();
        new Thread(exampleRunnable).start();
    }

    public void stopDownload(View view) {
        stopThread = true;
    }
}
