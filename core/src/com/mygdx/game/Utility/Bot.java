package com.mygdx.game.Utility;


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
        while (active){

        }
    }

    public boolean isActive() {
        return active;
    }

    public void Attack(NodeActor node, NodeActor target){
        //set "target" as an attack target of "node"
        node.setTarget(target);
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

    public void setActive(boolean active) {
        this.active = active;
    }
}
