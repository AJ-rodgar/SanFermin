package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.SanFerminGame;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Enums;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

public class Runner {

    private Level level;

    public boolean jumpButtonPressed;

    //ShapeRenderer shapeRenderer;

    private Vector2 spawnPosition;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 lastFramePosition;
    private Animation animation;
    private float runtime;

    private Enums.JumpState jumpState;
    private long jumpStartTime;
    public TextureRegion region;

    public Runner(Vector2 spawnPosition, Level level){
        this.spawnPosition = spawnPosition;
        this.level = level;
        position = new Vector2();
        velocity = new Vector2();
        lastFramePosition = new Vector2();
        //shapeRenderer = new ShapeRenderer();
        init();
    }

    public void render(SpriteBatch batch) {
        if(jumpState == Enums.JumpState.FALLING){
            region =  Assets.instance.runnerAssets.standing;
        }else if ( jumpState != Enums.JumpState.GROUNDED && jumpState != Enums.JumpState.JUMPING) {
            region = Assets.instance.runnerAssets.jumpingRight;
            startJump();
        }else if(jumpState == Enums.JumpState.GROUNDED){
            region = (TextureRegion) animation.getKeyFrame(runtime,true);

        }


        Utils.drawTextureRegion(
                batch,
                region,
                position,
                Constants.RUNNER_EYE_POSITION);

        /*batch.end();

        shapeRenderer.setProjectionMatrix(level.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(position.x + Constants.RUNNER_STANCE_WIDTH / 2 ,position.y + 10, Constants.RUNNER_STANCE_WIDTH,Constants.RUNNER_HEIGHT);
        shapeRenderer.end();

        batch.begin();*/

    }

    public void init(){
        position.set(spawnPosition);
        lastFramePosition.set(spawnPosition);
        velocity = new Vector2(Constants.RUNNER_MOVE_SPEED, 0);
        jumpState = Enums.JumpState.FALLING;
        animation = Assets.instance.runnerAssets.walkingRightAnimation;
    }

    public void update(float delta){

        lastFramePosition.set(position);
        velocity.y -= Constants.GRAVITY;
        runtime += delta * Constants.RUNNER_MOVE_SPEED;
        position.mulAdd(velocity, delta);

        if (position.y - Constants.RUNNER_EYE_HEIGHT < 0) {
            jumpState = Enums.JumpState.GROUNDED;
            position.y = Constants.RUNNER_EYE_HEIGHT;
            velocity.y = 0;
        }

        Rectangle runnerBounds = new Rectangle(
                position.x + Constants.RUNNER_STANCE_WIDTH / 2 ,
                position.y + 10,
                Constants.RUNNER_STANCE_WIDTH,
                Constants.RUNNER_HEIGHT
        );

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || jumpButtonPressed){
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }
        }

        DelayedRemovalArray<Obstacle> obstacles = level.getObstacles();

        obstacles.begin();
        for (int i = 0; i < obstacles.size; i++) {

            Obstacle obstacle = obstacles.get(i);
            Rectangle obstacleBounds = new Rectangle(
                    obstacle.left + 6,
                    obstacle.bottom,
                    (obstacle.right - obstacle.left) * 2/3,
                    obstacle.top - obstacle.bottom
            );

            if (runnerBounds.overlaps(obstacleBounds)) {
                level.getRunner().obstacle();
                Sound obstacleSound = Gdx.audio.newSound(Gdx.files.internal("sounds/obstacle.mp3"));
                obstacleSound.play();
                obstacles.removeIndex(i);
            }
        }
        obstacles.end();

        DelayedRemovalArray<PowerUp> powerups = level.getPowerUps();

        powerups.begin();
        for (int i = 0; i < powerups.size; i++) {

            PowerUp powerup = powerups.get(i);
            Rectangle powerupBounds = new Rectangle(
                    powerup.position.x,
                    powerup.position.y,
                    30,
                    25
            );

            if (runnerBounds.overlaps(powerupBounds)) {
                level.getRunner().powerUp();
                Sound powerUpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.mp3"));
                powerUpSound.play();
                powerups.removeIndex(i);
            }
        }
        powerups.end();
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

    public boolean isCatched(){

        Rectangle runnerBounds = new Rectangle(
                position.x + Constants.RUNNER_STANCE_WIDTH / 2 ,
                position.y + 10,
                Constants.RUNNER_STANCE_WIDTH,
                Constants.RUNNER_HEIGHT
        );

        Bull bull = level.getBull();

        Rectangle bullBounds = new Rectangle(
                bull.getPosition().x + Constants.BULL_STANCE_WIDTH,
                bull.getPosition().y + 10,
                Constants.BULL_STANCE_WIDTH,
                Constants.BULL_HEIGHT
        );

        if (!level.gameover && runnerBounds.overlaps(bullBounds)) {
            Sound loseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/lose.mp3"));
            loseSound.play();
        }

        return runnerBounds.overlaps(bullBounds);
    }

    private void startJump() {
        jumpState = Enums.JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump() {
        if (jumpState == Enums.JumpState.JUMPING) {
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);

            if (jumpDuration < Constants.MAX_JUMP_DURATION) {
                velocity.y = Constants.JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        if (jumpState == Enums.JumpState.JUMPING) {
            jumpState = Enums.JumpState.FALLING;
        }
    }

    private void obstacle(){
        velocity.x -= 5;
    }

    private void powerUp(){
        velocity.x += 2.5;
    }

    public boolean comeBullring() {
        Rectangle runnerBounds = new Rectangle(
                position.x + Constants.RUNNER_STANCE_WIDTH / 2 ,
                position.y + 10,
                Constants.RUNNER_STANCE_WIDTH,
                Constants.RUNNER_HEIGHT
        );

        Bullring bullring = level.getBullring();

        Rectangle bullBounds = new Rectangle(
                bullring.position.x,
                bullring.position.y,
                Constants.BULLRING_RADIUS,
                Constants.BULLRING_RADIUS
        );

        if (!level.victory && runnerBounds.overlaps(bullBounds)) {
            Sound winSound = Gdx.audio.newSound(Gdx.files.internal("sounds/win.mp3"));
            winSound.play();
        }

        return runnerBounds.overlaps(bullBounds);
    }
}
