package com.mygdx.game.Stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.mygdx.game.Actor.PrimitiveSqaure;

public class TransitionStage extends Stage {

    public TransitionStage() {
        super();

        PrimitiveSqaure cover = new PrimitiveSqaure(0);
        cover.setSize(1366, 768);
        cover.setColor(Color.BLACK);
        cover.setPosition(0, 0);
        addActor(cover);

        AlphaAction action = new AlphaAction();
        action.setAlpha(0);
        action.setDuration(1);
        action.setInterpolation(Interpolation.pow3Out);
        cover.addAction(action);
    }

    @Override
    public void dispose() {
        super.dispose();
        clear();
    }
}
