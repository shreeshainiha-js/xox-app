package com.example.xoxgame;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button[] buttons = new Button[9];
    private boolean playerXTurn = true;
    private int[] gameState = new int[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int index = i;
            buttons[i] = (Button) gridLayout.getChildAt(i);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (gameState[index] == 0) {
                        gameState[index] = playerXTurn ? 1 : 2;
                        buttons[index].setText(playerXTurn ? "X" : "O");
                        if (checkWinner()) {
                            Toast.makeText(MainActivity.this, "Player " + (playerXTurn ? "X" : "O") + " wins!", Toast.LENGTH_SHORT).show();
                            disableButtons();
                        } else if (isDraw()) {
                            Toast.makeText(MainActivity.this, "It's a draw!", Toast.LENGTH_SHORT).show();
                        }
                        playerXTurn = !playerXTurn;
                    }
                }
            });
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        resetGame();
    }

    private boolean checkWinner() {
        int[][] winPositions = {   {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (int state : gameState) {
            if (state == 0) {
                return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            gameState[i] = 0;
        }
        boolean playerXTurn1 = playerXTurn;
        playerXTurn1=true;
    }
}