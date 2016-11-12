package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.mygdx.game.Actor.*;
import com.mygdx.game.Incident;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.RunningBot;

import javax.swing.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;

public class MainMenuStage extends Stage {

    private final PrimitiveSqaure titleBack;
    private final MyTextDisplay title;
    private final MyButton startButton;
    private final MyButton creditButton;
    private final MyButton exitButton;
    private final MyButton creditBackButton;
    private final MyButton gameStart;
    private final MyButton gameBack;
    private final MyTextDisplay name1;
    private final MyTextDisplay name2;
    private final MyTextDisplay name3;
    private final MyTextDisplay school;
    private final MyTextDisplay creditText;
    private final MyTextDisplay gameText;
    private final MyTextDisplay oppoentText;
    private final MyTextDisplay diffiText;
    private final MyTextDisplay colorText;
    private final MyToggleButton ai_1;
    private final MyToggleButton ai_2;
    private final MyToggleButton ai_3;
    private final MyToggleButton ai_easy;
    private final MyToggleButton ai_hard;
    private final MyColorBlock redBlock;
    private final MyColorBlock greenBlock;
    private final MyColorBlock blueBlock;
    private final MyColorBlock yellowBlock;
    private final PrimitiveSqaure cover;

    private String botid;
    private VideoPlayer videoPlayer;
    private final Music themeSong;
    private Timer timer;
    private final Incident game;

    public MainMenuStage(Incident g) {
        super();

        final int[] player_team = {1};
        final int[] number_ai = {1};
        final int[] ai_level = {1};
        botid = null;
        game = g;

        themeSong = game.manager.get("theme.mp3", Music.class);
        themeSong.setLooping(true);
        themeSong.setVolume(0.5f);
        themeSong.play();

        titleBack = new PrimitiveSqaure(0);
        titleBack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        titleBack.setPosition(0, 0);
        titleBack.setColor(Color.BLACK);
        addActor(titleBack);

        title = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 60, 1);
        title.setText("INCIDENT");
        title.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        addActor(title);
        title.setAlpha(0);
        title.fadeIn();

        creditText = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 60, 1);
        creditText.setText("Credit");
        creditText.setPosition(-250, 650);
        addActor(creditText);

        gameText = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 60, 1);
        gameText.setText("Game Setup");
        gameText.setPosition(-250, 650);
        addActor(gameText);

        name1 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 0);
        name1.setText("Chayapol Chaimongkolnimit \n58070026");
        name1.setPosition(-400, 550);
        addActor(name1);

        name2 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 0);
        name2.setText("Boonyarith Piriyothinkul \n58070077");
        name2.setPosition(-400, 450);
        addActor(name2);

        name3 = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 0);
        name3.setText("Patcharapon Joksamut \n58070096");
        name3.setPosition(-400, 350);
        addActor(name3);

        oppoentText = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 1);
        oppoentText.setText("Number of AI");
        oppoentText.setPosition(-250, 575);
        addActor(oppoentText);

        diffiText = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 1);
        diffiText.setText("AI Level");
        diffiText.setPosition(-250, 450);
        addActor(diffiText);

        colorText = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Light.ttf", 24, 1);
        colorText.setText("Player Color");
        colorText.setPosition(-250, 325);
        addActor(colorText);

        school = new MyTextDisplay("fonts/helveticaneue/HelveticaNeue Medium.ttf", 32, 1);
        school.setText("ITKMITL");
        school.setPosition(-250, 250);
        addActor(school);

        startButton = new MyButton() {
            @Override
            public void myClick() {
                toStart();
            }
        };
        startButton.setSize(500, 50);
        startButton.setPosition(-750, 450);
        startButton.getText().setText("Start");
        startButton.getText().setColor(Color.WHITE);
        startButton.setColor(new Color(0, 0, 0, 0));
        addActor(startButton);

        creditButton = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                toCredit();
            }
        };
        creditButton.setSize(500, 50);
        creditButton.setPosition(-750, 325);
        creditButton.getText().setText("Credit");
        creditButton.getText().setColor(Color.WHITE);
        creditButton.setColor(new Color(0, 0, 0, 0));
        addActor(creditButton);

        exitButton = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                Gdx.app.exit();
            }
        };
        exitButton.setSize(500, 50);
        exitButton.setPosition(-750, 50);
        exitButton.getText().setText("Exit");
        exitButton.getText().setColor(Color.WHITE);
        exitButton.setColor(new Color(0, 0, 0, 0));
        addActor(exitButton);

        creditBackButton = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                clearCredit();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        inital();
                    }
                }, 1);


            }
        };
        creditBackButton.setSize(500, 50);
        creditBackButton.setPosition(-750, 50);
        creditBackButton.getText().setText("Back");
        creditBackButton.getText().setColor(Color.WHITE);
        creditBackButton.setColor(new Color(0, 0, 0, 0));
        addActor(creditBackButton);

        gameBack = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                startClear();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        inital();
                    }
                }, 1);


            }
        };
        gameBack.setSize(500, 50);
        gameBack.setPosition(-750, 50);
        gameBack.getText().setText("Back");
        gameBack.getText().setColor(Color.WHITE);
        gameBack.setColor(new Color(0, 0, 0, 0));
        addActor(gameBack);

        gameStart = new MyButton() {
            @Override
            public void myClick() {
                super.myClick();
                if (gameStart.getActive()) {
                    gameStart.setActive(false);
                    cover.changePosition();

                    timer.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            gameStart.setActive(true);
                            game.setScreen(new GameScreen(game, player_team[0], number_ai[0], ai_level[0]));
                            game.player_color = player_team[0];
                            game.ai_count = number_ai[0];
                            game.ai_diff = ai_level[0];
                            videoPlayer.pause();
                            themeSong.stop();
                            dispose();
                        }
                    }, 2);
                }
            }
        };
        gameStart.setSize(500, 50);
        gameStart.setPosition(-750, 130);
        gameStart.getText().setText("Commence");
        gameStart.getText().setColor(Color.WHITE);
        gameStart.setColor(new Color(0, 0, 0, 0));
        addActor(gameStart);

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                inital();
            }
        }, 3);

        ai_1 = new MyToggleButton() {
            @Override
            public void myClick() {
                super.myClick();
                ai_1.setSelected(true);
                ai_2.setSelected(false);
                ai_3.setSelected(false);
                number_ai[0] = 1;
            }
        };
        ai_1.setSelected(true);
        ai_1.setSize(65, 65);
        ai_1.getText().setText("1");
        ai_1.getText().setColor(Color.WHITE);
        ai_1.setColor(Color.WHITE);
        ai_1.setPosition(120 - 500, 480);
        addActor(ai_1);

        ai_2 = new MyToggleButton() {
            @Override
            public void myClick() {
                super.myClick();
                ai_1.setSelected(false);
                ai_2.setSelected(true);
                ai_3.setSelected(false);
                number_ai[0] = 2;
            }
        };
        ai_2.setSelected(false);
        ai_2.setSize(65, 65);
        ai_2.getText().setText("2");
        ai_2.getText().setColor(Color.WHITE);
        ai_2.setColor(Color.WHITE);
        ai_2.setPosition(220 - 500, 480);
        addActor(ai_2);

        ai_3 = new MyToggleButton() {
            @Override
            public void myClick() {
                super.myClick();
                ai_1.setSelected(false);
                ai_2.setSelected(false);
                ai_3.setSelected(true);
                number_ai[0] = 3;
            }
        };
        ai_3.setSelected(false);
        ai_3.setSize(65, 65);
        ai_3.getText().setText("3");
        ai_3.getText().setColor(Color.WHITE);
        ai_3.setColor(Color.WHITE);
        ai_3.setPosition(320 - 500, 480);
        addActor(ai_3);

        ai_easy = new MyToggleButton() {
            @Override
            public void myClick() {
                super.myClick();
                ai_easy.setSelected(true);
                ai_hard.setSelected(false);
                ai_level[0] = 1;
            }
        };
        ai_easy.setSelected(true);
        ai_easy.setSize(125, 65);
        ai_easy.getText().setText("default");
        ai_easy.getText().setColor(Color.WHITE);
        ai_easy.setColor(Color.WHITE);
        ai_easy.setPosition(115 - 500, 355);
        addActor(ai_easy);

        ai_hard = new MyToggleButton() {
            @Override
            public void myClick() {
                super.myClick();
                ai_easy.setSelected(false);
                ai_hard.setSelected(true);
                ai_level[0] = 2;

                JFrame frame = new JFrame("Add Custom Bot");
                botid = JOptionPane.showInputDialog(frame, "Please Insert Bot ID:");
                System.out.println(botid);

                try {

                    URL url = new URL("http://cloudian.in.th/incident/download.php?id=" + botid);
                    URLConnection conn = url.openConnection();
                    Map<String, List<String>> map = conn.getHeaderFields();
                    String filename = map.get("Content-Disposition").get(0).substring(22, map.get("Content-Disposition").get(0).length()-1);
                    RunningBot.filename = filename;

                    ReadableByteChannel rbc = Channels.newChannel(url.openStream());

                    File file = new File("BotContainer\\"+filename);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

                    if(file.length() <= 0){
                        throw new FileNotFoundException();
                    }

                    fos.close();
                    rbc.close();
                }
                catch (FileNotFoundException e){
                    System.err.println("This Bot is not existed.");
                    System.err.println(e);
                    JOptionPane.showMessageDialog(frame, "This Bot is not existed.", "Error", JOptionPane.ERROR_MESSAGE);
                    ai_easy.setSelected(true);
                    ai_hard.setSelected(false);
                    ai_level[0] = 1;
                }
                catch (Exception e){
                    System.err.println("Load Bot failed");
                    System.err.println(e);
                    JOptionPane.showMessageDialog(frame, "Error loading Bot.", "Error", JOptionPane.ERROR_MESSAGE);
                    ai_easy.setSelected(true);
                    ai_hard.setSelected(false);
                    ai_level[0] = 1;
                }


            }
        };
        ai_hard.setSelected(false);
        ai_hard.setSize(125, 65);
        ai_hard.getText().setText("custom");
        ai_hard.getText().setColor(Color.WHITE);
        ai_hard.setColor(Color.WHITE);
        ai_hard.setPosition(265 - 500, 355);
        addActor(ai_hard);

        redBlock = new MyColorBlock(game.manager) {
            @Override
            public void myClick() {
                super.myClick();
                redBlock.setSelected(true);
                blueBlock.setSelected(false);
                greenBlock.setSelected(false);
                yellowBlock.setSelected(false);
                player_team[0] = 1;
            }
        };
        redBlock.setSize(65, 65);
        redBlock.setPosition(105 - 500, 225);
        redBlock.setColor(new Color(0.8f, 0, 0, 1));
        redBlock.setSelected(true);
        addActor(redBlock);

        greenBlock = new MyColorBlock(game.manager) {
            @Override
            public void myClick() {
                super.myClick();
                redBlock.setSelected(false);
                blueBlock.setSelected(false);
                greenBlock.setSelected(true);
                yellowBlock.setSelected(false);
                player_team[0] = 2;
            }
        };
        greenBlock.setSize(65, 65);
        greenBlock.setPosition(180 - 500, 225);
        greenBlock.setColor(new Color(0, 0.8f, 0, 1));
        greenBlock.setSelected(false);
        addActor(greenBlock);

        blueBlock = new MyColorBlock(game.manager) {
            @Override
            public void myClick() {
                super.myClick();
                redBlock.setSelected(false);
                blueBlock.setSelected(true);
                greenBlock.setSelected(false);
                yellowBlock.setSelected(false);
                player_team[0] = 3;
            }
        };
        blueBlock.setSize(65, 65);
        blueBlock.setPosition(255 - 500, 225);
        blueBlock.setColor(new Color(0, 0, 0.8f, 1));
        blueBlock.setSelected(false);
        addActor(blueBlock);

        yellowBlock = new MyColorBlock(game.manager) {
            @Override
            public void myClick() {
                super.myClick();
                redBlock.setSelected(false);
                blueBlock.setSelected(false);
                greenBlock.setSelected(false);
                yellowBlock.setSelected(true);
                player_team[0] = 4;
            }
        };
        yellowBlock.setSize(65, 65);
        yellowBlock.setPosition(330 - 500, 225);
        yellowBlock.setColor(new Color(0.8f, 0.8f, 0, 1));
        yellowBlock.setSelected(false);
        addActor(yellowBlock);

        cover = new PrimitiveSqaure(0);
        cover.setSize(1366, 768);
        cover.setPosition(-1366, 0);
        cover.setColor(new Color(0, 0, 0, 1));
        addActor(cover);


        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        try {
            videoPlayer.play(Gdx.files.internal("video.ogv"));
        } catch (FileNotFoundException ignored) {

        }
        videoPlayer.resize(1366, 768);


    }

    @Override
    public void draw() {
        if (!videoPlayer.render()) {
            videoPlayer = VideoPlayerCreator.createVideoPlayer();
            try {
                videoPlayer.play(Gdx.files.internal("video.ogv"));
            } catch (FileNotFoundException ignored) {

            }
            videoPlayer.resize(1366, 768);
        }
        super.draw();

    }

    private void inital() {

        startClear();
        clearCredit();

        SizeToAction sa = new SizeToAction();
        sa.setSize(500, Gdx.graphics.getHeight());
        sa.setDuration(1);
        sa.setInterpolation(Interpolation.pow3);

        MoveToAction ma = new MoveToAction();
        ma.setDuration(1);
        ma.setInterpolation(Interpolation.pow3);
        ma.setPosition(0, 0);

        ColorAction col = new ColorAction();
        col.setDuration(1);
        col.setInterpolation(Interpolation.pow3);
        col.setEndColor(new Color(0, 0, 0, 0.6f));

        ParallelAction action = new ParallelAction();
        action.addAction(sa);
        action.addAction(ma);
        action.addAction(col);

        titleBack.addAction(action);

        title.changePosition(250, 650, 1);
        startButton.changePosition(0, 450, 1.2f);
        creditButton.changePosition(0, 325, 1.5f);
        exitButton.changePosition(0, 50, 2f);

    }

    private void toCredit() {
        clearMainMenu();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                creditBackButton.changePosition(0, 50, 2);
                creditText.changePosition(250, 650, 1);
                name1.changePosition(100, 550, 1.25f);
                name2.changePosition(100, 450, 1.5f);
                name3.changePosition(100, 350, 1.75f);
                school.changePosition(250, 250, 2);
            }
        }, 1);
    }

    private void clearMainMenu() {
        title.changePosition(-250, 650, 1);
        startButton.changePosition(-750, 450, 1);
        creditButton.changePosition(-750, 325, 1);
        exitButton.changePosition(-750, 50, 1);
    }

    private void clearCredit() {
        creditBackButton.changePosition(-750, 50, 1);
        creditText.changePosition(-250, 650, 1);
        name1.changePosition(-400, 550, 1);
        name2.changePosition(-400, 450, 1);
        name3.changePosition(-400, 350, 1);
        school.changePosition(-250, 250, 1);
    }

    private void toStart() {
        clearMainMenu();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                gameText.changePosition(250, 650, 1);
                oppoentText.changePosition(250, 575, 1.2f);
                ai_1.changePosition(120, 480, 1.3f);
                ai_2.changePosition(220, 480, 1.3f);
                ai_3.changePosition(320, 480, 1.3f);
                diffiText.changePosition(250, 450, 1.4f);
                ai_easy.changePosition(115, 355, 1.5f);
                ai_hard.changePosition(265, 355, 1.5f);
                colorText.changePosition(250, 325, 1.6f);
                redBlock.changePosition(105, 1.7f);
                greenBlock.changePosition(180, 1.7f);
                blueBlock.changePosition(255, 1.7f);
                yellowBlock.changePosition(330, 1.7f);
                gameStart.changePosition(0, 130, 1.8f);
                gameBack.changePosition(0, 50, 2);

            }
        }, 1);
    }

    private void startClear() {
        gameText.changePosition(-250, 650, 1);
        oppoentText.changePosition(-250, 575, 1);
        diffiText.changePosition(-250, 450, 1);
        colorText.changePosition(-250, 325, 1);
        gameBack.changePosition(-750, 50, 1);
        gameStart.changePosition(-750, 130, 1);
        ai_1.changePosition(120 - 500, 480, 1);
        ai_2.changePosition(220 - 500, 480, 1);
        ai_3.changePosition(320 - 500, 480, 1);
        ai_easy.changePosition(115 - 500, 355, 1);
        ai_hard.changePosition(265 - 500, 355, 1);
        redBlock.changePosition(105 - 500, 1);
        greenBlock.changePosition(180 - 500, 1);
        blueBlock.changePosition(255 - 500, 1);
        yellowBlock.changePosition(330 - 500, 1);
    }

    @Override
    public void dispose() {
        super.dispose();
        videoPlayer.dispose();
        themeSong.dispose();
        clear();
    }
}
