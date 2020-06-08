package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final Assets instance = new Assets();
    public RunnerAssets runnerAssets;
    public BullAssets bullAssets;
    public ObstacleAssets obstacleAssets;
    public BullringAssets bullringAssets;
    public PlatformAssets platformAssets;
    public PowerUpAssets powerUpAssets;
    public OnscreenControlsAssets onscreenControlsAssets;
    private AssetManager assetManager;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        runnerAssets = new RunnerAssets(atlas);
        bullAssets = new BullAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        bullringAssets = new BullringAssets(atlas);
        obstacleAssets = new ObstacleAssets(atlas);
        powerUpAssets = new PowerUpAssets(atlas);
        onscreenControlsAssets = new OnscreenControlsAssets(atlas);

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class RunnerAssets {

        public final TextureAtlas.AtlasRegion standing;
        public final TextureAtlas.AtlasRegion jumpingRight;

        public final Animation walkingRightAnimation;

        public RunnerAssets(TextureAtlas atlas) {

            standing = atlas.findRegion(Constants.RUNNING_1);
            jumpingRight = atlas.findRegion(Constants.RUNNING_4);

            Array<TextureAtlas.AtlasRegion> walkingRightFrames = new Array<>();

            walkingRightFrames.add(atlas.findRegion(Constants.RUNNING_1));
            walkingRightFrames.add(atlas.findRegion(Constants.RUNNING_2));
            walkingRightFrames.add(atlas.findRegion(Constants.RUNNING_3));
            walkingRightFrames.add(atlas.findRegion(Constants.RUNNING_4));
            walkingRightFrames.add(atlas.findRegion(Constants.RUNNING_5));
            walkingRightFrames.add(atlas.findRegion(Constants.RUNNING_6));

            walkingRightAnimation = new Animation(
                    Constants.WALK_LOOP_DURATION,
                    walkingRightFrames,
                    Animation.PlayMode.LOOP);
        }
    }

    public class BullAssets {
        public final TextureAtlas.AtlasRegion standing;
        public final Animation walkingRightAnimation;

        public BullAssets(TextureAtlas atlas){

            standing = atlas.findRegion(Constants.BULL_1);

            Array<TextureAtlas.AtlasRegion> walkingRightFrames = new Array<TextureAtlas.AtlasRegion>();

            walkingRightFrames.add(atlas.findRegion(Constants.BULL_1));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_2));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_3));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_4));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_5));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_6));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_7));
            walkingRightFrames.add(atlas.findRegion(Constants.BULL_8));

            walkingRightAnimation = new Animation(
                    Constants.WALK_LOOP_DURATION,
                    walkingRightFrames,
                    Animation.PlayMode.LOOP);
        }
    }

    public class ObstacleAssets {
        public final NinePatch obstacleNinePatch;

        public ObstacleAssets(TextureAtlas atlas) {
            TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.OBSTACLE_SPRITE);
            int edge = Constants.OBSTACLE_EDGE;
            obstacleNinePatch = new NinePatch(region, edge, edge, edge, edge);
        }
    }

    public class BullringAssets {
        public final TextureAtlas.AtlasRegion bullring;

        public BullringAssets(TextureAtlas atlas){
            bullring = atlas.findRegion(Constants.BULLRING);
        }
    }

    public class PlatformAssets {
        public final NinePatch platformNinePatch;

        public PlatformAssets(TextureAtlas atlas){
            TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_SPRITE);

            int edge = Constants.PLATFORM_EDGE;
            platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
        }
    }

    public class PowerUpAssets {

        public final TextureAtlas.AtlasRegion powerup;

        public PowerUpAssets(TextureAtlas atlas) {
            powerup = atlas.findRegion(Constants.POWERUP_SPRITE);
        }
    }

    public class OnscreenControlsAssets {

        public final TextureAtlas.AtlasRegion jump;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            jump = atlas.findRegion(Constants.JUMP_BUTTON);
        }


    }
}
