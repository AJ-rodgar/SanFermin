package com.rodriguezgarcia.antoniojesus.utils;

import com.badlogic.gdx.graphics.Camera;
import com.rodriguezgarcia.antoniojesus.entities.Bull;
import com.rodriguezgarcia.antoniojesus.entities.Runner;

public class ChaseCam {

    public Camera camera;
    public Runner target;
    public Bull target2;

    public ChaseCam(Camera camera, Runner runner, Bull bull) {
        this.camera = camera;
        target = runner;
        target2 = bull;
    }

    public void update(){
        camera.position.x = target.getPosition().x + Constants.RUNNER_STANCE_WIDTH;
    }

}
