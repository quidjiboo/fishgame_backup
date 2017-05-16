/*******************************************************************************
 * 
 * 
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

package com.mygdx.jumpfish.game;

import java.util.List;

import jdk.internal.dynalink.beans.StaticClass;
import sun.net.www.content.audio.wav;
import sun.util.logging.PlatformLogger.Level;
import net.dermetfan.utils.libgdx.box2d.Box2DMapObjectParser;
import net.dermetfan.utils.libgdx.maps.MapUtils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.compression.lz.InWindow;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.Screens.DirectedGameMy;
import com.mygdx.jumpfish.Screens.GameScreenMy;
import com.mygdx.jumpfish.Screens.MenuScreenMy;
import com.mygdx.jumpfish.Screens.SelectMenuScreenMy;
import com.mygdx.jumpfish.game.logika.Objects_logik;
import com.mygdx.jumpfish.gameobjects.MyFish;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.gameobjects.MyLizard.ANIMSTAT;
import com.mygdx.jumpfish.gameobjects.MyLizard.JUMPNAPRAVLENIE;
import com.mygdx.jumpfish.gameobjects.MyLizard.POSICIONLIZARD;
import com.mygdx.jumpfish.gameobjects.gui.GUI;
import com.mygdx.jumpfish.transactions.ScreenTransition;
import com.mygdx.jumpfish.transactions.ScreenTransitionSlice;
import com.mygdx.jumpfish.transactions.ScreenTransitionSlide;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.CameraHelper;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MyAudioManager;







public class WorldControllerMy extends InputAdapter implements Disposable {

//	public GUI Mygui;
	private static final String TAG = WorldControllerMy.class.getName();
	
	private DirectedGameMy game;
	private LevelMy level; 
	
//	 static public float lives; //переделать и отдать в свойство рфбки
	public int score;
	
	public CameraHelper cameraHelper;
	//public boolean goalReached;
	private boolean firtclick=false;

	private float timeLeftGameOverDelay;

	public static float globalcamx;
	public static float globalcamy;
	public static float storonaprizka;
	public static float timschetchik;
	
	private String tip; 
	private String name;
	private boolean below = false;
	// Rectangles for collision detection
	public enum TARGETCAM {
		LIZARD, FISH,NONE
	}
	static public TARGETCAM TargetCAM;
	
	public static boolean doubleclick_first=false;
	public static boolean doubleclick_second=false;
	public static float timertoclick=0;
    
	public WorldControllerMy(DirectedGameMy game2,String namemap ) {
	
		name=namemap;
		this.game = game2;
		init(namemap);
	}

	private void init(String namemap) {
		
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		
		timeLeftGameOverDelay = 3;
		timschetchik=0;
		
		
		
		initLevel(namemap);
		
		
	}


	private void initLevel(String namemap) {
		///System.out.println(Integer.parseInt(name) );
		firtclick=true;
		score = 0;
		level = new LevelMy(namemap+".tmx"); 
	//	cameraHelper.setPosition(10, 10);
		cameraHelper.setTarget(level.getPlayer());
		
	
	
		cameraHelper.setXY(MapUtils.getProperty(level.map.getProperties(),"dlinamap",1f),MapUtils.getProperty(level.map.getProperties(),"visotamap",1f));
	
		level.goalReached = false;
		level.notvisiblfish = false;
		// cameraHelper.addZoom(1);
		
	//	Mygui = new GUI(level.getPlayer(),level.ECcollection.size);
		
if(level.getLizard()!=null)	cameraHelper.setTarget(level.getLizard()); // добавил
		
	}
	public LevelMy getlevel(){
		return level;
	}


 static	public Vector2 getTrajectoryPoint_controller(Vector2 startingPosition,Vector2 startingVelocity,float n,LevelMy level){
	  float t = 1 / 60.0f;
	 Vector2 stepVelocity = new Vector2(startingVelocity.x*t,startingVelocity.y*t);
	 Vector2 stepGravity  = new Vector2(level.getWorld().getGravity().x*t*t,level.getWorld().getGravity().y*t*t);
	 Vector2 my=new Vector2(stepVelocity.x*n,stepVelocity.y*n);
	 Vector2 my2=new Vector2(stepGravity.x*0.5f*(n*n+n),stepGravity.y*0.5f*(n*n+n));
		return startingPosition.add(my).add(my2);
	}

	
	public Vector2 geBOOM_smash_POS(){
		return level.getPlayer().BOOM_smash_POS;
	}
	public void update(float deltaTime) {
		if(cameraHelper.getTarget()==level.getPlayer())TargetCAM=TargetCAM.FISH;
		else if(cameraHelper.getTarget()==level.getLizard())TargetCAM=TargetCAM.LIZARD;

		else TargetCAM=TargetCAM.NONE;
		
		if(TargetCAM==TARGETCAM.LIZARD&&!level.LizardYazik.ropeshoot){
			
		
			
	if (doubleclick_first==true){ timertoclick++;}
	
	if(timertoclick>=15){timertoclick=0;doubleclick_first=false;doubleclick_second=false;}
	
	if((doubleclick_second==false&&(level.getLizard().posliz==POSICIONLIZARD.DOWN||level.getLizard().posliz==POSICIONLIZARD.UP||level.getLizard().posliz==POSICIONLIZARD.FALLING))&&level.getLizard().numFootContacts!=0){
		if(WorldRendererMy.x2<=WorldRendererMy.x1&&Gdx.input.isTouched()){
			level.getLizard().getBody().applyLinearImpulse(new Vector2(-level.getLizard().getBody().getMass()*(1.1f+level.getLizard().getBody().getLinearVelocity().x),0.02f), level.getLizard().getBody().getWorldCenter(), true);
			level.getLizard().animstat=ANIMSTAT.RUN;	
		}
		else if(WorldRendererMy.x2>WorldRendererMy.x1+0.5f&&Gdx.input.isTouched()){
			level.getLizard().getBody().applyLinearImpulse(new Vector2(level.getLizard().getBody().getMass()*(1.1f-level.getLizard().getBody().getLinearVelocity().x),0.02f), level.getLizard().getBody().getWorldCenter(), true);
			level.getLizard().animstat=ANIMSTAT.RUN;
			//level.getLizard().getBody().setLinearVelocity(2,0);
		}
		}
	else if(doubleclick_second==false&&level.getLizard().posliz!=POSICIONLIZARD.DOWN&&level.getLizard().posliz!=POSICIONLIZARD.FALLING&&level.getLizard().posliz!=POSICIONLIZARD.UP&&level.getLizard().numFootContacts!=0){
		if(WorldRendererMy.y2<=WorldRendererMy.y1&&Gdx.input.isTouched()){
			level.getLizard().getBody().applyLinearImpulse(new Vector2(0,-level.getLizard().getBody().getMass()*(1.1f+level.getLizard().getBody().getLinearVelocity().y)), level.getLizard().getBody().getWorldCenter(), true);
			level.getLizard().animstat=ANIMSTAT.RUN;
		}
			//level.getLizard().getBody().setLinearVelocity(0,-2);
		else if(WorldRendererMy.y2>WorldRendererMy.y1&&Gdx.input.isTouched()){
			//level.getLizard().getBody().setLinearVelocity(0,2);
			level.getLizard().getBody().applyLinearImpulse(new Vector2(0,level.getLizard().getBody().getMass()*(1.1f-level.getLizard().getBody().getLinearVelocity().y)), level.getLizard().getBody().getWorldCenter(), true);
			level.getLizard().animstat=ANIMSTAT.RUN;
		}
		}		

		}
	//	System.out.println(doubleclick_first);
	//	System.out.println(timertoclick);

		
		
		
		

		
//	System.out.println(getTrajectoryPoint(level.getPlayer().getBody().getPosition(),new Vector2(level.getPlayer().PreperingTime * 0.085f* WorldRendererMy.cos,level.getPlayer().PreperingTime * 0.085f* WorldRendererMy.sin),2,level));

		
	
		Gdx.input.setInputProcessor(null);
		if (level.timer<0.5f)
			 Gdx.input.setInputProcessor(null);
		else 
			Gdx.input.setInputProcessor(this);
	//	Mygui.update(deltaTime,level.getPlayer().inwater,(int)level.getPlayer().lives);
		isGameOver();
		handleDebugInput(deltaTime);
		handleInputGame(deltaTime);
		
		
		if (isGameOver() ) {
			
			level.notvisiblfish=true;
			timeLeftGameOverDelay -= deltaTime;
			Gdx.input.setInputProcessor(null);}
			if (timeLeftGameOverDelay < 0){
				 Gdx.input.setInputProcessor(null); // / отключает управление
				 
				backToMenu();
		}
			if ( level.goalReached) {
				level.notvisiblfish=true;
				timeLeftGameOverDelay -= deltaTime;
				Gdx.input.setInputProcessor(null);}
				if (timeLeftGameOverDelay < 0){
					 Gdx.input.setInputProcessor(null); // / отключает управление
					 
					 backToMenuSelect();
			}
	
		
		LogikaObektov(Gdx.graphics.getDeltaTime());// тут 
		
		if (cameraHelper.hasTarget(level.getPlayer())||cameraHelper.hasTarget(level.getLizard())) {
		//	zoomingtest();
			if (!level.getPlayer().inwater) {
				level.getPlayer().lives = level.getPlayer().lives - 0.01f;
			}
			ChangeViw();
			
			level.updateokno(deltaTime);
			if (level.pause==false&&level.pokazat==false){
			level.update(deltaTime);} // тут 
			
			cameraHelper.update(deltaTime);// тут 
			
				goalReachedtEST();
			}
		
		
	}

	public boolean isGameOver() {
	
		return level.getPlayer().lives < 0;
		
	}

	public void goalReachedtEST() {
		if (!level.goalReached&&level.ECcollection.size==0&&level.getPlayer().body.getPosition().x<= level.portal.getBody().getPosition().x+0.5+0.5&&level.getPlayer().body.getPosition().x>= level.portal.getBody().getPosition().x&&
				level.getPlayer().body.getPosition().y<= level.portal.getBody().getPosition().y+0.5&&level.getPlayer().body.getPosition().y>= level.portal.getBody().getPosition().y	) {
			level.goalReached = true;level.notvisiblfish=true;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.portal);
		GamePreferences prefs = GamePreferences.instance;
		//prefs.levelpr=10;
				prefs.saveprogress(Integer.parseInt(name));
			//	prefs.save();
			System.out.println(level.goalReached);
			System.out.println(name+"СОХРАНИЛ");
			Gdx.input.setInputProcessor(null);
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
			init(name); //исправить
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
		level.baloonoff();
		Gdx.input.setInputProcessor(null); // / отключает управление
		// switch to menu screen
		ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);
		
		game.setScreen(new MenuScreenMy(game),transition);
	}
	private void backToMenuSelect() {
		level.baloonoff();
		Gdx.input.setInputProcessor(null); // / отключает управление
		// switch to menu screen
		ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);
		game.setScreen(new SelectMenuScreenMy(game),transition);
	}


	public void dispose() {
		level.dispose();
	}

	private void vistreligroka(){
		if(WorldRendererMy.y1<WorldRendererMy.y2){
		if (WorldRendererMy.x1 < WorldRendererMy.x2)
		{level.getPlayer().vistrel(level.getWorld(),WorldRendererMy.cos,WorldRendererMy.sin);}
		if (WorldRendererMy.x2 < WorldRendererMy.x1)
		{level.getPlayer().vistrel(level.getWorld(),-WorldRendererMy.cos,WorldRendererMy.sin);}
		level.getPlayer().numbervistrel=level.getPlayer().numbervistrel-1;}
	}
	private void ChangeNavigation(int x, int y) {
	
		if (level.pokazat==false
			//	&&level.pokazattext==false
		&&!level.getPlayer().menu
				){
		if (WorldRendererMy.x1 <= WorldRendererMy.x2	&& level.getPlayer().PreperingTime >= 20&& WorldRendererMy.y2 > WorldRendererMy.y1&&level.jump) {
	//		level.getPlayer().body.applyLinearImpulse(new Vector2(level.getPlayer().PreperingTime * 0.085f* WorldRendererMy.cos,level.getPlayer().PreperingTime * 0.085f* WorldRendererMy.sin),level.getPlayer().body.getPosition(), true);
			level.getPlayer().body.setLinearVelocity(new Vector2(level.getPlayer().PreperingTime * 0.085f* WorldRendererMy.cos,level.getPlayer().PreperingTime * 0.085f* WorldRendererMy.sin));
			level.getPlayer().whilbeslap = true;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.longjump,1,1);
			
		} else
		if (WorldRendererMy.x1 > WorldRendererMy.x2	&& level.getPlayer().PreperingTime >= 20&& WorldRendererMy.y2 > WorldRendererMy.y1&&level.jump) {
		//	level.getPlayer().body.applyLinearImpulse(new Vector2(-level.getPlayer().PreperingTime * 0.085f * WorldRendererMy.cos,level.getPlayer().PreperingTime * 0.085f	* WorldRendererMy.sin), level.getPlayer().body.getPosition(), true);
			level.getPlayer().body.setLinearVelocity(new Vector2(-level.getPlayer().PreperingTime * 0.085f * WorldRendererMy.cos,level.getPlayer().PreperingTime * 0.085f	* WorldRendererMy.sin));
			level.getPlayer().whilbeslap = true;
			
			MyAudioManager.instance.play(AssetsMy.instance.sounds.longjump,1,1);
			
		} else
		if (WorldRendererMy.x1 < WorldRendererMy.x2&& level.getPlayer().PreperingTime < 20) {
			
			level.getPlayer().body.applyLinearImpulse(new Vector2(2f, 1f),level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = false;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.jump,1,1);
			
		} else 
		if (WorldRendererMy.x1 > WorldRendererMy.x2&& level.getPlayer().PreperingTime < 20) {
			
			level.getPlayer().body.applyLinearImpulse(new Vector2(-2f, 1f),level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = false;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.jump,1,1);
			
			
		}
		storonaprizkais(level.getPlayer().viewDirection);
		
				}
	}
	private void ChangeNavigationsmash(int x, int y) {
		if (level.pokazat==false&&
				level.pokazattext==false&&!level.getPlayer().menu
						){
		if (WorldRendererMy.x1 < WorldRendererMy.x2) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(2.5f, 1f),level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = false;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.jump,1,1);
		} else 
		if (WorldRendererMy.x1 > WorldRendererMy.x2) {
			level.getPlayer().body.applyLinearImpulse(new Vector2(-2f, 1f),	level.getPlayer().body.getPosition(), true);
			level.getPlayer().whilbeslap = false;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.jump,1,1);
		}
		storonaprizkais(level.getPlayer().viewDirection);
	}}
	private void doublejump(int x, int y) {
		if (level.pokazat==false&&
				level.pokazattext==false&&!level.getPlayer().menu&&level.getPlayer().mozhnodoublejump
						){if (WorldRendererMy.x1 < WorldRendererMy.x2){		 
							level.getPlayer().numberdoublejump=level.getPlayer().numberdoublejump-1;
			level.getPlayer().body.setLinearVelocity(0,0);
			level.getPlayer().body.applyLinearImpulse(new Vector2(4f,7f),new Vector2(0f, 0f), true);
			MyAudioManager.instance.play(AssetsMy.instance.sounds.jump,1,1);
			storonaprizkais(level.getPlayer().viewDirection);}
						else if (WorldRendererMy.x1 > WorldRendererMy.x2){	
							level.getPlayer().numberdoublejump=level.getPlayer().numberdoublejump-1;
							level.getPlayer().body.setLinearVelocity(0,0);
							level.getPlayer().body.applyLinearImpulse(new Vector2(-4f,7f),new Vector2(0f, 0f), true);
							MyAudioManager.instance.play(AssetsMy.instance.sounds.jump,1,1);
							storonaprizkais(level.getPlayer().viewDirection);}
			level.getPlayer().mozhnodoublejump=false;
		}
	}
	private void  storonaprizkais (VIEW_DIRECTION viewDirection){
		if (viewDirection == VIEW_DIRECTION.LEFT){
			storonaprizka=1;
		}
		else
			storonaprizka=-1;
	}
	private void ChangeViw() {
		if (WorldRendererMy.x1 < WorldRendererMy.x2)
			level.getPlayer().viewDirection = VIEW_DIRECTION.RIGHT;
		if (WorldRendererMy.x1 > WorldRendererMy.x2)
			level.getPlayer().viewDirection = VIEW_DIRECTION.LEFT;
	}
	private void   touchmenu() {
		if(WorldRendererMy.x1 < WorldRendererMy.x2+0.5f&&WorldRendererMy.x1 > WorldRendererMy.x2-0.5f
				&&WorldRendererMy.y1 < WorldRendererMy.y2+0.5f&&WorldRendererMy.y1 > WorldRendererMy.y2	-0.3f&&(level.getPlayer().numberdoublejump!=0||level.getPlayer().numberhelthbonus!=0||level.getPlayer().numberplatforms!=0||level.getPlayer().numbervistrel!=0)
				){
			if(level.getPlayer().addplatforms==false&&level.getPlayer().vistrel==false&&level.getPlayer().doublejump==false&&level.getPlayer().animation!=level.getPlayer().fish_swim){
						level.getPlayer().showmenu();
					}
			else if(level.getPlayer().vistrel==true){
				level.getPlayer().vistrel=false;
							}
			else if(level.getPlayer().doublejump==true){
			level.getPlayer().doublejump=false;
						}
			else	 if(level.getPlayer().addplatforms==true){
				level.getPlayer().addplatforms=false;
			}
		
			 }

	}
	private void   touchhels() {
		if(WorldRendererMy.x1 < WorldRendererMy.x2+0.5f&&WorldRendererMy.x1 > WorldRendererMy.x2-0.5f
				&&WorldRendererMy.y1 < WorldRendererMy.y2+0.5f-0.7f&&WorldRendererMy.y1 > WorldRendererMy.y2-0.5f-0.7f
					)
		{System.out.println("Взял жизнь");
if(level.getPlayer().numberhelthbonus<0){level.getPlayer().showmenu();}
if(level.getPlayer().numberhelthbonus>0){
		Objects_logik.getInst().power(level.getPlayer(),Constants.PowerUp);
		if(level.globaljump){
		level.jump=true;}
		level.getPlayer().numberhelthbonus=level.getPlayer().numberhelthbonus-1;
		level.getPlayer().showmenu();
				}
		
		
			}
		
	}
	private void   touchdoublejump() {
		if(WorldRendererMy.x1 < WorldRendererMy.x2+0.5f&&WorldRendererMy.x1 > WorldRendererMy.x2-0.5f
				&&WorldRendererMy.y1 < WorldRendererMy.y2+0.5f+0.7f&&WorldRendererMy.y1 > WorldRendererMy.y2-0.3f+0.7f)
		{
			System.out.println("Взял даблджамп");
			if(level.getPlayer().doublejump==true){
				level.getPlayer().doublejump=false;
				level.getPlayer().showmenu();
			}
		if(level.getPlayer().numberdoublejump>0&&level.getPlayer().doublejump==false){
			level.getPlayer().doublejump=true;
			level.getPlayer().showmenu();
				}
		if(level.getPlayer().numberdoublejump<=0){
			level.getPlayer().doublejump=false;
			level.getPlayer().showmenu();
				}
		
			}
		if(level.getPlayer().numberdoublejump<=0){
			level.getPlayer().doublejump=false;
		
				}
	}
	private void   touchvistrel() {
		if(WorldRendererMy.x1 < WorldRendererMy.x2+0.5f-1f&&WorldRendererMy.x1 > WorldRendererMy.x2-0.5f-1f
				&&WorldRendererMy.y1 < WorldRendererMy.y2+0.5f&&WorldRendererMy.y1 > WorldRendererMy.y2-0.5f)
		{
			System.out.println("Взял touchvistrel");
			if(level.getPlayer().vistrel==true){
				level.getPlayer().vistrel=false;
				level.getPlayer().showmenu();
			}
		if(level.getPlayer().numbervistrel>0&&level.getPlayer().vistrel==false){
			level.getPlayer().vistrel=true;
			level.getPlayer().showmenu();
				}
		if(level.getPlayer().numbervistrel<=0){
			level.getPlayer().vistrel=false;
			level.getPlayer().showmenu();
				}
		
			}
		
	}
	private void   touchaddobj() {
		if(WorldRendererMy.x1 < WorldRendererMy.x2+0.5f+1f&&WorldRendererMy.x1 > WorldRendererMy.x2-0.5f+1f
				&&WorldRendererMy.y1 < WorldRendererMy.y2+0.5f&&WorldRendererMy.y1 > WorldRendererMy.y2-0.5f)
		{
			System.out.println("Взял touchaddobj!!!");
			if(level.getPlayer().addplatforms==true){
				level.getPlayer().addplatforms=false;
				level.getPlayer().showmenu();
			}
		if(level.getPlayer().numberplatforms>0&&level.getPlayer().addplatforms==false){
			level.getPlayer().addplatforms=true;
			level.getPlayer().showmenu();
				}
		if(level.getPlayer().numberplatforms<=0){
			level.getPlayer().addplatforms=false;
			level.getPlayer().showmenu();
				}
		
			}
		if(level.getPlayer().numberplatforms<=0){
			level.getPlayer().addplatforms=false;
		
				}
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

	
	//	AssetsMy.instance.sounds.jump.play() ;
		
		/*if(!firtclick){
			level.pokazat=false;
			level.pokazattext=false;
			level.pause=false;
						}
		//System.out.print("ПРФЫЖОКУ");
		if(firtclick){
		
		firtclick=false;
		level.pokazat=true;
		level.pokazattext=false;
		level.pause=true;
				}*/
		if(firtclick){
			
			level.pokazat=false;
			level.pokazattext=false;
			level.pause=false;
					}
	   touchmenu();
	   if (cameraHelper.hasTarget(level.getPlayer())&& level.getPlayer().notuse == false&&level.getPlayer().addplatforms){
		level.addplatform(WorldRendererMy.x2,WorldRendererMy.y2);}
	   
		if (cameraHelper.hasTarget(level.getPlayer())&& level.getPlayer().notuse == false&&level.getPlayer().vistrel&&!level.getPlayer().perezaridka){
			vistreligroka();
				} 
		
		if (level.getPlayer().canmoov&&!level.getPlayer().vistrel&&!level.getPlayer().addplatforms){
		
		if (cameraHelper.hasTarget(level.getPlayer())&& level.getPlayer().notuse == true&&level.getPlayer().doublejump&&level.getPlayer().numberdoublejump>0
				//&&level.getPlayer().whilbeslap /// ну както так))
				) {
			doublejump(x, y);
			
		}
		
		
		// TODO Auto-generated method stub
		if (cameraHelper.hasTarget(level.getPlayer())&& level.getPlayer().notuse == false) {
			if (level.getPlayer().PreperingTime != 0&&level.getPlayer().Timer==false){
				
			ChangeNavigation(x, y);
		
			level.getPlayer().Prepering = false;
			// level.myFish.PreperingTime = 0; // потом посмотрим, может не надо удалять
			}
			else if (level.getPlayer().PreperingTime != 0&&level.getPlayer().Timer==true){
				
				ChangeNavigationsmash(x, y);
				level.getPlayer().Prepering = false;
				// level.myFish.PreperingTime = 0; // потом посмотрим, может не надо удалять
				}
			if (level.getPlayer().pos != POSICION.FLY) {///пока не убирать
				level.getPlayer().pos = POSICION.FLY;		}///пока не убирать
		}
		if (cameraHelper.hasTarget(level.getPlayer())) {///пока не убирать
			level.getPlayer().notuse = true;///пока не убирать
	//		System.out.println("СТРАННО!!!");
			}///пока не убирать

		}
		if (	level.getPlayer().menu){
			touchhels();
			 touchdoublejump();
			 touchvistrel();
			 touchaddobj() ;}
		
		level.getPlayer().testmoov();
		
		
		

		return true;

	}
	
	public boolean Yazik(){
if(!level.Sensorfixtures.isEmpty())
		 for (Fixture it:level.Sensorfixtures)
		 {
			 if(it.getUserData()!=null){
			 String userDataTag = it.getUserData().toString();
			 if ( userDataTag.contains("energycell")) //large box or static ground
	              return true;
	         		 }}
	     
		 return false;
		
	}
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
		
		
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
	//System.out.println(TargetCAM);
		
		
		if(TargetCAM==TARGETCAM.LIZARD){
		
	
			level.touch(WorldRendererMy.x2-0.125f,WorldRendererMy.y2-0.125f,WorldRendererMy.x2+0.125f,WorldRendererMy.y2+0.125f);	
			if(Yazik()&&level.getLizard().numFootContacts!=0&&!level.LizardYazik.ropeshoot){
				level.LizardYazik.Ropeshoot(level.getLizard().getBody().getPosition().x, level.getLizard().getBody().getPosition().y);
				level.getLizard().getBody().setLinearVelocity(0,0);
				doubleclick_first=false;
				 timertoclick=0;
				 doubleclick_second=false;
			//	level.Ropeshoot();
				}	
			if(!level.LizardYazik.ropeshoot){
			
		if(WorldRendererMy.y2>WorldRendererMy.y1&&WorldRendererMy.tg>1
				&&doubleclick_first&&level.getLizard().m_jumpTimeout == 0
				&&(level.getLizard().CanJumpLizardUP()||level.getLizard().CanJumpLizardDOWN()
						||level.getLizard().CanJumpLizardLeft()||level.getLizard().CanJumpLizardRigt()))
		{doubleclick_second=true;
			level.getLizard().getBody().setLinearVelocity(0,0);
			if(level.getLizard().CanJumpLizardLeft()||level.getLizard().CanJumpLizardRigt())
			level.getLizard().getBody().applyLinearImpulse(new Vector2(0,2.5f),new Vector2(1,1),true);
			else
				level.getLizard().getBody().applyLinearImpulse(new Vector2(0,7.5f),new Vector2(1,1),true);
			level.getLizard().m_jumpTimeout = 15;
			level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.UP;
			level.getLizard().animstat=ANIMSTAT.JUMP;
			//level.getLizard().JUMPSTATE=true;
		} else
		if (WorldRendererMy.x1>WorldRendererMy.x2&&WorldRendererMy.tg<1&&doubleclick_first
				&&level.getLizard().m_jumpTimeout == 0&&(level.getLizard().CanJumpLizardUP()||level.getLizard().CanJumpLizardDOWN()||level.getLizard().CanJumpLizardLeft()||level.getLizard().CanJumpLizardRigt())){
			doubleclick_second=true; level.getLizard().getBody().setLinearVelocity(0,0);
			level.getLizard().getBody().applyLinearImpulse(new Vector2(-4,5),new Vector2(1,1),true);
			level.getLizard().m_jumpTimeout = 15;
			level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.LEFT;
			level.getLizard().animstat=ANIMSTAT.JUMP;
		//	level.getLizard().JUMPSTATE=true;
			}else
		if (WorldRendererMy.x2>WorldRendererMy.x1&&WorldRendererMy.tg<1&&doubleclick_first
				&&level.getLizard().m_jumpTimeout == 0&&(level.getLizard().CanJumpLizardUP()||level.getLizard().CanJumpLizardDOWN()||level.getLizard().CanJumpLizardLeft()||level.getLizard().CanJumpLizardRigt())){
			doubleclick_second=true; level.getLizard().getBody().setLinearVelocity(0,0);
			level.getLizard().getBody().applyLinearImpulse(new Vector2(4,5),new Vector2(1,1),true);
			level.getLizard().m_jumpTimeout = 15;
			level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.RIGHT;
			level.getLizard().animstat=ANIMSTAT.JUMP;
		//	level.getLizard().JUMPSTATE=true;
			}else
		if (WorldRendererMy.y1>WorldRendererMy.y2&&WorldRendererMy.tg>1&&doubleclick_first
				&&level.getLizard().m_jumpTimeout == 0&&(level.getLizard().CanJumpLizardUP()||level.getLizard().CanJumpLizardDOWN()||level.getLizard().CanJumpLizardLeft()||level.getLizard().CanJumpLizardRigt())){
			doubleclick_second=true;	 level.getLizard().getBody().setLinearVelocity(0,0);
			level.getLizard().getBody().applyLinearImpulse(new Vector2(0,-2.5f),new Vector2(1,1),true);
			level.getLizard().m_jumpTimeout = 15;
			level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.DOWN;
			level.getLizard().animstat=ANIMSTAT.JUMP;
		//	level.getLizard().JUMPSTATE=true;
			}
		
		
		}
		
		if(doubleclick_first==false&&timertoclick==0){doubleclick_first=true;}
		
		
			
		}
		
		
		
			
		
	
	//	level.newbomba(1, 1); //тест создаёт бомбы для теста
		
//		NOTMOOVCAM=false;
		if(level.getPlayer().numbervistrel<=0){
			level.getPlayer().vistrel=false;
		
				}
	//	System.out.println(WorldRendererMy.x2);
	//	System.out.println(level.getPlayer().getPosition().x);
		//System.out.println(level.getPlayer().getPosition().x);
	 ///!!!!!!!!!!!
		
		// TODO Auto-generated method stub
		if (cameraHelper.hasTarget(level.getPlayer())&& level.getPlayer().notuse == false&&level.getPlayer().canmoov&&!level.getPlayer().vistrel&&!level.getPlayer().addplatforms)
		 {
			level.getPlayer().pos = POSICION.PREPARING;
			
		
					}
		return true;
	}

	public LevelMy getPlayer(){
		return level;
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
		// level.groundedPlatform = null;
		

		level.getPlayer().z2=0;
		
		// ОБЩЁТ СТОЛКНОВЕНИ!!!!!
		Array<Contact> contactList = level.getWorld().getContactList();
		for (int i = 0; i < contactList.size; i++) {
			
			Contact contact = contactList.get(i);
			Objects_logik.getInst().OtskokIgrokaOtMashinki(contact,deltaTime,level.getPlayer())	;

			Objects_logik.getInst().Shar(contact,deltaTime,level.getPlayer())	;
			Objects_logik.getInst().VragprotivPlasmaball(contact,deltaTime);
	
			if(contact.isTouching()&&contact.getFixtureB().getUserData().equals("222")||contact.isTouching()&&contact.getFixtureA().getUserData().equals("222")){
			
				if(contact.getFixtureB().getUserData().equals("222"))
				contact.getFixtureB().setUserData("BAM");
				
				if(contact.getFixtureA().getUserData().equals("222"))
				contact.getFixtureA().setUserData("BAM");
			
							}
			if(contact.isTouching()&&contact.getFixtureB().getUserData().equals("fish_ball")||contact.isTouching()&&contact.getFixtureA().getUserData().equals("fish_ball")){
				
				if(contact.getFixtureB().getUserData().equals("fish_ball"))
				contact.getFixtureB().setUserData("PreParingBAM");
				
				if(contact.getFixtureA().getUserData().equals("fish_ball"))
				contact.getFixtureA().setUserData("PreParingBAM");
			
							}
		Objects_logik.getInst().LogikaMashinki(contact,deltaTime);
		 Objects_logik.getInst().OpredeleniePoverhnosti1(contact,deltaTime,level.getPlayer(),level.getPlayer().BOOM,level.getPlayer().BOOM_POS,level.getPlayer().BOOM_smash,below);
		
		}
		
		//ПРОВЕРКА ЧТО РЫБКА В ВОЗДУХЕ ВЫСТАВЛЕНИЕ ПАРАМЕТРОВ 
		// System.out.println(level.getPlayer().z2);
		if (level.getPlayer().z2 == 0) {
			//System.out.println("летим");
			level.getPlayer().BOOM_smash=false;
			level.getPlayer().BOOM=false;
			level.getPlayer().pos = POSICION.FLY;
			level.getPlayer().inwater = false;
			level.getPlayer().onGround = false;
			level.getPlayer().notuse = true;
		//	level.getPlayer().getBody().setFixedRotation(false);
			
		}
		if(level.getPlayer().getPosition().y <= 0){
			System.out.println("ПРИВЕТ ЖОПА");
			level.getPlayer().KillPlayer();
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
		
			if (Gdx.input.isKeyJustPressed(Keys.W)){
			//	if(level.getLizard().ropeshoot==false)
			//	level.Ropeshoot();
			
			//	level.getLizard().ropeshoot=true;
			}
			if (Gdx.input.isKeyJustPressed(Keys.Q)){
			//	level.Ropeshootoff(1, 1);
		//		level.getLizard().ropeshoot=false;
			}
			
			
			if (Gdx.input.isKeyPressed(Keys.U)&&level.getLizard().m_jumpTimeout == 0){
			//	level.getLizard().getBody().applyLinearImpulse(new Vector2(-5,0),new Vector2(1,1),true);
			//	level.getLizard().m_jumpTimeout = 15;
			//	level.getLizard().JUMPSTATE=true;
				if(level.getLizard().posliz==POSICIONLIZARD.DOWN||level.getLizard().posliz==POSICIONLIZARD.UP)
				level.getLizard().getBody().setLinearVelocity(-2,0);
				else level.getLizard().getBody().setLinearVelocity(0,-2);
				}
			if (Gdx.input.isKeyPressed(Keys.I)&&level.getLizard().m_jumpTimeout == 0){
			//	level.getLizard().getBody().applyLinearImpulse(new Vector2(5,0),new Vector2(1,1),true);
		//		level.getLizard().m_jumpTimeout = 15;
			//	level.getLizard().JUMPSTATE=true;
				if(level.getLizard().posliz==POSICIONLIZARD.DOWN||level.getLizard().posliz==POSICIONLIZARD.UP)
				level.getLizard().getBody().setLinearVelocity(2,0);
				else level.getLizard().getBody().setLinearVelocity(0,2);
				}
			
			
			if (Gdx.input.isKeyPressed(Keys.F))
				moveCamera(camMoveSpeed, 0);
		//	if (Gdx.input.isKeyPressed(Keys.A))
				
	///			moveCamera(0, camMoveSpeed);
	//		if (Gdx.input.isKeyPressed(Keys.S))
		//		moveCamera(0, -camMoveSpeed);	
			

			// Camera Controls (zoom)
			float camZoomSpeed = 1 * deltaTime;
			float camZoomSpeedAccelerationFactor = 5;
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
				camZoomSpeed *= camZoomSpeedAccelerationFactor;
			if (Gdx.input.isKeyPressed(Keys.COMMA))
				cameraHelper.addZoom(camZoomSpeed);
			
			if (Gdx.input.isKeyPressed(Keys.PERIOD))
				cameraHelper.addZoom(-camZoomSpeed);
		
			if (Gdx.input.isKeyPressed(Keys.SLASH))
				cameraHelper.setZoom(5);
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
