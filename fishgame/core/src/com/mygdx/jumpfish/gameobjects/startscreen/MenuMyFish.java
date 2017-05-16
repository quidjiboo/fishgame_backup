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

package com.mygdx.jumpfish.gameobjects.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;



public class MenuMyFish extends AbstractGameObject {
	
	public static float storonaprizka;
	public static float timschetchik;
	public static boolean Timer=false;
	
	public boolean BOOM = false;
	public boolean BOOM_smash= false;
	public Vector2 BOOM_POS ;
	public Vector2 BOOM_smash_POS ;
	public float lives;
	public Animation animNormal; // ìîæåò ïðèâàò !! 
	public Animation preparing; // ìîæåò ïðèâàò !! 
	public Animation slap;
	public Animation fly;
	public Animation fish_swim;
	public float Testspped;
	//public int PreperingTime=1;
	public float PreperingTimeold=0;
	public float PreperingTime1;
	public int z2 ;
	//public Body box;
//	public boolean vpered = false;
	public int  Perekl=0;
	
	public boolean Preperinganim;
	public boolean whilbeslap=false;
	public boolean notuse=false;
	public Fixture playerSensorFixture;
	public Fixture playerPhysicsFixture;
	
	public ParticleEffect dustParticles = new ParticleEffect();
	public ParticleEffect dustParticles1 = new ParticleEffect();
	public ParticleEffect dustParticles2 = new ParticleEffect();
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
	}
	public VIEW_DIRECTION viewDirection;
	
	public MenuMyFish(Body b) {
				body = b;
		init();
	}

	private void init() {
		BOOM_POS = new Vector2(0f,0f);
		BOOM_smash_POS = new Vector2(0f,0f);
		PreperingTimestop = 0;
		PreperingTime = 0;
		viewDirection = VIEW_DIRECTION.LEFT;
		pos=POSICION.NORMAL;
		lives = Constants.LIVES_START;
		//CircleShape circle = new CircleShape();		
	//	circle.setRadius(0.3f);
	//	circle.setPosition(new Vector2(0, 0));
	//	playerSensorFixture = body.createFixture(circle, 0);
	//	circle.dispose();
	//	playerSensorFixture.setUserData("play");
		
		
		
		
		PolygonShape poly = new PolygonShape();		
		poly.setAsBox(0.398f,0.25f);
		playerPhysicsFixture = body.createFixture(poly,0);
		poly.dispose();		
		
		playerPhysicsFixture.setDensity(0);
		playerPhysicsFixture.setRestitution(0);
		playerPhysicsFixture.setFriction(1);
		playerPhysicsFixture.setUserData("play");
//	
		body.resetMassData();
	
		
		
		dimension.set(1f, 1f);
		
		animNormal = AssetsMy.instance.fish.animNormal;
		preparing = AssetsMy.instance.fish.anim_fish_preparing_to_fly;
		slap = AssetsMy.instance.fish.anim_fish_slap;
		fly = AssetsMy.instance.fish.anim_fish_fly;
		fish_swim = AssetsMy.instance.fish.fish_swim;
		setAnimation(animNormal);

		origin.set(dimension.x / 2, dimension.y / 2);
		
		
		// Particles
				dustParticles.load(Gdx.files.internal("particles/111.pfx"), Gdx.files.internal("particles"));
				dustParticles1.load(Gdx.files.internal("particles/111.pfx"), Gdx.files.internal("particles"));
				dustParticles2.load(Gdx.files.internal("particles/boom_nobland.pfx"), Gdx.files.internal("particles"));
				
	}
	
		


	public void update(float deltaTime) {
	
		super.update(deltaTime);
		//System.out.println(WorldControllerMy.BOOM_smash);
		
		dustParticles2.setPosition(BOOM_smash_POS.x ,BOOM_smash_POS.y);
		dustParticles.setPosition(BOOM_POS.x ,BOOM_POS.y);
		dustParticles1.setPosition(BOOM_POS.x ,BOOM_POS.y);
		if (BOOM&&animation==fly&&whilbeslap==true){
		dustParticles1.start();	
		dustParticles.start();
		}
		else{
		dustParticles1.allowCompletion();;	
		dustParticles.allowCompletion();	
		}
		
		if (BOOM_smash){
			dustParticles2.start();
			
		}
		else
		{dustParticles2.allowCompletion();}
	//System.out.println(body.getLinearVelocity().x);
	//if (body.getLinearVelocity().y-Testspped!=0)notuse=true;
		//if (body.getLinearVelocity().y==0&&pos==POSICION.FLY){pos=POSICION.DOWN; System.out.println("sleep!!!");Prepering=false;}
		if (pos==POSICION.FLY&&inwater==true){
			//Prepering=false;
			Preperinganim=false;
			pos=POSICION.NORMAL;
			if( animation!=fish_swim)
			setAnimation(fish_swim); 
			}
		
		
	if (pos==POSICION.FLY&&onGround==true){pos=POSICION.DOWN; Prepering=false;Preperinganim=false;}
	
		if (body.getLinearVelocity().x < -0.3f ||body.getLinearVelocity().x > 0.3f) {
			viewDirection = body.getLinearVelocity().x > 0 ? VIEW_DIRECTION.RIGHT : VIEW_DIRECTION.LEFT;
			} 
		
			switch (pos){
	case NORMAL:
		Prepering=false;
	//setAnimation(animNormal);
	//	System.out.println("lalala");
		break;
	case FLY:
		PreperingTime = 0; //test
		if(animation!=fly)
		setAnimation(fly);
		Prepering=false;
		Preperinganim=true;
		break;
	case PREPARING:
		Prepering=true;
		Preperinganim=true;
		if(animation!=fish_swim&&inwater==true){
			setAnimation(fish_swim);
			break;}
		else if(animation!=preparing&&inwater==false){
			System.out.println("dsfffffffsdfdsfdsf");
			setAnimation(preparing);break;}
		
		//pos=POSICION.NORMAL;
		
		break;
	case DOWN2:
		Prepering=false;
		//setAnimation(slap);
		PreperingTime1 += 1;
		if (PreperingTime1>50){
			
		setAnimation(animNormal);
		PreperingTime1=0;
		pos=POSICION.NORMAL;
		break;
		}
		break;
		
	case DOWN:
		Prepering=false;
		if (whilbeslap==true&&BOOM){
			
		setAnimation(slap);
		pos=POSICION.DOWN2;
		break;
		}
		else
		
		setAnimation(animNormal);
		pos=POSICION.NORMAL;
		break;
//	case SWIM:
//		if(animation!=fish_swim)
//			setAnimation(fish_swim);
//		Prepering=false;
//		PreperingTime1 += 1;
//		if (PreperingTime1>50){
//		setAnimation(fish_swim);
//		PreperingTime1=0;
//	//	Prepering=false;
//		pos=POSICION.NORMAL;}
	
	}
		
			
			 
		if (PreperingTimeold<=PreperingTime&&Prepering == true && PreperingTime < 100) {
			PreperingTimeold=PreperingTime;
			PreperingTime += 1;
			
		} 
		else if (PreperingTimeold<=PreperingTime&&Prepering == true && PreperingTime == 100) {
			PreperingTimeold=PreperingTime;
			PreperingTimestop+= 1;
			if (PreperingTimestop==40){
				PreperingTimestop=0;
			PreperingTime=	PreperingTime -1 ;
			}
			if(!inwater)
			setAnimation(preparing);
			
				}
		else if (PreperingTimeold>PreperingTime&&Prepering == true && PreperingTime < 100&& PreperingTime >0) {
			PreperingTimeold=PreperingTime;
			PreperingTime=PreperingTime -1 ;
			if(!inwater)
			setAnimation(preparing);
			
								}
		else if (PreperingTimeold>PreperingTime&&Prepering == true && PreperingTime == 0) {
			PreperingTimeold=PreperingTime;
			PreperingTime=	PreperingTime+1 ;
			if(!inwater)
			setAnimation(preparing);
			
								}
			else if	(Prepering == false&& PreperingTime !=0){
			PreperingTime = 0;
			PreperingTimestop=0;}
		
		
		
		if(inwater==true&&animation!=fish_swim)setAnimation(fish_swim);

		dustParticles.update(deltaTime);	
		dustParticles1.update(deltaTime);
		dustParticles2.update(deltaTime);

		///WorldControllerMy.m.LIFEBAR.setpersents(PreperingTime); // ïîäóìàòü çà÷åì ñòàòèê 
	}
	public float getPreperingTime(){
		return PreperingTime;
	}
		// animation = animNormal;
	public float getFriction(){
		return playerPhysicsFixture.getFriction();
	}
	
	public Body getBody(){
		return body;
	}
	
	public void setFriction(float f){
		
		playerPhysicsFixture.setFriction(f); 
	}
	
	public Vector2 getPosition(){
		return body.getPosition();
	}
	
	
	public Vector2 getVelocity() {
		return velocity;
	}
	public void timer(float deltaTime){
	 	if(Timer==true){
			timschetchik=timschetchik+deltaTime;}	
			
			if(timschetchik>5f)
			{	Timer=false;
				timschetchik=0;}
			else if(!Timer){
				timschetchik=0;}
			}
		
		public void smashplayer(Body body,float deltaTime) {
			if(timschetchik==0)
			{System.out.println("PUUUI");
			body.applyLinearImpulse(storonaprizka*3 ,3,body.getPosition().x,body.getPosition().y, true);
			lives = lives - Constants.Smash;
			}
			Timer=true;
			}
	public void render(SpriteBatch batch) {
		
		dustParticles.draw(batch); // ÷àñòèöû
		TextureRegion reg = null;
		if (Preperinganim == true){
			reg = animation.getKeyFrame(stateTime, false);
		//batch.draw(reg.getTexture(), position.x - origin.x, position.y // ÓÁÐÀË ÇÀ×ÅÌÒÎ
		//		- origin.y/2, origin.x, origin.y, dimension.x, dimension.y, // ÓÁÐÀË ÇÀ×ÅÌÒÎ
		//		scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), // ÓÁÐÀË ÇÀ×ÅÌÒÎ
		//		reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.RIGHT, false); // ÓÁÐÀË ÇÀ×ÅÌÒÎ
			} 
		else{
			reg = animation.getKeyFrame(stateTime, true);}
		
		
		if (Timer){ 
	
			if (Perekl==16||Perekl==26||Perekl==10||Perekl==11||Perekl==20||Perekl==21||Perekl==12||Perekl==13||Perekl==22||Perekl==23||Perekl==14||Perekl==15||Perekl==24||Perekl==25){
		batch.draw(reg.getTexture(), position.x - origin.x, position.y
				- origin.y/2, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.RIGHT, false);
		Perekl=Perekl+1;}
			else {Perekl=Perekl+1;
				}
			
	//	dustParticles1.draw(batch);// ÷àñòèöû
	/*	batch.draw(reg.getTexture(), position.x + origin.x + 1, position.y
				+ origin.y + 1, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, 0, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);*/
		}
		else if (!Timer)
			batch.draw(reg.getTexture(), position.x - origin.x, position.y
					- origin.y/2, origin.x, origin.y, dimension.x, dimension.y,
					scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
					reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.RIGHT, false);

		if (Perekl==27)Perekl=0;
		
		dustParticles1.draw(batch);
		dustParticles2.draw(batch);// ÷àñòèöû
		
	}


}
