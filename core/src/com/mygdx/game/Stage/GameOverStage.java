package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Actor.MyButton;
import com.mygdx.game.Actor.MyTextDisplay;
import com.mygdx.game.Actor.PrimitiveSqaure;
import com.mygdx.game.Incident;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Screen.MainMenuScreen;

public class GameOverStage extends Stage {

    private MyTextDisplay text;
    private MyTextDisplay text2;
    private PrimitiveSqaure screenFlash;
    private PrimitiveSqaure screenBlack;

    public GameOverStage(final Incident game, final GameScreen screen) {
        super();

        screenBlack = new PrimitiveSqaure(0);
        screenBlack.setColor(new Color(0, 0, 0, 0));
        screenBlack.setPosition(0, 0);
        screenBlack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(screenBlack);

        text = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 60, 1);
        text.setColor(new Color(1, 1, 1, 0));
        text.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        text.setText("Temp");
        addActor(text);

        text2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 1);
        text2.setColor(new Color(1, 1, 1, 0));
        text2.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 50);
        text2.setText("Press Any Key to Continue");
        addActor(text2);

        screenFlash = new PrimitiveSqaure(0);
        screenFlash.setColor(new Color(1, 1, 1, 0));
        screenFlash.setPosition(0, 0);
        screenFlash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(screenFlash);


    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void dispose() {
        super.dispose();
        text.remove();
        screenFlash.remove();
    }

    public void gameover(boolean victory){
        if(victory){
            text.setText("VICTORY");
        }
        else{
            text.setText("DEFEAT");
            text.setColor(new Color(0.8f, 0, 0, 0));
        }

        screenBlack.setColor(new Color(0, 0, 0, 0.9f));

        AlphaAction aa = new AlphaAction();
        aa.setDuration(4f);
        aa.setAlpha(1);
        text.addAction(aa);

        screenFlash.setColor(Color.WHITE);
        AlphaAction ab = new AlphaAction();
        ab.setDuration(0.5f);
        ab.setAlpha(0);
        screenFlash.addAction(ab);

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                AlphaAction aa2 = new AlphaAction();
                aa2.setDuration(1f);
                aa2.setAlpha(1);
                text2.addAction(aa2);
            }
        }, 5);

    }

}