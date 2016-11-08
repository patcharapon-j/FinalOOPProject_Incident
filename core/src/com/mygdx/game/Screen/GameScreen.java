package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Actor.PrimitiveSqaure;
import com.mygdx.game.Incident;
import com.mygdx.game.Stage.*;
import com.mygdx.game.Utility.PlayerData;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {

    private Incident game;
    private int player_color;
    private int ai_count;
    private int ai_level;
    private GameStageBG gameStageBG;
    private CoverStage coverStage;
    private GameStageUI gameStageUI;
    private NodeStage nodeStage;
    private Stage pelletStage;
    private TransitionStage transitionStage;
    private ArrayList<PlayerData> alldata;

    static public ArrayList<Integer> userColor;
    static public final Color[] mainColor = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public GameScreen(Incident g, int c, int cnt, int cl) {
        super();
        game = g;
        player_color = c;
        ai_count = cnt;
        ai_level = cl;
        alldata = new ArrayList<PlayerData>();

        /*
        ## Set color player and random color to bot player
        */
        GameScreen gameScreen = this;

        userColor = new ArrayList<Integer>();
        userColor.add(gameScreen.getPlayer_color() - 1);

        for(int i=0; i<3; i++) {
            int tempRandom = -1;
            do{
                Random rd = new Random();
                tempRandom = rd.nextInt(4);
            }while(userColor.contains(tempRandom));
            userColor.add(tempRandom);
        }

        alldata.add(new PlayerData());
        alldata.add(new PlayerData());
        alldata.add(new PlayerData());
        alldata.add(new PlayerData());
        alldata.add(new PlayerData());

        gameStageBG = new GameStageBG(game);
        gameStageUI = new GameStageUI(game, ai_count, alldata);
        pelletStage = new Stage();
        nodeStage = new NodeStage(game, alldata, this);
        coverStage = new CoverStage(game, this);
        transitionStage = new TransitionStage();

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(coverStage);
        im.addProcessor(gameStageUI);
        im.addProcessor(nodeStage);

        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!gameStageUI.isPause()){
            gameStageBG.act();
            nodeStage.act();
            gameStageUI.act();
            pelletStage.act();
        }
        else{
            coverStage.pauseMenu();
        }

        coverStage.act();
        transitionStage.act();

        gameStageBG.draw();
        pelletStage.draw();
        nodeStage.draw();
        gameStageUI.draw();
        coverStage.draw();
        transitionStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameStageBG.dispose();
        gameStageUI.dispose();
        coverStage.dispose();
        pelletStage.dispose();
        nodeStage.dispose();


    }

    public GameStageBG getGameStageBG() {
        return gameStageBG;
    }

    public CoverStage getCoverStage() {
        return coverStage;
    }

    public GameStageUI getGameStageUI() {
        return gameStageUI;
    }

    public NodeStage getNodeStage() {
        return nodeStage;
    }

    public Stage getPelletStage() {
        return pelletStage;
    }

    public int getPlayer_color() {
        return player_color;
    }

    public int getAi_count() {
        return ai_count;
    }

    public int getAi_level() {
        return ai_level;
    }
}
