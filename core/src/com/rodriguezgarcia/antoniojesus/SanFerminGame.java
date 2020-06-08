package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Game;
import com.rodriguezgarcia.antoniojesus.utils.Enums;

public class SanFerminGame extends Game {

	@Override
	public void create() {
		showLevelScreen();
	}

	public void showLevelScreen() {
		setScreen(new LevelScreen(this));
	}

	public void showSanFerminScreen(Enums.Difficulty difficulty){
		setScreen(new SanFerminScreen(this, difficulty));
	}
}
