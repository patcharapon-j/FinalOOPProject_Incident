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

public class CoverStage extends Stage{

    private boolean isResume;
    private PrimitiveSqaure titleBack;
    private MyButton resumeButton;
    private MyButton backButton;
    private MyButton restartButton;
    private MyTextDisplay title;

    public CoverStage(final Incident g, final GameScreen screen) {
        super();
        isResume = true;
        titleBack = new PrimitiveSqaure(0);
        titleBack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        titleBack.setPosition(-2000 , 0);
        titleBack.setColor(Color.BLACK);
        addActor(titleBack);

        title = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 60, true);
        title.setText("Paused");
        title.setPosition(-500, 650);
        addActor(title);
        title.setAlpha(0);
        title.fadeIn(Interpolation.linear, 3);

        resumeButton = new MyButton("fonts/helveticaneue/HelveticaNeue Light.ttf", 35){
            @Override
            public void myClick() {
                screen.getGameStageUI().setPause(false);
                resumeMenu();

            }
        };

        resumeButton.setSize(500, 50);
        resumeButton.setPosition(-750, 450);
        resumeButton.getText().setText("resume");
        resumeButton.getText().setColor(Color.WHITE);
        resumeButton.setColor(new Color(0, 0, 0, 0));
        addActor(resumeButton);

        restartButton = new MyButton("fonts/helveticaneue/HelveticaNeue Light.ttf", 35){
            @Override
            public void myClick() {
                super.myClick();
                g.setScreen(new GameScreen(g, g.player_color, g.ai_count, g.ai_diff));
                dispose();
            }
        };
        restartButton.setSize(500, 50);
        restartButton.setPosition(-750, 325);
        restartButton.getText().setText("restart");
        restartButton.getText().setColor(Color.WHITE);
        restartButton.setColor(new Color(0, 0, 0, 0));
        addActor(restartButton);

        backButton = new MyButton("fonts/helveticaneue/HelveticaNeue Light.ttf", 35){
            @Override
            public void myClick() {
                super.myClick();
                g.setScreen(new MainMenuScreen(g));
                dispose();
            }
        };
        backButton.setSize(500, 50);
        backButton.setPosition(-750, 50);
        backButton.getText().setText("leave match");
        backButton.getText().setColor(Color.WHITE);
        backButton.setColor(new Color(0, 0, 0, 0));
        addActor(backButton);
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
    }

    public void pauseMenu(){
        if(isResume){
            isResume = false;

            MoveToAction ma = new MoveToAction();
            ma.setDuration(1);
            ma.setInterpolation(Interpolation.pow3);
            ma.setPosition(0, 0);

            ColorAction col = new ColorAction();
            col.setDuration(1);
            col.setInterpolation(Interpolation.pow3);
            col.setEndColor(new Color(0, 0, 0, 0.9f));

            ParallelAction action = new ParallelAction();
            action.addAction(ma);
            action.addAction(col);

            titleBack.addAction(action);

            resumeButton.setActive(false);
            backButton.setActive(false);
            restartButton.setActive(false);
            title.changePosition(250, 650, 1);
            resumeButton.changePosition(0, 450, 1.2f);
            backButton.changePosition(0, 50, 2f);
            restartButton.changePosition(0, 325, 1.5f);
        }
    }

    public void resumeMenu(){

        isResume = true;
            MoveToAction ma = new MoveToAction();
            ma.setDuration(2);
            ma.setInterpolation(Interpolation.pow3);
            ma.setPosition(-2000, 0);

            ColorAction col = new ColorAction();
            col.setDuration(2);
            col.setInterpolation(Interpolation.pow3);
            col.setEndColor(new Color(0, 0, 0, 0));

            ParallelAction action = new ParallelAction();
            action.addAction(ma);
            action.addAction(col);

            titleBack.addAction(action);

            resumeButton.setActive(false);
            backButton.setActive(false);
            restartButton.setActive(false);
            title.changePosition(-250, 650, 1);
            resumeButton.changePosition(-500, 450, 1.2f);
            backButton.changePosition(-500, 50, 2f);
            restartButton.changePosition(-500, 325, 1.5f);
    }

    public boolean isResume() {
        return isResume;
    }

    public void setResume(boolean resume) {
        isResume = resume;
    }
}
