package com.rodriguezgarcia.antoniojesus;

import com.rodriguezgarcia.antoniojesus.entities.Bull;
import com.rodriguezgarcia.antoniojesus.entities.Bullring;
import com.rodriguezgarcia.antoniojesus.entities.Runner;

public class Level {

    public boolean gameover;
    public boolean victory;
    public int score;

    private Runner runner;
    private Bull bull;
    private Bullring bullring;

    public void update(float delta) {
        if (runner.isCatched()) {
            gameover = true;
        } else if (runner.getPosition().dst(bullring.position) < 0) {
            victory = true;
        }
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Bull getBull() {
        return bull;
    }

    public void setBull(Bull bull) {
        this.bull = bull;
    }
}
