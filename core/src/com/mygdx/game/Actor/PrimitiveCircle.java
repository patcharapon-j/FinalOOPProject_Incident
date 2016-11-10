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

public class PrimitiveCircle extends Actor {
    private  float thick;
    private ShapeRenderer sr;
    private int drawMode;
    public PrimitiveCircle(int d) {
        super();
        sr = new ShapeRenderer();
        drawMode = d;
        setBounds(getX(), getY(), getWidth(), getHeight());
        thick = 3;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        if(drawMode == 0){
            Gdx.gl20.glLineWidth(1);
            sr.begin(ShapeRenderer.ShapeType.Filled);
        }
        else if(drawMode == 1){
            Gdx.gl20.glLineWidth(thick);
            sr.begin(ShapeRenderer.ShapeType.Line);
        }
        sr.setColor(getColor());
        sr.circle(getX(), getY(), getWidth());
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

    public void changeColor(Color c, float d){
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

    public void setDrawMode(int drawMode) {
        this.drawMode = drawMode;
    }

    public void setThick(float thick) {
        this.thick = thick;
    }
}

