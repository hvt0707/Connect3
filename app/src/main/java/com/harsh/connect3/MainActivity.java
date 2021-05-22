package com.harsh.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0: empty, 1: yellow, 2: red
    int active = 1;
    int[] gameState = {0,0,0,0,0,0,0,0,0};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view){
        TextView winnerMsg = findViewById(R.id.textView);
        winnerMsg.setVisibility(View.INVISIBLE);
        Button btnPlayAgain = (Button) findViewById(R.id.button);
        btnPlayAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView img = (ImageView)gridLayout.getChildAt(i);
            img.setImageDrawable(null);
        }

        active = 1;
        gameActive = true;
        Arrays.fill(gameState, 0);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 0 && gameActive) {
            gameState[tappedCounter] = active;
            counter.setTranslationY(-1500);
            if (active == 1) {
                counter.setImageResource(R.drawable.yellow);
                active = 2;
            } else {
                counter.setImageResource(R.drawable.red);
                active = 1;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(800);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 0) {
                    gameActive = false;
                    String winner;
                    if (active == 1)
                        winner = "Red";
                    else
                        winner = "Yellow";

                    TextView winnerMsg = (TextView) findViewById(R.id.textView);
                    winnerMsg.setText(winner + " has won.");
                    winnerMsg.setVisibility(View.VISIBLE);
                    Button btnPlayAgain = (Button) findViewById(R.id.button);
                    btnPlayAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}