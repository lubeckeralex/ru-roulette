package com.example.alexandrpadalko.myapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    TextView textView;
    private MediaPlayer shootSound, clickSound, spinSound, readySound;

    List<String> bullets;

    boolean shuffled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        shootSound = MediaPlayer.create(this, R.raw.shootsound);
        clickSound = MediaPlayer.create(this, R.raw.clicksound);
        spinSound =MediaPlayer.create(this, R.raw.spinsound);
        readySound = MediaPlayer.create(this, R.raw.readysound);


        bullets = new ArrayList<>();
        //add only one bullet in the gun
        bullets.add("NO");
        bullets.add("NO");
        bullets.add("NO");
        bullets.add("NO");
        bullets.add("NO");
        bullets.add("YES");

        //roll (randomize) the barrel
        Collections.shuffle(bullets);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //roll (randomize) the barrel
                Collections.shuffle(bullets);
                spinSound.start();
                readySound.start();
                imageView.setImageResource(R.drawable.ic_gun);
                shuffled = true;
                textView.setText("Click on the image to shoot!");
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shuffled) {
                    String currentBullet = bullets.get(0);
                    playGame(currentBullet);
                    shuffled = false;
                } else {
                    textView.setText("First roll the barrelQ");
                }
            }
        });

    }


    public void playGame(String currentB) {
        if(currentB.equals("YES")) {
            imageView.setImageResource(R.drawable.ic_bang);
            textView.setText("You are dead!");
            shootSound.start();
        } else {
            clickSound.start();
            imageView.setImageResource(R.drawable.ic_click);
            textView.setText("You are alive! Pass the gun to the next player!");
        }

    }
}
