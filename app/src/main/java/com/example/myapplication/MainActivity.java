package com.example.myapplication;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import uz.jamshid.lib.WinterLayout;

public class MainActivity extends AppCompatActivity {
    WinterLayout winter;
    Button btn;
    TextView txt;
    TextView textview;
    int counter = 1;
    int i = 0;
    int size = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winter = this.findViewById(R.id.winter);
        winter.startWinter();
        txt = findViewById(R.id.textView2);
        btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        textview = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (i) {
                    case 0:
                        textview.setBackgroundResource(R.drawable.fff);
                        i++;
                        break;
                    case 1:
                        textview.setBackgroundResource(R.drawable.aaa);
                        i++;
                        break;
                    case 2:
                        textview.setBackgroundResource(R.drawable.bbb);
                        i++;
                        break;
                    case 3:
                        textview.setBackgroundResource(R.drawable.ccc);
                        i++;
                        break;
                    case 4:
                        textview.setBackgroundResource(R.drawable.ddd);
                        i++;
                        break;
                    case 5:
                        textview.setBackgroundResource(R.drawable.eee);
                        i++;
                        break;
                }
                if (i >= 6)
                    i = 0;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Board Selected");
                toBoard();
                counter++;
                if (counter == 3) {
                    toBoard2();
                }
            }
        });
    }
    public void toBoard() {
        Intent intent = new Intent(this, Ongame.class);
        startActivity(intent);
    }
    public void toBoard2() {
        Intent intent = new Intent(this, Ongame2.class);
        startActivity(intent);
    }
}