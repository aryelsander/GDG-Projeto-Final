package com.aryel.humansvsorcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static EditText namePlayer;
    public GameActivity gameActivity = new GameActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namePlayer = (EditText) findViewById(R.id.name_player);
    }

    public void startGame(View view){

        Intent intent = new Intent(this,GameActivity.class);
        gameActivity.player.setName(namePlayer.getText().toString());
        startActivity(intent);


    }



}
