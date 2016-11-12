package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Actor.NodeActor;
import com.mygdx.game.Incident;
import com.mygdx.game.Stage.*;
import com.mygdx.game.Utility.Bot;
import com.mygdx.game.Utility.PlayerData;
import com.mygdx.game.Utility.RunningBot;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {

    private static final Sound invalidSound = Gdx.audio.newSound(Gdx.files.internal("invalid.mp3"));
    static public final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("clicked.mp3"));
    static public final Sound mOverSound = Gdx.audio.newSound(Gdx.files.internal("mouseOver.mp3"));
    static public final Color[] mainColor = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private static Music playingSceneSong;
    static public ArrayList<Integer> userColor;
    private final Incident game;
    private final int player_color;
    private final int ai_count;
    private final int ai_level;
    private final GameStageBG gameStageBG;
    private final CoverStage coverStage;
    private final GameStageUI gameStageUI;
    private final NodeStage nodeStage;
    private final Stage pelletStage;
    private final GameOverStage gameOverStage;
    private final TransitionStage transitionStage;
    private final ArrayList<PlayerData> alldata;
    private boolean isOver;
    private ArrayList<Bot> controlBot = new ArrayList<Bot>();

    public GameScreen(Incident g, int c, int cnt, int cl) {
        super();
        game = g;
        player_color = c;
        ai_count = cnt;
        ai_level = cl;
        isOver = false;
        alldata = new ArrayList<PlayerData>();


        playingSceneSong = game.manager.get("playing_scene.ogg", Music.class);
        playingSceneSong.setLooping(true);
        playingSceneSong.setVolume(0.5f);
        playingSceneSong.play();

        /*
        ## Set color player and random color to bot player
        */
        GameScreen gameScreen = this;

        userColor = new ArrayList<Integer>();
        userColor.add(gameScreen.getPlayer_color() - 1);

        for (int i = 0; i < 3; i++) {
            int tempRandom = -1;
            do {
                Random rd = new Random();
                tempRandom = rd.nextInt(4);
            } while (userColor.contains(tempRandom));
            userColor.add(tempRandom);
        }

        alldata.add(new PlayerData());
        alldata.add(new PlayerData());
        alldata.add(new PlayerData());
        alldata.add(new PlayerData());
        alldata.add(new PlayerData());

        ArrayList<NodeActor> allNode = new ArrayList<NodeActor>();

        gameStageBG = new GameStageBG(game);
        gameStageUI = new GameStageUI(game, ai_count, alldata, this);
        pelletStage = new Stage();
        nodeStage = new NodeStage(game, alldata, this, allNode);
        gameOverStage = new GameOverStage();
        coverStage = new CoverStage(game, this);
        transitionStage = new TransitionStage();

        final InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(nodeStage);
        im.addProcessor(gameStageUI);
        im.addProcessor(coverStage);
        im.addProcessor(gameOverStage);

        RunningBot rn = new RunningBot();
        Bot a;
        for(int i=2; i<=ai_count+1;i++){
            controlBot.add(rn.startBot(i, alldata, allNode));
        }

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                Gdx.input.setInputProcessor(im);
                for(int i=0; i<ai_count;i++){
                    controlBot.get(i).setActive(true);
                }
            }
        }, 4);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            alldata.get(1).increaseMoney(100);
        }

        if (!gameStageUI.isPause() && !isOver) {
            gameStageBG.act();
            nodeStage.act();
            gameStageUI.act();
            pelletStage.act();
            playingSceneSong.play();
        } else {
            if (!isOver) {
                playingSceneSong.pause();
                coverStage.pauseMenu();
            }
        }

        if (isOver) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
                game.setScreen(new MainMenuScreen(game));
                try {
                    dispose();
                } catch (Exception e) {

                }
            }
        } else {
            for (int i = 1; i <= ai_count + 1; i++) {
                if (alldata.get(i).getProgess() >= 100) {
                    if (i == 1) {
                        endGame(true);
                    } else {
                        endGame(false);
                    }
                }
            }

            int des = 0;

            for (int i = 1; i <= ai_count + 1; i++) {
                if (i == 1 && alldata.get(i).isDestroyed()) {
                    endGame(false);
                } else if (alldata.get(i).isDestroyed()) {
                    des++;
                }
            }
            if (des == ai_count) {
                endGame(true);
            }

            if (gameStageUI.getTime() <= 0) {
                int maxTeam = 0;
                float maxpercent = 0;
                for (int i = 1; i < 5; i++) {
                    if (alldata.get(i).getProgess() > maxpercent) {
                        maxpercent = alldata.get(i).getProgess();
                        maxTeam = i;
                    }
                }
                if (maxTeam > 0) {
                    if (maxTeam == 1) {
                        endGame(true);
                    } else {
                        endGame(false);
                    }
                } else {
                    maxTeam = 0;
                    maxpercent = 0;
                    for (int i = 1; i < 5; i++) {
                        if (alldata.get(i).getNodeCount() > maxpercent) {
                            maxpercent = alldata.get(i).getNodeCount();
                            maxTeam = i;
                        }
                    }
                    if (maxTeam == 1) {
                        endGame(true);
                    } else {
                        endGame(false);
                    }
                }
            }
        }

        gameOverStage.act();
        coverStage.act();
        transitionStage.act();

        gameStageBG.draw();
        pelletStage.draw();
        nodeStage.draw();
        gameStageUI.draw();
        coverStage.draw();
        gameOverStage.draw();
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
        gameOverStage.dispose();

        playingSceneSong.stop();
        playingSceneSong.dispose();
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

    private int getPlayer_color() {
        return player_color;
    }

    public int getAi_count() {
        return ai_count;
    }

    public int getAi_level() {
        return ai_level;
    }

    private void endGame(boolean b) {
        isOver = true;
        gameOverStage.gameover(b);
    }

    public void moneyFlash() {
        long id = invalidSound.play(1.0f);
        invalidSound.setPitch(id, 2);
        gameStageUI.redFlash();
    }

    public void playerDeath(int p) {
        gameStageUI.playerDeath(p);
    }
}
