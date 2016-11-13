package com.mygdx.game.Actor;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Utility.PlayerData;

import java.util.ArrayList;

public class BuyNode extends Actor {

    private final Sprite sprite;
    private int type;
    private final float oriWidth;
    private final float oriHeight;
    private final AssetManager manager;
    private final MyTextDisplay textDisplay;
    private final MyTextDisplay textDescribe;
    private final MyTextDisplay textName;
    private final PrimitiveSqaure back;
    private int cost;
    private final float oriX;
    private final float oriY;
    private ArrayList<PlayerData> allData;
    public BuyNode(AssetManager m, int ty, float y, ArrayList<PlayerData> data) {
        super();
        oriX = (float) 1280;
        oriY = y;
        setPosition((float) 1280, y);
        manager = m;
        allData = data;
        setTouchable(Touchable.disabled);
        sprite = new Sprite(manager.get("Sprite/blank.png", Texture.class));
        oriWidth = sprite.getWidth();
        oriHeight = sprite.getHeight();
        type = ty;
        cost = 30;
        textDisplay = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 1);
        textDescribe = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 14, 2);
        textName = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 14, 2);
        textDescribe.setColor(new Color(1, 1, 1, 1));
        textName.setColor(new Color(1, 1, 1, 1));
        back = new PrimitiveSqaure(0);
        textDisplay.setText(cost + "");
        changeType(ty);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
        textDisplay.draw(batch, parentAlpha);
        textDescribe.draw(batch, parentAlpha);
        textName.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        textDisplay.setText(cost + "");
        cost = 25 + (allData.get(1).getNodeCount()/2) * 5;
        sprite.setColor(getColor());
        sprite.setPosition(getX(), getY());
        sprite.setSize(getWidth(), getHeight());
        textDisplay.setPosition(getX() + getWidth() / 2, getY() - 15);
        textDescribe.setPosition(getX() - 20, getY() + getHeight() / 2 - 10);
        textName.setPosition(getX() - 20, getY() + getHeight() / 2 + 10);
        back.setPosition(getX() - 100, getY());
    }

    @Override
    public boolean remove() {
        textDescribe.remove();
        textName.remove();
        textDisplay.remove();
        return super.remove();
    }

    public void setScale(float s) {
        setSize(s * oriWidth, s * oriHeight);
    }

    public void changeColor(Color c, int d) {
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }

    private void changePosition(float x, float y) {
        MoveToAction ma = new MoveToAction();
        ma.setPosition(x, y);
        ma.setInterpolation(Interpolation.pow3);
        ma.setDuration(0.25f);
        addAction(ma);
    }

    private void changeType(int t) {
        type = t;
        switch (type) {
            //blank
            case 0:
                sprite.setTexture(manager.get("Sprite/blank.png", Texture.class));
                break;
            //mainframe
            case 1:
                sprite.setTexture(manager.get("Sprite/android.png", Texture.class));
                break;
            //ddos
            case 2:
                sprite.setTexture(manager.get("Sprite/DDos.png", Texture.class));
                textDescribe.setText("Strong attack single Node");
                textName.setText("DDos");
                break;
            //virus
            case 3:
                sprite.setTexture(manager.get("Sprite/virus.png", Texture.class));
                textDescribe.setText("Light attack nearby node");
                textName.setText("Virus");
                break;
            //antivirus
            case 4:
                sprite.setTexture(manager.get("Sprite/AntiVirus.png", Texture.class));
                textDescribe.setText("heal friendly node");
                textName.setText("Antivirus");
                break;
            //datacenter
            case 5:
                sprite.setTexture(manager.get("Sprite/datacenter.png", Texture.class));
                break;
            //defender
            case 6:
                sprite.setTexture(manager.get("Sprite/shield.png", Texture.class));
                break;
        }
    }

    public int getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public void resetPosition() {
        changePosition(oriX, oriY);
    }

    public void pickup() {
        textName.setAlpha(0);
        textDescribe.setAlpha(0);
    }

    public void drop() {
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                textName.setAlpha(1);
                textDescribe.setAlpha(1);
            }
        }, 0.25f);
    }


}
