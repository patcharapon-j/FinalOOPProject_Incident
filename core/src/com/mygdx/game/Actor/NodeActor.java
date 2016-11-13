package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.PlayerData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

public class NodeActor extends Actor {

    private final ArrayList<PlayerData> allData;
    private final ArrayList<NodeActor> allNode;
    private final Sprite sprite;
    private int team;
    private int type;
    private float maxHp;
    private float hp;
    private float attack;
    private final float oriWidth;
    private final float oriHeight;
    private NodeActor target;
    private final AssetManager manager;
    private long lastHealTime;
    private final GameScreen gameScreen;
    private ShapeRenderer sr;
    private int state;
    private long deathTime;
    private long lastWarnTime;
    private float point[] = {0, 0, 0, 0, 0};
    public NodeActor(AssetManager m, int i, int ty, ArrayList<PlayerData> data, GameScreen s, ArrayList<NodeActor> n) {
        super();
        gameScreen = s;
        manager = m;
        sprite = new Sprite(manager.get("Sprite/blank.png", Texture.class));
        team = i;
        type = ty;
        lastWarnTime = 0;
        allNode = n;
        sr = new ShapeRenderer();
        maxHp = 1024;
        hp = 1024;
        attack = 64;
        target = null;
        allData = data;
        lastHealTime = 0;
        sr = new ShapeRenderer();
        oriHeight = sprite.getHeight();
        oriWidth = sprite.getWidth();
        state= 1;
        setTouchable(Touchable.enabled);
        changeType(ty);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(state == 0){
            batch.end();
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(new Color(0.1f, 0.1f, 0.1f, 1));
            sr.circle(getX() + getWidth() / 2, getY() + getHeight() / 2, 10);
            sr.end();
            batch.begin();
            if(TimeUtils.millis() % 300 > 150){
                sprite.draw(batch);
            }
        }
        else if(state == 1){
            batch.end();
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(new Color(0.1f, 0.1f, 0.1f, 1));
            sr.circle(getX() + getWidth() / 2, getY() + getHeight() / 2, 10);
            sr.end();
            if (hp < maxHp * allData.get(team).getHpMul()) {
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(new Color(0.2f, 0.2f, 0.2f, 1));
                sr.rect(getX() + getWidth() - getWidth(), getY() - 6, getWidth(), 3);
                sr.setColor(Color.WHITE);
                sr.rect(getX() + getWidth() - getWidth(), getY() - 6, getWidth() * hp / (maxHp * allData.get(team).getHpMul()), 3);
                sr.end();
            }
            batch.begin();
            sprite.draw(batch);
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(state == 1){
            if (type != 5) {
                if(type == 1){
                    if (lastHealTime + 10000 < TimeUtils.millis()) {
                        hp += maxHp * allData.get(team).getHpMul() / 100 * Gdx.graphics.getDeltaTime();
                        hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
                    }
                }
                else{
                    if (lastHealTime + 10000 < TimeUtils.millis()) {
                        hp += maxHp * allData.get(team).getHpMul() / 60 * Gdx.graphics.getDeltaTime();
                        hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
                    }
                }
            } else {
                if (team == 0) {
                    if (lastHealTime + 10000 < TimeUtils.millis()) {
                        hp += maxHp * allData.get(team).getHpMul() / 60 * Gdx.graphics.getDeltaTime();
                        hp = Math.min(hp, maxHp * allData.get(team).getHpMul());
                    }
                } else {
                    hp -= maxHp * allData.get(team).getHpMul() / 30 * Gdx.graphics.getDeltaTime();
                    hp = Math.min(hp, maxHp * allData.get(team).getHpMul());


                }
            }

            switch (type) {
                //blank
                case 0:
                    break;
                //MainFrame
                case 1:
                    if (target != null) {
                        attack(target, 0);
                    }
                    break;
                //ddos
                case 2:
                    if (target != null) {
                        attack(target, 0);
                    }
                    break;
                //virus
                case 3:
                    for (NodeActor node : allNode) {
                        if (calDistance(getX() + getWidth() / 2, getY() + getHeight() / 2,
                                node.getX() + node.getWidth() / 2, node.getY() + node.getHeight() / 2) <= 150) {
                            attack(node, 0);
                        }
                    }
                    break;
                //antiviruss
                case 4:
                    for (NodeActor node : allNode) {
                        if (calDistance(getX() + getWidth() / 2, getY() + getHeight() / 2,
                                node.getX() + node.getWidth() / 2, node.getY() + node.getHeight() / 2) <= 150 && node.getTeam() == team) {
                            attack(node, 1);
                        }
                    }
                    break;
                //datacenter
                case 5:
                    if (team != 0) {
                        allData.get(team).advanceProgess(1 * Gdx.graphics.getDeltaTime());
                    }
                    if (hp <= 0) {

                        deathTime = TimeUtils.millis() + 5000;
                        if(type == 5){
                            gameScreen.switchVideo(0);
                            deathTime = TimeUtils.millis() + 10000;
                            manager.get("Effect/sfx_datacenter_loss.ogg", Sound.class).play(0.6f);
                            if(team == 1){
                                manager.get("Speech/speech_datacenter_loss.mp3", Sound.class).play(2);
                            }
                        }
                        allData.get(team).setNodeCount(allData.get(team).getNodeCount() - 1);
                        if (type == 1) {
                            manager.get("Effect/sfx_mainframe_offline.ogg", Sound.class).play(0.6f);
                            if(team > 1){
                                manager.get("Speech/speech_enemy_offline.mp3", Sound.class).play(2);
                            }
                            gameScreen.playerDeath(team);
                            for (NodeActor node : allNode) {
                                if (node.getTeam() == team && node != this) {
                                    node.changeTeam(0, Color.WHITE);
                                    node.changeType(0);
                                }
                            }
                            changeType(0);
                            allData.get(team).setDestroyed();
                        } else if (type != 5) {
                            changeType(0);
                        }
                        changeTeam(0, Color.WHITE);
                        state = 0;
                        point[0] = 0;
                        point[1] = 0;
                        point[2] = 0;
                        point[3] = 0;
                        point[4] = 0;
                    }
                    break;
                //defender
                case 6:
                    break;
            }

            if (type == 1) {
                allData.get(team).increaseMoney(1 * delta * allData.get(team).getRange());
            } else {
                allData.get(team).increaseMoney(0.25f * delta * allData.get(team).getRange());
            }
        }
        else if(deathTime < TimeUtils.millis()){
            state = 1;
            int winner = 0;
            float maxp = 0;
            for(int k=1;k<5;k++){
                if(point[k] > maxp){
                    maxp = point[k];
                    winner = k;
                }
            }
            if(winner == 0){
                changeTeam(0, Color.WHITE);
                hp = maxHp * allData.get(team).getHpMul();
                allData.get(team).setNodeCount(allData.get(team).getNodeCount() + 1);
            }
            else{
                changeTeam(winner, GameScreen.mainColor[GameScreen.userColor.get(winner-1)]);
                hp = maxHp * allData.get(team).getHpMul();
                allData.get(team).setNodeCount(allData.get(team).getNodeCount() + 1);
                if(type == 5){
                    gameScreen.switchVideo(winner);
                    Sound s = manager.get("Effect/sfx_datacenter_capture.ogg", Sound.class);
                    s.play(0.6f);
                    if(winner == 1){
                        manager.get("Speech/speech_datacenter_capture.mp3", Sound.class).play(2);
                    }
                    else{
                        manager.get("Speech/speech_enemy_capture_datacenter.mp3", Sound.class).play(2);
                    }
                }
            }
        }
        sprite.setColor(getColor());
        sprite.setPosition(getX(), getY());
        sprite.setSize(getWidth(), getHeight());
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void setScale(float s) {
        setSize(s * oriWidth, s * oriHeight);
    }

    private void changeColor(Color c) {
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(1);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }

    public void changePosition(float x, float y, float d) {
        MoveToAction ma = new MoveToAction();
        ma.setPosition(x, y);
        ma.setInterpolation(Interpolation.pow3);
        ma.setDuration(d);
        addAction(ma);
    }

    public void changeType(int t) {
        type = t;
        switch (type) {
            //blank
            case 0:
                sprite.setTexture(manager.get("Sprite/blank.png", Texture.class));
                hp = hp / maxHp * 1024 * allData.get(team).getHpMul();
                maxHp = 1024;
                attack = 0;
                break;
            //mainframe
            case 1:
                sprite.setTexture(manager.get("Sprite/android.png", Texture.class));
                hp = 4096;
                maxHp = 4096;
                attack = 96;
                break;
            //ddos
            case 2:
                sprite.setTexture(manager.get("Sprite/DDos.png", Texture.class));
                hp = hp / maxHp * 1024 * allData.get(team).getHpMul();
                maxHp = 1024;
                attack = 64;
                break;
            //virus
            case 3:
                sprite.setTexture(manager.get("Sprite/virus.png", Texture.class));
                hp = hp / maxHp * 512 * allData.get(team).getHpMul();
                maxHp = 512;
                attack = 32;
                break;
            //antivirus
            case 4:
                sprite.setTexture(manager.get("Sprite/AntiVirus.png", Texture.class));
                hp = hp / maxHp * 2048 * allData.get(team).getHpMul();
                maxHp = 2048;
                attack = -22;
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

    private void changeTeam(int t, Color c) {
        team = t;
        changeColor(c);
    }

    private void damage(float amount, int t, Color c) {
        if(state == 0){
            point[t] += amount;
        }
        else{
            if(team == 1 && type == 1){
                if(lastWarnTime < TimeUtils.millis() && amount > 0){
                    lastWarnTime = TimeUtils.millis() + 3000;
                    manager.get("Effect/sfx_mainframe_under_atk.ogg", Sound.class).play(0.6f);
                    manager.get("Speech/speech_you_are_under_attack.ogg", Sound.class).play();
                }
            }
            hp -= amount;
            lastHealTime = TimeUtils.millis();
            if(hp > allData.get(team).getHpMul()*maxHp){
                hp = allData.get(team).getHpMul()*maxHp;
            }
            if (hp <= 0) {

                deathTime = TimeUtils.millis() + 5000;
                if(type == 5){
                    gameScreen.switchVideo(0);
                    deathTime = TimeUtils.millis() + 10000;
                    manager.get("Effect/sfx_datacenter_loss.ogg", Sound.class).play(0.6f);
                    if(team == 1){
                        manager.get("Speech/speech_datacenter_loss.mp3", Sound.class).play(2);
                    }
                }
                allData.get(team).setNodeCount(allData.get(team).getNodeCount() - 1);
                if (type == 1) {
                    manager.get("Effect/sfx_mainframe_offline.ogg", Sound.class).play(0.6f);
                    if(team > 1){
                        manager.get("Speech/speech_enemy_offline.mp3", Sound.class).play(2);
                    }
                    gameScreen.playerDeath(team);
                    for (NodeActor node : allNode) {
                        if (node.getTeam() == team && node != this) {
                            node.changeTeam(0, Color.WHITE);
                            node.changeType(0);
                        }
                    }
                    changeType(0);
                    allData.get(team).setDestroyed();
                } else if (type != 5) {
                    changeType(0);
                }
                changeTeam(0, Color.WHITE);
                state = 0;
                point[0] = 0;
                point[1] = 0;
                point[2] = 0;
                point[3] = 0;
                point[4] = 0;
            }
        }
    }

    private void attack(NodeActor node,  int mode) {
        switch (mode){
            case  0:
                if (node.getTeam() == team) {
                    target = null;
                } else {
                    node.damage(attack * Gdx.graphics.getDeltaTime() * allData.get(team).getAttackMul(), team, getColor());
                    if (TimeUtils.millis() % 500 > 450) {
                        Pellet temp = new Pellet(node.getX() + node.getWidth() / 2, node.getY() + node.getHeight() / 2);
                        temp.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
                        temp.setColor(getColor());
                        gameScreen.getPelletStage().addActor(temp);
                    }
                }
                break;
            case 1:
                if(node.getHp() >= node.maxHp * allData.get(team).getHpMul() || node == this){
                    target = null;
                } else {
                    node.damage(attack * Gdx.graphics.getDeltaTime() * allData.get(team).getAttackMul(), team, getColor());
                    if (TimeUtils.millis() % 500 > 450) {
                        Pellet temp = new Pellet(node.getX() + node.getWidth() / 2, node.getY() + node.getHeight() / 2);
                        temp.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
                        temp.setColor(getColor());
                        gameScreen.getPelletStage().addActor(temp);
                    }
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

    private double calDistance(float x1, float y1, float x2, float y2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 0.5);
    }

    public NodeActor getTarget() {
        return target;
    }

    public void setMaxHp(float maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }


}
