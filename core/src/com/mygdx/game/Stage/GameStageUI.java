package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private MyTextDisplay moneyCount;

    private MyTextDisplay playerScore1;
    private MyTextDisplay playerScore2;
    private MyTextDisplay playerScore3;
    private MyTextDisplay playerScore4;

    private MyTextDisplay labelDatacenter;

    private MyTextDisplay playerInfo1;
    private MyTextDisplay playerInfo2;
    private MyTextDisplay playerInfo3;
    private MyTextDisplay playerInfo4;

    private PrimitiveSqaure labelColorPy1;
    private PrimitiveSqaure labelColorPy2;
    private PrimitiveSqaure labelColorPy3;
    private PrimitiveSqaure labelColorPy4;
    private PrimitiveSqaure moneyBack;

    private PrimitiveSqaure infoBoxTop;
    private PrimitiveSqaure infoBoxMid;
    private PrimitiveSqaure infoBoxBot;

    private PrimitiveSqaure colorMark2;
    private PrimitiveSqaure colorMark3;
    private PrimitiveSqaure colorMark4;


    private PrimitiveCircle circle;

    private MySpriteActor mainframeSp;
    private MySpriteActor creditIcon;
    private MySpriteActor adkUp, mechUp, coinUp;
    private MySpriteActor adkUpIcon, mechUpIcon, coinUpIcon;
    private MySpriteActor nodeIcon;

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

        final int hBoxInfo = 40;
        final int wBoxInfo = 180;

        final int hlabelBoxInfo = 40;
        final int wlabelBoxInfo = 10;

        final float boxDefaultY = 200;
        final float boxGap = 5;
//Player info background **[Left Side of Screen(Gray)]
        infoBoxTop = new PrimitiveSqaure(0);
        infoBoxMid = new PrimitiveSqaure(0);
        infoBoxBot = new PrimitiveSqaure(0);

        infoBoxTop.setSize(wBoxInfo, hBoxInfo);
        infoBoxMid.setSize(wBoxInfo, hBoxInfo);
        infoBoxBot.setSize(wBoxInfo, hBoxInfo);

        infoBoxTop.setPosition(0, boxDefaultY+(hBoxInfo+boxGap)*2);
        infoBoxMid.setPosition(0, boxDefaultY+hBoxInfo+boxGap);
        infoBoxBot.setPosition(0, boxDefaultY);

        infoBoxTop.setColor(1, 1, 1, 0.1f);
        infoBoxMid.setColor(1, 1, 1, 0.1f);
        infoBoxBot.setColor(1, 1, 1, 0.1f);

//Player color marks **[Left side of Screen]
        colorMark2 = new PrimitiveSqaure(0);
        colorMark3 = new PrimitiveSqaure(0);
        colorMark4 = new PrimitiveSqaure(0);

        colorMark2.setSize(wlabelBoxInfo, hlabelBoxInfo);
        colorMark3.setSize(wlabelBoxInfo, hlabelBoxInfo);
        colorMark4.setSize(wlabelBoxInfo, hlabelBoxInfo);

        colorMark2.setPosition(infoBoxTop.getX(), infoBoxTop.getY());
        colorMark3.setPosition(infoBoxMid.getX(), infoBoxMid.getY());
        colorMark4.setPosition(infoBoxBot.getX(), infoBoxBot.getY());

        colorMark2.setColor(labelColorPy2.getColor());
        colorMark3.setColor(labelColorPy3.getColor());
        colorMark4.setColor(labelColorPy4.getColor());

//Label : Player info about node, upgrade **[Left Side of Screen]
        playerInfo1 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        playerInfo2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        playerInfo3 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        playerInfo4 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);

        final float paddingB = 16;
        final float paddingL = 48;

        playerInfo1.setText("0        0       0        0");
        playerInfo2.setText("0        0       0        0");
        playerInfo3.setText("0        0       0        0");
        playerInfo4.setText("0        0       0        0");

        //playerInfo1.setPosition(0, 50);
        //addActor(playerInfo1);

//Player Info Icon **[Left side of Screen (icon)]


        final float wIcon = 25;
        final float hIcon = 25;


        for(int i=0; i < ai_count;i++) {
            adkUpIcon = new MySpriteActor("Sprite/adkUpIcon2.png", game);
            mechUpIcon = new MySpriteActor("Sprite/mechUpIcon.png", game);
            coinUpIcon = new MySpriteActor("Sprite/credit.png", game);
            nodeIcon = new MySpriteActor("Sprite/blank.png", game);

            nodeIcon.setSize(wIcon, hIcon);
            adkUpIcon.setSize(wIcon, hIcon);
            mechUpIcon.setSize(wIcon, hIcon);
            coinUpIcon.setSize(wIcon, hIcon);

            nodeIcon.setPosition(20, (infoBoxBot.getY() + 7.5f)+(45*i));
            adkUpIcon.setPosition(60, (infoBoxBot.getY() + 7.5f)+(45*i));
            mechUpIcon.setPosition(100,(infoBoxBot.getY() + 7.5f)+(45*i));
            coinUpIcon.setPosition(140,(infoBoxBot.getY() + 7.5f)+(45*i));
            addActor(nodeIcon);
            addActor(adkUpIcon);
            addActor(mechUpIcon);
            addActor(coinUpIcon);
        }

        if (ai_count == 1) {
            colorMark4.setColor(labelColorPy2.getColor());
            playerInfo2.setColor(labelColorPy2.getColor());
            playerInfo2.setPosition(infoBoxBot.getX()+paddingL, infoBoxBot.getY()+paddingB);
            addActor(infoBoxBot);
            addActor(colorMark4);
            addActor(playerInfo2);
        }
        else if (ai_count == 2) {
            colorMark3.setColor(labelColorPy2.getColor());
            colorMark4.setColor(labelColorPy3.getColor());
            playerInfo2.setColor(labelColorPy2.getColor());
            playerInfo3.setColor(labelColorPy3.getColor());
            playerInfo2.setPosition(infoBoxMid.getX()+paddingL, infoBoxMid.getY()+paddingB);
            playerInfo3.setPosition(infoBoxBot.getX()+paddingL, infoBoxBot.getY()+paddingB);
            addActor(infoBoxMid);
            addActor(colorMark3);
            addActor(infoBoxBot);
            addActor(colorMark4);
            addActor(playerInfo2);
            addActor(playerInfo3);
        }
        else if (ai_count == 3) {
            colorMark2.setColor(labelColorPy2.getColor());
            colorMark3.setColor(labelColorPy3.getColor());
            colorMark4.setColor(labelColorPy4.getColor());
            playerInfo2.setColor(labelColorPy2.getColor());
            playerInfo3.setColor(labelColorPy3.getColor());
            playerInfo4.setColor(labelColorPy4.getColor());
            playerInfo2.setPosition(infoBoxTop.getX()+paddingL, infoBoxTop.getY()+paddingB);
            playerInfo3.setPosition(infoBoxMid.getX()+paddingL, infoBoxMid.getY()+paddingB);
            playerInfo4.setPosition(infoBoxBot.getX()+paddingL, infoBoxBot.getY()+paddingB);
            addActor(infoBoxTop);
            addActor(colorMark2);
            addActor(infoBoxMid);
            addActor(colorMark3);
            addActor(infoBoxBot);
            addActor(colorMark4);
            addActor(playerInfo2);
            addActor(playerInfo3);
            addActor(playerInfo4);
        }
        circle = new PrimitiveCircle(0);
        circle.setColor(1, 1, 1, 0.1f);
        circle.setSize(130, 130);
        circle.setPosition(30, 20);

        addActor(circle);

        mainframeSp = new MySpriteActor("Sprite/MainFrame.png", game);
        mainframeSp.setPosition(circle.getX()-80, circle.getY()-70);
        mainframeSp.setRotation(-45f);
        mainframeSp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        mainframeSp.setSize(190, 190);

        addActor(mainframeSp);



        adkUp = new MySpriteActor("Sprite/adkUpgrade2.png", game);
        adkUp.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
            }
        });
        mechUp = new MySpriteActor("Sprite/mechineUpgrade.png", game);
        coinUp = new MySpriteActor("Sprite/creditUpgrade.png", game);
        final float wUpgrade = 50;
        final float hUpgrade = 50;

        adkUp.setSize (wUpgrade, hUpgrade);
        mechUp.setSize(wUpgrade, hUpgrade);
        coinUp.setSize(wUpgrade, hUpgrade);

        adkUp.setPosition(20,130);
        mechUp.setPosition(95,95);
        coinUp.setPosition(140,20);

        addActor(adkUp);
        addActor(mechUp);
        addActor(coinUp);

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

        backCover = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 80, 1);
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

        moneyBack = new PrimitiveSqaure(0);
        moneyBack.setColor(1, 1, 1, 0.1f);
        moneyBack.setSize(300, 60);
        moneyBack.setPosition(1125, 40);
        addActor(moneyBack);

        moneyCount = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 40, 2);
        moneyCount.setPosition(1265, 84);
        moneyCount.setColor(Color.WHITE);
        moneyCount.setText("0");
        addActor(moneyCount);

        creditIcon = new MySpriteActor("Sprite/credit.png", game);
        creditIcon.setSize(40, 40);
        creditIcon.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        creditIcon.setPosition(1285, 50);
        addActor(creditIcon);
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

        moneyCount.setText((int)allData.get(1).getMoney()+"");

        playerScore1.setText((int)allData.get(1).getProgess() +" %");
        playerScore2.setText((int)allData.get(2).getProgess() +" %");
        playerScore3.setText((int)allData.get(3).getProgess() +" %");
        playerScore4.setText((int)allData.get(4).getProgess() +" %");

        //allData.get(1).get

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
        else{
            allData.get(0).setMoney(0);
            allData.get(1).setMoney(0);
            allData.get(2).setMoney(0);
            allData.get(3).setMoney(0);
            allData.get(4).setMoney(0);
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

    public void redFlash(){

        ColorAction ca = new ColorAction();
        ca.setDuration(0.1f);
        ca.setInterpolation(Interpolation.pow3In);
        ca.setEndColor(Color.RED);

        ColorAction ca2 = new ColorAction();
        ca2.setDuration(0.1f);
        ca2.setInterpolation(Interpolation.pow3Out);
        ca2.setEndColor(new Color(1, 1, 1, 0.1f));

        ColorAction ca3 = new ColorAction();
        ca3.setDuration(0.1f);
        ca3.setInterpolation(Interpolation.pow3In);
        ca3.setEndColor(Color.RED);

        ColorAction ca4 = new ColorAction();
        ca4.setDuration(0.1f);
        ca4.setInterpolation(Interpolation.pow3Out);
        ca4.setEndColor(new Color(1, 1, 1, 0.1f));


        SequenceAction action = new SequenceAction(ca, ca2, ca3, ca4);

        moneyBack.addAction(action);
    }

    public int getTime() {
        return time;
    }
}
