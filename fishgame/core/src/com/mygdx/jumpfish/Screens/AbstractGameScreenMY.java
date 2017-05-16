package com.mygdx.jumpfish.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.AlieInJumpFish;
import com.mygdx.jumpfish.util.AssetsMy;


public abstract class AbstractGameScreenMY implements Screen {

	protected DirectedGameMy game;
	
	public AbstractGameScreenMY(DirectedGameMy game) {
	
		this.game = game;
		
	}

	public abstract void render(float deltaTime);

	public abstract void resize(int width, int height);

	public abstract void show();

	public abstract void hide();

	public abstract void pause();
	
	
	public abstract InputProcessor getInputProcessor ();
	
	public void resume() {
		//AssetsMy.instance.init(new AssetManager());
	}

	public void dispose() {
		System.out.println("ÂÀÂÀÂÀÀÂÀÂ");
		AssetsMy.instance.dispose();
	}

	
}

