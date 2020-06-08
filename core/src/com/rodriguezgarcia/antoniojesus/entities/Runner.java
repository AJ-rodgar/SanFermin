package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        init();
    }

    public void render(SpriteBatch batch) {
        run();
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

    }

    private void run() {
        velocity.x = Constants.RUNNER_MOVE_SPEED;
    }

    public void init(){
        position.set(spawnPosition);
        lastFramePosition.set(spawnPosition);
        velocity.setZero();
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
                position.x - Constants.RUNNER_STANCE_WIDTH,
                position.y - Constants.RUNNER_EYE_HEIGHT,
                Constants.RUNNER_STANCE_WIDTH,
                Constants.RUNNER_HEIGHT
        );

        for (Obstacle obstacle : level.getObstacles()){
            Rectangle obstacleBounds = new Rectangle(
                    obstacle.left - 1,
                    obstacle.bottom-1,
                    obstacle.right-obstacle.left-10,
                    obstacle.top-obstacle.bottom-10
            );

            if (runnerBounds.overlaps(obstacleBounds)){
                velocity.x -= 20;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
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

        DelayedRemovalArray<PowerUp> powerups = level.getPowerUps();

        powerups.begin();
        for (int i = 0; i < powerups.size; i++) {

            PowerUp powerup = powerups.get(i);
            Rectangle powerupBounds = new Rectangle(
                    powerup.position.x - Constants.POWERUP_CENTER.x,
                    powerup.position.y - Constants.POWERUP_CENTER.y,
                    Assets.instance.powerUpAssets.powerup.getRegionWidth(),
                    Assets.instance.powerUpAssets.powerup.getRegionHeight()
            );

            if (runnerBounds.overlaps(powerupBounds)) {
                level.getRunner().setVelocity(new Vector2(level.getRunner().getVelocity().x + 10, level.getRunner().getVelocity().y));
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
                position.x - Constants.RUNNER_STANCE_WIDTH / 2,
                position.y - Constants.RUNNER_EYE_HEIGHT,
                Constants.RUNNER_STANCE_WIDTH,
                Constants.RUNNER_HEIGHT
        );

        Bull bull = level.getBull();

        Rectangle bullBounds = new Rectangle(
                bull.getPosition().x - Constants.ENEMY_COLLISION_RADIUS,
                bull.getPosition().y - Constants.ENEMY_COLLISION_RADIUS,
                2 * Constants.ENEMY_COLLISION_RADIUS,
                2 * Constants.ENEMY_COLLISION_RADIUS
        );

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

    boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastFramePosition.y - Constants.RUNNER_EYE_HEIGHT >= platform.top &&
                position.y - Constants.RUNNER_EYE_HEIGHT < platform.top) {

            float leftFoot = position.x - Constants.RUNNER_STANCE_WIDTH / 2;
            float rightFoot = position.x + Constants.RUNNER_STANCE_WIDTH / 2;

            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);
            straddle = (platform.left > leftFoot && platform.right < rightFoot);
        }
        return leftFootIn || rightFootIn || straddle;
    }
}
