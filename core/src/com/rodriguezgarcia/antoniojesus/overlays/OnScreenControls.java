package com.rodriguezgarcia.antoniojesus.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodriguezgarcia.antoniojesus.entities.Runner;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

public class OnScreenControls extends InputAdapter {

    public final Viewport viewport;
    public Runner runner;
    private int jumpPointer;

    private Vector2 jumpCenter = new Vector2();

    public OnScreenControls() {
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);

        recalculateButtonPositions();
    }

    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (!Gdx.input.isTouched(jumpPointer)) {

            runner.jumpButtonPressed = false;
            jumpPointer = 0;

        }

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.jump,
                jumpCenter,
                Constants.BUTTON_CENTER
        );

        batch.end();
    }

    public void recalculateButtonPositions() {

        jumpCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 3 / 4,
                Constants.BUTTON_RADIUS
        );

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (viewportPosition.dst(jumpCenter) < Constants.BUTTON_RADIUS) {

            jumpPointer = pointer;
            runner.jumpButtonPressed = true;

        }

        return super.touchDown(screenX, screenY, pointer, button);

    }
}
