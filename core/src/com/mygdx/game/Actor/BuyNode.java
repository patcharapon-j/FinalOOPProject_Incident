package com.mygdx.game.Actor;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BuyNode extends Actor{

    private Sprite sprite;
    private int team;
    private int type;
    private float oriWidth;
    private float oriHeight;
    private AssetManager manager;
    private MyTextDisplay textDisplay;
    private MyTextDisplay textDescribe;
    private MyTextDisplay textName;
    private PrimitiveSqaure back;
    private int cost;
    private float oriX;
    private float oriY;

    public BuyNode(AssetManager m, int ty, int c, float x, float y) {
        super();
        oriX = x;
        oriY = y;
        setPosition(x, y);
        manager = m;
        setTouchable(Touchable.disabled);
        sprite = new Sprite(manager.get("Sprite/blank.png", Texture.class));
        oriWidth = sprite.getWidth();
        oriHeight = sprite.getHeight();
        type = ty;
        cost = c;
        textDisplay = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 18, 1);
        textDescribe = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 18, 2);
        textName = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 18, 2);
        textDescribe.setColor(new Color(1, 1, 1, 0));
        textName.setColor(new Color(1, 1, 1, 0));
        back = new PrimitiveSqaure(0);
        textDisplay.setText(cost+"");
        changeType(ty);

        addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                textDescribe.changeColor(new Color(1, 1, 1, 1), 0.5f);
                textName.changeColor(new Color(1, 1, 1, 1), 0.5f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                textDescribe.changeColor(new Color(1, 1, 1, 0), 0.5f);
                textName.changeColor(new Color(1, 1, 1, 0), 0.5f);
            }

        });

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
        sprite.setColor(getColor());
        sprite.setPosition(getX(), getY());
        sprite.setSize(getWidth(), getHeight());
        textDisplay.setPosition(getX() + getWidth()/2, getY() - 15);
        textDescribe.setPosition(getX() - 20, getY() + getHeight()/2 - 10);
        textName.setPosition(getX() - 20, getY() + getHeight()/2 + 10);
        back.setPosition(getX() - 100, getY());
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
                textDescribe.setText("Light attack all nearby node");
                textName.setText("Virus");
                break;
            //antivirus
            case 4:
                sprite.setTexture(manager.get("Sprite/AntiVirus.png", Texture.class));
                textDescribe.setText("Auto heal all friendly node");
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

    public void changeTeam(int t, Color c){
        team = t;
        changeColor(c, 1);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public int getTeam() {
        return team;
    }

    public int getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public void resetPosition(){
        setPosition(oriX, oriY);
    }
}
