package com.example.lakemm.scarnesdice;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

//for multi-threading


//import static com.example.lakemm.scarnesdice.R.id.imageButton;

public class MainActivity extends AppCompatActivity {

    private int user_totalScore;
    private int user_turnScore;
    private int comp_totalScore;
    private int comp_turnScore;
    private Boolean userTurn;

    ImageButton imageButton;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textViewTimer;
    long startTime = 0;

    Handler timeHandler = new Handler();

    Runnable timeRunnable = new Runnable(){
        @Override
        public void run() {
            //long millis = System.currentTimeMillis() - startTime;
            //int seconds = (int) (millis / 1000);
            //int minutes = seconds / 60;
            //seconds = seconds % 60;

            textViewTimer.setText(String.format("%d:%02d", minutes, seconds));

            textViewTimer.postDelayed(this, 500);
            timeHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userTurn = true;

        imageButton = (ImageButton)findViewById(R.id.imageButton);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
    }

//user functions only
    public void rollDie(View view){
        int rand = setRand();
        set_dieFace(rand);
        update_turnScore(userTurn, rand);
    }

    public void holdDie(View view){
        hold();
    }

    public void hold(){
        //update user total score
        user_totalScore += user_turnScore;
        textView.setText("Your total score: ");
        textView.append(Integer.toString(user_totalScore));
        reset_turnScore();

        //computer's turn
        compMove();
    }



    public void reset_turnScore(){

        user_turnScore = 0;
        comp_turnScore = 0;
        textView3.setText("");
    }

    public void reset(View view){

        reset_turnScore();

        user_totalScore = 0;
        comp_totalScore = 0;

        textView.setText(R.string.your_score);
        textView2.setText(R.string.computer_score);

    }

//computer auto moves
    public void compMove(){
        userTurn = false;



        int rand = setRand();
        set_dieFace(rand);
        update_turnScore(userTurn, rand);
        Log.d("problem:", Integer.toString(comp_turnScore));


        updateComp();
    }

    public void updateComp(){
        Log.d("problem turn total:", Integer.toString(comp_turnScore));
        comp_totalScore += comp_turnScore;
        textView2.setText("Computer total score: ");
        textView2.append(Integer.toString(comp_totalScore));
        reset_turnScore();
        userTurn = true;
    }

//calculations
    public int setRand(){
        Random random = new Random();
        return random.nextInt(7 - 1) + 1;
    }


    public void set_dieFace(int rand){

        switch(rand){
            case 1:
                imageButton.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageButton.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageButton.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageButton.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageButton.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageButton.setImageResource(R.drawable.dice6);
                break;
        }


/*
String dieFace = String.format("dice%d.png", rand);

        StringBuilder sb = new StringBuilder("dice");
        sb.append(rand);
        sb.append(".png");

        String dieFace = sb.toString();

        int id = getResources().getIdentifier("drawable://" + dieFace, null, null);


        imageButton.setImageResource(id);*/
    }

    public void update_turnScore(Boolean user, int rand){

        if (rand == 1) {
            if(user) {

                user_turnScore = 0;
                textView3.setText("Your turn score: ");
                textView3.append(Integer.toString(user_turnScore));
                hold();
            }
            else{

                comp_turnScore = 0;
                textView3.setText("Computer turn score: ");
                textView3.append(Integer.toString(comp_turnScore));
                userTurn = true;
            }
        }
        else{
            if(user) {

                user_turnScore += rand;
                textView3.setText("Your turn score: ");
                textView3.append(Integer.toString(user_turnScore));
            }
            else{

                comp_turnScore += rand;
                textView3.setText("Comp turn score: ");
                textView3.append(Integer.toString(comp_turnScore));
            }
        }


    }



}
