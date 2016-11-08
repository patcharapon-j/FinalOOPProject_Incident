package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.PlayerData;

import java.sql.Time;
import java.util.ArrayList;

public class NodeActor extends Actor{

    private Sprite sprite;
    private int team;
    private int type;
    private float maxHp;
    private float hp;
    private float attack;
    private float oriWidth;
    private float oriHeight;
    private NodeActor target;
    private AssetManager manager;
    private long lastHealTime;
    private GameScreen gameScreen;
    private long lastPelletTime;
    private ShapeRenderer sr;
    ArrayList<PlayerData> allData;

    public NodeActor(AssetManager m, int i, int ty, ArrayList<PlayerData> data, GameScreen s) {
        super();
        gameScreen = s;
        manager = m;
        sprite = new Sprite(manager.get("Sprite/blank.png", Texture.class));
        team = i;
        type = ty;
        sr = new ShapeRenderer();
        maxHp = 1024;
        hp = 1024;
        attack = 64;
        target = null;
        allData = data;
        lastHealTime = 0;
        lastPelletTime = 0;
        sr = new ShapeRenderer();
        oriHeight = sprite.getHeight();
        oriWidth = sprite.getWidth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(getX(), getY(), getWidth(), getHeight());
        sr.end();
        if(hp < maxHp * allData.get(team).getHpMul()){
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(new Color(0.2f, 0.2f, 0.2f, 1));
            sr.rect(getX()+getWidth() - getWidth(), getY() - 6, getWidth(), 3);
            sr.setColor(Color.WHITE);
            sr.rect(getX()+getWidth() - getWidth(), getY() - 6, getWidth() * hp / (maxHp * allData.get(team).getHpMul()), 3);
            sr.end();
        }
        batch.begin();
        sprite.draw(batch);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setColor(getColor());
        sprite.setPosition(getX(), getY());
        sprite.setSize(getWidth(), getHeight());

        if(lastHealTime + 10000 < TimeUtils.millis()){
            hp += maxHp * allData.get(team).getHpMul() / 30 * delta;
            hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
        }

        if(target != null){
            attack(target);
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void setScale(float s){
        setSize(s * oriWidth, s*oriHeight);
    }

    public void changeColor(Color c, int d){
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }

    public void changePosition(float x, float y, float d){
        MoveToAction ma = new MoveToAction();
        ma.setPosition(x, y);
        ma.setInterpolation(Interpolation.pow3);
        ma.setDuration(d);
        addAction(ma);
    }

    public void changeType(int t){
        type = t;
        switch (type){
            case 0:
                sprite.setTexture(manager.get("Sprite/blank.png", Texture.class));
                break;
        }
    }

    public void changeTeam(int t, Color c){
        team = t;
        changeColor(c, 1);
    }

    public void damage(float amount, int t, Color c){
        hp -= amount;
        lastHealTime = TimeUtils.millis();
        if(hp <= 0){
            hp = maxHp * allData.get(team).getHpMul();
            changeTeam(t, c);
        }
    }

    public void attack(NodeActor node){
        if(node.getTeam() == team){
            target = null;
        }
        else{
            target.damage(attack * Gdx.graphics.getDeltaTime() * allData.get(team).getAttackMul(), team, getColor());
            if(lastPelletTime + 200 < TimeUtils.millis()){
                Pellet temp = new Pellet(node.getX() + node.getWidth()/2, node.getY() + node.getHeight()/2);
                temp.setPosition(getX() + getWidth()/2, getY()+getHeight()/2);
                temp.setColor(getColor());
                gameScreen.getPelletStage().addActor(temp);
                lastPelletTime = TimeUtils.millis();
            }
        }
    }

    public int getTeam() {
        return team;
    }

    public int getType() {
        return type;
    }

    public void setTarget(NodeActor target) {
        this.target = target;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public float getHp() {
        return hp;
    }
}
