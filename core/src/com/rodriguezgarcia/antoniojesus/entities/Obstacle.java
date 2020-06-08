package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.utils.Assets;

public class Obstacle {

    public final float top;
    public final float bottom;
    public final float left;
    public final float right;

    ShapeRenderer shapeRenderer;
    Level level;

    public Obstacle(float left, float top, float width, float height, Level level) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.level = level;
        shapeRenderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        final float width = right - left;
        final float height = top - bottom;
        Assets.instance.obstacleAssets.obstacleNinePatch.draw(batch, left, bottom, width, height);

        batch.end();

        shapeRenderer.setProjectionMatrix(level.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(left + 6,
                bottom,
                (right - left) * 2/3,
                top - bottom);
        shapeRenderer.end();

        batch.begin();
    }
}
