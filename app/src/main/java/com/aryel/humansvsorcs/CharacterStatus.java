/**
 * Created by adminjta on 12/03/2016.
 */
package com.aryel.humansvsorcs;

import android.widget.TextView;
import android.widget.Toast;

public class CharacterStatus {

    private String name;
    private int hitPoints;
    private int stamina;
    private int attack;
    private int defense;
    private TextView displayMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }


    public void increaseStamina(CharacterStatus character){
        character.setStamina(3);
    }

    public CharacterStatus(String name,int hitPoints,int stamina,int attack,int defense) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.stamina = stamina;
        this.attack = attack;
        this.defense = defense;
    }
}



