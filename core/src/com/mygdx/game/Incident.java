package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Screen.MainMenuScreen;

public class Incident extends Game {

	public SpriteBatch batch;
	public AssetManager manager;
    private boolean isGameStart;
    public int player_color;
    public int ai_count;
    public int ai_diff;
    @Override
	public void create () {
		batch = new SpriteBatch();
	    manager = new AssetManager();
		manager.load("theme.mp3", Music.class);
		manager.load("playing_scene.ogg", Music.class);
		manager.load("Sprite/correct.png", Texture.class);
		manager.load("Sprite/GridBg.png", Texture.class);
		manager.load("Sprite/blank.png", Texture.class);
		manager.load("Sprite/pauseBtn.png", Texture.class);
		manager.load("Sprite/android.png", Texture.class);
        manager.load("Sprite/datacenter.png", Texture.class);
        manager.load("Sprite/shield.png", Texture.class);
        manager.load("Sprite/virus.png", Texture.class);
        manager.load("Sprite/DDos.png", Texture.class);
        manager.load("Sprite/AntiVirus.png", Texture.class);
        manager.load("Sprite/miner.png", Texture.class);
		manager.load("Sprite/MainFrame.png", Texture.class);
		manager.load("Sprite/adkUpgrade.png", Texture.class);
		manager.load("Sprite/adkUpIcon.png", Texture.class);
		manager.load("Sprite/adkUpgrade2.png", Texture.class);
		manager.load("Sprite/adkUpIcon2.png", Texture.class);
		manager.load("Sprite/mechineUpgrade.png", Texture.class);
		manager.load("Sprite/mechUpIcon.png", Texture.class);
		manager.load("Sprite/creditUpgrade.png", Texture.class);
		manager.load("Sprite/credit.png", Texture.class);

        isGameStart = true;

	}

	@Override
	public void render () {
		super.render();
        while (manager.update() && isGameStart){
            isGameStart = false;
            //this.setScreen(new GameScreen(this, 1, 1, 1));
			this.setScreen(new MainMenuScreen(this));
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        manager.dispose();
	}


}
