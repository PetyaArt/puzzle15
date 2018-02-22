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
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button21;
    private Button button22;
    private Button button23;
    private Button button24;
    private Button button31;
    private Button button32;
    private Button button33;
    private Button button34;
    private Button button41;
    private Button button42;
    private Button button43;
    private Button button44;

    //private TextView infoTextView = findViewById(R.id.textView);

    private Button NULL;

    private Button [][] mButtons = new Button[4][4];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (button11 == null){
            createMass();
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

    private void createMass(){
        button11 = findViewById(R.id.button11);
        mButtons[0][0] = button11;
        button12 = findViewById(R.id.button12);
        mButtons[0][1] = button12;
        button13 = findViewById(R.id.button13);
        mButtons[0][2] = button13;
        button14 = findViewById(R.id.button14);
        mButtons[0][3] = button14;

        button21 = findViewById(R.id.button21);
        mButtons[1][0] = button21;
        button22 = findViewById(R.id.button22);
        mButtons[1][1] = button22;
        button23 = findViewById(R.id.button23);
        mButtons[1][2] = button23;
        button24 = findViewById(R.id.button24);
        mButtons[1][3] = button24;

        button31 = findViewById(R.id.button31);
        mButtons[2][0] = button31;
        button32 = findViewById(R.id.button32);
        mButtons[2][1] = button32;
        button33 = findViewById(R.id.button33);
        mButtons[2][2] = button33;
        button34 = findViewById(R.id.button34);
        mButtons[2][3] = button34;

        button41 = findViewById(R.id.button41);
        mButtons[3][0] = button41;
        button42 = findViewById(R.id.button42);
        mButtons[3][1] = button42;
        button43 = findViewById(R.id.button43);
        mButtons[3][2] = button43;
        button44 = findViewById(R.id.button44);
        mButtons[3][3] = button44;

        NULL = button44;
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
