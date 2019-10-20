package ca.uwaterloo.cs.a4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class gameActivity extends AppCompatActivity implements Observer {

    LinearLayout root;      // root layout
    Model model;            // game model
    boolean animation_finish = false;   // flag to indicates if animation finished

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        model = Model.getInstance();
        model.addObserver(this);
        // get the parent layout
        root = (LinearLayout) findViewById(R.id.parentLayout);

        //declare all buttons
        Button backButton = (Button) findViewById(R.id.back);
        final Button startButton = (Button) findViewById(R.id.start);
        final Button b1 = (Button) findViewById(R.id.b1);
        final Button b2 = (Button) findViewById(R.id.b2);
        final Button b3 = (Button) findViewById(R.id.b3);
        final Button b4 = (Button) findViewById(R.id.b4);
        final Button b5 = (Button) findViewById(R.id.b5);
        final Button b6 = (Button) findViewById(R.id.b6);
        final TextView score = (TextView) findViewById(R.id.score);
        final TextView message = (TextView) findViewById(R.id.message);


        // Declare a button that points to the setting button
        // and add listener to it
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backToMain();
            }
        });

        // Declare a button that points to the start button
        // and add listener to it
        message.setText("Click START to play");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setText("Watch what I do ...");
                model.newRound();
                score.setText("Score: " + String.valueOf(model.score));
                if(model.state == 2){
                    animation_finish = false;
                    for(int i = 0; i< model.length; i++) {
                        int curbutton = model.nextButton();
                        //Log.d("COMPUTER", String.valueOf(curbutton));
                        int invisiable_delay = (1000 - model.difficulty * 250) * (i * 2);
                        int visiable_delay = (1000 - model.difficulty * 250) * (i * 2 + 1);
                        if (curbutton == 1) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b1.setVisibility(View.INVISIBLE);
                                }
                            }, invisiable_delay); // where 1000 is equal to 1 sec (1 * 1000)

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b1.setVisibility(View.VISIBLE);
                                }
                            }, visiable_delay); // where 1000 is equal to 1 sec (1 * 1000)
                        } else if (curbutton == 2) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b2.setVisibility(View.INVISIBLE);
                                }
                            }, invisiable_delay); // where 1000 is equal to 1 sec (1 * 1000)

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b2.setVisibility(View.VISIBLE);
                                }
                            }, visiable_delay); // where 1000 is equal to 1 sec (1 * 1000)
                        } else if (curbutton == 3) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b3.setVisibility(View.INVISIBLE);
                                }
                            }, invisiable_delay); // where 1000 is equal to 1 sec (1 * 1000)

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b3.setVisibility(View.VISIBLE);
                                }
                            }, visiable_delay); // where 1000 is equal to 1 sec (1 * 1000)
                        } else if (curbutton == 4) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b4.setVisibility(View.INVISIBLE);
                                }
                            }, invisiable_delay); // where 1000 is equal to 1 sec (1 * 1000)

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b4.setVisibility(View.VISIBLE);
                                }
                            }, visiable_delay); // where 1000 is equal to 1 sec (1 * 1000)
                        } else if (curbutton == 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b5.setVisibility(View.INVISIBLE);
                                }
                            }, invisiable_delay); // where 1000 is equal to 1 sec (1 * 1000)

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b5.setVisibility(View.VISIBLE);
                                }
                            }, visiable_delay); // where 1000 is equal to 1 sec (1 * 1000)
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b6.setVisibility(View.INVISIBLE);
                                }
                            }, invisiable_delay); // where 1000 is equal to 1 sec (1 * 1000)

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b6.setVisibility(View.VISIBLE);
                                }
                            }, visiable_delay); // where 1000 is equal to 1 sec (1 * 1000)
                        }
                    }
                }
                // wait till buttons finish
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("Now it's your turn");
                        animation_finish = true;
                    }
                }, (1000 - model.difficulty * 250) * model.length * 2 - 100);
            }
        });

        // Declare a button that points to the simon button 1
        // and add listener to it
        if (model.buttons < 1) {
            b1.setEnabled(false);
            b1.setVisibility(View.GONE);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MyApp","1");
                if (model.state == 3 && animation_finish) {
                    boolean correct = model.verifyButton(1);
                    if (!correct) {
                        // change the message context
                        message.setText("You lose. Click START to play again");
                        score.setText("Score: " + String.valueOf(model.score));
                    } else {
                        // change the message context
                        if (model.state == 5) {
                            message.setText("You won! Click START to continue");
                            score.setText("Score: " + String.valueOf(model.score));
                        }
                    }
                }
            }
        });

        // Declare a button that points to the simon button 2
        // and add listener to it
        if (model.buttons < 2) {
            b2.setEnabled(false);
            b2.setVisibility(View.GONE);
        }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.state == 3 && animation_finish) {
                    //Log.d("MyApp","2");
                    boolean correct = model.verifyButton(2);
                    if (!correct) {
                        // change the message context
                        message.setText("You lose. Press START to play again");
                        score.setText("Score: " + String.valueOf(model.score));
                    } else {
                        // change the message context
                        if (model.state == 5) {
                            message.setText("You won! Click START to continue");
                            score.setText("Score: " + String.valueOf(model.score));
                        }
                    }
                }
            }
        });
        // Declare a button that points to the simon button 3
        // and add listener to it
        if (model.buttons < 3) {
            b3.setEnabled(false);
            b3.setVisibility(View.GONE);
        }
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MyApp","3");
                if (model.state == 3 && animation_finish) {
                    boolean correct = model.verifyButton(3);
                    if (!correct) {
                        // change the message context
                        message.setText("You lose. Press START to play again");
                        score.setText("Score: " + String.valueOf(model.score));
                    } else {
                        // change the message context
                        if (model.state == 5) {
                            message.setText("You won! Click START to continue");
                            score.setText("Score: " + String.valueOf(model.score));
                        }
                    }
                }
            }
        });
        // Declare a button that points to the simon button 4
        // and add listener to it
        if (model.buttons < 4) {
            b4.setEnabled(false);
            b4.setVisibility(View.GONE);
        }
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MyApp","4");
                if (model.state == 3 && animation_finish) {
                    boolean correct = model.verifyButton(4);
                    if (!correct) {
                        // change the message context
                        message.setText("You lose. Press START to play again");
                        score.setText("Score: " + String.valueOf(model.score));
                    } else {
                        // change the message context
                        if (model.state == 5) {
                            message.setText("You won! Click START to continue");
                            score.setText("Score: " + String.valueOf(model.score));
                        }
                    }
                }
            }
        });
        // Declare a button that points to the simon button 5
        // and add listener to it
        if (model.buttons < 5) {
            b5.setEnabled(false);
            b5.setVisibility(View.GONE);
        }
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MyApp","5");
                if (model.state == 3 && animation_finish) {
                    boolean correct = model.verifyButton(5);
                    if (!correct) {
                        // change the message context
                        message.setText("You lose. Press START to play again");
                        score.setText("Score: " + String.valueOf(model.score));
                    } else {
                        // change the message context
                        if (model.state == 5) {
                            message.setText("You won! Click START to continue");
                            score.setText("Score: " + String.valueOf(model.score));
                        }
                    }
                }
            }
        });
        // Declare a button that points to the simon button 6
        // and add listener to it
        if (model.buttons < 6) {
            b6.setEnabled(false);
            b6.setVisibility(View.GONE);
        }
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MyApp","6");
                if (model.state == 3 && animation_finish) {
                    boolean correct = model.verifyButton(6);
                    if (!correct) {
                        // change the message context
                        message.setText("You lose. Press START to play again");
                        score.setText("Score: " + String.valueOf(model.score));
                    } else {
                        // change the message context
                        if (model.state == 5) {
                            message.setText("You won! Click START to continue");
                            score.setText("Score: " + String.valueOf(model.score));
                        }
                    }
                }
            }
        });

        model.initObservers();
    }


    private void backToMain() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
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
    public void update(Observable o, Object arg) {
        // Update button with click count from model
        //mIncrementButton.setText(String.valueOf(mModel.getCounter()));
    }
}
