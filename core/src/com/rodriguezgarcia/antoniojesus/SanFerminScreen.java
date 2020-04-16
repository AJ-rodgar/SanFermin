package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.rodriguezgarcia.antoniojesus.overlays.GameOverOverlay;
import com.rodriguezgarcia.antoniojesus.overlays.HUD;
import com.rodriguezgarcia.antoniojesus.overlays.VictoryOverlay;
import com.rodriguezgarcia.antoniojesus.utils.Assets;
import com.rodriguezgarcia.antoniojesus.utils.ChaseCam;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Enums;
import com.rodriguezgarcia.antoniojesus.utils.LevelLoader;
import com.rodriguezgarcia.antoniojesus.utils.Utils;

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
        level = LevelLoader.load("levels/Level1.dt");
        chaseCam = new ChaseCam(viewport.getCamera(), level.getRunner());
        victoryOverlay = new VictoryOverlay();
        gameOverOverlay = new GameOverOverlay();
        hud = new HUD();
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
        hud.render(batch,level.getRunner().getPosition().x);

        renderLevelEndOverlays(batch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        //hud.getViewport().update(width,height,true);
        //victoryOverlay.getViewport().update(width,height,true);
        //gameOverOverlay.getViewport().update(width,height,true);
        level.getViewport().update(width,height,true);
        chaseCam.camera = level.viewport.getCamera();
        chaseCam.target = level.getRunner();
    }

    private void renderLevelEndOverlays(SpriteBatch batch) {

        if (level.gameover) {

            gameOverOverlay.render(batch);

        } else if (level.victory) {

            victoryOverlay.render(batch);

        }
    }
}
