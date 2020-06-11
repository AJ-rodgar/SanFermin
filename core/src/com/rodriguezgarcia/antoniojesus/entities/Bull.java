package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Enums;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

public class Bull {

    private Vector2 spawnPosition;
    private Vector2 position;
    private Vector2 velocity;
    public TextureRegion region;
    private Animation animation;
    private float runtime;

    Level level;
    //ShapeRenderer shapeRenderer;

    public Bull(Vector2 spawnPosition, Level level){
        this.spawnPosition = spawnPosition;
        position = new Vector2();
        velocity = new Vector2();
        init();
        //shapeRenderer = new ShapeRenderer();
        this.level = level;
    }

    public void init(){
        position.set(spawnPosition);
        velocity.x = Constants.BULL_MOVE_SPEED;
        animation = Assets.instance.bullAssets.walkingRightAnimation;

    }

    public void update(float delta) {
        position.mulAdd(velocity, delta);
        runtime += delta * Constants.BULL_MOVE_SPEED;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void render(SpriteBatch batch) {
        region = (TextureRegion) animation.getKeyFrame(runtime,true);
        run();


        Utils.drawTextureRegion(
                batch,
                region,
                position,
                Constants.BULL_EYE_POSITION);


        /*batch.end();

        shapeRenderer.setProjectionMatrix(level.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(position.x + Constants.BULL_STANCE_WIDTH ,position.y + 10, Constants.BULL_STANCE_WIDTH,Constants.BULL_HEIGHT);
        shapeRenderer.end();

        batch.begin();*/
    }

    private void run() {
        velocity.x = Constants.BULL_MOVE_SPEED;
    }
}
