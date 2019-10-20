package ca.uwaterloo.cs.a4;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Observable;
import java.util.Observer;

public class settingActivity extends AppCompatActivity implements Observer{
    Model model;
    private RadioGroup levelRadio;
    private RadioGroup numberRadio;

    // difficulty buttons
    RadioButton rbeasy;
    RadioButton rbnormal;
    RadioButton rbhard;
    // buttons for number of simon buttons
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    RadioButton rb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Get Model instance
        model = Model.getInstance();
        model.addObserver(this);

        // difficulty buttons
        rbeasy = (RadioButton) findViewById(R.id.easy);
        rbnormal = (RadioButton) findViewById(R.id.normal);
        rbhard = (RadioButton) findViewById(R.id.hard);
        // buttons for number of simon buttons
        rb1 = (RadioButton) findViewById(R.id.one);
        rb2 = (RadioButton) findViewById(R.id.two);
        rb3 = (RadioButton) findViewById(R.id.three);
        rb4 = (RadioButton) findViewById(R.id.four);
        rb5 = (RadioButton) findViewById(R.id.five);
        rb6 = (RadioButton) findViewById(R.id.six);

        // load on current settings
        // Check which level of difficulty was selected
        switch (model.difficulty){
            case 1:
                rbeasy.setTypeface(null, Typeface.BOLD_ITALIC);
                rbeasy.setChecked(true);
                break;
            case 2:
                rbnormal.setTypeface(null, Typeface.BOLD_ITALIC);
                rbnormal.setChecked(true);
                break;
            case 3:
                rbhard.setTypeface(null, Typeface.BOLD_ITALIC);
                rbhard.setChecked(true);
                break;
        }
        // Check which button amount was selected
        switch (model.buttons){
            case 1:
                rb1.setTypeface(null, Typeface.BOLD_ITALIC);
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setTypeface(null, Typeface.BOLD_ITALIC);
                rb2.setChecked(true);
                break;
            case 3:
                rb3.setTypeface(null, Typeface.BOLD_ITALIC);
                rb3.setChecked(true);
                break;
            case 4:
                rb4.setTypeface(null, Typeface.BOLD_ITALIC);
                rb4.setChecked(true);
                break;
            case 5:
                rb5.setTypeface(null, Typeface.BOLD_ITALIC);
                rb5.setChecked(true);
                break;
            case 6:
                rb6.setTypeface(null, Typeface.BOLD_ITALIC);
                rb6.setChecked(true);
                break;

        }

        levelRadio = (RadioGroup) findViewById(R.id.levelRadio);
        // setup radio button listener
        numberRadio = (RadioGroup) findViewById(R.id.numberRadio);
        // setup radio button listener

        // Declare a button that points to the setting button
        Button backButton = (Button) findViewById(R.id.back);
        //setup start button listener
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                backToMain();
            }
        });

        // Init observers
        model.initObservers();
    }

    private void backToMain(){
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

    public void onRadioButtonClickedLevel(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.easy:
                if (checked) {
                    model.difficulty = 1;
                    rbeasy.setTypeface(null, Typeface.BOLD_ITALIC);
                    //set the other two radio buttons text style to default
                    rbnormal.setTypeface(null, Typeface.NORMAL);
                    // reqire to import Typeface class
                    rbhard.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.normal:
                if (checked) {
                    model.difficulty = 2;
                    rbeasy.setTypeface(null, Typeface.NORMAL);
                    //set the other two radio buttons text style to default
                    rbnormal.setTypeface(null, Typeface.BOLD_ITALIC);
                    // reqire to import Typeface class
                    rbhard.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.hard:
                if (checked) {
                    model.difficulty = 3;
                    rbeasy.setTypeface(null, Typeface.NORMAL);
                    //set the other two radio buttons text style to default
                    rbnormal.setTypeface(null, Typeface.NORMAL);
                    // reqire to import Typeface class
                    rbhard.setTypeface(null, Typeface.BOLD_ITALIC);
                }
                break;
        }
    }

    public void onRadioButtonClickedNumber(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.one:
                if (checked) {
                    model.buttons = 1;
                    rb1.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb2.setTypeface(null, Typeface.NORMAL);
                    rb3.setTypeface(null, Typeface.NORMAL);
                    rb4.setTypeface(null, Typeface.NORMAL);
                    rb5.setTypeface(null, Typeface.NORMAL);
                    rb6.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.two:
                if (checked) {
                    model.buttons = 2;
                    rb1.setTypeface(null, Typeface.NORMAL);
                    rb2.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb3.setTypeface(null, Typeface.NORMAL);
                    rb4.setTypeface(null, Typeface.NORMAL);
                    rb5.setTypeface(null, Typeface.NORMAL);
                    rb6.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.three:
                if (checked) {
                    model.buttons = 3;
                    rb1.setTypeface(null, Typeface.NORMAL);
                    rb2.setTypeface(null, Typeface.NORMAL);
                    rb3.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb4.setTypeface(null, Typeface.NORMAL);
                    rb5.setTypeface(null, Typeface.NORMAL);
                    rb6.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.four:
                if (checked) {
                    model.buttons = 4;
                    rb1.setTypeface(null, Typeface.NORMAL);
                    rb2.setTypeface(null, Typeface.NORMAL);
                    rb3.setTypeface(null, Typeface.NORMAL);
                    rb4.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb5.setTypeface(null, Typeface.NORMAL);
                    rb6.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.five:
                if (checked) {
                    model.buttons = 5;
                    rb1.setTypeface(null, Typeface.NORMAL);
                    rb2.setTypeface(null, Typeface.NORMAL);
                    rb3.setTypeface(null, Typeface.NORMAL);
                    rb4.setTypeface(null, Typeface.NORMAL);
                    rb5.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb6.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.six:
                if (checked) {
                    model.buttons = 6;
                    rb1.setTypeface(null, Typeface.NORMAL);
                    rb2.setTypeface(null, Typeface.NORMAL);
                    rb3.setTypeface(null, Typeface.NORMAL);
                    rb4.setTypeface(null, Typeface.NORMAL);
                    rb5.setTypeface(null, Typeface.NORMAL);
                    rb6.setTypeface(null, Typeface.BOLD_ITALIC);
                }
                break;
        }
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {}
}
