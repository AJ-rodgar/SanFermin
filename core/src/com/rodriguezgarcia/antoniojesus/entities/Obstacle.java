package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rodriguezgarcia.antoniojesus.utils.Assets;

public class Obstacle {

    public final float top;
    public final float bottom;
    public final float left;
    public final float right;

    public Obstacle(float left, float top, float width, float height) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
    }

    public void render(SpriteBatch batch) {
        final float width = right - left;
        final float height = top - bottom;
        Assets.instance.obstacleAssets.obstacleNinePatch.draw(batch, left - 1, bottom - 1, width +2, height +2);

    }
}
