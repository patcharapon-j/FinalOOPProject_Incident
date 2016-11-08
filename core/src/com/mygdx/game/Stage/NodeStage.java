package com.mygdx.game.Stage;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Actor.CircleDynamic;
import com.mygdx.game.Actor.NodeActor;
import com.mygdx.game.Incident;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Utility.PlayerData;

import java.util.ArrayList;

public class NodeStage extends Stage{

    private Incident game;
    private ArrayList<PlayerData> allData;
    private NodeActor selected;
    private GameScreen gameScreen;
    private CircleDynamic cir;
    private int count;

    public NodeStage(Incident g, ArrayList<PlayerData> datas, GameScreen ga) {
        super();
        game = g;
        allData = datas;
        selected = null;
        gameScreen = ga;
        cir = new CircleDynamic();
        gameScreen.getGameStageBG().addActor(cir);
        count = gameScreen.getAi_count();
        //count = 1;

        int[] number_node = {4, 5, 6, 7, 6, 5, 4};
        int[] space_ratio = {3, 2, 1, 0, 1, 2, 3};



        int t=0;
        for(int line=0; line < number_node.length; line++) {

            for(int node=0; node<number_node[line]; node++){

                NodeActor temp1 = new NodeActor(game.manager, 0, t, allData, gameScreen);
                temp1.setColor(Color.WHITE);
                temp1.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                temp1.setScale(0.3f);
                addActor(temp1);
                t+=1;

                switch (count) {
                    case 1: {
                        if(line == 3 && node == 0){
                            NodeActor temp = new NodeActor(game.manager, 1, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        } else if((line == 3 && node == 6)){
                            NodeActor temp = new NodeActor(game.manager, 2, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        }
                        break;
                    }
                    case 2: {
                        if(line == 3 && node == 0){
                            NodeActor temp = new NodeActor(game.manager, 1, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        } else if((line == 6 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 2, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        } else if((line == 0 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 2, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(2)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        }
                        break;
                    }
                    case 3: {
                        if(line == 0 && node == 0){
                            NodeActor temp = new NodeActor(game.manager, 1, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        } else if((line == 6 && node == 0)){
                            NodeActor temp = new NodeActor(game.manager, 2, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        } else if((line == 6 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 2, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(2)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        } else if((line == 0 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 2, 0, allData, gameScreen);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(3)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp.setScale(0.3f);
                            addActor(temp);
                        }
                        break;
                    }

                }

            }

        }

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(selected == null){
            Actor temp = hit(screenX, Gdx.graphics.getHeight() - screenY, false);
            if(temp != null){
                if(temp.getClass() == NodeActor.class){
                    NodeActor node = (NodeActor) temp;
                    selected = node;
                    selected.setTarget(null);


                    cir.setSize(0, 0);
                    cir.setColor(new Color(0.4f, 0.4f, 0.4f, 0));
                    cir.setPosition(selected.getX() + selected.getWidth()/2, selected.getY() + selected.getHeight()/2);
                    cir.expand();
                }
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Actor temp = hit(screenX, Gdx.graphics.getHeight() - screenY, false);
        if(temp != null && selected != null){
            if(temp.getClass() == NodeActor.class){
                if(calDistance(selected.getX() + selected.getWidth()/2,
                        selected.getY() + selected.getHeight()/2,
                        temp.getX() + temp.getWidth()/2,
                        temp.getY() + temp.getHeight()/2) <= 150){
                    selected.setTarget((NodeActor) temp);
                }
            }
        }
        cir.contract();
        selected = null;
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public double calDistance(float x1, float y1, float x2, float y2){
        return Math.pow(Math.pow(x1-x2, 2) +  Math.pow(y1-y2, 2), 0.5);
    }
}