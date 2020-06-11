package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.math.Vector2;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.entities.Bull;
import com.rodriguezgarcia.antoniojesus.entities.Bullring;
import com.rodriguezgarcia.antoniojesus.entities.Obstacle;
import com.rodriguezgarcia.antoniojesus.entities.Platform;
import com.rodriguezgarcia.antoniojesus.entities.PowerUp;
import com.rodriguezgarcia.antoniojesus.entities.Runner;


public class LevelLoader {

    public static Level load(Enums.Difficulty difficulty) {

        Level level = new Level();

        int obstacleStart = 500;

        switch (difficulty){
            case EASY:

                level.setRunner(new Runner(new Vector2(200,50),level));
                level.setBull(new Bull(new Vector2(-100,35),level));
                level.setBullring(new Bullring(new Vector2(3000,50),level));
                level.getPlatforms().add(new Platform(-500, 50, 5000,70));

                obstacleStart = 500;
                while (obstacleStart < 3000) {
                    level.getObstacles().add(new Obstacle(obstacleStart, 85, 35,35,level));
                    obstacleStart += 300;
                }

                level.getPowerUps().add(new PowerUp(new Vector2(1100, 100),level));

                break;

            case MEDIUM:

                level.setRunner(new Runner(new Vector2(200,50),level));
                level.setBull(new Bull(new Vector2(0,35),level));
                level.setBullring(new Bullring(new Vector2(3000,50),level));
                level.getPlatforms().add(new Platform(-500, 50, 5000,70));

                obstacleStart = 500;
                while (obstacleStart < 3000) {
                    level.getObstacles().add(new Obstacle(obstacleStart, 85, 35,35,level));
                    obstacleStart += 300;
                }

                level.getPowerUps().add(new PowerUp(new Vector2(1100, 100),level));

                break;
            case HARD:

                level.setRunner(new Runner(new Vector2(200,50),level));
                level.setBull(new Bull(new Vector2(100,35),level));
                level.setBullring(new Bullring(new Vector2(3000,50),level));
                level.getPlatforms().add(new Platform(-500, 50, 5000,70));

                obstacleStart = 500;
                while (obstacleStart < 3000) {
                    level.getObstacles().add(new Obstacle(obstacleStart, 85, 35,35,level));
                    obstacleStart += 300;
                }

                level.getPowerUps().add(new PowerUp(new Vector2(1100, 100),level));

                break;
        }

        return level;
    }
}
