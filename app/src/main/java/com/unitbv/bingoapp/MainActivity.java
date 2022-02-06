package com.unitbv.bingoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView random1TV = null;
    private TextView random2TV = null;
    private TextView random3TV = null;

    private Button button1 = null;
    private Button button2 = null;
    private Button button3 = null;

    private TextView generatedTV = null;

    GenerateNumber generateNumber = new GenerateNumber();
    int generatedNr;
    Timer timeout;

    int checked = 0;

    class GenerateNumber extends TimerTask {
        boolean started = false;

        @Override
        public void run() {
            generatedNr = new Random().nextInt(5) + 1;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    generatedTV.setText(String.valueOf(generatedNr));
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random1TV = findViewById(R.id.random1);
        random2TV = findViewById(R.id.random2);
        random3TV = findViewById(R.id.random3);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        generatedTV = findViewById(R.id.generatedNr);

        final int random1 = new Random().nextInt(5) + 1;
        final int random2 = new Random().nextInt(5) + 1;
        final int random3 = new Random().nextInt(5) + 1;

        random1TV.setText(String.valueOf(random1));
        random2TV.setText(String.valueOf(random2));
        random3TV.setText(String.valueOf(random3));

        if(!generateNumber.started) {
            generateNumber.started = true;
            timeout = new Timer();
            timeout.scheduleAtFixedRate(generateNumber, 0, 5000);
        } else {
            generateNumber.started = false;
            timeout.cancel();
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(generatedNr == random1) {
                    button1.setEnabled(false);
                    random1TV.setBackground(getResources().getDrawable(R.drawable.number_checked));
                    checked++;
                    if(checked == 3) {
                        Toast.makeText(MainActivity.this, "Well Done!", Toast.LENGTH_LONG).show();
                        recreate();
                    }
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(generatedNr == random2) {
                    button2.setEnabled(false);
                    random2TV.setBackground(getResources().getDrawable(R.drawable.number_checked));
                    checked++;
                    if(checked == 3) {
                        Toast.makeText(MainActivity.this, "Well Done!", Toast.LENGTH_LONG).show();
                        recreate();
                    }
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(generatedNr == random3) {
                    button3.setEnabled(false);
                    random3TV.setBackground(getResources().getDrawable(R.drawable.number_checked));
                    checked++;
                    if(checked == 3) {
                        Toast.makeText(MainActivity.this, "Well Done!", Toast.LENGTH_LONG).show();
                        recreate();
                    }
                }
            }
        });
    }
}