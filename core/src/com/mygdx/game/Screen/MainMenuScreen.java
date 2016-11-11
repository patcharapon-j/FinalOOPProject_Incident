package com.mygdx.game.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Incident;
import com.mygdx.game.Stage.MainMenuStage;

public class MainMenuScreen implements Screen {

    private final MainMenuStage stage;

    public MainMenuScreen(Incident gam) {
        super();
        stage = new MainMenuStage(gam);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        try {
            stage.dispose();
        } catch (Exception ignored) {

        }
    }
}
