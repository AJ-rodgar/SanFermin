package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.entities.Bull;
import com.rodriguezgarcia.antoniojesus.entities.Bullring;
import com.rodriguezgarcia.antoniojesus.entities.Obstacle;
import com.rodriguezgarcia.antoniojesus.entities.Platform;
import com.rodriguezgarcia.antoniojesus.entities.PowerUp;
import com.rodriguezgarcia.antoniojesus.entities.Runner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Comparator;


public class LevelLoader {

    public static Level load(Enums.Difficulty difficulty) {

        Level level = new Level();

        switch (difficulty){
            case EASY:

                level.setRunner(new Runner(new Vector2(200,0),level));
                level.setBull(new Bull(new Vector2(0,35)));
                level.setBullring(new Bullring(new Vector2(2000,50)));
                level.getPlatforms().add(new Platform(-500, 50, 3000,70));

                int obstacleStart = 500;
                while (obstacleStart < 2000) {
                    level.getObstacles().add(new Obstacle(obstacleStart, 82, 35,32));
                    obstacleStart += 300;
                }

                level.getPowerUps().add(new PowerUp(new Vector2(1100, 89)));

                break;

            case MEDIUM:

                break;
            case HARD:

                break;
        }

        return level;
    }
}
