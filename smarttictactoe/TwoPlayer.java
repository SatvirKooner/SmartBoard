package com.example.theotherlegacy.smarttictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class TwoPlayer extends AppCompatActivity {
    private Button[] b = new Button[9];
    int player = 1;
    int[][] grid = new int[3][3];
    ArrayList<Integer> buts = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        createBoardList();
        createBoard();

        b[0] = (Button) findViewById(R.id.b1);
        b[1] = (Button) findViewById(R.id.b2);
        b[2] = (Button) findViewById(R.id.b3);
        b[3] = (Button) findViewById(R.id.b4);
        b[4] = (Button) findViewById(R.id.b5);
        b[5] = (Button) findViewById(R.id.b6);
        b[6] = (Button) findViewById(R.id.b7);
        b[7] = (Button) findViewById(R.id.b8);
        b[8] = (Button) findViewById(R.id.b9);

        for (int i = 0; i < b.length; i++) {
            final int c = i;
            b[i].setTextSize(40);
            b[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!MainActivity.getRobot()) {
                        if (player == 1) {
                            if (b[c].getText().equals("")) {
                                b[c].setText("x");
                                updateBoard(c, 1);
                                buts.remove(Integer.valueOf(c));
                                int gameStatus = isGameOver(grid);
                                if (gameStatus == -1)
                                    player = 2;
                                else if (gameStatus ==1) {
                                    Toast.makeText(TwoPlayer.this,
                                            MainActivity.getPlayerOne()+ " Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                } else if (gameStatus ==2) {
                                    Toast.makeText(TwoPlayer.this,
                                            MainActivity.getPlayerTwo()+ " Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                }else {
                                    Toast.makeText(TwoPlayer.this, "Nobody Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                }
                            }
                        } else {
                            if (b[c].getText().equals("")) {
                                b[c].setText("O");
                                b[c].setTextColor(Color.RED);
                                updateBoard(c, 2);
                                buts.remove(Integer.valueOf(c));
                                int gameStatus = isGameOver(grid);
                                if (gameStatus == -1)
                                    player = 1;
                                else if (gameStatus ==1) {
                                    Toast.makeText(TwoPlayer.this,
                                            MainActivity.getPlayerOne()+ " Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                } else if (gameStatus ==2) {
                                    Toast.makeText(TwoPlayer.this,
                                            MainActivity.getPlayerTwo()+ " Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                }else {
                                    Toast.makeText(TwoPlayer.this, "Nobody Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                }
                            }
                        }
                    } else {
                        if (player == 1) {
                            if (b[c].getText().equals("")) {
                                b[c].setText("x");
                                updateBoard(c, 1);
                                buts.remove(Integer.valueOf(c));
                                int gameStatus = isGameOver(grid);
                                if (gameStatus == -1) {
                                    int move = getNextMove(grid,player);
                                    if (move >= 0) {
                                        buts.remove(Integer.valueOf(move));
                                        b[move].setText("O");
                                        b[move].setTextColor(Color.RED);
                                        updateBoard(move, 2);
                                        gameStatus = isGameOver(grid);
                                         if (gameStatus ==1) {
                                            Toast.makeText(TwoPlayer.this,
                                                    MainActivity.getPlayerOne()+ " Wins!", Toast.LENGTH_SHORT).show();
                                            disableBoard();
                                        } else if (gameStatus ==2) {
                                            Toast.makeText(TwoPlayer.this,
                                                    "Robot Wins!", Toast.LENGTH_SHORT).show();
                                            disableBoard();
                                        }else if (gameStatus ==0){
                                            Toast.makeText(TwoPlayer.this, "Nobody Wins!", Toast.LENGTH_SHORT).show();
                                            disableBoard();
                                        }
                                    }
                                } else if (gameStatus ==1) {
                                    Toast.makeText(TwoPlayer.this,
                                            MainActivity.getPlayerOne()+ " Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                } else if (gameStatus ==2) {
                                    Toast.makeText(TwoPlayer.this,
                                            "Robot Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                }else {
                                    Toast.makeText(TwoPlayer.this, "Nobody Wins!", Toast.LENGTH_SHORT).show();
                                    disableBoard();
                                }

                            }
                        }
                    }
                }
            });
        }
    }

    public  int getNextMove(int[][]state, int player) {
        boolean moved = false;
        MiniMax mmax = new MiniMax();
        int move=mmax.getAIMove(state, 2);
        return move;



    }

    public void createBoardList() {
        for (int i = 0; i < 9; i++) {
            buts.add(i);
        }
    }

    public void createBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void disableBoard() {
        for (int i = 0; i < 9; i++) {
            b[i].setEnabled(false);
            b[i].setTextColor(Color.GRAY);
        }
    }

    public int isGameOver(int[][] grid) {
        if ((grid[0][0] != 0) && (grid[0][0] == grid[0][1]) && (grid[0][1] == grid[0][2]))
            return grid[0][0];
        else if ((grid[1][0] != 0) && (grid[1][0] == grid[1][1]) && (grid[1][1] == grid[1][2]))
            return grid[1][0];
        else if ((grid[2][0] != 0) && (grid[2][0] == grid[2][1]) && (grid[2][1] == grid[2][2]))
            return grid[2][0];
        else if ((grid[0][0] != 0) && (grid[0][0] == grid[1][0]) && (grid[1][0] == grid[2][0]))
            return grid[0][0];
        else if ((grid[1][1] != 0) && (grid[0][1] == grid[1][1]) && (grid[1][1] == grid[2][1]))
            return grid[1][1];
        else if ((grid[0][2] != 0) && (grid[0][2] == grid[1][2]) && (grid[1][2] == grid[2][2]))
            return grid[0][2];
        else if ((grid[1][1] != 0) && (grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]))
            return grid[1][1];
        else if ((grid[0][2] != 0) && (grid[0][2] == grid[1][1]) && (grid[0][2] == grid[2][0])) {
            return grid[0][2];
        } else if (buts.isEmpty()){
            return 0;
        } else
        return -1;

         /*   int zeros = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == 0)
                        zeros++;
                }
            }
            if (zeros == 0)
                return 0;
            else
                return -1;
        }*/

    }

    public int minimax(int[][] state, int depth, int player) {
        int[][] currentState;
        int gameStatus = isGameOver(state);
        if (gameStatus == 2)
            return 10 - depth;
        else if (gameStatus == 1)
            return depth - 10;
        else if (gameStatus == 0)
            return 0;
        else if (player == 1) {
            ArrayList<Integer> scores=new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    currentState = state;
                    if (currentState[i][j] == 0)
                        currentState[i][j] = player;
                    scores.add(minimax(currentState, ++depth, 2));
                }
            }
            return(Collections.min(scores));
        }
        else {
            ArrayList<Integer> scores=new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    currentState = state;
                    if (currentState[i][j] == 0)
                        currentState[i][j] = player;
                    scores.add(minimax(currentState, ++depth, 1));
                }
            }
            return(Collections.max(scores));
        }


    }

    public void updateBoard(int position, int player) {
        if (player == 1) {
            switch (position) {
                case 0:
                    grid[0][0] = 1;
                    break;
                case 1:
                    grid[0][1] = 1;
                    break;
                case 2:
                    grid[0][2] = 1;
                    break;
                case 3:
                    grid[1][0] = 1;
                    break;
                case 4:
                    grid[1][1] = 1;
                    break;
                case 5:
                    grid[1][2] = 1;
                    break;
                case 6:
                    grid[2][0] = 1;
                    break;
                case 7:
                    grid[2][1] = 1;
                    break;
                case 8:
                    grid[2][2] = 1;
                    break;

            }

        } else {
            switch (position) {
                case 0:
                    grid[0][0] = 2;
                    break;
                case 1:
                    grid[0][1] = 2;
                    break;
                case 2:
                    grid[0][2] = 2;
                    break;
                case 3:
                    grid[1][0] = 2;
                    break;
                case 4:
                    grid[1][1] = 2;
                    break;
                case 5:
                    grid[1][2] = 2;
                    break;
                case 6:
                    grid[2][0] = 2;
                    break;
                case 7:
                    grid[2][1] = 2;
                    break;
                case 8:
                    grid[2][2] = 2;
                    break;
            }
        }
    }
}
