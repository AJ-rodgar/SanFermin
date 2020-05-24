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

    /*public static Level load(String path) {

        Level level = new Level();

        FileHandle file = Gdx.files.internal(path);

        JSONParser parser = new JSONParser();
        JSONObject rootJsonObject;

        try {

            rootJsonObject = (JSONObject) parser.parse(file.reader());
            JSONObject composite = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);
            JSONArray platforms = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);

            loadPlatforms(platforms, level);

            JSONArray nonPlatformObjects = (JSONArray) composite.get(Constants.LEVEL_IMAGES);
            loadNonPlatformEntities(level, nonPlatformObjects);

        } catch (Exception ex) {

        }

        return level;

    }

    private static Vector2 extractXY(JSONObject object) {

        Number x = (Number) object.get(Constants.LEVEL_X_KEY);
        Number y = (Number) object.get(Constants.LEVEL_Y_KEY);

        return new Vector2(

                (x == null) ? 0 : x.floatValue(),
                (y == null) ? 0 : y.floatValue()

        );
    }


    private static void loadNonPlatformEntities(Level level, JSONArray nonPlatformObjects) {

        for (Object o : nonPlatformObjects) {
            JSONObject item = (JSONObject) o;

            final Vector2 imagePosition = extractXY(item);

            if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.POWERUP_SPRITE)) {

                final Vector2 powerupPosition = imagePosition.add(Constants.POWERUP_CENTER);
                level.getPowerUps().add(new PowerUp(powerupPosition));

            } else if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.RUNNING_1)) {

                final Vector2 runnerPosition = imagePosition.add(Constants.RUNNER_EYE_POSITION);
                level.setRunner(new Runner(runnerPosition, level));

            } else if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.BULLRING)) {

                //final Vector2 bullringPosition = imagePosition.add(Constants.BULLRING_CENTER);
                //level.setBullring(new Bullring(bullringPosition));
                level.setBullring(new Bullring(new Vector2(1000, 23)));

            } else if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.BULL_1)) {

                final Vector2 bullPosition = imagePosition.add(Constants.BULL_CENTER);
                level.setBull(new Bull(bullPosition));

            } else if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.OBSTACLE_SPRITE)) {
                Vector2 bottomLeft = extractXY(item);

                float height = 20;
                float width = 20;
                Obstacle obstacle = new Obstacle(bottomLeft.x, bottomLeft.y + height, width, height);

                level.getObstacles().add(obstacle);
            }
        }
    }

    private static void loadPlatforms(JSONArray array, Level level) {

        Array<Platform> platformArray = new Array<>();

        for (Object object : array) {

            final JSONObject platformObject = (JSONObject) object;
            Vector2 bottomLeft = extractXY(platformObject);

            //final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            //final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();

            final float width = 1500;
            final float height = 23;


            final Platform platform = new Platform(0, 0 + height, width, height);
            platformArray.add(platform);

        }

        platformArray.sort(new Comparator<Platform>() {

            @Override
            public int compare(Platform o1, Platform o2) {

                if (o1.top < o2.top) {

                    return 1;

                } else if (o1.top > o2.top) {

                    return -1;

                }
                return 0;
            }
        });

        level.getPlatforms().addAll(platformArray);
    }*/

    public static Level load(Enums.Difficulty difficulty) {
        Level level = new Level();

        switch (difficulty){
            case EASY:
                level.setRunner(new Runner(new Vector2(200,0),level));
                level.setBull(new Bull(new Vector2(0,0)));
                level.setBullring(new Bullring(new Vector2(2000,0)));
                level.getPlatforms().add(new Platform(0, 25, 3000,25));

                int obstacleStart = 500;
                while (obstacleStart < 2000) {
                    level.getObstacles().add(new Obstacle(obstacleStart, 57, 35,32));
                    obstacleStart += 300;
                }

                level.getPowerUps().add(new PowerUp(new Vector2(1100, 64)));

                break;
            case MEDIUM:

                break;
            case HARD:

                break;
        }

        return level;
    }
}
