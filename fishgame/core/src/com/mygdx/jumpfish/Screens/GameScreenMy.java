package com.mygdx.jumpfish.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.AlieInJumpFish;
import com.mygdx.jumpfish.game.LevelMy;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.MyAudioManager;
import com.sun.org.glassfish.gmbal.ManagedAttribute;



public class GameScreenMy extends AbstractGameScreenMY {
	private static final String TAG = GameScreenMy.class.getName();
	private WorldControllerMy worldController;
	private String a123;
	private WorldRendererMy worldRenderer;
	private boolean paused;
	

	public GameScreenMy(DirectedGameMy game,String start) {
		super(game);
	//	Gdx.graphics.setVSync(true);
		MyAudioManager.instance.getPlayingMusic().stop();
		int song = MathUtils.random(1, 6);
		switch(song){
	case 1:
		MyAudioManager.instance.play(AssetsMy.instance.music.song01);
		break;
	case 2:
		MyAudioManager.instance.play(AssetsMy.instance.music.song01);
		break;
	case 3:
		MyAudioManager.instance.play(AssetsMy.instance.music.song01);
		break;
	case 4:
		MyAudioManager.instance.play(AssetsMy.instance.music.song01);
		break;
	case 5:
		MyAudioManager.instance.play(AssetsMy.instance.music.song01);
		break;
	case 6:
		MyAudioManager.instance.play(AssetsMy.instance.music.song01);
		break;}
		a123=start;
	
	
	}

	@Override
	public void render(float deltaTime) {
		
		
		// Do not update game world when paused.
		if (!paused) {
			// Update game world by the time that has passed
			// since last rendered frame.
		worldController.update(deltaTime); 	
		
		}
		// Sets the clear screen color to: Cornflower Blue
	//	Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
	//			0xff / 255.0f);
		// Sets the clear screen color to: Cornflower black
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Render game world to screen
		worldRenderer.render();
		
	}

	@Override
	public void resize(int width, int height) {
		
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
	//	AlieInJumpFish.showads();
		Gdx.input.setCatchBackKey(true);
		System.out.println("«¿„ÛÁÍ‡");
	 worldController = new WorldControllerMy(game,a123); 
			worldRenderer = new WorldRendererMy(worldController);
			Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void hide() {
		
		//Gdx.input.setCatchBackKey(false);
		System.out.println("”ƒ¿À»À");
		worldController.dispose();
		worldRenderer.dispose();
		Gdx.input.setInputProcessor(null);
	//	Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void pause() {
		paused = true;
		
	}

	@Override
	public void resume() {
		super.resume();
		// Only called on Android!
		paused = false;
	}

	@Override
	public InputProcessor getInputProcessor() {
		return worldController;
	}
}
