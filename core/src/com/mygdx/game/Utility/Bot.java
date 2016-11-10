package com.mygdx.game.Utility;


import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.mygdx.game.Actor.NodeActor;

import java.util.ArrayList;

public class Bot implements Runnable{

    private int team;
    private ArrayList<PlayerData> allData;
    private ArrayList<NodeActor> allNode;
    private boolean active;

    public Bot(int team, ArrayList<PlayerData> allData, ArrayList<NodeActor> allNode) {
        super();
        active = false;
        this.team = team;
        this.allData = allData;
        this.allNode = allNode;
    }
    
    @Override
    public void run() {
        while (true){
            System.out.println(active);
            if(active){
                //Put logic here

            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public boolean Attack(NodeActor node, NodeActor target){
        //set "target" as an attack target of "node"
        if(calDistance(node.getX() + node.getWidth()/2,
                node.getY() + node.getHeight()/2,
                target.getX() + target.getWidth()/2,
                target.getY() + target.getHeight()/2) <= 150){
            node.setTarget(target);
            return  true;
        }
        return false;
    }

    public boolean upgrade(int type){
        // 1 - attack, 2 - defense, 3 - money
       switch (type){
           case 1:
               if(allData.get(team).getMoney() >= allData.get(team).getAttackPrice()){
                   allData.get(team).levelupAttack();
                   return true;
               }
               else{
                   return false;
               }
           case 2:
               if(allData.get(team).getMoney() >= allData.get(team).getAttackPrice()){
                   allData.get(team).levelupHp();
                   return true;
               }
               else{
                   return false;
               }
           case 3:
               if(allData.get(team).getMoney() >= allData.get(team).getAttackPrice()){
                   allData.get(team).levelupRange();
                   return true;
               }
               else{
                   return false;
               }
       }
       return false;
    }

    public boolean build(NodeActor node, int type){
        // 2 - DDos, 3 - Virus, 4 - Antivirus
        switch (type){
            case 2:
                if(allData.get(team).getMoney() >= 30){
                    allData.get(team).decreaseMoney(30);
                    node.changeType(type);
                    return true;
                }
                break;
            case 3:
                if(allData.get(team).getMoney() >= 30){
                    allData.get(team).decreaseMoney(30);
                    node.changeType(type);
                    return true;
                }
                break;
            case 4:
                if(allData.get(team).getMoney() >= 30){
                    allData.get(team).decreaseMoney(30);
                    node.changeType(type);
                    return true;
                }
                break;
        }
        return false;
    }


    public boolean isAttackAble(NodeActor a, NodeActor b){
            //set "target" as an attack target of "node"
            if(calDistance(a.getX() + a.getWidth()/2,
                    a.getY() + a.getHeight()/2,
                    b.getX() + b.getWidth()/2,
                    b.getY() + b.getHeight()/2) <= 150){
                return  true;
            }
            return false;
    }
    public double calDistance(float x1, float y1, float x2, float y2){
        return Math.pow(Math.pow(x1-x2, 2) +  Math.pow(y1-y2, 2), 0.5);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
