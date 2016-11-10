package com.mygdx.game.Utility;

public class PlayerData {

    private int attackLevel;
    private float attackMul;
    private int hpLevel;
    private float hpMul;
    private int rangeLevel;
    private float range;
    private float money;
    private float progess;
    private int attackPrice;
    private int hpPrice;
    private int rangePrince;
    private boolean destroyed;
    public PlayerData() {
        super();
        destroyed = false;
        attackLevel = 1;
        attackMul = 1;
        hpLevel = 1;
        hpMul = 1;
        range = 1;
        rangeLevel = 1;
        money = 99999;
        progess = 0;
        attackPrice = 100;
        hpPrice = 100;
        rangePrince = 100;
    }

    public void decreaseMoney(float m){
        money -= m;
    }
    public void increaseMoney(float m){
        money += m;
    }
    public void advanceProgess(float m){
        progess += m;
    }
    public void levelupAttack(){
        decreaseMoney(attackPrice);
        attackPrice *= 2;
        attackLevel += 1;
        attackMul *= 1.5;
    }
    public void levelupHp(){
        decreaseMoney(hpPrice);
        hpPrice *= 2;
        hpLevel += 1;
        hpMul *= 1.5;
    }
    public void levelupRange(){
        decreaseMoney(rangePrince);
        rangePrince *= 2;
        rangeLevel += 1;
        range *= 1.5;
    }
    public int getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public float getAttackMul() {
        return attackMul;
    }

    public void setAttackMul(float attackMul) {
        this.attackMul = attackMul;
    }

    public int getHpLevel() {
        return hpLevel;
    }

    public void setHpLevel(int hpLevel) {
        this.hpLevel = hpLevel;
    }

    public float getHpMul() {
        return hpMul;
    }

    public void setHpMul(float hpMul) {
        this.hpMul = hpMul;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getProgess() {
        return progess;
    }

    public void setProgess(float progess) {
        this.progess = progess;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}

