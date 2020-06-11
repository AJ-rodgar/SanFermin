package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.rodriguezgarcia.antoniojesus.overlays.GameOverOverlay;
import com.rodriguezgarcia.antoniojesus.overlays.HUD;
import com.rodriguezgarcia.antoniojesus.overlays.OnScreenControls;
import com.rodriguezgarcia.antoniojesus.overlays.VictoryOverlay;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.ChaseCam;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Enums;
import com.rodriguezgarcia.antoniojesus.utils.LevelLoader;

import java.sql.Time;

public class SanFerminScreen extends ScreenAdapter {

    public SpriteBatch batch;
    public ExtendViewport viewport;
    public SanFerminGame game;
    public Enums.Difficulty difficulty;
    public Level level;
    public ChaseCam chaseCam;
    public HUD hud;
    public VictoryOverlay victoryOverlay;
    public GameOverOverlay gameOverOverlay;
    public OnScreenControls onScreenControls;
    private Music bgmusic;
    private boolean end = false;
    private long time;


    public SanFerminScreen(SanFerminGame game, Enums.Difficulty difficulty){
        this.game = game;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);

        batch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        level = LevelLoader.load(difficulty);
        chaseCam = new ChaseCam(viewport.getCamera(), level.getRunner(), level.getBull());
        victoryOverlay = new VictoryOverlay();
        gameOverOverlay = new GameOverOverlay();
        hud = new HUD();
        onScreenControls = new OnScreenControls();
        onScreenControls.runner = level.getRunner();

        if (onMobile()) {
            Gdx.input.setInputProcessor(onScreenControls);
        }

        bgmusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/song.mp3"));
        bgmusic.play();
    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android
                || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }

    @Override
    public void render(float delta) {
        level.update(delta);
        chaseCam.update();
        viewport.apply();

        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.render(batch);

        hud.render(batch, Math.round(level.getBullring().position.x - (level.getRunner().getPosition().x + Constants.RUNNER_STANCE_WIDTH / 2 + Constants.RUNNER_STANCE_WIDTH)), Math.round(level.getRunner().getPosition().x + Constants.RUNNER_STANCE_WIDTH/2 - (level.getBull().getPosition().x + Constants.BULL_STANCE_WIDTH*2)));

        if (onMobile()) {
            onScreenControls.render(batch);
        }

        renderLevelEndOverlays(batch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        hud.getViewport().update(width,height,true);
        victoryOverlay.getViewport().update(width,height,true);
        gameOverOverlay.getViewport().update(width,height,true);
        level.getViewport().update(width,height,true);
        chaseCam.camera = level.viewport.getCamera();
        chaseCam.target = level.getRunner();
        onScreenControls.viewport.update(width, height, true);
        onScreenControls.recalculateButtonPositions();
    }

    private void renderLevelEndOverlays(SpriteBatch batch) {

        if (level.gameover) {

            if (!end) {
                time = System.nanoTime();
                end = true;
            }

            bgmusic.dispose();
            gameOverOverlay.render(batch);

            long millis = TimeUtils.nanosToMillis(TimeUtils.timeSinceNanos(time));
            if (millis > 5000) {
                game.showLevelScreen();
            }


        } else if (level.victory) {

            if (!end) {
                time = System.nanoTime();
                end = true;
            }

            bgmusic.dispose();
            victoryOverlay.render(batch);

            long millis = TimeUtils.nanosToMillis(TimeUtils.timeSinceNanos(time));
            if (millis > 5000) {
                if (difficulty.equals(Enums.Difficulty.EASY)) {
                    game.showSanFerminScreen(Enums.Difficulty.MEDIUM);
                } else if (difficulty.equals(Enums.Difficulty.MEDIUM)) {
                    game.showSanFerminScreen(Enums.Difficulty.HARD);
                } else {
                    game.showLevelScreen();
                }
            }
        }
    }
}
