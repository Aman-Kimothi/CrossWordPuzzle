package com.example.ankitkumarojha.nowordsfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public final String DICTIONARY_LOAD = "Dictionary-LOAD";
    public Dictionary dictionary;
    public String selectedString;
    public int score=0,penalty=0;
    PopulateWords populateWords = new PopulateWords(dictionary.getRandomWords());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
         //   dictionary = new Dictionary(inputStream);
        } catch (IOException e) {
            Log.e(DICTIONARY_LOAD, "Cannot Load The Dictionary");
        }
        onStart();

    }

    @Override
    protected void onStart() {
        Button checkWordButton = (Button) findViewById(R.id.checkWordButton);
        Button finishButton = (Button) findViewById(R.id.finishButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        populateWords.vertical();
        populateWords.horizontal();
        populateWords.addrandomCharaters();


        char ch[][] = populateWords.arr;    //  Use this array to populate the TextView


        fillTable(table);

        checkWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dictionary.checkIsWord(selectedString))
                    updateScore(true);
                else
                    updateScore(false);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //computerTurn();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart();
            }
        });
        super.onStart();
    }

    public void fillTable(TableLayout table) {
        TextView resultTextView = (TextView)findViewById(R.id.resultTextView);
        table.removeAllViews();
        Display display = getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        Log.d("Screen size",""+screenWidth);
        //resultTextView.setText(""+screenWidth);
        int sizeOfEachCell = screenWidth/10;
        for (int i = 0; i < 10; i++) {
            TableRow row = new TableRow(MainActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            for (int j = 0; j < 10; j++) {
                final TextView gridTextView = new TextView(this);
                int x = (i * 10 + j);
                gridTextView.setId(x);
                gridTextView.setTag("n_selected");
                gridTextView.setText("A");
                gridTextView.setTextSize((int)(sizeOfEachCell/3.18)); //3.18
                gridTextView.setPadding(sizeOfEachCell/4, sizeOfEachCell/128, sizeOfEachCell/4, sizeOfEachCell/128);
                gridTextView.setBackgroundResource(R.drawable.gridcellstyle);
                row.addView(gridTextView);
                gridTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(gridTextView.getTag().equals("selected")){
                            gridTextView.setTag("n_selected");
                            gridTextView.setBackgroundResource(R.drawable.gridcellstyle);
                        } else {
                            gridTextView.setTag("selected");
                            gridTextView.setBackgroundColor(0xc0c0c0);
                        }

                    }
                });

            }
            table.addView(row);
        }
    }

    public void updateScore(boolean correct)
    {
        final String UPDATE_SCORE="Update Score Method";
        if(correct)
        {
            Log.d(UPDATE_SCORE,"Correct Word Selected");
            score += 10;
        }
        else
        {
            Log.d(UPDATE_SCORE,"Wrong Word Selected");
            penalty +=2;
        }
        updateScoreTextView();
        Log.d(UPDATE_SCORE,"Score Updated");
    }

    public void updateScoreTextView()
    {
        final String UPDATE_SCORE_TEXT_VIEW = "Update Score TextView";
        Log.d(UPDATE_SCORE_TEXT_VIEW,"Method Called");
        TextView scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        TextView penaltyTextView = (TextView) findViewById(R.id.penaltyTextView);
        scoreTextView.setText("Score = "+score);
        penaltyTextView.setText("Penalty = +"+penalty);
        Log.d(UPDATE_SCORE_TEXT_VIEW,"Score TextView Updated");
    }
}