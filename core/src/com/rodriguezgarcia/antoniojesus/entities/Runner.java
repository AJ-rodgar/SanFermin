package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.utils.Constants;

public class Runner {

    private Level level;

    private Vector2 spawnPosition;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 lastFramePosition;

    public Runner(Vector2 spawnPosition, Level level){
        this.spawnPosition = spawnPosition;
        this.level = level;
        position = new Vector2();
        velocity = new Vector2();
        lastFramePosition = new Vector2();
        init();
    }

    public void init(){
        position.set(spawnPosition);
        velocity.x = Constants.RUNNER_MOVE_SPEED;
    }

    public void update(float delta){
        lastFramePosition.set(position);

        position.mulAdd(velocity, delta);

        Rectangle runnerBounds = new Rectangle(
                position.x - Constants.RUNNER_STANCE_WIDTH / 2,
                position.y - Constants.RUNNER_EYE_HEIGHT,
                Constants.RUNNER_STANCE_WIDTH,
                Constants.RUNNER_HEIGHT
        );

        Bull bull = level.getBull();

        Rectangle bullBounds = new Rectangle(
        );
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
        );

        return runnerBounds.overlaps(bullBounds);
    }
}
