package com.mygdx.game.Stage;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Actor.BuyNode;
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
    private BuyNode selectedBuy;
    private GameScreen gameScreen;
    private CircleDynamic cir;
    private int count;
    private ArrayList<NodeActor> allNode;
    private BuyNode buy1;
    private BuyNode buy2;
    private BuyNode buy3;

    public NodeStage(Incident g, ArrayList<PlayerData> datas, GameScreen ga) {
        super();
        game = g;
        allData = datas;
        selected = null;
        gameScreen = ga;
        cir = new CircleDynamic();
        gameScreen.getGameStageBG().addActor(cir);
        count = gameScreen.getAi_count();
        allNode = new ArrayList<NodeActor>();
        //count = 3;

        int[] number_node = {4, 5, 6, 7, 6, 5, 4};
        int[] space_ratio = {3, 2, 1, 0, 1, 2, 3};

        for(int line=0; line < number_node.length; line++) {

            for(int node=0; node<number_node[line]; node++){

                switch (count) {
                    case 1: {
                        if(line == 3 && node == 0){
                            NodeActor temp = new NodeActor(game.manager, 1, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 77 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if((line == 3 && node == 6)){
                            NodeActor temp = new NodeActor(game.manager, 2, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 77 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if(line == 3 && node == 3) {
                            NodeActor temp1 = new NodeActor(game.manager, 0, 5, allData, gameScreen, allNode);
                            temp1.setColor(Color.WHITE);
                            temp1.setPosition(286 + node * 120 + (60* space_ratio[line]), 70 + line * 95);
                            temp1.setScale(0.5f);
                            addActor(temp1);
                            allNode.add(temp1);
                        } else {
                            NodeActor temp1 = new NodeActor(game.manager, 0, 0, allData, gameScreen, allNode);
                            temp1.setColor(Color.WHITE);
                            temp1.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp1.setScale(0.3f);
                            addActor(temp1);
                            allNode.add(temp1);
                        }
                        break;
                    }
                    case 2: {
                        if(line == 3 && node == 0){
                            NodeActor temp = new NodeActor(game.manager, 1, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 77 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if((line == 6 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 2, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 77 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if((line == 0 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 3, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(2)]);
                            temp.setPosition(300 + node * 120 + (60* space_ratio[line]), 77 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if(line == 3 && node == 3) {
                            NodeActor temp1 = new NodeActor(game.manager, 0, 5, allData, gameScreen, allNode);
                            temp1.setColor(Color.WHITE);
                            temp1.setPosition(286 + node * 120 + (60* space_ratio[line]), 70 + line * 95);
                            temp1.setScale(0.5f);
                            addActor(temp1);
                            allNode.add(temp1);
                        } else {
                            NodeActor temp1 = new NodeActor(game.manager, 0, 0, allData, gameScreen, allNode);
                            temp1.setColor(Color.WHITE);
                            temp1.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp1.setScale(0.3f);
                            addActor(temp1);
                            allNode.add(temp1);
                        }
                        break;
                    }
                    case 3: {
                        if(line == 0 && node == 0){
                            NodeActor temp = new NodeActor(game.manager, 1, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
                            temp.setPosition(296 + node * 120 + (60* space_ratio[line]), 79 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if((line == 6 && node == 0)){
                            NodeActor temp = new NodeActor(game.manager, 2, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(1)]);
                            temp.setPosition(296 + node * 120 + (60* space_ratio[line]), 79 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if((line == 6 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 3, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(2)]);
                            temp.setPosition(296 + node * 120 + (60* space_ratio[line]), 79 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if((line == 0 && node == 3)){
                            NodeActor temp = new NodeActor(game.manager, 4, 1, allData, gameScreen, allNode);
                            temp.setColor(GameScreen.mainColor[GameScreen.userColor.get(3)]);
                            temp.setPosition(296 + node * 120 + (60* space_ratio[line]), 79 + line * 95);
                            temp.setScale(0.4f);
                            addActor(temp);
                            allNode.add(temp);
                        } else if(line == 3 && node == 3) {
                            NodeActor temp1 = new NodeActor(game.manager, 0, 5, allData, gameScreen, allNode);
                            temp1.setColor(Color.WHITE);
                            temp1.setPosition(286 + node * 120 + (60* space_ratio[line]), 70 + line * 95);
                            temp1.setScale(0.5f);
                            addActor(temp1);
                            allNode.add(temp1);
                        } else {
                            NodeActor temp1 = new NodeActor(game.manager, 0, 0, allData, gameScreen, allNode);
                            temp1.setColor(Color.WHITE);
                            temp1.setPosition(300 + node * 120 + (60* space_ratio[line]), 85 + line * 95);
                            temp1.setScale(0.3f);
                            addActor(temp1);
                            allNode.add(temp1);
                        }
                        break;
                    }

                }

            }

        }

        buy1 = new BuyNode(game.manager, 2, 30, 1280, 150);
        buy1.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        buy1.setSize(50, 50);
        addActor(buy1);

        buy2 = new BuyNode(game.manager, 3, 30, 1280, 250);
        buy2.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        buy2.setSize(50, 50);
        addActor(buy2);

        buy3 = new BuyNode(game.manager, 4, 30, 1280, 350);
        buy3.setColor(GameScreen.mainColor[GameScreen.userColor.get(0)]);
        buy3.setSize(50, 50);
        addActor(buy3);

    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act() {
        super.act();
        if(selectedBuy != null){
            selectedBuy.setPosition(Gdx.input.getX() - selectedBuy.getWidth()/2,
                    Gdx.graphics.getHeight() - Gdx.input.getY() - selectedBuy.getHeight()/2);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
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
                    else if(temp.getClass() == BuyNode.class){
                        BuyNode node = (BuyNode) temp;
                        selectedBuy = node;
                    }
                }
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
       if(button == Input.Buttons.LEFT){
           Actor temp = hit(screenX, Gdx.graphics.getHeight() - screenY, true);
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
           if(selectedBuy != null){
               if(temp != null){
                    if(temp.getClass() == NodeActor.class){
                        NodeActor node = (NodeActor)temp;
                        if(allData.get(node.getTeam()).getMoney() >= selectedBuy.getCost()){
                            allData.get(node.getTeam()).decreaseMoney(selectedBuy.getCost());
                            node.changeType(selectedBuy.getType());
                        }
                        else{
                            gameScreen.moneyFlash();
                        }
                    }
               }
               selectedBuy.resetPosition();
           }
           selectedBuy = null;
           cir.contract();
           selected = null;
       }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public double calDistance(float x1, float y1, float x2, float y2){
        return Math.pow(Math.pow(x1-x2, 2) +  Math.pow(y1-y2, 2), 0.5);
    }
}
