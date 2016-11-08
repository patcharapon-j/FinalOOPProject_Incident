package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Game_BuildButton extends Actor {

    private ShapeRenderer sr;
    private MyTextDisplay text;

    public Game_BuildButton() {
        super();
        sr = new ShapeRenderer();
        //text = new MyTextDisplay()
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
