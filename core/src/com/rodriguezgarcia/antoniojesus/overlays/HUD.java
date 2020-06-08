package com.rodriguezgarcia.antoniojesus.overlays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.ChaseCam;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

public class HUD {

    public final Viewport viewport;
    final BitmapFont font;

    public HUD() {

        this.viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);

        font = new BitmapFont();
        font.getData().setScale(1);

    }

    public void render(SpriteBatch batch, float distance, float bull) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (distance < 0) {
            distance = 0;
        }
        final String hudString =

                Constants.HUD_DISTANCE_LABEL + distance + "\n" +
                        Constants.HUD_BULL_LABEL + bull;

        font.draw(batch, hudString, Constants.HUD_MARGIN, viewport.getWorldHeight() - Constants.HUD_MARGIN);

        batch.end();

    }

    public Viewport getViewport() {
        return viewport;
    }
}
