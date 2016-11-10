package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

public class MyColorBlock extends Actor{

    private PrimitiveSqaure shape;
    private PrimitiveSqaure shape2;
    private Boolean selected;
    private Boolean active;
    private Sprite sprite;
    private Color setOColor;

    private final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("clicked.mp3"));

    public MyColorBlock(AssetManager manager) {
        super();
        active = false;
        selected = false;
        shape = new PrimitiveSqaure(1);
        shape2 = new PrimitiveSqaure(0);
        sprite = new Sprite(manager.get("Sprite/correct.png", Texture.class));
        sprite.setScale(0.1f);
        setOColor = null;

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(active){
                    long id = clickSound.play(1.0f);
                    clickSound.setPitch(id, 5);
                    myClick();
                }

            }
        });
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        shape.draw(batch, parentAlpha);
        shape2.draw(batch, parentAlpha);
        if(selected){
            sprite.draw(batch, parentAlpha);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        shape.setPosition(getX(), getY());
        shape.setSize(getWidth(), getHeight());
        shape2.setPosition(getX()+1, getY()+1);
        shape2.setSize(getWidth()-2, getHeight()-2);
        shape2.setColor(getColor());
        sprite.setPosition(getX() - sprite.getWidth()/2 + getWidth()/2, getY() - sprite.getHeight()/2 + getHeight()/2);
    }

    @Override
    public boolean remove() {
        shape.remove();
        shape2.remove();
        return super.remove();
    }
    public PrimitiveSqaure getShape() {
        return shape;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
        if(selected){
            setColor(Color.WHITE);
            changeColor(setOColor, 0.5f, Interpolation.pow3);
        }
    }

    public void changePosition(float x, float y, float d){
        active = false;

        MoveToAction ma = new MoveToAction();
        ma.setPosition(x, y);
        ma.setInterpolation(Interpolation.pow3);
        ma.setDuration(d);

        RunnableAction ra = new RunnableAction();
        ra.setRunnable(new Timer.Task() {
            @Override
            public void run() {
                active = true;
            }
        });

        SequenceAction action = new SequenceAction();
        action.addAction(ma);
        action.addAction(ra);

        addAction(action);
    }

    public void changeColor(Color c, float d, Interpolation t){
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(t);
        addAction(ca);
    }
    public void changeSize(float w, float h, Interpolation i, float t){
        SizeToAction sa = new SizeToAction();
        sa.setSize(w, h);
        sa.setInterpolation(i);
        sa.setDuration(t);

        MoveToAction ma = new MoveToAction();
        ma.setPosition(getX(), getY()-((h-getHeight())/2));
        ma.setInterpolation(i);
        ma.setDuration(t);

        ParallelAction action = new ParallelAction();
        action.addAction(sa);
        action.addAction(ma);

        addAction(action);
    }

    public void myClick(){

    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        if(setOColor == null){
            setOColor = color;
        }
    }
}
