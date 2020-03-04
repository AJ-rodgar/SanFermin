package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Game;

public class SanFerminGame extends Game {

	@Override
	public void create() {
		setScreen(new LevelScreen(this));
	}
}
