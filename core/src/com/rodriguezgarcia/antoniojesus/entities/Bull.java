package com.rodriguezgarcia.antoniojesus.entities;

import com.badlogic.gdx.math.Vector2;

public class Bull {

    private Vector2 spawnPosition;
    private Vector2 position;
    private Vector2 velocity;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
