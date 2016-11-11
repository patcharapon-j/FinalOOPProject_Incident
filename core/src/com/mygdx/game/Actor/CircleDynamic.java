package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;

public class CircleDynamic extends Actor{

    private ShapeRenderer sr;
    private float oriX;
    private float oriY;
    public CircleDynamic() {
        super();
        sr = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(getColor());
        sr.circle(getX(), getY(), getWidth());
        sr.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);
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

    public void changeColor(Color c, int d){
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }

    public void expand(){
        SizeToAction sa = new SizeToAction();
        sa.setSize(150, 150);
        sa.setDuration(0.5f);
        sa.setInterpolation(Interpolation.pow3Out);

        ColorAction ca = new ColorAction();
        ca.setEndColor(new Color(0.6f, 0.6f, 0.6f, 0.5f));
        ca.setDuration(0.5f);
        ca.setInterpolation(Interpolation.pow3Out);

        ParallelAction pa = new ParallelAction();
        pa.addAction(sa);
        pa.addAction(ca);

        addAction(pa);
    }

    public void contract(){
        SizeToAction sa = new SizeToAction();
        sa.setSize(0, 0);
        sa.setDuration(0.5f);
        sa.setInterpolation(Interpolation.pow3Out);

        ColorAction ca = new ColorAction();
        ca.setEndColor(new Color(0.4f, 0.4f, 0.4f, 0));
        ca.setDuration(0.5f);
        ca.setInterpolation(Interpolation.pow3Out);

        ParallelAction pa = new ParallelAction();
        pa.addAction(sa);
        pa.addAction(ca);

        addAction(pa);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        oriX = x;
        oriY = y;
    }
}
