package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.graphics.Camera;
import com.rodriguezgarcia.antoniojesus.entities.Runner;

public class ChaseCam {

    public Camera camera;
    public Runner target;

    public ChaseCam(Camera camera, Runner runner) {
        this.camera = camera;
        target = runner;
    }

    public void update(){
        camera.position.x = target.getPosition().x-75;
        //camera.position.y = target.getPosition().y+100;
    }

}
