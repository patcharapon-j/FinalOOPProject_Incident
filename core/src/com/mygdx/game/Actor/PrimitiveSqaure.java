package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;

public class PrimitiveSqaure extends Actor {

    private final ShapeRenderer sr;
    private int drawMode;

    public PrimitiveSqaure(int d) {
        super();
        sr = new ShapeRenderer();
        drawMode = d;
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        if (drawMode == 0) {
            Gdx.gl20.glLineWidth(1);
            sr.begin(ShapeRenderer.ShapeType.Filled);
        } else if (drawMode == 1) {
            Gdx.gl20.glLineWidth(2);
            sr.begin(ShapeRenderer.ShapeType.Line);
        }
        sr.setColor(getColor());
        sr.rect(getX(), getY(), getWidth(), getHeight());
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public boolean remove() {
        sr.dispose();
        return super.remove();
    }

    public void changeColor(Color c, float d) {
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }

    public void changePosition() {
        MoveToAction ma = new MoveToAction();
        ma.setPosition((float) 0, (float) 0);
        ma.setInterpolation(Interpolation.pow3);
        ma.setDuration((float) 1);
        addAction(ma);
    }

    public void changeSize() {
        SizeToAction sa = new SizeToAction();
        sa.setSize((float) 180, (float) 40);
        sa.setInterpolation(Interpolation.pow3);
        sa.setDuration((float) 1);
        addAction(sa);
    }

    public void setDrawMode(int drawMode) {
        this.drawMode = drawMode;
    }

}

