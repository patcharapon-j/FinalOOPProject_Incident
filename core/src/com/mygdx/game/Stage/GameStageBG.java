package com.mygdx.game.Stage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Actor.MySpriteActor;
import com.mygdx.game.Incident;

public class GameStageBG extends Stage {

    public GameStageBG(Incident g) {
        super();
        MySpriteActor gridBG = new MySpriteActor("Sprite/GridBg.png", g);
        gridBG.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gridBG.setPosition(0, -50);
        gridBG.setScale(1.20f);
        addActor(gridBG);
        gridBG.setalpha();
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
        clear();
    }
}
