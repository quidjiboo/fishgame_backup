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

package com.mygdx.jumpfish.startscreen;

import java.util.prefs.Preferences;

import net.dermetfan.utils.libgdx.maps.MapUtils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.Screens.CreditsMenuScreenMy;
import com.mygdx.jumpfish.Screens.DirectedGameMy;
import com.mygdx.jumpfish.Screens.GameScreenMy;
import com.mygdx.jumpfish.Screens.MenuScreenMy;
import com.mygdx.jumpfish.Screens.SelectMenuScreenMy;
import com.mygdx.jumpfish.game.logika.MenuObjects_logik;
import com.mygdx.jumpfish.game.logika.Objects_logik;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.gui.Knopka;
import com.mygdx.jumpfish.gameobjects.gui.MenuGUI;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.transactions.ScreenTransition;
import com.mygdx.jumpfish.transactions.ScreenTransitionFade;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MenuCameraHelper;
import com.mygdx.jumpfish.util.MyAudioManager;




public class MenuWorldControllerMy extends InputAdapter implements Disposable {
	public boolean touch=false;
	public	float timerok=0; 
	public MenuGUI Mygui;
	private static final String TAG = MenuWorldControllerMy.class.getName();
	private boolean below = false;
	private DirectedGameMy game;
	 public MenuLevelMy level; 
	 
//	 static public float lives; //переделать и отдать в свойство рфбки
	 private int score;

	public MenuCameraHelper cameraHelper;
	

	public boolean jump=true;
	public boolean goalReached;
	public float timeLeftGameOverDelay;
	public static float globalcamx;
	public static float globalcamy;
	public static float storonaprizka;
	public static float timschetchik;
	private AdsController adsController;
	
	// Rectangles for collision detection

	public MenuWorldControllerMy(DirectedGameMy game2) {
		
		
		this.game = game2;
		init();
	}

	private void init() {
	//	GamePreferences prefs = GamePreferences.instance;
	//	prefs.loadlevelkolvo();
		
		Gdx.input.setInputProcessor(this);
		cameraHelper = new MenuCameraHelper();
		
		timeLeftGameOverDelay = 3;
		timschetchik=0;
		
		
		
		initLevel();
		
	}

	private void initLevel() {
		goalReached = false;
		score = 0;
		level = new MenuLevelMy();
		//cameraHelper.setPosition(10, 10);
		cameraHelper.setTarget(level.getPlayer());
		
		
	Mygui = new MenuGUI(level.getPlayer(),level.ECcollection.size);
	
	}

	public MenuLevelMy getlevel(){
		return level;
	}
  
	public void update(float deltaTime) {
		
		if(level.alien.alieninwater==true)
		
		timerok=timerok+deltaTime;
	//	System.out.println(timerok);
	//	handleDebugInput(deltaTime);
	//	handleInputGame(deltaTime);
		if (isGameOver() || goalReached) {
			timeLeftGameOverDelay -= deltaTime;
			if (timeLeftGameOverDelay < 0)
				// Gdx.input.setInputProcessor(null); // / отключает управление
				backToMenu();
		}
		Mygui.update(deltaTime,level.getPlayer().inwater,(int)level.getPlayer().lives);
		LogikaObektov(deltaTime);// тут 
		if (cameraHelper.hasTarget(level.getPlayer())) {
		//	zoomingtest();
			if (!level.getPlayer().inwater) {
				level.getPlayer().lives = level.getPlayer().lives - 0.01f;
			}
		//	ChangeViw(); //ЗАКОМЕНТИЛ!
			level.update(deltaTime); // тут 
			cameraHelper.setPosition(12.5f+12.5f ,7.5f);
			
			cameraHelper.update(deltaTime);// тут 
			
				goalReachedtEST();
			}
		if(level.getPlayer().inwater)level.renderfish=true;
		if(level.getPlayer().inwater&&level.alien.alieninwater==true){
			level.renderfish=true;
		}
		if(level.getPlayer().inwater&&jump&&level.alien.alieninwater==true&&timerok>=1 ){
			level.getPlayer().body.applyLinearImpulse(new Vector2(1.8f,9.5f),level.getPlayer().getPosition(),true);
			
			jump=false;
		
		}
		if(level.getPlayer().onGround&&!level.getPlayer().inwater&&level.getPlayer().getPosition().y>10){
			level.getPlayer().body.applyLinearImpulse(new Vector2(0.2f,1f),level.getPlayer().getPosition(),true);
			
		}
		
	}

	public boolean isGameOver() {

		return level.getPlayer().lives < 0;
	}

	public void goalReachedtEST() {
		if (level.getPlayer().body.getPosition().x<= level.portal.getBody().getPosition().x+0.5+0.5&&level.getPlayer().body.getPosition().x>= level.portal.getBody().getPosition().x&&
				level.getPlayer().body.getPosition().y<= level.portal.getBody().getPosition().y+0.5&&level.getPlayer().body.getPosition().y>= level.portal.getBody().getPosition().y	) {
			goalReached = true;
			level.alien.fly=false;
			level.alien.timerok=0;
			level.alien.alieninwater=false;
			level.getPlayer().getBody().setActive(false);	
			level.getPlayer().getBody().setTransform(9.6f+12.5f,8.7f, 0);
			level.getPlayer().getBody().setActive(true);	
			level.renderfish=false; // всегда рендерить!
			jump=true;
			level.alienspaceship.polet=true;
			level.alienspaceship.contact=false;
			timerok=0;
			level.goalReachedalien=false;
			System.out.println("KIOIIOLPK:LDJKFDS");
		}
		
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
	//	else if (keycode == Keys.ESCAPE) {
	//		Gdx.input.setInputProcessor(null);
	//		backToMenu();
	//	}
		
		return false;
	}

	private void backToMenu() {
	//	Gdx.input.setInputProcessor(null); // / отключает управление
		// switch to menu screen
	//	game.setScreen(new MenuScreen(game));
	}

	public void dispose() {
		level.dispose();
	}

	private void ChangeNavigation(int x, int y) {
		if (MenuWorldRendererMy.x1 < MenuWorldRendererMy.x2	&& level.getPlayer().PreperingTime > 20&& MenuWorldRendererMy.y2 > MenuWorldRendererMy.y1) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(level.getPlayer().PreperingTime * 0.1f* MenuWorldRendererMy.cos,level.getPlayer().PreperingTime * 0.1f* MenuWorldRendererMy.sin),level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = true;
		} else
		if (MenuWorldRendererMy.x1 > MenuWorldRendererMy.x2	&& level.getPlayer().PreperingTime > 20&& MenuWorldRendererMy.y2 > MenuWorldRendererMy.y1) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(-level.getPlayer().PreperingTime * 0.1f * MenuWorldRendererMy.cos,level.getPlayer().PreperingTime * 0.1f	* MenuWorldRendererMy.sin), level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = true;
		} else
		if (MenuWorldRendererMy.x1 < MenuWorldRendererMy.x2&& level.getPlayer().PreperingTime < 20) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(2.5f, 1f),new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		} else 
		if (MenuWorldRendererMy.x1 > MenuWorldRendererMy.x2&& level.getPlayer().PreperingTime < 20) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(-2f, 1f),	new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		}
		storonaprizkais(level.getPlayer().viewDirection);
	}
	private void ChangeNavigationsmash(int x, int y) {
	
		if (MenuWorldRendererMy.x1 < MenuWorldRendererMy.x2) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(2.5f, 1f),new Vector2(0f, 0f), true);
			level.getPlayer().whilbeslap = false;
		} else 
		if (MenuWorldRendererMy.x1 > MenuWorldRendererMy.x2) {
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
		if (MenuWorldRendererMy.x1 < MenuWorldRendererMy.x2)
			level.getPlayer().viewDirection = VIEW_DIRECTION.RIGHT;
		if (MenuWorldRendererMy.x1 > MenuWorldRendererMy.x2)
			level.getPlayer().viewDirection = VIEW_DIRECTION.LEFT;
	}
	public void useuse(String Start)
	
	{touch = true;
	//	GamePreferences prefs = GamePreferences.instance; //зачемто убрал
	if (Start!=null&&Start.contains("credits")){
		ScreenTransition transition = ScreenTransitionFade.init(0.75f);
		game.setScreen(new CreditsMenuScreenMy(game),transition);
		MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
}
		if (Start!=null&&Start.contains("start")){
			ScreenTransition transition = ScreenTransitionFade.init(0.75f);
			game.setScreen(new SelectMenuScreenMy(game),transition);
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
	}
		if (Start!=null&&Start.contains("addon")){
			System.out.println("ADOONNN");
			ScreenTransition transition = ScreenTransitionFade.init(0.75f);
			GamePreferences prefs = GamePreferences.instance;
		//	prefs.save20();
			prefs.cansave=false;
			prefs.loadlevelkolvo123();
			game.setScreen(new SelectMenuScreenMy(game),transition);
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
	}
		if (Start!=null&&Start.contains("lang")){
			touch = true;
			System.out.println("ЯЗЫК"+GamePreferences.instance.lang);
			if(GamePreferences.instance.lang==1){
		GamePreferences.instance.lang=11;}
			else
			if(GamePreferences.instance.lang==11){
				GamePreferences.instance.lang=1;}
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
	}
		if (Start!=null&&Start.contains("muz")){touch = true;
			System.out.println("мизыка"+GamePreferences.instance.volMusic);
			if(GamePreferences.instance.volMusic==0.75f){
		GamePreferences.instance.volMusic=0;
		MyAudioManager.instance.onSettingsUpdated();}
			else
			if(GamePreferences.instance.volMusic==0){
				GamePreferences.instance.volMusic=0.75f;
				MyAudioManager.instance.onSettingsUpdated();}
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
	}
		if (Start!=null&&Start.contains("sfx")){touch = true;
		
			if(GamePreferences.instance.volSound==0.5f){
		GamePreferences.instance.volSound=0;
		MyAudioManager.instance.onSettingsUpdated();}
			else
			if(GamePreferences.instance.volSound==0){
				GamePreferences.instance.volSound=0.5f;
				MyAudioManager.instance.onSettingsUpdated();}
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
	}
			// добавить блокировку нажатия!!!!
		 GamePreferences.instance.savemenu();
		}
	
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		
	//	System.out.println("!!!!"+x/MenuWorldRendererMy.UnitScale);
		
//		if (x<Gdx.graphics.getWidth()/2+200&&x>Gdx.graphics.getWidth()/2-200&&y>Gdx.graphics.getHeight()/2&&y<Gdx.graphics.getHeight()/2+200){
//			ScreenTransition transition = ScreenTransitionFade.init(0.75f);
//		game.setScreen(new SelectMenuScreenMy(game),transition);
//		}
		// СПРАВА ОТ КНОПКИ СТАРТА!!!
	/*	if (MenuWorldRendererMy.x2>level.knopka.getBody().getPosition().x){
			
			ScreenTransition transition = ScreenTransitionFade.init(0.75f);
		game.setScreen(new SelectMenuScreenMy(game),transition);
		MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
		}*/
		// TODO Auto-generated method stub
	
		useuse(level.knopka_addon.PressKnopka(x, y));
		useuse(level.knopkacredits.PressKnopka(x, y));
		useuse(level.knopka.PressKnopka(x, y));
		useuse(level.knopkalang.Presseng(x, y));
		useuse(level.knopkamuz.Pressmuz(x, y));
		useuse(level.knopkasfx.Pressmuz(x, y));
		if (touch){
			MyAudioManager.instance.play(AssetsMy.instance.sounds.touch);
			touch=false;}
		
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
	private void LogikaObektov(float deltaTime) {
		level.getPlayer().BOOM_smash=false;
		level.getPlayer().timer(deltaTime);
		level.getPlayer().z2= 0;
		Array<Contact> contactList = level.getWorld().getContactList();
		for (int i = 0; i < contactList.size; i++) {
			Contact contact = contactList.get(i);
			
			MenuObjects_logik.getInst().OtskokIgrokaOtMashinki(contact,deltaTime,level.getPlayer())	;
			if(contact.isTouching()&&contact.getFixtureB().getUserData().equals("222")||contact.isTouching()&&contact.getFixtureA().getUserData().equals("222")){
							if(contact.getFixtureB().getUserData().equals("222"))
				contact.getFixtureB().setUserData("BAM");
							if(contact.getFixtureA().getUserData().equals("222"))
				contact.getFixtureA().setUserData("BAM");
									}
		MenuObjects_logik.getInst().LogikaMashinki(contact,deltaTime);
		MenuObjects_logik.getInst().OpredeleniePoverhnosti1(contact,deltaTime,level.getPlayer(),level.getPlayer().BOOM,level.getPlayer().BOOM_POS,level.getPlayer().BOOM_smash,below);
			}
			if (level.getPlayer().z2 == 0) {
		//	System.out.println("летим");
			level.getPlayer().BOOM_smash=false;
			level.getPlayer().BOOM=false;
			level.getPlayer().pos = POSICION.FLY;
			level.getPlayer().inwater = false;
			level.getPlayer().onGround = false;
			level.getPlayer().notuse = true;
		}

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
