package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    //World Constants
    public static final Color BACKGROUND_COLOR = Color.SKY;
    public static final float WORLD_SIZE = 300.0f;
    public static final float GRAVITY = 10;
    public static final String TEXTURE_ATLAS = "images/sanfermin.atlas";

    //Runner Constants
    public static final Vector2 RUNNER_EYE_POSITION = new Vector2(250,0);
    public static final float RUNNER_EYE_HEIGHT = 16.0f;
    public static final float RUNNER_STANCE_WIDTH = 19.0f;
    public static final float RUNNER_HEIGHT = 23.0f;
    public static final float RUNNER_MOVE_SPEED = 30;

    public static final String RUNNING_1 = "trump1";
    public static final String RUNNING_2 = "trump2";
    public static final String RUNNING_3 = "trump3";
    public static final String RUNNING_4 = "trump4";
    public static final String RUNNING_5 = "trump5";
    public static final String RUNNING_6 = "trump6";
    public static final float WALK_LOOP_DURATION = 2.5f;

    public static final float JUMP_SPEED = 200;
    public static final float MAX_JUMP_DURATION = 0.1f;

    //PowerUp
    public static final String POWERUP_SPRITE = "chilito";
    public static final Vector2 POWERUP_CENTER = new Vector2(0, 0);

    //
    public static final String FONT_FILE = "font/header.fnt";
    public static final String VICTORY_MESSAGE = "OLÉ!";
    public static final String GAME_OVER_MESSAGE = "TOO SLOW, TURTLE";

    //
    public static final String OBSTACLE_SPRITE = "cactus";
    public static final int OBSTACLE_EDGE = 8;

    //
    public static final String PLATFORM_SPRITE = "ground";
    public static final int PLATFORM_EDGE = 8;

    //
    public static final String BULLRING = "bullring";
    public static final Vector2 BULLRING_CENTER = new Vector2(200, 0);
    public static final float BULLRING_RADIUS = 85;

    //
    public static final Vector2 BULL_CENTER = new Vector2(0,0);
    public static final float BULL_MOVE_SPEED = 40;
    public static final String BULL_1 = "bull1";
    public static final String BULL_2 = "bull2";
    public static final String BULL_3 = "bull3";
    public static final String BULL_4 = "bull4";
    public static final String BULL_5 = "bull5";
    public static final String BULL_6 = "bull6";
    public static final String BULL_7 = "bull7";
    public static final String BULL_8 = "bull8";

    //JSON
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_IMAGES = "sImages";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";

    public static final float ENEMY_COLLISION_RADIUS = 15;


}
