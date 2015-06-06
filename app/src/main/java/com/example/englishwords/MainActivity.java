package com.example.englishwords;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button start;
    private Button next;
    private Button repeat;
    private Button knownWords;
    private Button help;
    private LinearLayout layout;
    private int mistake = 0;
    private int correct = 0;
    private String s;
    private DataBaseWords words;
    private TextView display;
    private TextView helper;
    private EditText answer;
    private TextView correctly;
    private TextView mistakes;
    private SwipeFlingAdapterView tinderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.Tv_display);
        helper = (TextView) findViewById(R.id.Tv_helper);
        answer = (EditText) findViewById(R.id.editText_answer);
        correctly = (TextView) findViewById(R.id.tv_correctly);
        mistakes = (TextView) findViewById(R.id.tv_mistakes);
        layout = (LinearLayout) findViewById(R.id.ll_count);
        tinderView = (SwipeFlingAdapterView)findViewById(R.id.tinder_view);

        start = (Button) findViewById(R.id.btn_start);
        next = (Button) findViewById(R.id.btn_next);
        repeat = (Button) findViewById(R.id.btn_Repeat);
        help = (Button) findViewById(R.id.btn_Help);
        knownWords = (Button) findViewById(R.id.btn_I_know);

        start.setOnClickListener(this);
        next.setOnClickListener(this);
        repeat.setOnClickListener(this);
        help.setOnClickListener(this);
        knownWords.setOnClickListener(this);

        words = new DataBaseWords();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isCorrectly() {
        if (answer.getText().toString().toLowerCase().equals(words.getEnglishWord(display.getText().toString().toLowerCase()))) {
            correctly.setText(String.valueOf(++correct));
            return true;
        } else {
            mistakes.setText(String.valueOf(++mistake));
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                words.generateDemoWords();
                display.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                display.setText(words.getRussianWord());
                start.setText(next.getText());
                start.setId(R.id.btn_next);

                break;
            case R.id.btn_next:
                if (isCorrectly()) {
                    helper.setVisibility(View.GONE);
                    display.setText(words.getRussianWord());
                    answer.setText("");
                    Toast.makeText(this, "Correctly", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "перевод неверный " + words.getEnglishWord(display.getText().toString()),
                            Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_I_know:
                words.deleteEnglishWord(display.getText().toString());
                display.setText(words.getRussianWord());
                answer.setText("");

                break;
            case R.id.btn_Repeat:
                words.addWordsForRepeat(display.getText().toString());
                break;
            case R.id.btn_Help:
                helper.setVisibility(View.VISIBLE);
                helper.setText(words.getEnglishWord(display.getText().toString()));
                break;
        }

    }
}
