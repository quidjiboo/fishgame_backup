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

package com.mygdx.jumpfish.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.game.LevelMy;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.MyAudioManager;
import com.sun.glass.ui.Window.Level;



public class MyFish extends AbstractGameObject {
	public  boolean  perezaridka=false;
	public float timervistrel=0;
	public FishBall ball;
	public  boolean canmoov=true;
	public Array<FishBall> bools;
	public int numberhelthbonus=0;
	public boolean addplatforms=false;
	public int numberplatforms=0; // 4 максимум
	public boolean menu=false;
	public int numbervistrel=0;
	public boolean vistrel=false;
	public boolean mozhnodoublejump=true;
	public boolean doublejump=false;
	public int numberdoublejump=0;
	public static boolean Timer;
	public static float storonaprizka;
	public static float timschetchik;
	
	public boolean BOOM = false;
	public boolean BOOM_smash= false;
	public Vector2 BOOM_POS ;
	public Vector2 BOOM_smash_POS ;
	public int z2 ;
	public float lives;
	public Animation animNormal; // может приват !! 
	public Animation preparing; // может приват !! 
	public Animation slap;
	public Animation fly;
	public Animation fish_swim;
	public float Testspped;
	//public int PreperingTime=1;
	public float PreperingTimeold=0;
	public float PreperingTime1;
	public int MaxPOwer=100;
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
	
	public MyFish(Body b) {
				body = b;
				
		init();
	}

	private void init() {
		
		
		bools = new Array<FishBall>();
		Timer=false;
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
	//	playerPhysicsFixture.setDensity(2.25f);
		playerPhysicsFixture.setDensity(2.4f);
		playerPhysicsFixture.setRestitution(0);
		playerPhysicsFixture.setFriction(1);
		
		body.resetMassData();
		playerPhysicsFixture.setUserData("play");
		
		body.setFixedRotation(true);
//	
		
		

		
		
		dimension.set(1f, 1f);

		animNormal = AssetsMy.instance.fish.animNormal;
		preparing = AssetsMy.instance.fish.anim_fish_preparing_to_fly;
		slap = AssetsMy.instance.fish.anim_fish_slap;
		fly = AssetsMy.instance.fish.anim_fish_fly;
		fish_swim = AssetsMy.instance.fish.fish_swim;
		setAnimation(animNormal);

		origin.set(dimension.x / 2, dimension.y / 2);
		
		Filter f = new Filter();
		f.groupIndex = -7;
		body.getFixtureList().first().setFilterData(f);
		
		// Particles
				dustParticles.load(Gdx.files.internal("particles/111.pfx"), Gdx.files.internal("particles"));
				dustParticles1.load(Gdx.files.internal("particles/111.pfx"), Gdx.files.internal("particles"));
				dustParticles2.load(Gdx.files.internal("particles/boom.pfx"), Gdx.files.internal("particles"));
				numberplatforms=0;
				numberhelthbonus=0;
				numbervistrel=0;
				numberdoublejump=0;
	}
	
	public void vistrel(World MY_f_world,float cos,float sin){
		 if (timervistrel==0){
			BodyDef bodyDef1 = new BodyDef();
		Body body1 = MY_f_world.createBody(bodyDef1);
		//bodyDef1.type = BodyType.DynamicBody;
		ball = new FishBall(body1,body,cos,sin);
		
		bools.add(ball);
		perezaridka=true;
		MyAudioManager.instance.play(AssetsMy.instance.sounds.vistrel_fish);
		 }
		
	}
	public void testmoov(){
		if(!menu)
			canmoov=true;
			else
				canmoov=false;	
	}
	
	public void showmenu(){
	
		whilbeslap=false;

		if (!menu){	if(numberhelthbonus!=0||numberdoublejump!=0||numberplatforms!=0||numbervistrel!=0){
			addplatforms=false;
			vistrel=false;
			doublejump=false;
			menu=true;
			System.out.println("Меню");
		}
		}
		
		else if (menu){
		
			menu=false;
			System.out.println("Закрыть меню");
			
		} 
	}
	
	public void resetgrav(float deltaTime){
		
		body.applyForce(new Vector2(body.getWorld().getGravity().x,body.getMass()*-body.getWorld().getGravity().y),body.getWorldCenter(), true);
	}
	public void update(float deltaTime,World MY_f_world,boolean canjump,boolean notvisible) {
	//	
	
//		System.out.println("angle="+body.getTransform().getRotation());
	
		if(!notvisible){
			
			super.update(deltaTime);
	//		System.out.println("Massa="+body.getMass());
	//		System.out.println("Worldgrav="+body.getWorld().getGravity().y);

		if(perezaridka)
			timervistrel=timervistrel+0.01f;
		if(timervistrel>=1){
			perezaridka=false;
		timervistrel=0;}
		
		for (FishBall bool : bools)
		{
			bool.update(deltaTime);
		//	System.out.println(bool.getBody().getFixtureList().get(0).getUserData());
		if(bool.getBody().getFixtureList().get(0).getUserData().equals("BAM")){	MY_f_world.destroyBody(bool.body);bools.removeValue(bool, true);
		}
			}
		
	if(!canjump){MaxPOwer=1;}
	else{MaxPOwer=100;}
//	super.update(deltaTime);
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
		MyAudioManager.instance.play(AssetsMy.instance.sounds.onground,1,1);
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
		
			
			 
		if (PreperingTimeold<=PreperingTime&&Prepering == true && PreperingTime < MaxPOwer-20  ) {
			PreperingTimeold=PreperingTime;
			PreperingTime += 1;
			
		} 
		else if (PreperingTimeold<=PreperingTime&&Prepering == true && PreperingTime >= MaxPOwer-20&&PreperingTime < MaxPOwer  ) {
			PreperingTimeold=PreperingTime;
			PreperingTime = PreperingTime+0.3f ;
			
		} 
		else if (PreperingTimeold<=PreperingTime&&Prepering == true && PreperingTime >= MaxPOwer) {
			PreperingTimeold=PreperingTime;
			PreperingTimestop+= 1;
			if (PreperingTimestop>=70){
				PreperingTimestop=0;
			PreperingTime=	PreperingTime -1 ;
			}
			if(!inwater&&PreperingTimestop==0)
			setAnimation(preparing);
			
				}
		else if (PreperingTimeold>PreperingTime&&Prepering == true && PreperingTime <= MaxPOwer&& PreperingTime >=0) {
			PreperingTimeold=PreperingTime;
			PreperingTime=PreperingTime -1 ;
			if(!inwater&&PreperingTimestop==0)
			setAnimation(preparing);
			
								}
		else if (PreperingTimeold>PreperingTime&&Prepering == true && PreperingTime <= 0) {
			PreperingTimeold=PreperingTime;
			PreperingTime=	PreperingTime+1 ;
			if(!inwater&&PreperingTimestop==0)
			setAnimation(preparing);
			
								}
			else if	(Prepering == false&& PreperingTime !=0){
			PreperingTime = 0;
			PreperingTimestop=0;}
		
		
		
		if(inwater==true&&animation!=fish_swim)setAnimation(fish_swim);

		dustParticles.update(deltaTime);	
		dustParticles1.update(deltaTime);
		dustParticles2.update(deltaTime);
		
		

		///WorldControllerMy.m.LIFEBAR.setpersents(PreperingTime); // подумать зачем статик 
		if(numberdoublejump==0)doublejump=false;
		if(numberplatforms==0)addplatforms=false;
		if(numbervistrel==0)vistrel=false;
		}}
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
	 	if(timschetchik>1f&&timschetchik<1.1f){	MyAudioManager.instance.play(AssetsMy.instance.sounds.Hurt);timschetchik=1.11f;}
	 	if(timschetchik>2f&&timschetchik<2.1f){MyAudioManager.instance.play(AssetsMy.instance.sounds.Hurt);timschetchik=2.11f;}
	 	if(timschetchik>3f&&timschetchik<3.1f){MyAudioManager.instance.play(AssetsMy.instance.sounds.Hurt);	timschetchik=3.11f;}
	 	if(timschetchik>4f&&timschetchik<4.1f){MyAudioManager.instance.play(AssetsMy.instance.sounds.Hurt);timschetchik=4.11f;}
			if(timschetchik>5f)
			{	Timer=false;
			
				timschetchik=0;}
			else if(!Timer){
				timschetchik=0;}
			}
		
		public void smashplayer(Body body) {
			if(timschetchik==0)
			{System.out.println("PUUUI");
			body.applyLinearImpulse(storonaprizka*3 ,3,body.getPosition().x,body.getPosition().y, true);
			lives = lives - Constants.Smash;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.Hurt);
		
			}
			Timer=true;
			}
		public void KillPlayer() {
				lives = -1;
						}
	public void render(SpriteBatch batch,boolean notvisible) {
		for (FishBall bl : bools)
		{bl.render(batch);}
		if(!notvisible){
		dustParticles.draw(batch); // частицы
		TextureRegion reg = null;
		if (Preperinganim == true){
			reg = animation.getKeyFrame(stateTime, false);
		//batch.draw(reg.getTexture(), position.x - origin.x, position.y // УБРАЛ ЗАЧЕМТО
		//		- origin.y/2, origin.x, origin.y, dimension.x, dimension.y, // УБРАЛ ЗАЧЕМТО
		//		scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), // УБРАЛ ЗАЧЕМТО
		//		reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.RIGHT, false); // УБРАЛ ЗАЧЕМТО
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
			
	//	dustParticles1.draw(batch);// частицы
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
		dustParticles2.draw(batch);// частицы
		
	}

	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
}
