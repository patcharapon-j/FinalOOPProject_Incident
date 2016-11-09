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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.PlayerData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

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
    ArrayList<NodeActor> allNode;

    public NodeActor(AssetManager m, int i, int ty, ArrayList<PlayerData> data, GameScreen s, ArrayList<NodeActor> n) {
        super();
        gameScreen = s;
        manager = m;
        sprite = new Sprite(manager.get("Sprite/blank.png", Texture.class));
        team = i;
        type = ty;
        allNode = n;
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
        setTouchable(Touchable.enabled);
        changeType(ty);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.circle(getX()+getWidth()/2, getY()+getHeight()/2, 10);
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

        if(type != 5){
            if(lastHealTime + 10000 < TimeUtils.millis()){
                hp += maxHp * allData.get(team).getHpMul() / 60 * Gdx.graphics.getDeltaTime();
                hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
            }
        }
        else{
            if(team == 0){
                if(lastHealTime + 10000 < TimeUtils.millis()){
                    hp += maxHp * allData.get(team).getHpMul() / 60 * Gdx.graphics.getDeltaTime();
                    hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
                }
            }
            else{
                    hp -= maxHp * allData.get(team).getHpMul() / 30 * Gdx.graphics.getDeltaTime();
                    hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
            }
        }

        switch (type){
            //blank
            case 0:
                break;
            //MainFrame
            case 1:
                if(target != null){
                    attack(target);
                }
                break;
            //ddos
            case 2:
                if(target != null){
                    attack(target);
                }
                break;
            //virus
            case 3:
                Iterator<NodeActor> iter = allNode.iterator();
                while(iter.hasNext()){
                    NodeActor node =  iter.next();
                    if(calDistance(getX()+getWidth()/2, getY()+getHeight()/2,
                            node.getX()+node.getWidth()/2, node.getY()+node.getHeight()/2) <= 150){
                        attack(node);
                    }
                }
                break;
            //antivirus
            case 4:
                Iterator<NodeActor> iter2 = allNode.iterator();
                while(iter2.hasNext()){
                    NodeActor node =  iter2.next();
                    if(calDistance(getX()+getWidth()/2, getY()+getHeight()/2,
                            node.getX()+node.getWidth()/2, node.getY()+node.getHeight()/2) <= 150 && node.getTeam()==team){
                        attack(node, true);
                    }
                }
                break;
            //datacenter
            case 5:
                if(team != 0){
                    allData.get(team).advanceProgess(1 * Gdx.graphics.getDeltaTime());
                }
                if(hp <= 0){
                    changeTeam(0, Color.WHITE);
                    changeType(5);

                }
                break;
            //defender
            case 6:
                break;
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
            //blank
            case 0:
                sprite.setTexture(manager.get("Sprite/blank.png", Texture.class));
                hp = hp / maxHp * 1024;
                maxHp = 1024;
                attack = 0;
                break;
            //mainframe
            case 1:
                sprite.setTexture(manager.get("Sprite/android.png", Texture.class));
                hp = 4096;
                maxHp = 4096;
                attack = 128;
                break;
            //ddos
            case 2:
                sprite.setTexture(manager.get("Sprite/DDos.png", Texture.class));
                hp = hp / maxHp * 1024;
                maxHp = 1024;
                attack = 64;
                break;
            //virus
            case 3:
                sprite.setTexture(manager.get("Sprite/virus.png", Texture.class));
                hp = hp / maxHp * 512;
                maxHp = 512;
                attack = 32;
                break;
            //antivirus
            case 4:
                sprite.setTexture(manager.get("Sprite/AntiVirus.png", Texture.class));
                hp = hp / maxHp * 2048;
                maxHp = 2048;
                attack = -24;
                break;
            //datacenter
            case 5:
                sprite.setTexture(manager.get("Sprite/datacenter.png", Texture.class));
                hp = 8192;
                maxHp = 8192;
                attack = 0;
                break;
            //defender
            case 6:
                sprite.setTexture(manager.get("Sprite/shield.png", Texture.class));
                hp = 8192;
                maxHp = 8192;
                attack = 0;
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
            if(type != 5){
                changeType(0);
            }
        }
    }

    public void attack(NodeActor node){
        if(node.getTeam() == team){
            target = null;
        }
        else{
            node.damage(attack * Gdx.graphics.getDeltaTime() * allData.get(team).getAttackMul(), team, getColor());
            if(TimeUtils.millis()%200>150){
                Pellet temp = new Pellet(node.getX() + node.getWidth()/2, node.getY() + node.getHeight()/2);
                temp.setPosition(getX() + getWidth()/2, getY()+getHeight()/2);
                temp.setColor(getColor());
                gameScreen.getPelletStage().addActor(temp);
            }
        }
    }

    public void attack(NodeActor node, boolean b){
        if(node.getTeam() != team || (node.getTeam() == team && node.getHp()>=node.getMaxHp())){
            target = null;
        }
        else{
            node.damage(attack * Gdx.graphics.getDeltaTime() * allData.get(team).getAttackMul(), team, getColor());
            if(TimeUtils.millis()%200>150){
                Pellet temp = new Pellet(node.getX() + node.getWidth()/2, node.getY() + node.getHeight()/2);
                temp.setPosition(getX() + getWidth()/2, getY()+getHeight()/2);
                temp.setColor(getColor());
                gameScreen.getPelletStage().addActor(temp);
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

    public double calDistance(float x1, float y1, float x2, float y2){
        return Math.pow(Math.pow(x1-x2, 2) +  Math.pow(y1-y2, 2), 0.5);
    }
}
