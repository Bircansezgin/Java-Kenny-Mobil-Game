package com.example.javaprojeoyun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // Label'lari Tanimlamak!
    TextView sayacText;
    TextView skorText;
    int score;

    // Fotograflari Aldik!
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView12;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView13;
    ImageView imageView11;
    ImageView imageView17;
    ImageView imageView14;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_main);

        // Initialize
        // Bir kere textlerimi baslattik!
        sayacText = (TextView) findViewById(R.id.sayacText);
        skorText = (TextView) findViewById(R.id.skorText);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView12 = findViewById(R.id.imageView12);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView17 = findViewById(R.id.imageView17);
        imageView14 = findViewById(R.id.imageView14);
        // Fotogaflari arrayin icine attiK!
        imageArray = new ImageView[] {imageView1, imageView11, imageView2, imageView13, imageView12, imageView16, imageView15, imageView17, imageView14};

        score = 0;

        AlertDialog.Builder baslangisAlart = new AlertDialog.Builder(this);
        baslangisAlart.setTitle("Merhaba?");
        baslangisAlart.setMessage("Baslamaya Hazir Misin?");

        baslangisAlart.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Zamanlayami Baslatsin!
                new CountDownTimer(10000, 1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        sayacText.setText("Time : " + millisUntilFinished/1000);
                    }

                    // Sayac Bitince ne olsun?
                    @Override
                    public void onFinish() {
                        sayacText.setText("Sure Bitti");
                        //Runnable durdur!
                        handler.removeCallbacks(runnable);

                        // Zaman Bitince Gizle!
                        for (ImageView image: imageArray){
                            image.setVisibility(View.INVISIBLE);
                        }

                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Yeniden?");
                        alert.setMessage("Tekrar Oynamak Ister misiniz?");

                        alert.setPositiveButton("Yeniden Baslat", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Oyunu Terkar Baslat!
                                // onCreate Tekrardan Baslat
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });

                        alert.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this , "Game OVER", Toast.LENGTH_SHORT).show();
                            }
                        });

                        alert.show();
                    }
                }.start();


                fotoGizle();
            }

        });

        baslangisAlart.setNegativeButton("Hayir Oynamak Istemiyorum!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this , "COK KORKAKSIN!!!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        baslangisAlart.show();

    }

    public void skorArttir(View view){
        score++;
        skorText.setText("Skor :" + score);
    }

    // Fotogaflari gizlemek!
    public void fotoGizle(){

        handler = new Handler();

        // Belirli periyotlarda bir seyler yaptirmak istersek Kullanabilirz!
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                // Runnable calistir, fakat rotarli calistir!
                handler.postDelayed(this, 500);
            }
        };

        // Runnable Calistirmak icin
        handler.post(runnable);

    }
}