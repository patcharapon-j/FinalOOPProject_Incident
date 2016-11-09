package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Actor.MyPauseBtn;
import com.mygdx.game.Actor.MySpriteActor;
import com.mygdx.game.Actor.MyTextDisplay;
import com.mygdx.game.Actor.PrimitiveSqaure;
import com.mygdx.game.Actor.*;
import com.mygdx.game.Incident;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.PlayerData;

import java.util.ArrayList;

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

    private MyTextDisplay playerScore1;
    private MyTextDisplay playerScore2;
    private MyTextDisplay playerScore3;
    private MyTextDisplay playerScore4;

    private MyTextDisplay labelDatacenter;

    private PrimitiveSqaure labelColorPy1;
    private PrimitiveSqaure labelColorPy2;
    private PrimitiveSqaure labelColorPy3;
    private PrimitiveSqaure labelColorPy4;

    private PrimitiveCircle circle;

    private MySpriteActor mainframeSp;

    private Timer timer;
    private boolean isActive;
    private PrimitiveSqaure beginBack;
    private ArrayList<PlayerData> allData;

    public GameStageUI(Incident g, int ai_count, ArrayList<PlayerData> d) {
        super();
        game = g;
        isRed = true;
        isPause = false;
        isActive = false;
        timer = new Timer();
        allData = d;

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

        labelDatacenter = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 25, 2);
        labelDatacenter.setText("Data Center");
        labelDatacenter.setPosition(1325, 660);
        addActor(labelDatacenter);


        playerScore1 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 25, 2);
        playerScore2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 25, 2);
        playerScore3 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 25, 2);
        playerScore4 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 25, 2);

        playerScore1.setText("0 %");
        playerScore2.setText("0 %");
        playerScore3.setText("0 %");
        playerScore4.setText("0 %");

        MyTextDisplay[] tempObjText = {playerScore1, playerScore2, playerScore3, playerScore4};
        for(int i=0; i<4; i++) {
            tempObjText[i].setPosition(1300, 620 - (i * 33));
        }

        addActor(playerScore1);
        if (ai_count >= 1) addActor(playerScore2);
        if (ai_count >= 2) addActor(playerScore3);
        if (ai_count >= 3) addActor(playerScore4);


        labelColorPy1 = new PrimitiveSqaure(0);
        labelColorPy2 = new PrimitiveSqaure(0);
        labelColorPy3 = new PrimitiveSqaure(0);
        labelColorPy4 = new PrimitiveSqaure(0);

        labelColorPy1.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        labelColorPy2.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
        labelColorPy3.setColor(GameScreen.mainColor[GameScreen.userColor.get(2)]);
        labelColorPy4.setColor(GameScreen.mainColor[GameScreen.userColor.get(3)]);

        labelColorPy1.setSize(15, 15);
        labelColorPy2.setSize(15, 15);
        labelColorPy3.setSize(15, 15);
        labelColorPy4.setSize(15, 15);

        PrimitiveSqaure[] tempObj = {labelColorPy1, labelColorPy2, labelColorPy3, labelColorPy4};
        for(int i=0; i<4; i++) {
            tempObj[i].setPosition(1310, 604 - (i * 33));
        }

        addActor(labelColorPy1);
        if (ai_count >= 1) addActor(labelColorPy2);
        if (ai_count >= 2) addActor(labelColorPy3);
        if (ai_count >= 3) addActor(labelColorPy4);

        mainframeSp = new MySpriteActor("Sprite/MainFrame.png", game);
        mainframeSp.setPosition(50, 50);
        mainframeSp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        mainframeSp.setSize(70 ,70);

        addActor(mainframeSp);

        circle = new PrimitiveCircle(1);
        circle.setColor(Color.WHITE);
        circle.setSize(45, 45);
        circle.setPosition(mainframeSp.getWidth()/2+ mainframeSp.getX(),mainframeSp.getHeight()/2+mainframeSp.getY());

        addActor(circle);

        digit1 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 55, 0);
        digit1.setText("99");
        digit2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 55, 0);
        digit2.setText("99");
        digit3 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Thin.ttf", 20, 0);
        digit3.setText("99");
        colon = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 55, 0);
        colon.setText(":");

        digit1.setPosition(1150, 725);
        digit2.setPosition(1230, 725);
        digit3.setPosition(1295, 725);
        colon.setPosition(1213, 730);

        addActor(digit1);
        addActor(digit2);
        addActor(digit3);
        addActor(colon);

        beginBack = new PrimitiveSqaure(0);
        beginBack.setColor(new Color(34/255f, 34/255f, 34/255f, 0.8f));
        beginBack.setSize(Gdx.graphics.getWidth(), 150);
        beginBack.setPosition(0, Gdx.graphics.getHeight()/2 - 75);
        addActor(beginBack);

        backCover = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 125, 1);
        backCover.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        backCover.setColor(new Color(1, 1, 1, 1));
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
                                beginBack.changeColor(new Color(0, 0, 0, 0), 2);
                                isActive = true;
                            }
                        }, 1);
                    }
                }, 1);
            }
        }, 2);
        addActor(backCover);
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

        playerScore1.setText((int)allData.get(1).getProgess() +" %");
        playerScore2.setText((int)allData.get(2).getProgess() +" %");
        playerScore3.setText((int)allData.get(3).getProgess() +" %");
        playerScore4.setText((int)allData.get(4).getProgess() +" %");


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
