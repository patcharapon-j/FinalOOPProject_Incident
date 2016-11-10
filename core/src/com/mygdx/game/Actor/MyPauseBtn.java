package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Screen.GameScreen;

public class MyPauseBtn extends Actor {

    private Sprite sprite;
    private float oriX;
    private float oriY;

    public MyPauseBtn(AssetManager manager) {
        super();

        sprite = new Sprite(manager.get("Sprite/pauseBtn.png", Texture.class));
        sprite.setOriginCenter();
        addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);

                long id = GameScreen.mOverSound.play(1.0f);
                GameScreen.mOverSound.setPitch(id, 3);

                SizeToAction sa = new SizeToAction();
                sa.setInterpolation(Interpolation.pow3);
                sa.setDuration(0.25f);
                sa.setSize(100, 100);

                MoveToAction ma = new MoveToAction();
                ma.setDuration(0.25f);
                ma.setInterpolation(Interpolation.pow3);
                ma.setPosition(oriX - 25, oriY - 25);

                ParallelAction action = new ParallelAction(sa, ma);
                addAction(action);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                SizeToAction sa = new SizeToAction();
                sa.setInterpolation(Interpolation.pow3);
                sa.setDuration(0.25f);
                sa.setSize(50, 50);


                MoveToAction ma = new MoveToAction();
                ma.setDuration(0.25f);
                ma.setInterpolation(Interpolation.pow3);
                ma.setPosition(oriX, oriY);

                ParallelAction action = new ParallelAction(sa, ma);
                addAction(action);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                long id = GameScreen.clickSound.play(1.0f);
                GameScreen.clickSound.setPitch(id, 3);
                Myclick();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        batch.begin();
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setColor(Color.WHITE);
        sprite.setSize(getWidth(), getHeight());
        sprite.setPosition(getX(), getY());
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        oriX = x;
        oriY = y;
    }

    public void Myclick(){

    }
}
