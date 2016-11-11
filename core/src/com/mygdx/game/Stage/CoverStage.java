package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.mygdx.game.Actor.MyButton;
import com.mygdx.game.Actor.MyTextDisplay;
import com.mygdx.game.Actor.PrimitiveSqaure;
import com.mygdx.game.Incident;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Screen.MainMenuScreen;

public class CoverStage extends Stage {

    private boolean isResume;
    private final PrimitiveSqaure titleBack;
    private final PrimitiveSqaure gameCover;
    private final MyButton resumeButton;
    private final MyButton backButton;
    private final MyButton restartButton;
    private final MyTextDisplay title;

    public CoverStage(final Incident g, final GameScreen screen) {
        super();
        isResume = true;

        gameCover = new PrimitiveSqaure(0);
        gameCover.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameCover.setPosition(0, 0);
        gameCover.setColor(new Color(0.16f, 0.16f, 0.16f, 0));
        addActor(gameCover);

        titleBack = new PrimitiveSqaure(0);
        titleBack.setSize(500, Gdx.graphics.getHeight());
        titleBack.setPosition(-500, 0);
        titleBack.setColor(Color.BLACK);
        addActor(titleBack);

        title = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 60, 1);
        title.setText("Paused");
        title.setPosition(-500, 650);
        addActor(title);
        title.setAlpha(0);
        title.fadeIn();

        resumeButton = new MyButton() {
            @Override
            public void myClick() {
                screen.getGameStageUI().setPause();
                resumeMenu();

            }
        };

        resumeButton.setSize(500, 50);
        resumeButton.setPosition(-750, 450);
        resumeButton.getText().setText("resume");
        resumeButton.getText().setColor(Color.WHITE);
        resumeButton.setColor(new Color(0, 0, 0, 0));
        addActor(resumeButton);

        restartButton = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                g.setScreen(new GameScreen(g, g.player_color, g.ai_count, g.ai_diff));
                try {
                    screen.dispose();
                } catch (Exception ignored) {

                }
            }
        };
        restartButton.setSize(500, 50);
        restartButton.setPosition(-750, 325);
        restartButton.getText().setText("restart");
        restartButton.getText().setColor(Color.WHITE);
        restartButton.setColor(new Color(0, 0, 0, 0));
        addActor(restartButton);

        backButton = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                g.setScreen(new MainMenuScreen(g));
                try {
                    screen.dispose();
                } catch (Exception ignored) {

                }
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

    public void pauseMenu() {
        if (isResume) {
            isResume = false;

            MoveToAction ma = new MoveToAction();
            ma.setDuration(1);
            ma.setInterpolation(Interpolation.pow3);
            ma.setPosition(0, 0);

            ColorAction col = new ColorAction();
            col.setDuration(1);
            col.setInterpolation(Interpolation.pow3);
            col.setEndColor(new Color(0, 0, 0, 0.7f));

            ParallelAction action = new ParallelAction();
            action.addAction(ma);
            action.addAction(col);

            titleBack.addAction(action);

            AlphaAction aa = new AlphaAction();
            aa.setDuration(1f);
            aa.setInterpolation(Interpolation.pow3);
            aa.setAlpha(0.8f);
            gameCover.addAction(aa);

            resumeButton.setActive(false);
            backButton.setActive(false);
            restartButton.setActive(false);
            title.changePosition(250, 650, 1);
            resumeButton.changePosition(0, 450, 1.2f);
            backButton.changePosition(0, 50, 2f);
            restartButton.changePosition(0, 325, 1.5f);
        }
    }

    private void resumeMenu() {

        isResume = true;
        MoveToAction ma = new MoveToAction();
        ma.setDuration(2);
        ma.setInterpolation(Interpolation.pow3);
        ma.setPosition(-500, 0);

        ColorAction col = new ColorAction();
        col.setDuration(2);
        col.setInterpolation(Interpolation.pow3);
        col.setEndColor(new Color(0, 0, 0, 0));

        ParallelAction action = new ParallelAction();
        action.addAction(ma);
        action.addAction(col);

        titleBack.addAction(action);

        AlphaAction aa = new AlphaAction();
        aa.setDuration(1f);
        aa.setInterpolation(Interpolation.pow3);
        aa.setAlpha(0);
        gameCover.addAction(aa);

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
