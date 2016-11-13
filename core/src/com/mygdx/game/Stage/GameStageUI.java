package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Actor.*;
import com.mygdx.game.Incident;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.PlayerData;

import java.util.ArrayList;

public class GameStageUI extends Stage {

    private boolean isPause;
    private int time = 600000;
    private boolean isRed;

    private final MyTextDisplay digit1;
    private final MyTextDisplay digit2;
    private final MyTextDisplay digit3;
    private final MyTextDisplay colon;
    private final MyTextDisplay backCover;
    private final MyTextDisplay moneyCount;

    private final MyTextDisplay playerScore1;
    private final MyTextDisplay playerScore2;
    private final MyTextDisplay playerScore3;
    private final MyTextDisplay playerScore4;

    private final MyTextDisplay playerInfo2;
    private final MyTextDisplay playerInfo3;
    private final MyTextDisplay playerInfo4;

    private final MyTextDisplay txtadkUpInfo;
    private final MyTextDisplay txtmechUpInfo;
    private final MyTextDisplay txtcreditUpInfo;

    private final MyTextDisplay adkSelectCost;
    private final MyTextDisplay mechSelectCost;
    private final MyTextDisplay creditSelectCost;

    private final PrimitiveSqaure moneyBack;

    private final PrimitiveSqaure colorMark2;
    private final PrimitiveSqaure colorMark3;
    private final PrimitiveSqaure colorMark4;


    private final PrimitiveCircle adkOnSelect;
    private final PrimitiveCircle mechOnSelect;
    private final PrimitiveCircle creditOnSelect;

    private final Timer timer;
    private boolean isActive;
    private final PrimitiveSqaure beginBack;
    private final ArrayList<PlayerData> allData;
    private final int ai_c;
    private GameScreen screen;

    public GameStageUI(Incident g, int ai_count, ArrayList<PlayerData> d, final GameScreen gameScreen) {
        super();
        ai_c = ai_count;
        isRed = true;
        isPause = false;
        isActive = false;
        timer = new Timer();
        allData = d;
        screen = gameScreen;

        MyPauseBtn myPauseBtn = new MyPauseBtn(g.manager) {
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

        MyTextDisplay labelDatacenter = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 25, 2);
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
        for (int i = 0; i < 4; i++) {
            tempObjText[i].setPosition(1300, 620 - (i * 33));
        }

        addActor(playerScore1);
        if (ai_count >= 1) addActor(playerScore2);
        if (ai_count >= 2) addActor(playerScore3);
        if (ai_count >= 3) addActor(playerScore4);


        PrimitiveSqaure labelColorPy1 = new PrimitiveSqaure(0);
        PrimitiveSqaure labelColorPy2 = new PrimitiveSqaure(0);
        PrimitiveSqaure labelColorPy3 = new PrimitiveSqaure(0);
        PrimitiveSqaure labelColorPy4 = new PrimitiveSqaure(0);

        labelColorPy1.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        labelColorPy2.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
        labelColorPy3.setColor(GameScreen.mainColor[GameScreen.userColor.get(2)]);
        labelColorPy4.setColor(GameScreen.mainColor[GameScreen.userColor.get(3)]);

        labelColorPy1.setSize(15, 15);
        labelColorPy2.setSize(15, 15);
        labelColorPy3.setSize(15, 15);
        labelColorPy4.setSize(15, 15);

        PrimitiveSqaure[] tempObj = {labelColorPy1, labelColorPy2, labelColorPy3, labelColorPy4};
        for (int i = 0; i < 4; i++) {
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

        final float boxDefaultY = 250;
        final float boxGap = 5;
//Player info background **[Left Side of Screen(Gray)]
        PrimitiveSqaure infoBoxTop = new PrimitiveSqaure(0);
        PrimitiveSqaure infoBoxMid = new PrimitiveSqaure(0);
        PrimitiveSqaure infoBoxBot = new PrimitiveSqaure(0);

        infoBoxTop.setSize(wBoxInfo, hBoxInfo);
        infoBoxMid.setSize(wBoxInfo, hBoxInfo);
        infoBoxBot.setSize(wBoxInfo, hBoxInfo);

        infoBoxTop.setPosition(0, boxDefaultY + (hBoxInfo + boxGap) * 2);
        infoBoxMid.setPosition(0, boxDefaultY + hBoxInfo + boxGap);
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
        playerInfo2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        playerInfo3 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        playerInfo4 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);

        final float paddingB = 16;
        final float paddingL = 48;

        playerInfo2.setText("0        0       0        0");
        playerInfo3.setText("0        0       0        0");
        playerInfo4.setText("0        0       0        0");

//Player Info Icon **[Left side of Screen (icon)]


        final float wIcon = 25;
        final float hIcon = 25;


        for (int i = 0; i < ai_count; i++) {
            MySpriteActor adkUpIcon = new MySpriteActor("Sprite/adkUpIcon2.png", g);
            MySpriteActor mechUpIcon = new MySpriteActor("Sprite/mechUpIcon.png", g);
            MySpriteActor creditUpIcon = new MySpriteActor("Sprite/credit.png", g);
            MySpriteActor nodeIcon = new MySpriteActor("Sprite/blank.png", g);

            nodeIcon.setSize(wIcon, hIcon);
            adkUpIcon.setSize(wIcon, hIcon);
            mechUpIcon.setSize(wIcon, hIcon);
            creditUpIcon.setSize(wIcon, hIcon);

            nodeIcon.setPosition(20, (infoBoxBot.getY() + 7.5f) + (45 * i));
            adkUpIcon.setPosition(60, (infoBoxBot.getY() + 7.5f) + (45 * i));
            mechUpIcon.setPosition(100, (infoBoxBot.getY() + 7.5f) + (45 * i));
            creditUpIcon.setPosition(140, (infoBoxBot.getY() + 7.5f) + (45 * i));
            addActor(nodeIcon);
            addActor(adkUpIcon);
            addActor(mechUpIcon);
            addActor(creditUpIcon);
        }

        if (ai_count == 1) {
            colorMark4.setColor(labelColorPy2.getColor());
            playerInfo2.setColor(labelColorPy2.getColor());
            playerInfo2.setPosition(infoBoxBot.getX() + paddingL, infoBoxBot.getY() + paddingB);
            addActor(infoBoxBot);
            addActor(colorMark4);
            addActor(playerInfo2);
        } else if (ai_count == 2) {
            colorMark3.setColor(labelColorPy2.getColor());
            colorMark4.setColor(labelColorPy3.getColor());
            playerInfo2.setColor(labelColorPy2.getColor());
            playerInfo3.setColor(labelColorPy3.getColor());
            playerInfo2.setPosition(infoBoxMid.getX() + paddingL, infoBoxMid.getY() + paddingB);
            playerInfo3.setPosition(infoBoxBot.getX() + paddingL, infoBoxBot.getY() + paddingB);
            addActor(infoBoxMid);
            addActor(colorMark3);
            addActor(infoBoxBot);
            addActor(colorMark4);
            addActor(playerInfo2);
            addActor(playerInfo3);
        } else if (ai_count == 3) {
            colorMark2.setColor(labelColorPy2.getColor());
            colorMark3.setColor(labelColorPy3.getColor());
            colorMark4.setColor(labelColorPy4.getColor());
            playerInfo2.setColor(labelColorPy2.getColor());
            playerInfo3.setColor(labelColorPy3.getColor());
            playerInfo4.setColor(labelColorPy4.getColor());
            playerInfo2.setPosition(infoBoxTop.getX() + paddingL, infoBoxTop.getY() + paddingB);
            playerInfo3.setPosition(infoBoxMid.getX() + paddingL, infoBoxMid.getY() + paddingB);
            playerInfo4.setPosition(infoBoxBot.getX() + paddingL, infoBoxBot.getY() + paddingB);
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
//------------------------------------------------------------//
//      Player Upgrade Bar **[Botton-Left of Screen]
//------------------------------------------------------------//
        //Create Object Zone...
        MySpriteActor adkUp = new MySpriteActor("Sprite/adkUpgrade2.png", g);
        MySpriteActor mechUp = new MySpriteActor("Sprite/mechineUpgrade.png", g);
        MySpriteActor creditUp = new MySpriteActor("Sprite/creditUpgrade.png", g);
        PrimitiveCircle cUpgradeBg = new PrimitiveCircle(0);
        PrimitiveCircle adkLvBg = new PrimitiveCircle(0);
        PrimitiveCircle mechLvBg = new PrimitiveCircle(0);
        PrimitiveCircle creditLvBg = new PrimitiveCircle(0);
        PrimitiveCircle cUpgreadeIcon1 = new PrimitiveCircle(1);
        PrimitiveCircle cUpgreadeIcon2 = new PrimitiveCircle(1);
        PrimitiveCircle cUpgreadeIcon3 = new PrimitiveCircle(1);
        PrimitiveCircle adkSelectBg = new PrimitiveCircle(0);
        PrimitiveCircle mechSelectBg = new PrimitiveCircle(0);
        PrimitiveCircle creditSelectBg = new PrimitiveCircle(0);
        adkOnSelect = new PrimitiveCircle(0);
        mechOnSelect = new PrimitiveCircle(0);
        creditOnSelect = new PrimitiveCircle(0);
        txtadkUpInfo = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        txtmechUpInfo = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        txtcreditUpInfo = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 16, 0);
        MySpriteActor mainframeSp = new MySpriteActor("Sprite/MainFrame.png", g);
        adkSelectCost = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 18, 1);
        mechSelectCost = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 18, 1);
        creditSelectCost = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 18, 1);


        //Defind final variable Zone...
        final float wUpgrade = 50;  //Upgrade Actor Size (Robot Head)
        final float hUpgrade = 50;  //->-----------------------------
        final float lvBgSize = 12;  //Lv Backgroung Size (Fill Circle on Ring)
        final float xLvPad = 3;   // Align Lv Backgroung Size to center
        final float yLvPad = 10;  //->--------------------------------
        final float onSelectTime = 0.05f;
        final float onClickTime = 0.05f;
        final float onClickFadeTime = 1;
        final float xTextpad = 0; //Fix Align text pad


        //Set Actor Size zone....
        adkUp.setSize(wUpgrade, hUpgrade);
        mechUp.setSize(wUpgrade, hUpgrade);
        creditUp.setSize(wUpgrade, hUpgrade);
        cUpgradeBg.setSize(130, 130);
        adkLvBg.setSize(lvBgSize, lvBgSize);
        mechLvBg.setSize(lvBgSize, lvBgSize);
        creditLvBg.setSize(lvBgSize, lvBgSize);
        cUpgreadeIcon1.setSize(30, 30);
        cUpgreadeIcon2.setSize(30, 30);
        cUpgreadeIcon3.setSize(30, 30);
        mainframeSp.setSize(190, 190);
        adkSelectBg.setSize(cUpgreadeIcon1.getWidth(), cUpgreadeIcon1.getHeight());
        mechSelectBg.setSize(cUpgreadeIcon2.getWidth(), cUpgreadeIcon2.getHeight());
        creditSelectBg.setSize(cUpgreadeIcon3.getWidth(), cUpgreadeIcon3.getHeight());
        adkOnSelect.setSize(cUpgreadeIcon1.getWidth(), cUpgreadeIcon1.getHeight());
        mechOnSelect.setSize(cUpgreadeIcon2.getWidth(), cUpgreadeIcon2.getHeight());
        creditOnSelect.setSize(cUpgreadeIcon3.getWidth(), cUpgreadeIcon3.getHeight());

        //Set Actor position zone .....
        adkUp.setPosition(10, 130);
        mechUp.setPosition(88, 97);
        creditUp.setPosition(133, 25);
        cUpgradeBg.setPosition(30, 20);
        cUpgreadeIcon1.setPosition(adkUp.getX() + adkUp.getWidth() / 2, adkUp.getY() + adkUp.getHeight() / 2);
        cUpgreadeIcon2.setPosition(mechUp.getX() + mechUp.getWidth() / 2, mechUp.getY() + mechUp.getHeight() / 2);
        cUpgreadeIcon3.setPosition(creditUp.getX() + creditUp.getWidth() / 2, creditUp.getY() + creditUp.getHeight() / 2);
        adkLvBg.setPosition(cUpgreadeIcon1.getX() + cUpgreadeIcon1.getWidth() - xLvPad, cUpgreadeIcon1.getY() + cUpgreadeIcon1.getHeight() - yLvPad);
        mechLvBg.setPosition(cUpgreadeIcon2.getX() + cUpgreadeIcon2.getWidth() - xLvPad, cUpgreadeIcon2.getY() + cUpgreadeIcon2.getHeight() - yLvPad);
        creditLvBg.setPosition(cUpgreadeIcon3.getX() + cUpgreadeIcon3.getWidth() - xLvPad, cUpgreadeIcon3.getY() + cUpgreadeIcon3.getHeight() - yLvPad);
        txtadkUpInfo.setPosition(adkLvBg.getX() - adkLvBg.getWidth() / 2 + 1, adkLvBg.getY() + adkLvBg.getHeight() / 2);
        txtmechUpInfo.setPosition(mechLvBg.getX() - mechLvBg.getWidth() / 2 + 1, mechLvBg.getY() + mechLvBg.getHeight() / 2);
        txtcreditUpInfo.setPosition(creditLvBg.getX() - creditLvBg.getWidth() / 2 + 1, creditLvBg.getY() + creditLvBg.getHeight() / 2);
        mainframeSp.setPosition(cUpgradeBg.getX() - 90, cUpgradeBg.getY() - 80);
        adkSelectBg.setPosition(cUpgreadeIcon1.getX(), cUpgreadeIcon1.getY());
        mechSelectBg.setPosition(cUpgreadeIcon2.getX(), cUpgreadeIcon2.getY());
        creditSelectBg.setPosition(cUpgreadeIcon3.getX(), cUpgreadeIcon3.getY());
        adkOnSelect.setPosition(cUpgreadeIcon1.getX(), cUpgreadeIcon1.getY());
        mechOnSelect.setPosition(cUpgreadeIcon2.getX(), cUpgreadeIcon2.getY());
        creditOnSelect.setPosition(cUpgreadeIcon3.getX(), cUpgreadeIcon3.getY());
        adkSelectCost.setPosition(adkOnSelect.getX() - xTextpad, adkOnSelect.getY());
        mechSelectCost.setPosition(mechOnSelect.getX() - xTextpad, mechOnSelect.getY());
        creditSelectCost.setPosition(creditOnSelect.getX() - xTextpad, creditOnSelect.getY());

        //Set Actor Color Zone......
        cUpgradeBg.setColor(0.18f, 0.18f, 0.18f, 1f);
        adkLvBg.setColor(0.18f, 0.18f, 0.18f, 1f);
        mechLvBg.setColor(0.18f, 0.18f, 0.18f, 1f);
        creditLvBg.setColor(0.18f, 0.18f, 0.18f, 1f);
        cUpgreadeIcon1.setColor(0.18f, 0.18f, 0.18f, 1f);
        cUpgreadeIcon2.setColor(0.18f, 0.18f, 0.18f, 1f);
        cUpgreadeIcon3.setColor(0.18f, 0.18f, 0.18f, 1f);
        mainframeSp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        adkSelectBg.setColor(0.1f, 0.1f, 0.1f, 1);
        mechSelectBg.setColor(0.1f, 0.1f, 0.1f, 1f);
        creditSelectBg.setColor(0.1f, 0.1f, 0.1f, 1f);
        adkOnSelect.setColor(1, 1, 1, 0);
        mechOnSelect.setColor(1, 1, 1, 0);
        creditOnSelect.setColor(1, 1, 1, 0);
        adkSelectCost.setColor(1, 1, 1, 0);
        mechSelectCost.setColor(1, 1, 1, 0);
        creditSelectCost.setColor(1, 1, 1, 0);

        //Add Actor Listener Zone.....
        adkUp.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                adkOnSelect.changeColor(new Color(0, 0, 0, 0.9f), onSelectTime);
                adkSelectCost.changeColor(new Color(1, 1, 1, 1), onSelectTime);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                adkOnSelect.changeColor(new Color(0, 0, 0, 0), onSelectTime);
                adkSelectCost.changeColor(new Color(1, 1, 1, 0), onSelectTime);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (allData.get(1).getMoney() >= allData.get(1).getAttackPrice()) {
                    adkOnSelect.setColor(Color.WHITE);
                    adkOnSelect.changeColor(new Color(1, 1, 1, 0), onClickFadeTime);
                    allData.get(1).levelupAttack();
                } else {
                    gameScreen.moneyFlash();
                }

            }

        });
        mechUp.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                mechOnSelect.changeColor(new Color(0, 0, 0, 0.9f), onSelectTime);
                mechSelectCost.changeColor(new Color(1, 1, 1, 1), onSelectTime);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                mechOnSelect.changeColor(new Color(0, 0, 0, 0), onSelectTime);
                mechSelectCost.changeColor(new Color(1, 1, 1, 0), onSelectTime);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (allData.get(1).getMoney() >= allData.get(1).getHpPrice()) {
                    mechOnSelect.setColor(Color.WHITE);
                    mechOnSelect.changeColor(new Color(1, 1, 1, 0), onClickFadeTime);
                    allData.get(1).levelupHp();
                } else {
                    gameScreen.moneyFlash();
                }
            }
        });
        creditUp.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                creditOnSelect.changeColor(new Color(0, 0, 0, 0.9f), onSelectTime);
                creditSelectCost.changeColor(new Color(1, 1, 1, 1), onSelectTime);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                creditOnSelect.changeColor(new Color(0, 0, 0, 0), onSelectTime);
                creditSelectCost.changeColor(new Color(1, 1, 1, 0), onSelectTime);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (allData.get(1).getMoney() >= allData.get(1).getRangePrince()) {
                    creditOnSelect.setColor(Color.WHITE);
                    creditOnSelect.changeColor(new Color(1, 1, 1, 0), onClickFadeTime);
                    allData.get(1).levelupRange();
                } else {
                    gameScreen.moneyFlash();
                }
            }
        });

        //Set Text for each Actor....
        txtadkUpInfo.setText("0");
        txtmechUpInfo.setText("0");
        txtcreditUpInfo.setText("0");

        adkSelectCost.setText("0");
        mechSelectCost.setText("0");
        creditSelectCost.setText("0");


        //Set etc......
        mainframeSp.setRotation(-45f);

        //Add Actor Layer Zone for Upgrade bar Naja...
        addActor(cUpgradeBg);
        addActor(adkSelectBg);
        addActor(mechSelectBg);
        addActor(creditSelectBg);
        addActor(adkUp);
        addActor(mechUp);
        addActor(creditUp);
        addActor(adkOnSelect);
        addActor(mechOnSelect);
        addActor(creditOnSelect);
        addActor(adkSelectCost);
        addActor(mechSelectCost);
        addActor(creditSelectCost);
        addActor(mainframeSp);
        addActor(cUpgreadeIcon1);
        addActor(cUpgreadeIcon2);
        addActor(cUpgreadeIcon3);
        addActor(adkLvBg);
        addActor(mechLvBg);
        addActor(creditLvBg);
        addActor(txtadkUpInfo);
        addActor(txtmechUpInfo);
        addActor(txtcreditUpInfo);

//----------------------------------------------------------------------------//
//Download Data Center Progress **[Top-Right of Screen]
//----------------------------------------------------------------------------//
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
        beginBack.setColor(new Color(34 / 255f, 34 / 255f, 34 / 255f, 0.8f));
        beginBack.setSize(Gdx.graphics.getWidth(), 150);
        beginBack.setPosition(0, Gdx.graphics.getHeight() / 2 - 75);
        addActor(beginBack);

        backCover = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 80, 1);
        backCover.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
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

        MySpriteActor creditIcon = new MySpriteActor("Sprite/credit.png", g);
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

        if(time <= 160000){
            screen.switchMusic();
        }
        digit1.setText(String.format("%02d", time / 60000));
        digit2.setText(String.format("%02d", time % 60000 / 1000));
        digit3.setText(String.format("%03d", time % 1000));

        adkSelectCost.setText(allData.get(1).getAttackPrice() + "");
        mechSelectCost.setText(allData.get(1).getHpPrice() + "");
        creditSelectCost.setText(allData.get(1).getRangePrince() + "");

        if (!allData.get(2).isDestroyed()) {
            playerInfo2.setText(allData.get(2).getNodeCount() + "        " +
                    allData.get(2).getAttackLevel() + "       "
                    + allData.get(2).getHpLevel() + "        "
                    + allData.get(2).getRangeLevel());
        } else {
            playerInfo2.setText("");
        }
        if (!allData.get(3).isDestroyed()) {
            playerInfo3.setText(allData.get(3).getNodeCount() + "        " +
                    allData.get(3).getAttackLevel() + "       "
                    + allData.get(3).getHpLevel() + "        "
                    + allData.get(3).getRangeLevel());
        } else {
            playerInfo3.setText("");
        }
        if (!allData.get(4).isDestroyed()) {
            playerInfo4.setText(allData.get(4).getNodeCount() + "        " +
                    allData.get(4).getAttackLevel() + "       "
                    + allData.get(4).getHpLevel() + "        "
                    + allData.get(4).getRangeLevel());
        } else {
            playerInfo4.setText("");
        }

        txtadkUpInfo.setText(allData.get(1).getAttackLevel() + "");
        txtmechUpInfo.setText(allData.get(1).getHpLevel() + "");
        txtcreditUpInfo.setText(allData.get(1).getRangeLevel() + "");


        moneyCount.setText((int) allData.get(1).getMoney() + "");

        playerScore1.setText((int) allData.get(1).getProgess() + " %");
        playerScore2.setText((int) allData.get(2).getProgess() + " %");
        playerScore3.setText((int) allData.get(3).getProgess() + " %");
        playerScore4.setText((int) allData.get(4).getProgess() + " %");

        //allData.get(1).get

        if (isActive) {
            time -= 1000 * Gdx.graphics.getDeltaTime();
            if (time <= 60000 && isRed) {
                isRed = false;
                digit1.changeColor(Color.RED, 1);
                digit2.changeColor(Color.RED, 1);
                digit3.changeColor(Color.RED, 1);
                colon.changeColor(Color.RED, 1);
            }
        } else {
            allData.get(0).setMoney();
            allData.get(1).setMoney();
            allData.get(2).setMoney();
            allData.get(3).setMoney();
            allData.get(4).setMoney();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        for(Actor a:getActors()){
            a.addAction(new RemoveAction());
        }
        clear();
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause() {
        isPause = false;
    }

    public void redFlash() {

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

    public void playerDeath(int t) {
        t = (4 - ai_c) + (t) - 1;
        System.out.print(t + "");
        switch (t) {
            case 2:
                colorMark2.changeSize();
                colorMark2.changeColor(new Color(colorMark2.getColor().r,
                        colorMark2.getColor().g,
                        colorMark2.getColor().b,
                        0.7f), 1);
                break;
            case 3:
                colorMark3.changeSize();
                colorMark3.changeColor(new Color(colorMark3.getColor().r,
                        colorMark3.getColor().g,
                        colorMark3.getColor().b,
                        0.7f), 1);
                break;
            case 4:
                colorMark4.changeSize();
                colorMark4.changeColor(new Color(colorMark4.getColor().r,
                        colorMark4.getColor().g,
                        colorMark4.getColor().b,
                        0.7f), 1);
                break;
        }
    }
}
