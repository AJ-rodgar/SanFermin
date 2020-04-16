package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

public class Bullring {

    public final Vector2 position;

    public Bullring(Vector2 position) {
        this.position = position;
    }

    public void render(SpriteBatch batch) {

        final TextureRegion region = (TextureRegion) Assets.instance.bullringAssets.bullring;
        Utils.drawTextureRegion(batch, region, position, Constants.BULLRING_CENTER);
    }
}
