package com.mygdx.game.Utility;


import com.mygdx.game.Actor.NodeActor;

import java.util.ArrayList;

abstract class Bot implements Runnable {

    private final int team;
    private final ArrayList<PlayerData> allData;
    private boolean active;

    Bot(int team, ArrayList<PlayerData> allData, ArrayList<NodeActor> allNode) {
        super();
        active = false;
        this.team = team;
        this.allData = allData;
        ArrayList<NodeActor> allNode1 = allNode;
    }

    @Override
    public void run() {
        while (true) {
            if (active) {
                calculation();
            }
        }
    }

    protected abstract void calculation();

    protected boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected boolean attack(NodeActor node, NodeActor target) {
        //set "target" as an attack target of "node"
        if (calDistance(node.getX() + node.getWidth() / 2,
                node.getY() + node.getHeight() / 2,
                target.getX() + target.getWidth() / 2,
                target.getY() + target.getHeight() / 2) <= 150) {
            node.setTarget(target);
            return true;
        }
        return false;
    }

    protected boolean upgrade(int type) {
        // 1 - attack, 2 - defense, 3 - money
        switch (type) {
            case 1:
                if (allData.get(team).getMoney() >= allData.get(team).getAttackPrice()) {
                    allData.get(team).levelupAttack();
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (allData.get(team).getMoney() >= allData.get(team).getAttackPrice()) {
                    allData.get(team).levelupHp();
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (allData.get(team).getMoney() >= allData.get(team).getAttackPrice()) {
                    allData.get(team).levelupRange();
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    protected boolean build(NodeActor node, int type) {
        // 2 - DDos, 3 - Virus, 4 - Antivirus
        if (node.getTeam() == team && node.getType() != 5 && node.getType() != 1) {
            switch (type) {
                case 2:
                    if (allData.get(team).getMoney() >= 30) {
                        allData.get(team).decreaseMoney(30);
                        node.changeType(type);
                        return true;
                    }
                    break;
                case 3:
                    if (allData.get(team).getMoney() >= 30) {
                        allData.get(team).decreaseMoney(30);
                        node.changeType(type);
                        return true;
                    }
                    break;
                case 4:
                    if (allData.get(team).getMoney() >= 30) {
                        allData.get(team).decreaseMoney(30);
                        node.changeType(type);
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    protected boolean isAttackAble(NodeActor a, NodeActor b) {
        //set "target" as an attack target of "node"
        return calDistance(a.getX() + a.getWidth() / 2,
                a.getY() + a.getHeight() / 2,
                b.getX() + b.getWidth() / 2,
                b.getY() + b.getHeight() / 2) <= 150;
    }

    private double calDistance(float x1, float y1, float x2, float y2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 0.5);
    }
}
