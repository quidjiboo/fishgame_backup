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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Library;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.game.LevelMy;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldControllerMy.TARGETCAM;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.MyAudioManager;




public class MyLizard extends AbstractGameObject {
	private ShaderProgram shader;
	
	public float smechnie_x=0;
	public float smechnie_y=0;
	//static public boolean ropeshoot=false; 
	public boolean JUMPSTATE=false;
	public int numFootContacts;
	public int m_jumpTimeout = 0;
	
	public HashSet<Fixture> fixturesDOWN;
	public HashSet<Fixture> fixturesUP;
	public HashSet<Fixture> fixturesLEFT;
	public HashSet<Fixture> fixturesRIGHT;
	public Animation yazikshooting;
	public Animation jump;
	public Animation fall;
	public Animation walking;
	public Animation animNormal; // может приват !! 

	
	

	public Fixture playerSensorFixture_down;
	public Fixture playerSensorFixture_up;
	public Fixture playerSensorFixture_left;
	public Fixture playerSensorFixture_right;

	public Fixture playerSensorFixture_puzo_niz;
	public Fixture playerSensorFixture_puzo_verh;
	public Fixture playerSensorFixture_puzo_left;
	public Fixture playerSensorFixture_puzo_right;
	public Fixture playerPhysicsFixture;
	public enum ANIMSTAT {
		RUN,STAND,JUMP,YAZ,HURT,FALL;
	}
	
	public ANIMSTAT animstat;
	
	public enum POSICIONLIZARD {
		UP,DOWN,LEFT_WALL,RIGHT_WALL,FALLING;
	}
	
	public POSICIONLIZARD posliz;
	
	
	
	public enum JUMPNAPRAVLENIE {
		LEFT, RIGHT,UP,DOWN,NONE;
	}
	public JUMPNAPRAVLENIE Jump_Napravlenie;
	
	
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
	}
	public VIEW_DIRECTION viewDirection;
	
	public enum VIEW_UP_DOWN {
		UP_VIEW, DOWN_VIEW
	}
	public VIEW_UP_DOWN viewDirection_Up_Down;
	
	public MyLizard(World w) {
		
		BodyDef bodyDef = new BodyDef();
		body = w.createBody(bodyDef);		
		
		body.setTransform(2, 9, 0);
		body.setType(BodyType.DynamicBody);
		body.setUserData("lizard_body");
		init();
	}

	private void init() {
		ShaderProgram.pedantic = false;
		
		shader = new ShaderProgram(Gdx.files.internal("shaders/invert.vsh"), Gdx.files.internal("shaders/invert.fsh"));
		
		if(!shader.isCompiled())
			System.out.println(shader.getLog());
	
		
		Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
		animstat=ANIMSTAT.STAND;
		fixturesDOWN = new HashSet<Fixture>()	;
		fixturesUP = new HashSet<Fixture>()	;
		fixturesLEFT = new HashSet<Fixture>()	;
		fixturesRIGHT = new HashSet<Fixture>()	;
		numFootContacts=0;
		
		
		viewDirection_Up_Down=VIEW_UP_DOWN.DOWN_VIEW;
		viewDirection = VIEW_DIRECTION.LEFT;
		posliz=POSICIONLIZARD.DOWN;
		

		
		
		Vector2[] vertices = new Vector2[8];
		vertices[0] = new Vector2(-0.45f  , -0.5f  );
	    vertices[1] = new Vector2(-0.5f ,-0.45f  );
	    vertices[2] = new Vector2(-0.5f , 0.45f);
	    vertices[3] = new Vector2(-0.45f , 0.5f);
	    vertices[4] = new Vector2(0.45f ,  0.5f);
	    vertices[5] = new Vector2(0.5f ,  0.45f);     
	    vertices[6] = new Vector2(0.5f , -0.45f);
	    vertices[7] = new Vector2(0.45f , -0.5f);
		 
		
	
		PolygonShape poly = new PolygonShape();		
		poly.set(vertices);
		//poly.setAsBox(0.5f,0.5f);
		playerPhysicsFixture = body.createFixture(poly,2);
			
	//	PolygonShape poly1 = new PolygonShape();		
		poly.setAsBox(0.25f,0.05f,new Vector2(0,-0.5f),0);
		playerSensorFixture_down = body.createFixture(poly,0);
		poly.setAsBox(0.25f,0.05f,new Vector2(0,0.5f),0);
		playerSensorFixture_up = body.createFixture(poly,0);
		poly.setAsBox(0.05f,0.25f,new Vector2(0.5f,0),0);
		playerSensorFixture_right = body.createFixture(poly,0);
		poly.setAsBox(0.05f,0.25f,new Vector2(-0.5f,0),0);
		playerSensorFixture_left = body.createFixture(poly,0);
		poly.setAsBox(0.45f,0.18f,new Vector2(0f,-0.29f),0);
	//	playerSensorFixture_puzo_niz = body.createFixture(poly,0);
	//	poly.setAsBox(0.45f,0.18f,new Vector2(0f,0.29f),0);
	//	playerSensorFixture_puzo_verh = body.createFixture(poly,0);
	//	poly.setAsBox(0.18f,0.45f,new Vector2(0.29f,0f),0);
	//	playerSensorFixture_puzo_right = body.createFixture(poly,0);
	//	poly.setAsBox(0.18f,0.45f,new Vector2(-0.29f,0f),0);
	//	playerSensorFixture_puzo_left = body.createFixture(poly,0);
		poly.dispose();	
		playerSensorFixture_down.setUserData("l_down");
		playerSensorFixture_up.setUserData("l_up");
		playerSensorFixture_right.setUserData("_right");
		playerSensorFixture_left.setUserData("_left");
		
	//	playerSensorFixture_puzo_niz.setUserData("lizard");
	//	playerSensorFixture_puzo_verh.setUserData("lizard");
	//	playerSensorFixture_puzo_left.setUserData("lizard");
	//	playerSensorFixture_puzo_right.setUserData("lizard");
		playerSensorFixture_down.setSensor(true);
		playerSensorFixture_up.setSensor(true);
		playerSensorFixture_right.setSensor(true);
		playerSensorFixture_left.setSensor(true);
		
		playerPhysicsFixture.setDensity(1f);
		playerPhysicsFixture.setRestitution(0);
		playerPhysicsFixture.setFriction(2);
		
		
		playerPhysicsFixture.setUserData("lizard");
			
	body.setFixedRotation(true);
	body.resetMassData();
//	
		
		

		
		
		dimension.set(1f, 1f);
		yazikshooting = AssetsMy.instance.fish.Lizaed_anim_yaz;
		animNormal = AssetsMy.instance.fish.Lizaed_anim_stand;
		walking = AssetsMy.instance.fish.Lizaed_anim_walk;
		jump = AssetsMy.instance.fish.Lizaed_anim_jump;
		fall = AssetsMy.instance.fish.Lizaed_anim_fall;
		setAnimation(animNormal);

		origin.set(dimension.x / 2, dimension.y / 2);
		
		Filter f = new Filter();
		f.groupIndex = -1;
		Filter fpuzo = new Filter();
		fpuzo.groupIndex = -23;
		body.getFixtureList().first().setFilterData(f);
		
	//	playerSensorFixture_puzo_left.setFilterData(f); //временно
	//	playerSensorFixture_puzo_right.setFilterData(f); //временно
	//	playerSensorFixture_puzo_niz.setFilterData(f); //временно
	//	playerSensorFixture_puzo_verh.setFilterData(f); //временно
				
	}
	
	
	
	public void grav_down(float deltaTime) {
		body.applyForce(
				new Vector2(body.getWorld().getGravity().x, body.getMass() * -2	* body.getWorld().getGravity().y),body.getWorldCenter(), true);
	}
	public void grav_left(float deltaTime) {
		body.applyForce(new Vector2(body.getMass() * 1* body.getWorld().getGravity().y, body.getMass()*-body.getWorld().getGravity().y), body.getWorldCenter(), true);
	}
	public void grav_right(float deltaTime) {
		body.applyForce(new Vector2(body.getMass() * -1* body.getWorld().getGravity().y, body.getMass()*-body.getWorld().getGravity().y), body.getWorldCenter(), true);
	}
	public boolean CanJumpLizardDOWN(){
		  if ( m_jumpTimeout > 0 )
	          return false;
		 
		
		 for (Fixture it:fixturesDOWN)
		 {
			 
			 String userDataTag = it.getUserData().toString();
			 if ( userDataTag.contains("flour")||userDataTag.contains("YES_ON")) //large box or static ground
	              return true;
	         		 }
	     
		 return false;
		
	}
	public boolean CanJumpLizardRigt(){
		  if ( m_jumpTimeout > 0 )
	          return false;
		 
		
		 for (Fixture it:fixturesRIGHT)
		 {
			 
			 String userDataTag = it.getUserData().toString();
			 if ( userDataTag.contains("YES_ON")) //large box or static ground
	              return true;
	         		 }
	     
		 return false;
		
	}
	public boolean CanJumpLizardLeft(){
		  if ( m_jumpTimeout > 0 )
	          return false;
		 
		
		 for (Fixture it:fixturesLEFT)
		 {
			 
			 String userDataTag = it.getUserData().toString();
			 if ( userDataTag.contains("YES_ON")) //large box or static ground
	              return true;
	         		 }
	     
		 return false;
		
	}
	public boolean CanJumpLizardUP(){
		  if ( m_jumpTimeout > 0 )
	          return false;
		 
		
		 for (Fixture it:fixturesUP)
		 {
			 
			 String userDataTag = it.getUserData().toString();
			 if ( userDataTag.contains("YES_ON")) //large box or static ground
	              return true;
	         		 }
	     
		 return false;
		
	}
	
	

	public void update(float deltaTime) {
		
		if(WorldControllerMy.TargetCAM!=TARGETCAM.LIZARD){
			if(numFootContacts==0){posliz=POSICIONLIZARD.FALLING;
			}else posliz=POSICIONLIZARD.DOWN;
		}
		else {
			if(fixturesDOWN.size()!=0&&!Gdx.input.isTouched())animstat=ANIMSTAT.STAND;
			if( animstat!=ANIMSTAT.STAND&&!Gdx.input.isTouched()&&(posliz==POSICIONLIZARD.DOWN||posliz==POSICIONLIZARD.LEFT_WALL||posliz==POSICIONLIZARD.RIGHT_WALL||posliz==POSICIONLIZARD.UP))animstat=ANIMSTAT.STAND;
			if(YazikRope.ropeshoot&&animstat!=ANIMSTAT.YAZ)animstat=ANIMSTAT.YAZ;
	//	if(Jump_Napravlenie!=JUMPNAPRAVLENIE.NONE&&posliz==POSICIONLIZARD.FALLING&&animstat!=ANIMSTAT.JUMP){animstat=ANIMSTAT.JUMP;System.out.println("FLY!!");}
			// можно вынести в контроллер
		/*	if((posliz==POSICIONLIZARD.DOWN||posliz==POSICIONLIZARD.UP)&&numFootContacts!=0){
				if(WorldRendererMy.x2<WorldRendererMy.x1&&Gdx.input.isTouched())body.setLinearVelocity(-2,0);	
				else if(WorldRendererMy.x2>WorldRendererMy.x1&&Gdx.input.isTouched()) body.setLinearVelocity(2,0);}
			else if(posliz!=POSICIONLIZARD.DOWN&&posliz!=POSICIONLIZARD.UP&&numFootContacts!=0){
				if(WorldRendererMy.y2<WorldRendererMy.y1&&Gdx.input.isTouched())body.setLinearVelocity(0,-2);
				else if(WorldRendererMy.y2>WorldRendererMy.y1&&Gdx.input.isTouched()) body.setLinearVelocity(0,2);
			}	*/		
			
	//		System.out.println(ropeshoot);
	//		System.out.println(Jump_Napravlenie);
			if(posliz==POSICIONLIZARD.LEFT_WALL&&!CanJumpLizardLeft()){posliz=POSICIONLIZARD.FALLING;}
			
			if(posliz==POSICIONLIZARD.RIGHT_WALL&&!CanJumpLizardRigt()){posliz=POSICIONLIZARD.FALLING;}
			
			
			if(numFootContacts==0&&fixturesRIGHT.size()==0&&fixturesLEFT.size()==0&&fixturesDOWN.size()==0&&fixturesUP.size()==0){posliz=POSICIONLIZARD.FALLING;}
			else
			if(numFootContacts==0&&fixturesUP.size()==0){posliz=POSICIONLIZARD.FALLING;}
			else 
			if(posliz==POSICIONLIZARD.LEFT_WALL&&Jump_Napravlenie==JUMPNAPRAVLENIE.RIGHT){posliz=POSICIONLIZARD.FALLING;}
			else
			if(posliz==POSICIONLIZARD.RIGHT_WALL&&Jump_Napravlenie==JUMPNAPRAVLENIE.LEFT){posliz=POSICIONLIZARD.FALLING;}
		
				else
			if(posliz==POSICIONLIZARD.UP&&Jump_Napravlenie==JUMPNAPRAVLENIE.DOWN){posliz=POSICIONLIZARD.FALLING;}
			
			if(CanJumpLizardDOWN()&&fixturesDOWN.size()!=0){posliz=POSICIONLIZARD.DOWN;}
			else
			if(CanJumpLizardRigt()&&fixturesRIGHT.size()!=0&&Jump_Napravlenie==JUMPNAPRAVLENIE.RIGHT){posliz=POSICIONLIZARD.RIGHT_WALL;Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;}
			else
			if(CanJumpLizardLeft()&&fixturesLEFT.size()!=0&&Jump_Napravlenie==JUMPNAPRAVLENIE.LEFT){posliz=POSICIONLIZARD.LEFT_WALL;Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;}
			else
			if(CanJumpLizardUP()&&fixturesUP.size()!=0&&Jump_Napravlenie==JUMPNAPRAVLENIE.UP){posliz=POSICIONLIZARD.UP;Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;}
			else
			if(CanJumpLizardDOWN()&&fixturesDOWN.size()!=0&&Jump_Napravlenie==JUMPNAPRAVLENIE.DOWN){posliz=POSICIONLIZARD.DOWN;Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;}	
			
			
			
				
		} 
	//	if(numFootContacts==0&&Jump_Napravlenie==JUMPNAPRAVLENIE.NONE){posliz=POSICIONLIZARD.DOWN;}
		
		
		
		super.update(deltaTime);
	
		
		
		if(m_jumpTimeout>0)
		m_jumpTimeout--;
	//	System.out.println(CanJumpLizardRigt());
	
		
		switch (posliz){
		case DOWN:
		
		
			rotation=0;
	//		playerSensorFixture_puzo_niz.setSensor(false);
	//		playerSensorFixture_puzo_verh.setSensor(true);
	//		playerSensorFixture_puzo_left.setSensor(true);
	//		playerSensorFixture_puzo_right.setSensor(true);
			viewDirection_Up_Down=VIEW_UP_DOWN.DOWN_VIEW;	
			break;
		case FALLING:
			rotation=0;
	//		playerSensorFixture_puzo_niz.setSensor(false);
	//		playerSensorFixture_puzo_verh.setSensor(true);
	//		playerSensorFixture_puzo_left.setSensor(true);
	//		playerSensorFixture_puzo_right.setSensor(true);
			viewDirection_Up_Down=VIEW_UP_DOWN.DOWN_VIEW;	
			break;
		case UP:
			viewDirection_Up_Down=VIEW_UP_DOWN.UP_VIEW;
	//		playerSensorFixture_puzo_verh.setSensor(false);
	//		playerSensorFixture_puzo_niz.setSensor(true);
	//		playerSensorFixture_puzo_left.setSensor(true);
	//		playerSensorFixture_puzo_right.setSensor(true);
			grav_down(deltaTime);
			rotation=0;
			break;
		case LEFT_WALL:
			grav_left(deltaTime);
	//		playerSensorFixture_puzo_verh.setSensor(true);
	//		playerSensorFixture_puzo_niz.setSensor(true);
	//		playerSensorFixture_puzo_left.setSensor(false);
	//		playerSensorFixture_puzo_right.setSensor(true);
			rotation=0;
			viewDirection_Up_Down=VIEW_UP_DOWN.DOWN_VIEW;	
		rotation=-90;
			break;
		case RIGHT_WALL:
			grav_right(deltaTime);
		//	playerSensorFixture_puzo_verh.setSensor(true);
		//	playerSensorFixture_puzo_niz.setSensor(true);
		//	playerSensorFixture_puzo_left.setSensor(true);
		//	playerSensorFixture_puzo_right.setSensor(false);
			viewDirection_Up_Down=VIEW_UP_DOWN.DOWN_VIEW;	
			rotation=90;
			break;
			
		}	

		switch (animstat){
		case STAND:	
			if(animation!=animNormal){
			animation=animNormal;
			animation.setPlayMode(PlayMode.LOOP_PINGPONG);}
			break;
			case RUN:
				if(animation!=walking){
				animation=walking;
				animation.setPlayMode(PlayMode.LOOP);}
				break;
			case JUMP:
				
				if(animation!=jump&&(Jump_Napravlenie==JUMPNAPRAVLENIE.UP||Jump_Napravlenie==JUMPNAPRAVLENIE.RIGHT||Jump_Napravlenie==JUMPNAPRAVLENIE.LEFT)){
					setAnimation(jump);
					}
				
				if(animation!=jump&&Jump_Napravlenie==JUMPNAPRAVLENIE.DOWN){
				
					setAnimation(jump);
					animation.setPlayMode(PlayMode.REVERSED);
					}
				if(animation==jump&&animation.isAnimationFinished(stateTime)){
					animstat=ANIMSTAT.FALL;
					}	
				break;
			case	YAZ:
			
			if(animation!=yazikshooting){
				animation.setPlayMode(PlayMode.NORMAL);
			setAnimation(yazikshooting);
			animation.setPlayMode(PlayMode.NORMAL);}
			break;
			
			case FALL:
				animation.setPlayMode(PlayMode.LOOP);
				if(animation!=fall){
					animation=fall;
					}
				break;
		}
//if(animation==jump){
//		System.out.println(animation.getKeyFrameIndex(stateTime));	

//		}	
		
		
	//	if(fixturesRIGHT.size()==0&&fixturesLEFT.size()==0&&fixturesDOWN.size()==0&&fixturesUP.size()==0){posliz=POSICIONLIZARD.DOWN;}
	//	if(fixturesLEFT.size()==0&&posliz==POSICIONLIZARD.LEFT_WALL){posliz=POSICIONLIZARD.DOWN;}
	
	//	if(JUMPSTATE==false&&numFootContacts==0&&posliz==POSICIONLIZARD.UP){posliz=POSICIONLIZARD.DOWN;}
		
	//	if(JUMPSTATE==false&&numFootContacts==0&&posliz==POSICIONLIZARD.LEFT_WALL){posliz=POSICIONLIZARD.DOWN;}
		
	//	if(JUMPSTATE==false&&numFootContacts==0&&posliz==POSICIONLIZARD.RIGHT_WALL ){posliz=POSICIONLIZARD.DOWN;}
		
	//	if(JUMPSTATE==true&&numFootContacts==0&&(Jump_Napravlenie==JUMPNAPRAVLENIE.LEFT||Jump_Napravlenie==JUMPNAPRAVLENIE.RIGHT)&&posliz!=POSICIONLIZARD.DOWN){posliz=POSICIONLIZARD.DOWN;}
	
		if (body.getLinearVelocity().x < -0.3f ||body.getLinearVelocity().x > 0.3f) {
		
			viewDirection = body.getLinearVelocity().x > 0 ? VIEW_DIRECTION.RIGHT : VIEW_DIRECTION.LEFT;
			} 	
		
		if ((body.getLinearVelocity().y < -0.05f ||body.getLinearVelocity().y > 0.05f)&&(posliz==POSICIONLIZARD.LEFT_WALL)) {
		
			viewDirection = body.getLinearVelocity().y > 0 ? VIEW_DIRECTION.LEFT : VIEW_DIRECTION.RIGHT;
			} else
				if ((body.getLinearVelocity().y < -0.05f ||body.getLinearVelocity().y > 0.05f)&&(posliz==POSICIONLIZARD.RIGHT_WALL)) {
					
					viewDirection = body.getLinearVelocity().y > 0 ? VIEW_DIRECTION.RIGHT : VIEW_DIRECTION.LEFT;
					}
		
		if((posliz==POSICIONLIZARD.DOWN||posliz==POSICIONLIZARD.UP||posliz==POSICIONLIZARD.FALLING)&&viewDirection==VIEW_DIRECTION.RIGHT){smechnie_x=-0.25f;smechnie_y=0;}
		else 	if((posliz==POSICIONLIZARD.DOWN||posliz==POSICIONLIZARD.UP||posliz==POSICIONLIZARD.FALLING)&&viewDirection==VIEW_DIRECTION.LEFT){smechnie_x=0.25f;smechnie_y=0;}
		else 	if(posliz==POSICIONLIZARD.RIGHT_WALL&&viewDirection==VIEW_DIRECTION.LEFT){smechnie_x=0;smechnie_y=0.25f;}
		else 	if(posliz==POSICIONLIZARD.RIGHT_WALL&&viewDirection==VIEW_DIRECTION.RIGHT){smechnie_x=0;smechnie_y=-0.25f;}
		else 	if(posliz==POSICIONLIZARD.LEFT_WALL&&viewDirection==VIEW_DIRECTION.LEFT){smechnie_x=0;smechnie_y=-0.25f;}
		else 	if(posliz==POSICIONLIZARD.LEFT_WALL&&viewDirection==VIEW_DIRECTION.RIGHT){smechnie_x=0;smechnie_y=0.25f;}
//		else 	if(posliz==POSICIONLIZARD.UP&&viewDirection==VIEW_DIRECTION.RIGHT){smechnie_x=0.25f;smechnie_y=0;}
		else{smechnie_x=0;smechnie_y=0;}
		
	
	}


	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
	
		
			
			
TextureRegion reg = null;
		
reg = animation.getKeyFrame(stateTime, false);


//shader.begin();
//	shader.setUniformf("u_resolution", width, height);
//shader.end();

//batch.setShader(shader);
		
		if(animation==jump||animation==fall){
			batch.draw(reg.getTexture(), position.x - origin.x+smechnie_x, position.y
					- origin.y+smechnie_y, origin.x, origin.y, dimension.x, dimension.y,
					scale.x*1.75f, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
					reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.LEFT, viewDirection_Up_Down==VIEW_UP_DOWN.UP_VIEW);
		}
		else
			{batch.draw(reg.getTexture(), position.x - origin.x+smechnie_x, position.y
					- origin.y+smechnie_y, origin.x, origin.y, dimension.x, dimension.y,
					scale.x*1.5f, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
					reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.LEFT, viewDirection_Up_Down==VIEW_UP_DOWN.UP_VIEW);}
//		shader.setUniformf("u_resolution", width, height);
			
		 batch.setShader(null);
	}

	public Body getBody(){
		return body;
	}
}
