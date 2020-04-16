package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rodriguezgarcia.antoniojesus.Level;
import com.rodriguezgarcia.antoniojesus.entities.Bull;
import com.rodriguezgarcia.antoniojesus.entities.Bullring;
import com.rodriguezgarcia.antoniojesus.entities.Platform;
import com.rodriguezgarcia.antoniojesus.entities.PowerUp;
import com.rodriguezgarcia.antoniojesus.entities.Runner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Comparator;


public class LevelLoader {

    public static Level load(String path) {

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

                final Vector2 bullringPosition = imagePosition.add(Constants.BULLRING_CENTER);
                level.setBullring(new Bullring(bullringPosition));

            } else if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.BULL_1)) {

                final Vector2 bullPosition = imagePosition.add(Constants.BULL_CENTER);
                level.setBull(new Bull(bullPosition));

            }
        }
    }

    private static void loadPlatforms(JSONArray array, Level level) {

        Array<Platform> platformArray = new Array<>();

        for (Object object : array) {

            final JSONObject platformObject = (JSONObject) object;
            Vector2 bottomLeft = extractXY(platformObject);

            final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();

            final Platform platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height);
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
    }
}
