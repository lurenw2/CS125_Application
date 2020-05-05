package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import uz.jamshid.lib.WinterLayout;

public class Ongame extends AppCompatActivity {
    private int[] image = {R.drawable.crystal1, R.drawable.crystal2, R.drawable.crystal3, R.drawable.crystal4, R.drawable.crystal5,
            R.drawable.crystal6, R.drawable.crystal7, R.drawable.crystal8, R.drawable.crystal9};
    private int[] imageIndex = new int[image.length];
    private ImageButton ib00, ib01, ib02, ib10, ib11, ib12, ib20, ib21, ib22;
    private TextView countdown;
    private Button btn;
    private CountDownTimer countDownTimer;
    private long time = 120000;
    private boolean timerunout;
    private int Xnum = 3;
    private int Ynum = 3;
    private int Imagenum = Xnum * Ynum;
    private int blankswap = Imagenum - 1;
    private int blankImage = R.id.crystal9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongame);
        countdown = findViewById(R.id.timer);
        btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Startstop();
                ib21.setClickable(true);
                ib12.setClickable(true);
            }
        });
        initialView();
        disruptRandom();
        ib12.setClickable(false);
        ib21.setClickable(false);
    }
    private void disruptRandom() {
        for(int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
        int rand1, rand2;
        for(int j = 0; j < 20; j++) {
            rand1 = (int)(Math.random() * (imageIndex.length - 1));
            do {
                rand2 = (int) (Math.random()*(imageIndex.length - 1));
                if (rand1 != rand2) {
                    break;
                }
            } while (true);
            Swap(rand1, rand2);
        }
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib10.setImageResource(image[imageIndex[3]]);
        ib11.setImageResource(image[imageIndex[4]]);
        ib12.setImageResource(image[imageIndex[5]]);
        ib20.setImageResource(image[imageIndex[6]]);
        ib21.setImageResource(image[imageIndex[7]]);
        ib22.setImageResource(image[imageIndex[8]]);
    }

    private void Swap(int rand1, int rand2) {
        int temp = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }


    public void Startstop() {
        if (timerunout) {
            stopTimer();
        } else {
            startTimer();
        }
    }
    public void startTimer() {
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                Updatetimer();
            }

            @Override
            public void onFinish() {
                countdown.setText("Time up!");
                ib00.setClickable(false);
                ib01.setClickable(false);
                ib02.setClickable(false);
                ib10.setClickable(false);
                ib11.setClickable(false);
                ib12.setClickable(false);
                ib20.setClickable(false);
                ib21.setClickable(false);
                ib22.setClickable(false);
            }
        }.start();
        btn.setText("Pause");
        timerunout = true;
    }
    public void stopTimer() {
        countDownTimer.cancel();
        btn.setText("Start");
        timerunout = false;
    }
    public void Updatetimer() {
        int minutes = (int) time / 60000;
        int seconds = (int) time % 60000 / 1000;
        String text = "" + minutes;
        text += ":";
        if (seconds < 10) {
            text += "0";
        }
        text += seconds;
        countdown.setText(text);
    }
    public void initialView() {
        ib00 = findViewById(R.id.crystal1);
        ib01 = findViewById(R.id.crystal2);
        ib02 = findViewById(R.id.crystal3);
        ib10 = findViewById(R.id.crystal4);
        ib11 = findViewById(R.id.crystal5);
        ib12 = findViewById(R.id.crystal6);
        ib20 = findViewById(R.id.crystal7);
        ib21 = findViewById(R.id.crystal8);
        ib22 = findViewById(R.id.crystal9);
    }
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.crystal1:
                move(R.id.crystal1, 0);
                break;
            case R.id.crystal2:
                move(R.id.crystal2, 1);
                break;
            case R.id.crystal3:
                move(R.id.crystal3, 2);
                break;
            case R.id.crystal4:
                move(R.id.crystal4, 3);
                break;
            case R.id.crystal5:
                move(R.id.crystal5, 4);
                break;
            case R.id.crystal6:
                move(R.id.crystal6, 5);
                break;
            case R.id.crystal7:
                move(R.id.crystal7, 6);
                break;
            case R.id.crystal8:
                move(R.id.crystal8, 7);
                break;
            case R.id.crystal9:
                move(R.id.crystal9, 8);
                break;
        }
    }

    private void move(int id, int site) {
        int sitex = site/Xnum;
        int sitey = site % Ynum;
        int blankx = blankswap/Xnum;
        int blanky = blankswap % Ynum;
        int xx = Math.abs(sitex - blankx);
        int yy = Math.abs(sitey - blanky);
        if ((xx == 0 && yy == 1) || (yy == 0 && xx == 1)) {
            ImageButton clickButton = findViewById(id);
            clickButton.setVisibility(View.INVISIBLE);
            ImageButton blankButton = findViewById(blankImage);
            blankButton.setImageResource(image[imageIndex[site]]);
            blankButton.setVisibility(View.VISIBLE);
            Swap(site, blankswap);
            blankswap = site;
            blankImage = id;
        }
        Gameover();
    }
    private void Gameover() {
        boolean over = true;
        for (int i = 0; i < imageIndex.length; i++) {
            if (imageIndex[i] != i) {
                over = false;
                break;
            }
        }
        if (over == true) {
            stopTimer();
            ib22.setImageResource(image[8]);
            ib22.setVisibility(View.VISIBLE);
            AlertDialog.Builder bbb = new AlertDialog.Builder(this);
            bbb.setMessage("Congrats, you win").setPositiveButton("confirm", null);
            AlertDialog dialog = bbb.create();
            dialog.show();
        }
    }
}
