package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Screen.MainMenuScreen;

public class Incident extends Game {

    private SpriteBatch batch;
    public AssetManager manager;
    public int player_color;
    public int ai_count;
    public int ai_diff;
    private boolean isGameStart;

    @Override
    public void create() {

        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("theme.mp3", Music.class);
        manager.load("playing_scene.ogg", Music.class);

        manager.load("clicked.mp3", Music.class);
        manager.load("mouseOver.mp3", Music.class);
        manager.load("invalid.mp3", Music.class);
        manager.load("240remain.mp3", Music.class);
        manager.load("Defeat.mp3", Music.class);
        manager.load("victory.mp3", Music.class);

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

        manager.load("Effect/sfx_buynode_click.ogg", Sound.class);
        manager.load("Effect/sfx_buynode_release.ogg", Sound.class);
        manager.load("Effect/sfx_countdown.ogg", Sound.class);
        manager.load("Effect/sfx_datacenter_capture.ogg", Sound.class);
        manager.load("Effect/sfx_datacenter_loss.ogg", Sound.class);
        manager.load("Effect/sfx_game_begin_1.ogg", Sound.class);
        manager.load("Effect/sfx_game_begin_2.ogg", Sound.class);
        manager.load("Effect/sfx_game_begin_3.ogg", Sound.class);
        manager.load("Effect/sfx_game_begin_4.ogg", Sound.class);
        manager.load("Effect/sfx_mainframe_offline.ogg", Sound.class);
        manager.load("Effect/sfx_mainframe_under_atk.ogg", Sound.class);
        manager.load("Effect/sfx_node_release.ogg", Sound.class);
        manager.load("Effect/sfx_upgrade_click.ogg", Sound.class);

        manager.load("Speech/speech_datacenter_capture.mp3", Sound.class);
        manager.load("Speech/speech_datacenter_loss.mp3", Sound.class);
        manager.load("Speech/speech_download_complete.mp3", Sound.class);
        manager.load("Speech/speech_enemy_capture_datacenter.mp3", Sound.class);
        manager.load("Speech/speech_enemy_offline.mp3", Sound.class);
        manager.load("Speech/speech_insufficient_credits.mp3", Sound.class);
        manager.load("Speech/speech_system_online.mp3", Sound.class);
        manager.load("Speech/speech_upgraded.ogg", Sound.class);
        manager.load("Speech/speech_victory.mp3", Sound.class);
        manager.load("Speech/speech_defeated.mp3", Sound.class);
        manager.load("Speech/speech_you_are_under_attack.ogg", Sound.class);


        isGameStart = true;

    }

    @Override
    public void render() {
        super.render();
        while (manager.update() && isGameStart) {
            isGameStart = false;
            this.setScreen(new MainMenuScreen(this));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        manager.dispose();
    }


}
