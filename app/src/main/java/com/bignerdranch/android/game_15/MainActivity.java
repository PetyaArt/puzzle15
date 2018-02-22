package com.bignerdranch.android.game_15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int INT = 4;

    private Button NULL;

    private Button [][] mButtons = new Button[4][4];
    private int[][] img = new int[][]{{R.id.button11, R.id.button12, R.id.button13, R.id.button14},
            {R.id.button21, R.id.button22, R.id.button23, R.id.button24,},
            {R.id.button31, R.id.button32, R.id.button33, R.id.button34,},
            {R.id.button41, R.id.button42, R.id.button43, R.id.button44,} };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mButtons == null){
            CreateMass();
        }
    }

    public void shuffle(int iterations){
        Random random = new Random(42);

        for(int i = 0; i < iterations; i++){
            int x = Math.abs(random.nextInt()) % 4;
            int y = Math.abs(random.nextInt()) % 4;

            checkButton(mButtons[x][y]);
        }

    }

    private void CreateMass(){
        for (int i = 0; i < INT; i++){
            for (int j = 0; j < INT; j++){
                mButtons[i][j] = findViewById(img[i][j]);
            }
        }
        NULL = mButtons[3][3];
        mButtons[3][3].setVisibility(View.INVISIBLE);

        shuffle(1000);
    }

    public void onMyButtonClick(View view) {
        checkButton(view);
        if (checkWin(view)){
            Toast.makeText(this, "You win", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkWin(View view) {
        String[] array = getResources().getStringArray(R.array.array_button);
        int o = 0;
        boolean flag = false;
        for (int i = 0; i < INT; i++){
            for (int j = 0; j < INT; j++){
                if ((array[o]).equals(((TextView)mButtons[i][j]).getText().toString())){
                    flag = true;
                }
                else if(!((array[o]).equals(((TextView)mButtons[i][j]).getText().toString()))){
                    return false;
                }
                o++;
            }
        }
        return flag;
    }

    private void checkButton(View view){
        for (int i = 0; i < INT; i++){
            for (int j = 0; j < INT; j++){
                if(NULL.getId() == mButtons[i][j].getId()) {
                    if (i - 1 >= 0) {
                        if (mButtons[i - 1][j].getId() == view.getId()) {
                            mButtons[i][j].setVisibility(View.VISIBLE);
                            view.setVisibility(View.INVISIBLE);
                            mButtons[i][j].setText(((TextView)view).getText().toString());
                            NULL = mButtons[i - 1][j];
                            mButtons[i - 1][j].setText(R.string.button44);
                        }
                    }
                    if (i + 1 <= 3) {
                        if (mButtons[i + 1][j].getId() == view.getId()) {
                            mButtons[i][j].setVisibility(View.VISIBLE);
                            view.setVisibility(View.INVISIBLE);
                            mButtons[i][j].setText(((TextView)view).getText().toString());
                            NULL = mButtons[i + 1][j];
                            mButtons[i + 1][j].setText(R.string.button44);
                        }
                    }
                    if (j - 1 >= 0){
                        if (mButtons[i][j - 1].getId() == view.getId()){
                            mButtons[i][j].setVisibility(View.VISIBLE);
                            view.setVisibility(View.INVISIBLE);
                            mButtons[i][j].setText(((TextView)view).getText().toString());
                            NULL = mButtons[i][j - 1];
                            mButtons[i][j - 1].setText(R.string.button44);
                        }
                    }
                    if (j + 1 <= 3){
                        if (mButtons[i][j + 1].getId() == view.getId()){
                            mButtons[i][j].setVisibility(View.VISIBLE);
                            view.setVisibility(View.INVISIBLE);
                            mButtons[i][j].setText(((TextView)view).getText().toString());
                            NULL = mButtons[i][j + 1];
                            mButtons[i][j + 1].setText(R.string.button44);
                        }
                    }
                }
            }
        }
    }

}
