package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodriguezgarcia.antoniojesus.entities.Bull;
import com.rodriguezgarcia.antoniojesus.entities.Bullring;
import com.rodriguezgarcia.antoniojesus.entities.Obstacle;
import com.rodriguezgarcia.antoniojesus.entities.Platform;
import com.rodriguezgarcia.antoniojesus.entities.PowerUp;
import com.rodriguezgarcia.antoniojesus.entities.Runner;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Enums;

public class Level {

    public boolean gameover;
    public boolean victory;

    public Runner runner;
    public Bull bull;
    public Bullring bullring;
    public DelayedRemovalArray<Obstacle> obstacles;
    public DelayedRemovalArray<PowerUp> powerUps;
    public Array<Platform> platforms;

    public Viewport viewport;

    public Level(){
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        platforms = new Array<>();
        obstacles = new DelayedRemovalArray<>();
        powerUps = new DelayedRemovalArray<>();
    }

    public void update(float delta) {
        if (runner.isCatched()) {
            gameover = true;
        } else if (runner.getPosition().dst(bullring.position) < Constants.BULLRING_RADIUS){
            victory = true;
        } else {
            runner.update(delta, platforms);
            bull.update(delta);
        }
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Bull getBull() {
        return bull;
    }

    public void setBull(Bull bull) {
        this.bull = bull;
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        bull.render(batch);

        runner.render(batch);

        for (Platform p : platforms) {
            p.render(batch);
        }

        //Obstacle o = new Obstacle(500, 55, 35,32);
        //o.render(batch);

        for (Obstacle o : obstacles) {
            o.render(batch);
        }

        //PowerUp p = new PowerUp(new Vector2(500, 60));
        //p.render(batch);

        for (PowerUp p : powerUps) {
            p.render(batch);
        }

        bullring.render(batch);
        batch.end();
    }

    public DelayedRemovalArray<Obstacle> getObstacles() {
        return obstacles;
    }

    public DelayedRemovalArray<PowerUp> getPowerUps() {
        return powerUps;
    }

    public Bullring getBullring() {
        return bullring;
    }

    public void setBullring(Bullring bullring) {
        this.bullring = bullring;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Array getPlatforms() {
        return platforms;
    }
}
