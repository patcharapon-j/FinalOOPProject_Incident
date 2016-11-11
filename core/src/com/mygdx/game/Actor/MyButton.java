package com.mygdx.game.Actor;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Screen.GameScreen;

public class MyButton extends Group {

    private final PrimitiveSqaure shape;
    private final MyTextDisplay text;
    private Boolean active;
    private Timer timer;

    public MyButton() {
        super();
        active = false;
        shape = new PrimitiveSqaure(0);
        text = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 35, 1);
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (active) {
                    changeColor(new Color(0.2f, 0.6f, 1, 0.5f), 0.2f);
                    changeSize(525, 75, 0.2f);
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (active) {
                    long id = GameScreen.mOverSound.play(1.0f);
                    GameScreen.mOverSound.setPitch(id, 3);
                    changeColor(new Color(0.2f, 0.6f, 1, 0.5f), 0.5f);
                    changeSize(550, 100, 0.5f);
                }

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (active) {
                    changeColor(new Color(0, 0, 0, 0), 0.5f);
                    changeSize(500, 50, 0.5f);
                }
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (active) {
                    long id = GameScreen.clickSound.play(1.0f);
                    GameScreen.clickSound.setPitch(id, 3);
                    clickDelay();
                }
            }
        });

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        shape.draw(batch, parentAlpha);
        text.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        shape.setPosition(getX(), getY());
        shape.setSize(getWidth(), getHeight());
        shape.setColor(getColor());
        text.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    @Override
    public boolean remove() {
        shape.remove();
        text.remove();
        return super.remove();
    }

    public PrimitiveSqaure getShape() {
        return shape;
    }

    public MyTextDisplay getText() {
        return text;
    }

    private void changeColor(Color c, float d) {
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }

    private void changeSize(float w, float h, float t) {
        SizeToAction sa = new SizeToAction();
        sa.setSize(w, h);
        sa.setInterpolation(Interpolation.pow3Out);
        sa.setDuration(t);

        MoveToAction ma = new MoveToAction();
        ma.setPosition(getX(), getY() - ((h - getHeight()) / 2));
        ma.setInterpolation(Interpolation.pow3Out);
        ma.setDuration(t);

        ParallelAction action = new ParallelAction();
        action.addAction(sa);
        action.addAction(ma);

        addAction(action);
    }

    private void clickDelay() {
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                myClick();
            }
        }, 0.3f);
    }

    public void myClick() {
        clearActions();
    }

    public void changePosition(float x, float y, float d) {
        active = false;

        changeColor(new Color(0, 0, 0, 0), 0.3f);
        changeSize(500, 50, 0.3f);

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

        final SequenceAction action = new SequenceAction();
        action.addAction(ma);
        action.addAction(ra);

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                addAction(action);
            }
        }, 0.3f);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
