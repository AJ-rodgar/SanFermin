package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.graphics.Camera;
import com.rodriguezgarcia.antoniojesus.entities.Runner;

public class ChaseCam {

    public Camera camera;
    public Runner target;

    public void update(){
        camera.position.x = target.getPosition().x;
        camera.position.y = target.getPosition().y;
    }

}
