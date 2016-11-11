package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

class Pellet extends Actor {

    private final ShapeRenderer sr;

    public Pellet(float x, float y) {
        super();
        sr = new ShapeRenderer();
        MoveToAction ma = new MoveToAction();
        ma.setDuration(1.5f);
        ma.setInterpolation(Interpolation.pow3);
        ma.setPosition(x, y);

        RunnableAction ra = new RunnableAction();
        ra.setRunnable(new Runnable() {
            @Override
            public void run() {
                remove();
            }
        });

        SequenceAction action = new SequenceAction();
        action.addAction(ma);
        action.addAction(ra);

        addAction(action);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(getColor());
        int radius = 4;
        sr.circle(getX() - radius, getY() - radius, radius);
        sr.end();
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
}
