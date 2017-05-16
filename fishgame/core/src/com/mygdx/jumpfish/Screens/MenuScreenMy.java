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
import com.mygdx.jumpfish.startscreen.MenuWorldControllerMy;
import com.mygdx.jumpfish.startscreen.MenuWorldRendererMy;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.MyAudioManager;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
  	


public class MenuScreenMy extends AbstractGameScreenMY{
	
	private static final String TAG = MenuScreenMy.class.getName();
	private MenuWorldControllerMy worldController1;

	private MenuWorldRendererMy worldRenderer1;
	private boolean paused1;


	
	
	

	public MenuScreenMy(DirectedGameMy game) {
		 
		super(game);
		MyAudioManager.instance.getPlayingMusic().stop();
		MyAudioManager.instance.play(AssetsMy.instance.music.song02);
	//	Gdx.graphics.setVSync(true);
	
	}

	@Override
	public void render(float deltaTime) {
		//System.out.println(AlieInJumpFish.isconnect());
		if (!paused1) {
			// Update game world by the time that has passed
			// since last rendered frame.
		worldController1.update(deltaTime); 	
		
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
		worldRenderer1.render();
		
	}

	@Override
	public void resize(int width, int height) {
		
		worldRenderer1.resize(width, height);
	}

	@Override
	public void show() {
		
		
		
		Gdx.input.setCatchBackKey(false);
	//	Gdx.input.setCatchBackKey(true);
	 worldController1 = new MenuWorldControllerMy(game); 
			worldRenderer1 = new MenuWorldRendererMy(worldController1);
		Gdx.input.setCatchBackKey(false);
		
	}

	@Override
	public void hide() {
		
	//	Gdx.input.setCatchBackKey(true);
		System.out.println("”ƒ¿À»À ÃÂÌ˛");
		worldController1.dispose();
		worldRenderer1.dispose();
		Gdx.input.setInputProcessor(null);
	Gdx.input.setCatchBackKey(true);
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
		return worldController1;
	}
}