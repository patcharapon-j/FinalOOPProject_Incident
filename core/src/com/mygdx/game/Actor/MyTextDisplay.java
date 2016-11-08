package com.mygdx.game.Actor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class MyTextDisplay extends Actor{

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font;
    private AssetManager manager;
    private int center;
    private String text;

    public MyTextDisplay(String path, int size, int c) {
        super();
        center = c;
        text = "";
        generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(center == 1){
            GlyphLayout layout = new GlyphLayout(font, text);
            font.setColor(getColor());
            font.draw(batch, text, getX() - layout.width/2, getY() + layout.height/2);
        }
        else if(center == 2){
            GlyphLayout layout = new GlyphLayout(font, text);
            font.setColor(getColor());
            font.draw(batch, text, getX() - layout.width, getY());
        }
        else{
            font.setColor(getColor());
            font.draw(batch, text, getX(), getY());
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public boolean remove() {

        font.dispose();
        generator.dispose();
        return super.remove();
    }



    public void setText(String text) {
        this.text = text;
    }

    public void setAlpha(float alpha){
        setColor(getColor().r, getColor().g, getColor().b, alpha);
    }

    public void fadeIn(Interpolation inter, int duration){
        AlphaAction action = new AlphaAction();
        action.setAlpha(1);
        action.setInterpolation(inter);
        action.setDuration(duration);
        addAction(action);
    }

    public void fadeOut(Interpolation inter, int duration){
        AlphaAction action = new AlphaAction();
        action.setAlpha(0);
        action.setInterpolation(inter);
        action.setDuration(duration);
        addAction(action);
    }

    public void changeColor(Color c, int d){
        ColorAction ca = new ColorAction();
        ca.setEndColor(c);
        ca.setDuration(d);
        ca.setInterpolation(Interpolation.pow3);
        addAction(ca);
    }
    public void changePosition(float x, float y, float d){
        MoveToAction ma = new MoveToAction();
        ma.setPosition(x, y);
        ma.setInterpolation(Interpolation.pow3);
        ma.setDuration(d);
        addAction(ma);
    }

    public void setCenter(int center) {
        this.center = center;
    }
}
