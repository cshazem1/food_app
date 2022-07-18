package com.demo.hazem_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class circular extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressText.setText("" + i);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 1);
                } else {
                    handler.removeCallbacks(this);
                }
                if(i==100)
                {
                    Intent intent=new Intent(circular.this,Activity_Enter.class);
                    startActivity(intent);
                    circular.this.finish();

                }
            }
        }, 1);
    }
    }
