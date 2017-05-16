package com.mygdx.jumpfish.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.AlieInJumpFish;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.levelselect.SelectWorldControllerMy;
import com.mygdx.jumpfish.levelselect.SelectWorldRendererMy;
import com.mygdx.jumpfish.startscreen.MenuWorldControllerMy;
import com.mygdx.jumpfish.startscreen.MenuWorldRendererMy;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.MyAudioManager;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;


public class SelectMenuScreenMy extends AbstractGameScreenMY
{
	private static final String TAG = SelectMenuScreenMy.class.getName();
	private SelectWorldControllerMy worldController2;

	private SelectWorldRendererMy worldRenderer2;
	private boolean paused1;
	public int numotkrito;

	public SelectMenuScreenMy(DirectedGameMy game) {
		
		super(game);
	//	Gdx.graphics.setVSync(true);
	//	MyAudioManager.instance.getPlayingMusic().stop();
	//	MyAudioManager.instance.play(AssetsMy.instance.music.song01);
	}


	@Override
	public void render(float deltaTime) {
		
		if (!paused1) {
			// Update game world by the time that has passed
			// since last rendered frame.
		worldController2.update(deltaTime); 	
		
		}
		
	//	if (Gdx.input.isKeyPressed(Keys.SPACE)
	//			||Gdx.input.justTouched()
	//			){
	//		Gdx.input.setCatchBackKey(false);
	//		game.setScreen(new GameScreen(game));
	//		}
		
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Render game world to screen
	//	worldController1.update(deltaTime); 	
		worldRenderer2.render();
		
	}

	@Override
	public void resize(int width, int height) {
		
		worldRenderer2.resize(width, height);
	}

	@Override
	public void show() {
		
		Gdx.input.setCatchBackKey(true);
	 worldController2 = new SelectWorldControllerMy(game); 
			worldRenderer2 = new SelectWorldRendererMy(worldController2);
		Gdx.input.setCatchBackKey(true); // ÇÀÊÎÌÅÒÈË ÍÎ ÇÀ×ÅÌ !! ìîæåò áûòü êîñÿê
	
	}

	@Override
	public void hide() {
	
		//Gdx.input.setCatchBackKey(false);
		System.out.println("ÓÄÀËÈË Ìåíş");
		worldController2.dispose();
		worldRenderer2.dispose();
	//	Gdx.input.setCatchBackKey(false);// ÇÀÊÎÌÅÒÈË ÍÎ ÇÀ×ÅÌ !! ìîæåò áûòü êîñÿê
	}

	@Override
	public void pause() {
		paused1 = true;
	}
	@Override
	public void resume() {
		super.resume();
		// Only called on Android!
		paused1 = false;
	}
	

	@Override
	public InputProcessor getInputProcessor() {
		return worldController2;
	}
}