package ca.uwaterloo.cs.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare a button that points to the setting button
        Button settingButton = (Button) findViewById(R.id.setting);
        //setup setting button listener
        settingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                settingGame();
            }
        });

        // Declare a button that points to the setting button
        Button startButton = (Button) findViewById(R.id.start);
        //setup start button listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startingGame();
            }
        });
    }

    private void settingGame(){
        Intent launchSetting = new Intent(this, settingActivity.class);
        startActivity(launchSetting);
    }

    private void startingGame(){
        Intent launchGame = new Intent(this, gameActivity.class);
        startActivity(launchGame);
    }
}
