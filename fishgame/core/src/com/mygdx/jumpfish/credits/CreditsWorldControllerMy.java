/*******************************************************************************
 * Copyright 2013 Andreas Oehlke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.mygdx.jumpfish.credits;

import net.dermetfan.utils.libgdx.maps.MapUtils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.Screens.DirectedGameMy;
import com.mygdx.jumpfish.Screens.GameScreenMy;
import com.mygdx.jumpfish.Screens.MenuScreenMy;
import com.mygdx.jumpfish.game.logika.MenuObjects_logik;
import com.mygdx.jumpfish.game.logika.Objects_logik;
import com.mygdx.jumpfish.gameobjects.Platform;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.gui.Knopka;
import com.mygdx.jumpfish.gameobjects.gui.MenuGUI;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.transactions.ScreenTransition;
import com.mygdx.jumpfish.transactions.ScreenTransitionFade;
import com.mygdx.jumpfish.transactions.ScreenTransitionSlice;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MenuCameraHelper;
import com.mygdx.jumpfish.util.MyAudioManager;


public class CreditsWorldControllerMy extends InputAdapter implements Disposable {
	public	float timerok=0; 
	public MenuGUI Mygui;
	private static final String TAG = CreditsWorldControllerMy.class.getName();
	private boolean below = false;
	private DirectedGameMy game;
	 public CreditsLevelMy level; 
	 
//	 static public float lives; //переделать и отдать в свойство рфбки
	 private int score;

	public MenuCameraHelper cameraHelper;
	
	public boolean touch=false;
	public boolean jump=true;
	public boolean goalReached;
	public float timeLeftGameOverDelay;
	public static float globalcamx;
	public static float globalcamy;
	public static float storonaprizka;
	public static float timschetchik;
	private AdsController adsController;
	
	// Rectangles for collision detection

	public CreditsWorldControllerMy(DirectedGameMy game2) {
	
		this.game = game2;
		init();
	}

	private void init() {
	
		Gdx.input.setInputProcessor(this);
		cameraHelper = new MenuCameraHelper();
		
		timeLeftGameOverDelay = 3;
		timschetchik=0;
		
	
		
		initLevel();
		
	}

	private void initLevel() {
		goalReached = false;
		score = 0;
		level = new CreditsLevelMy();
		//cameraHelper.setPosition(10, 10);
		cameraHelper.setTarget(level.getPlayer());
		
		
	Mygui = new MenuGUI(level.getPlayer(),level.ECcollection.size);
	}

	public CreditsLevelMy getlevel(){
		return level;
	}
  
	public void update(float deltaTime) {
	//	if(level.alien.alieninwater==true)
		
	//	timerok=timerok+deltaTime;
	//	System.out.println(timerok);
	//	handleDebugInput(deltaTime);
	//	handleInputGame(deltaTime);
	//	if (isGameOver() || goalReached) {
//			timeLeftGameOverDelay -= deltaTime;
	//		if (timeLeftGameOverDelay < 0)
				// Gdx.input.setInputProcessor(null); // / отключает управление
//				backToMenu();
	//	}
	//	Mygui.update(deltaTime,level.getPlayer().inwater,(int)level.getPlayer().lives);
//		LogikaObektov(deltaTime);// тут 
	//	if (cameraHelper.hasTarget(level.getPlayer())) {
		//	zoomingtest();
	//		if (!level.getPlayer().inwater) {
	//			level.getPlayer().lives = level.getPlayer().lives - 0.01f;
	//		}
		//	ChangeViw(); //ЗАКОМЕНТИЛ!
			level.update(deltaTime); // тут 
			cameraHelper.setPosition(12.5f ,7.5f);
			cameraHelper.update(deltaTime);// тут 
			
	//			goalReachedtEST();
	//		}

		
	}

	public boolean isGameOver() {

		return level.getPlayer().lives < 0;
	}

	
		
	
	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	@Override
	public boolean keyUp(int keycode) {
		// Reset game world
		if (keycode == Keys.R) {
			init(); //исправить
			Gdx.app.debug(TAG, "Game world resetted");
		}
		// Toggle camera follow
		else if (keycode == Keys.ENTER) {
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null : level.getPlayer());
			Gdx.app.debug(TAG,
					"Camera follow enabled: " + cameraHelper.hasTarget());
		}
		// Back to Menu
		else if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			Gdx.input.setInputProcessor(null);
			backToMenu();
		}

		
		return false;
	}

	private void backToMenu() {
		Gdx.input.setInputProcessor(null); // / отключает управление
		// switch to menu screen
		ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);
		game.setScreen(new MenuScreenMy(game),transition);
	}

	public void dispose() {
		level.dispose();
	}

	private void ChangeNavigation(int x, int y) {
		if (CreditsWorldRendererMy.x1 < CreditsWorldRendererMy.x2	&& level.getPlayer().PreperingTime > 20&& CreditsWorldRendererMy.y2 > CreditsWorldRendererMy.y1) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(level.getPlayer().PreperingTime * 0.1f* CreditsWorldRendererMy.cos,level.getPlayer().PreperingTime * 0.1f* CreditsWorldRendererMy.sin),level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = true;
		} else
		if (CreditsWorldRendererMy.x1 > CreditsWorldRendererMy.x2	&& level.getPlayer().PreperingTime > 20&& CreditsWorldRendererMy.y2 > CreditsWorldRendererMy.y1) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(-level.getPlayer().PreperingTime * 0.1f * CreditsWorldRendererMy.cos,level.getPlayer().PreperingTime * 0.1f	* CreditsWorldRendererMy.sin), level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = true;
		} else
		if (CreditsWorldRendererMy.x1 < CreditsWorldRendererMy.x2&& level.getPlayer().PreperingTime < 20) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(2.5f, 1f),new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		} else 
		if (CreditsWorldRendererMy.x1 > CreditsWorldRendererMy.x2&& level.getPlayer().PreperingTime < 20) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(-2f, 1f),	new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		}
		storonaprizkais(level.getPlayer().viewDirection);
	}
	private void ChangeNavigationsmash(int x, int y) {
	
		if (CreditsWorldRendererMy.x1 < CreditsWorldRendererMy.x2) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(2.5f, 1f),new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		} else 
		if (CreditsWorldRendererMy.x1 > CreditsWorldRendererMy.x2) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(-2f, 1f),	new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		}
		storonaprizkais(level.getPlayer().viewDirection);
	}
	private void  storonaprizkais (VIEW_DIRECTION viewDirection){
		if (viewDirection == VIEW_DIRECTION.LEFT){
			storonaprizka=1;
		}
		else
			storonaprizka=-1;
	}
	private void ChangeViw() {
		if (CreditsWorldRendererMy.x1 < CreditsWorldRendererMy.x2)
			level.getPlayer().viewDirection = VIEW_DIRECTION.RIGHT;
		if (CreditsWorldRendererMy.x1 > CreditsWorldRendererMy.x2)
			level.getPlayer().viewDirection = VIEW_DIRECTION.LEFT;
	}
public void ChangeLevel(String Start)
{touch = true;
	GamePreferences prefs = GamePreferences.instance;
	if (Start!=null&&Integer.parseInt(Start)<=prefs.levelpr){
	ScreenTransition transition = ScreenTransitionFade.init(0.75f);
game.setScreen(new GameScreenMy(game,Start),transition);}
		// добавить блокировку нажатия!!!!
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
	//	System.out.println("!!!!"+x/MenuWorldRendererMy.UnitScale);
		
	
		if (touch){
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
			touch=false;}
	
		// TODO Auto-generated method stub
		
		
		return true;

	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (cameraHelper.hasTarget(level.getPlayer())&& level.getPlayer().notuse == false)
		 {
			level.getPlayer().pos = POSICION.PREPARING;
			
					}
		return true;
	}
	public int newsignux(VIEW_DIRECTION x){
		int x1 = 1;
		if (x==VIEW_DIRECTION.LEFT)x1=1;	
		else x1=-1;
		return x1;
	}
	

	private void handleDebugInput(float deltaTime) {
		if (Gdx.app.getType() != ApplicationType.Desktop)
			return;

		// if (!cameraHelper.hasTarget(level.bunnyHead)) {
		if (!cameraHelper.hasTarget(level.getPlayer())) {
			// Camera Controls (move)
			float camMoveSpeed = 5 * deltaTime;
			float camMoveSpeedAccelerationFactor = 5;
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
				camMoveSpeed *= camMoveSpeedAccelerationFactor;
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				moveCamera(-camMoveSpeed, 0);
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				moveCamera(camMoveSpeed, 0);
			if (Gdx.input.isKeyPressed(Keys.UP))
				moveCamera(0, camMoveSpeed);
			if (Gdx.input.isKeyPressed(Keys.DOWN))
				moveCamera(0, -camMoveSpeed);
			if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
				cameraHelper.setPosition(0, 0);
		}
	}

	private void handleInputGame(float deltaTime) {
		// if (cameraHelper.hasTarget(level.bunnyHead)) {
		if (cameraHelper.hasTarget(level.getPlayer())) {
			// Player Movement
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				level.getPlayer().body.applyLinearImpulse(
						new Vector2(-0.2f, 1f),
						level.getPlayer().body.getPosition(), true);
				// level.bunnyHead.velocity.x =
				// -level.bunnyHead.terminalVelocity.x;
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				// level.bunnyHead.velocity.x =
				// level.bunnyHead.terminalVelocity.x;
				level.getPlayer().body.applyLinearImpulse(
						new Vector2(0.2f, 1f),
						level.getPlayer().body.getPosition(), true);
			}
		}

	}
}
