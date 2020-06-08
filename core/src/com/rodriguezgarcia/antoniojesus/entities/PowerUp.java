package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

public class PowerUp {

    public Vector2 position;
    ShapeRenderer shapeRenderer;
    Level level;

    public PowerUp(Vector2 position, Level level) {
        this.position = position;
        this.level = level;
        shapeRenderer=new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        final TextureRegion region = Assets.instance.powerUpAssets.powerup;
        Utils.drawTextureRegion(batch, region, position, Constants.POWERUP_CENTER);

        batch.end();

        shapeRenderer.setProjectionMatrix(level.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(position.x,position.y, 40,30);
        shapeRenderer.end();

        batch.begin();
    }

}
