package com.example;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;

    TextView scoreText;

    ImageView iv;
    int score;

    Random rand;

    int die1,die2,die3;

    ArrayList<Integer> dice;

    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;

        rollResult = findViewById(R.id.rollResult);
        scoreText = findViewById(R.id.scoreText);

        rand = new Random();
        dice = new ArrayList<Integer>();

        ImageView iv1 = findViewById(R.id.die1Image);
        ImageView iv2 = findViewById(R.id.die2Image);
        ImageView iv3 = findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<>();
        diceImageViews.add(iv1);
        diceImageViews.add(iv2);
        diceImageViews.add(iv3);


    }

    public void rollDice(View view){

        rollResult.setText("Clicked!");

        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int i = 0; i < 3; i++){

            String img = "die_" + dice.get(i) + ".png";

            try{
                InputStream stream = getAssets().open(img);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(i).setImageDrawable(d);

            }catch(IOException e){
                e.printStackTrace();

            }

        }

        String msg;

        if (die1 == die2 && die1 == die3){
            int scored = die1 * 100;
            msg = "You rolled a triple " + die1 + "! You score " + scored + " points!";
            score += scored;
        }else if(die1 == die2 || die1 == die3 || die2 == die3){

            msg = "You rolled a doubles for 50 point ";
            score += 50;
        }else{
            msg = "You didn't score this roll. Try Again!";
        }

        rollResult.setText(msg);
        scoreText.setText("Score: " + score);
    }



}
