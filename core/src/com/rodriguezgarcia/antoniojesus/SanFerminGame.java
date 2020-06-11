package com.rodriguezgarcia.antoniojesus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.rodriguezgarcia.antoniojesus.utils.Enums;

public class SanFerminGame extends Game {

	@Override
	public void create() {
		showLevelScreen();
	}

	public void showLevelScreen() {
		Music init = Gdx.audio.newMusic(Gdx.files.internal("sounds/bulltrumpet.mp3"));
		init.play();
		setScreen(new LevelScreen(this));
	}

	public void showSanFerminScreen(Enums.Difficulty difficulty){
		setScreen(new SanFerminScreen(this, difficulty));
	}
}
