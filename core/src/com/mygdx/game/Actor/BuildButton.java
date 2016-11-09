package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BuildButton extends Actor{

    PrimitiveSqaure sqaure;

    public BuildButton() {
        super();
        sqaure = new PrimitiveSqaure(1);
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
