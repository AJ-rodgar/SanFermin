package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    //World Constants
    public static final Color BACKGROUND_COLOR = Color.SKY;
    public static final float WORLD_SIZE = 300;
    public static final float GRAVITY = 10;
    public static final String TEXTURE_ATLAS = "images/sanfermin.atlas";

    //HUD
    public static final float HUD_MARGIN = 20;
    public static final String HUD_DISTANCE_LABEL = "Distance: ";
    public static final String HUD_BULL_LABEL = "Bull: ";

    //Runner Constants
    public static final Vector2 RUNNER_EYE_POSITION = new Vector2(250,0);
    public static final float RUNNER_EYE_HEIGHT = 40f;
    public static final float RUNNER_STANCE_WIDTH = 19.0f;
    public static final float RUNNER_HEIGHT = 23.0f;
    public static final float RUNNER_MOVE_SPEED = 60;

    public static final String RUNNING_1 = "trump1";
    public static final String RUNNING_2 = "trump2";
    public static final String RUNNING_3 = "trump3";
    public static final String RUNNING_4 = "trump4";
    public static final String RUNNING_5 = "trump5";
    public static final String RUNNING_6 = "trump6";
    public static final float WALK_LOOP_DURATION = 5f;

    public static final float JUMP_SPEED = 100;
    public static final float MAX_JUMP_DURATION = 0.8f;

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
    public static final int PLATFORM_EDGE = 1;

    //
    public static final String BULLRING = "bullring";
    public static final Vector2 BULLRING_CENTER = new Vector2(200, 0);
    public static final float BULLRING_RADIUS = 12;

    //
    public static final float BULL_MOVE_SPEED = 61;
    public static final String BULL_1 = "bull1";
    public static final String BULL_2 = "bull2";
    public static final String BULL_3 = "bull3";
    public static final String BULL_4 = "bull4";
    public static final String BULL_5 = "bull5";
    public static final String BULL_6 = "bull6";
    public static final String BULL_7 = "bull7";
    public static final String BULL_8 = "bull8";

    public static final float ENEMY_COLLISION_RADIUS = 60;

    //LevelScreen
    public static final Color EASY_COLOR = new Color(0f, 0.5f,0, 1);
    public static final Color MEDIUM_COLOR = new Color(0.8f, 0.5f,0, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0f,0, 1);

    public static final float DIFFICULTY_WORLD_SIZE = 480;
    public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;

    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);

    public static final String EASY_LABEL = "EASY";
    public static final String MEDIUM_LABEL = "MEDIUM";
    public static final String HARD_LABEL = "HARD";
}
