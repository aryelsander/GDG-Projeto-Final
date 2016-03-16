package com.aryel.humansvsorcs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] enemyNames = new String[]{ "Garrosh","Zodagh","Viggulm","Nofhug","Igmut",
                                                "Fodagog","Viguka","Garothmuk","Xugor","Borug",
                                                "Burub","Gulfim","Urog","Sharog","Mor","Gharol",
                                                "Onog","Futgarek","Ungagh","Xugar","Milug","Largakh",
                                                "Klog","Ogrumbu","Vakmu","Vigdolg","Trugagh","Igubat"};

    private Random rand = new Random();

    public CharacterStatus player = new CharacterStatus("PLAYER",10,10,1,1);
    public CharacterStatus enemy = new CharacterStatus(enemyNames[rand.nextInt(enemyNames.length)],10,10,1,1);

    private int randomChoice;
    private int maxPlayerHP = player.getHitPoints();
    private int maxEnemyHP = 10;
    private int score = 0;

    private TextView namePlayerView;
    private TextView nameEnemyView;
    private TextView messageDisplay;
    private TextView hpPlayerText;
    private TextView stPlayerText;
    private TextView atkPlayerText;
    private TextView defPlayerText;
    private TextView hpEnemyText;
    private TextView stEnemyText;
    private TextView atkEnemyText;
    private TextView defEnemyText;

    private Button btnAtk;
    private Button btnDef;
    private Button btnRest;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        namePlayerView = (TextView)findViewById(R.id.player_name);
        nameEnemyView = (TextView)findViewById(R.id.enemy_name);
        messageDisplay = (TextView) findViewById(R.id.display_text_view);
        hpPlayerText = (TextView)findViewById(R.id.player_hp_text_view);
        stPlayerText = (TextView)findViewById(R.id.player_st_text_view);
        atkPlayerText = (TextView)findViewById(R.id.player_atk_text_view);
        defPlayerText = (TextView)findViewById(R.id.player_def_text_view);
        hpEnemyText = (TextView)findViewById(R.id.enemy_hp_text_view);
        stEnemyText = (TextView)findViewById(R.id.enemy_st_text_view);
        atkEnemyText = (TextView)findViewById(R.id.enemy_atk_text_view);
        defEnemyText = (TextView)findViewById(R.id.enemy_def_text_view);
        btnAtk = (Button) findViewById(R.id.player_button_atk);
        btnDef = (Button) findViewById(R.id.player_button_defend);
        btnRest = (Button) findViewById(R.id.player_button_rest);
        btnNext = (Button) findViewById(R.id.player_button_nextBattle);
        displayName();
        updateStatus();
    }


    public void displayName(){
        if(MainActivity.namePlayer.length() != 0)
            namePlayerView.setText(MainActivity.namePlayer.getText().toString().toUpperCase());
        nameEnemyView.setText(enemy.getName().toUpperCase());
    }

    public void attack(View view){
        randomChoice = rand.nextInt(3);
        if(player.getStamina() >= 2){
            player.setStamina(player.getStamina()-2);
            if(randomChoice == 0 && enemy.getStamina() >=2){
                enemy.setStamina(enemy.getStamina() - 2);
                player.setHitPoints(player.getHitPoints() - enemy.getAttack());
                enemy.setHitPoints(enemy.getHitPoints() - player.getAttack());
                displayMessage(String.format("You hit your enemy and hit %d and your enemy hit in you %d !",player.getAttack(), enemy.getAttack()));
            }else if(randomChoice == 1 && enemy.getStamina() >= 1){
                enemy.setStamina(enemy.getStamina() - 1);
                if(enemy.getDefense() < player.getAttack()){
                    enemy.setStamina(enemy.getStamina() - 1);
                    player.setHitPoints(player.getHitPoints() - enemy.getAttack());
                    enemy.setHitPoints(enemy.getHitPoints() - (player.getAttack() - enemy.getDefense()));
                    displayMessage(String.format("You attack your enemy and he defend, you hit %d!", player.getAttack() - enemy.getDefense()));
                }else{
                    displayMessage(String.format("You miss!",enemy.getAttack()));
                }

            }else{
                enemy.setHitPoints(enemy.getHitPoints() - (player.getAttack() * 2));
                enemy.setStamina(enemy.getStamina() + 3);
                displayMessage(String.format("Suprise Attack! you hit %d !",player.getAttack() * 2));
            }
        } else {
            displayMessage(String.format("Not enough Stamina. Please Rest"));
        }

        updateStatus();
    }
    public void defend(View view){

        randomChoice = rand.nextInt(3);

        if(player.getStamina() >=1){
            player.setStamina(player.getStamina() - 1);
            if(randomChoice == 0 && enemy.getStamina() >=2){
                if(player.getDefense() < enemy.getAttack()){
                    enemy.setStamina(enemy.getStamina() - 2);
                    displayMessage(String.format("You defend your enemy's attack,he hit %d !",enemy.getAttack() - player.getDefense()));
                }else{
                    enemy.setStamina(enemy.getStamina() - 2);
                    displayMessage(String.format("Your enemy miss!"));
                }
            }
            else if (randomChoice == 1 && enemy.getStamina() >= 1) {
                enemy.setStamina(enemy.getStamina() - 1);
                displayMessage("Both stay in defensive position!");

            }
            else{
                enemy.setStamina(enemy.getStamina() + 3);
                displayMessage("Your enemy rest and you stay in defensive position!");
            }
        }
        else{
            displayMessage(String.format("Not enough Stamina. Please Rest."));
        }
        updateStatus();
    }
    public void rest(View view){
        player.setStamina(player.getStamina() + 3);
        randomChoice = rand.nextInt(3);

        if(randomChoice == 0 && enemy.getStamina() >=2){
            enemy.setStamina(enemy.getStamina() - 2);
            player.setHitPoints(player.getHitPoints() - (enemy.getAttack() * 2));
            displayMessage(String.format("Your enemy attack in a suprise attack! he hit %d !", enemy.getAttack() * 2));
        }
        else if(randomChoice == 1 && enemy.getStamina() >=1){
            enemy.setStamina(enemy.getStamina() - 1);
            displayMessage("You stop to rest and your enemy stay in defensive position!");
        }
        else{
            displayMessage(String.format("Both stop to rest!"));
            enemy.setStamina(enemy.getStamina() + 3);
        }
        updateStatus();
    }
    public void nextBattle(View view){
        if(player.getHitPoints() > 0){
            displayMessage("After training and a long rest, you find another orc!");
            player.setHitPoints(player.getHitPoints() + 25);
            player.setStamina(10);
            player.setAttack(player.getAttack() + 2);
            player.setDefense(player.getDefense() + 2);
            enemy.setHitPoints(maxEnemyHP + rand.nextInt(25));
            maxEnemyHP = enemy.getHitPoints();
            enemy.setStamina(10);
            enemy.setAttack(enemy.getAttack() + rand.nextInt(5));
            enemy.setDefense(enemy.getDefense() + rand.nextInt(5));
            btnNext.setEnabled(false);
            btnAtk.setEnabled(true);
            btnDef.setEnabled(true);
            btnRest.setEnabled(true);
            updateStatus();
            enemy.setName(enemyNames[rand.nextInt(enemyNames.length)]);
            displayName();
        } else{
            score = 0;
            player.setHitPoints(maxPlayerHP);
            player.setStamina(10);
            player.setAttack(1);
            player.setDefense(1);
            enemy.setHitPoints(maxEnemyHP);
            enemy.setStamina(10);
            enemy.setAttack(1);
            enemy.setDefense(1);
            btnNext.setEnabled(false);
            btnNext.setText("NextBattle");
            btnAtk.setEnabled(true);
            btnDef.setEnabled(true);
            btnRest.setEnabled(true);
            updateStatus();
            enemy.setName(enemyNames[rand.nextInt(enemyNames.length)]);
            displayName();
            displayMessage("THE FIGHT BEGINS!");
        }


    }

    public void checkIsOver(){
        if(player.getHitPoints() <=0){
            displayMessage(String.format("YOU DIE! YOU KILL %d ORCS!",score));
            btnAtk.setEnabled(false);
            btnDef.setEnabled(false);
            btnRest.setEnabled(false);
            btnNext.setEnabled(true);
            btnNext.setText("Restart Game");
            maxEnemyHP = 10;
        } else if (enemy.getHitPoints() <= 0) {
            score++;
            displayMessage("YOU WIN! Prepare to the next battle!");
            btnAtk.setEnabled(false);
            btnDef.setEnabled(false);
            btnRest.setEnabled(false);
            btnNext.setEnabled(true);
        }
    }


    public void displayMessage(String message){
        messageDisplay.setText(message);
    }
    public void updateStatus(){

        hpPlayerText.setText(String.format("HitPoints - %d",player.getHitPoints()));
        stPlayerText.setText(String.format("Stamina - %d",player.getStamina()));
        atkPlayerText.setText(String.format("Attack - %d",player.getAttack()));
        defPlayerText.setText(String.format("Defense - %d",player.getDefense()));
        hpEnemyText.setText(String.format("HitPoints - %d", enemy.getHitPoints()));
        stEnemyText.setText(String.format("Stamina - %d",enemy.getStamina()));
        atkEnemyText.setText(String.format("Attack - %d",enemy.getAttack()));
        defEnemyText.setText(String.format("Defense - %d",enemy.getDefense()));
        checkIsOver();
    }

}
