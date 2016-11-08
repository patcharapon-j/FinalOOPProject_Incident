package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Actor.MyPauseBtn;
import com.mygdx.game.Actor.MyTextDisplay;
import com.mygdx.game.Incident;

public class GameStageUI extends Stage {

    private Incident game;
    private MyPauseBtn myPauseBtn;
    private boolean isPause;
    private int time = 600000;
    private boolean isRed;
    private MyTextDisplay digit1;
    private MyTextDisplay digit2;
    private MyTextDisplay digit3;
    private MyTextDisplay colon;
    private MyTextDisplay backCover;
    private Timer timer;
    private boolean isActive;

    public GameStageUI(Incident g) {
        super();
        game = g;
        isRed = true;
        isPause = false;
        isActive = false;
        timer = new Timer();

        backCover = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 150, true);
        backCover.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        backCover.setColor(new Color(1, 1, 1, 0.2f));

        backCover.setText("3");
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                backCover.setText("2");
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        backCover.setText("1");
                        timer.scheduleTask(new Timer.Task() {
                            @Override
                            public void run() {
                                backCover.setText("Start");
                                backCover.changeColor(new Color(0, 0, 0, 0), 2);
                                isActive = true;
                            }
                        }, 1);
                    }
                }, 1);
            }
        }, 2);
        addActor(backCover);


        myPauseBtn = new MyPauseBtn(game.manager){
            @Override
            public void Myclick() {
                super.Myclick();
                isPause = true;
            }
        };
        myPauseBtn.setPosition(30, 685);
        myPauseBtn.setSize(50, 50);
        myPauseBtn.setColor(new Color(1, 1, 1, 0.3f));
        addActor(myPauseBtn);

        digit1 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 55, false);
        digit1.setText("99");
        digit2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 55, false);
        digit2.setText("99");
        digit3 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Thin.ttf", 20, false);
        digit3.setText("99");
        colon = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 55, false);
        colon.setText(":");

        digit1.setPosition(1150, 725);
        digit2.setPosition(1230, 725);
        digit3.setPosition(1295, 725);
        colon.setPosition(1213, 730);

        addActor(digit1);
        addActor(digit2);
        addActor(digit3);
        addActor(colon);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act() {
        super.act();
        digit1.setText(String.format("%02d", time/60000));
        digit2.setText(String.format("%02d", time%60000/1000));
        digit3.setText(String.format("%03d", time%1000));

        if(isActive){
            time -= 1000 * Gdx.graphics.getDeltaTime();
            if(time <= 60000 && isRed){
                isRed = false;
                digit1.changeColor(Color.RED, 1);
                digit2.changeColor(Color.RED, 1);
                digit3.changeColor(Color.RED, 1);
                colon.changeColor(Color.RED, 1);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }
}
