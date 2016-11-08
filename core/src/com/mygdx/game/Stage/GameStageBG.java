package com.mygdx.game.Stage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Actor.MySpriteActor;
import com.mygdx.game.Incident;

public class GameStageBG extends Stage{

    private Incident game;
    private MySpriteActor gridBG;

    public GameStageBG(Incident g) {
        super();
        game = g;
        gridBG = new MySpriteActor("Sprite/GridBg.png", game);
        gridBG.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gridBG.setPosition(0,0);
        gridBG.setScale(1.25f);
        addActor(gridBG);
        gridBG.setalpha(0.20f);
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
}
