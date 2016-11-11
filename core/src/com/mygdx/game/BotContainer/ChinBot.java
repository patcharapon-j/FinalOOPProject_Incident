package com.mygdx.game.BotContainer;

import com.mygdx.game.Actor.NodeActor;
import com.mygdx.game.Utility.Bot;
import com.mygdx.game.Utility.PlayerData;

import java.util.ArrayList;

/**
 * Created by boon8 on 11/11/2559.
 */
public class ChinBot extends Bot{

    public ChinBot() {

    }

    public ChinBot(int team, ArrayList<PlayerData> allData, ArrayList<NodeActor> allNode) {
        super(team, allData, allNode);
    }

    @Override
    public void calculation() {

        for(NodeActor node: allNode){
            if(node.getTeam() == team){
                if(node.getType() == 1 || node.getType() == 2){
                    for(NodeActor n2: allNode){
                        if(n2.getTeam() == team && isAttackAble(node, n2)){
                            if(node.getTarget() == null){
                                attack(node, n2);
                            }
                        }
                    }
                }
            }
        }

    }
}
