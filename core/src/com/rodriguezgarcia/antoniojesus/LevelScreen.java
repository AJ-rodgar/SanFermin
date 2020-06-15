package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rodriguezgarcia.antoniojesus.utils.Constants;
import com.rodriguezgarcia.antoniojesus.utils.Enums;

public class LevelScreen extends InputAdapter implements Screen {

    SanFerminGame game;
    ExtendViewport viewport;
    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;

    Texture texture;

    public LevelScreen(SanFerminGame game){
        this.game = game;
    }

    @Override
    public void show(){
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new ExtendViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();

        font.getData().setScale(1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texture = new Texture(Gdx.files.internal("images/inicio.png"));
    }

    @Override
    public void render(float delta) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture,0,0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.end();

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        Vector2 center = new Vector2(viewport.getWorldWidth(), viewport.getWorldHeight());

        renderer.setColor(Constants.EASY_COLOR);
        //renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);
        renderer.circle(center.x / 4, center.y / 2, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.MEDIUM_COLOR);
        //renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);
        renderer.circle(center.x / 2, center.y / 2, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.HARD_COLOR);
        //renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);
        renderer.circle(center.x * 3 / 4, center.y / 2, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.end();

        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.EASY_LABEL);
        font.draw(batch, Constants.EASY_LABEL, center.x / 4, Constants.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.MEDIUM_LABEL);
        font.draw(batch, Constants.MEDIUM_LABEL, center.x / 2, Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.HARD_LABEL);
        font.draw(batch, Constants.HARD_LABEL, center.x * 3 / 4, Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));
        Vector2 easy_center = new Vector2(viewport.getWorldWidth() / 4, viewport.getWorldHeight() / 2);
        Vector2 medium_center = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        Vector2 hard_center = new Vector2(viewport.getWorldWidth() * 3 / 4, viewport.getWorldHeight() / 2);

        if (worldTouch.dst(easy_center) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showSanFerminScreen(Enums.Difficulty.EASY);
        } else if (worldTouch.dst(medium_center) < Constants.DIFFICULTY_BUBBLE_RADIUS) {

            game.showSanFerminScreen(Enums.Difficulty.MEDIUM);
        } else if (worldTouch.dst(hard_center) < Constants.DIFFICULTY_BUBBLE_RADIUS) {

            game.showSanFerminScreen(Enums.Difficulty.HARD);
        }

        return true;
    }
}
