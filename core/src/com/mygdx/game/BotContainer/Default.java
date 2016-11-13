package com.mygdx.game.BotContainer;

        import com.badlogic.gdx.utils.TimeUtils;
        import com.mygdx.game.Actor.NodeActor;
        import com.mygdx.game.Utility.Bot;
        import com.mygdx.game.Utility.PlayerData;

        import java.sql.Time;
        import java.util.ArrayList;
        import java.util.Random;

public class Default extends Bot{

    private Random rd = new Random();
    private boolean waitingForUpgrade = false;
    private int upgradeTarger = rd.nextInt(3) + 1;
    private PlayerData playerData;
    private long upgradeTime = TimeUtils.millis() + (rd.nextInt(60) + 60)*1000;

    public Default(int team, ArrayList<PlayerData> allData, ArrayList<NodeActor> allNode) {
        super(team, allData, allNode);
        playerData = allData.get(team);
    }

    @Override
    public void calculation() {

        if(waitingForUpgrade){
            switch (upgradeTarger){
                case 1:
                    if(playerData.getMoney() >= playerData.getAttackPrice()){
                        upgrade(1);
                        waitingForUpgrade = false;
                        upgradeTarger = rd.nextInt(3) + 1;
                        upgradeTime = TimeUtils.millis() + (rd.nextInt(60) + 60)*1000;
                    }
                    break;
                case 2:
                    if(playerData.getMoney() >= playerData.getHpPrice()){
                        upgrade(2);
                        waitingForUpgrade = false;
                        upgradeTarger = rd.nextInt(3) + 1;
                        upgradeTime = TimeUtils.millis() + (rd.nextInt(60) + 60)*1000;
                    }
                    break;
                case 3:
                    if(playerData.getMoney() >= playerData.getRangePrince()){
                        upgrade(3);
                        waitingForUpgrade = false;
                        upgradeTarger = rd.nextInt(3) + 1;
                        upgradeTime = TimeUtils.millis() + (rd.nextInt(60) + 60)*1000;
                    }
                    break;
            }
        }else{
            if(upgrade(rd.nextInt(3) + 1)){
                waitingForUpgrade = false;
                upgradeTarger = rd.nextInt(3) + 1;
                upgradeTime = TimeUtils.millis() + (rd.nextInt(60) + 60)*1000;
            }
            if(upgradeTime < TimeUtils.millis()){
                waitingForUpgrade = true;
                upgradeTarger = rd.nextInt(3) + 1;
            }
            for(NodeActor node: allNode){
                if(node.getTeam() == team){
                    if((node.getType() == 1 || node.getType() == 2)){
                        for(NodeActor n2: allNode){
                            if(isAttackAble(node, n2) && node.getTarget() == null){
                                if(n2.getType() == 4 || n2.getType() == 1){
                                    build(node, 2);
                                }
                                attack(node, n2);
                            }
                        }
                    }
                    else if(node.getType() == 0){
                        if(playerData.getMoney() >= 30){
                            if(playerData.getNodeCount() >= 6){
                                int x = rd.nextInt(5);
                                switch (x){
                                    case 0:
                                        build(node, 2);
                                        break;
                                    case 1:
                                        build(node, 2);
                                        break;
                                    case 2:
                                        build(node, 3);
                                        break;
                                    case 3:
                                        build(node, 3);
                                        break;
                                    case 4:
                                        build(node, 4);
                                }
                            }
                            else{
                                int x = rd.nextInt(2);
                                switch (x){
                                    case 0:
                                        build(node, 2);
                                        break;
                                    case 1:
                                        build(node, 3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
