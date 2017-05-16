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

import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.MyAudioManager;



public class VragTrinity extends AbstractGameObject {
	float cvet=1;
	public ParticleEffect dustParticles = new ParticleEffect();
	public ParticleEffect plasma1 = new ParticleEffect();
	public ParticleEffect plasma2 = new ParticleEffect();
	public ParticleEffect plasma3 = new ParticleEffect();
	public ParticleEffect boom = new ParticleEffect();
	float rastoianiey;
	float rastoianiex;
	float rastoianie;
	
	public Animation animnorm;
	public Animation animvistrel;
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
		}
	public VIEW_DIRECTION viewDirection;
	private int counter = 0;
	private boolean uvidel = false;
	private boolean vistrel=false;
	private boolean rendervrag=true;
	private int lives = 3;
	//private float generate=0;
	private float timofdia=0;
	private float timer;
	private float starty;
	private float startx;
	private float timerfreez;
	private float vistrelposy;
	private float vistrelposx;
	//private float generate=0;
	public int live=2;
	public NewPlasmaBall_Trin ball1;
	public NewPlasmaBall_Trin ball2;
	public NewPlasmaBall_Trin ball3;
	
	public Fixture  Fixture1;
	public Array<NewPlasmaBall_Trin> bools;
	
	BodyDef bodyDef1;
	
	public VragTrinity(Body b,World MY_f_world) {

	
				body = b;
		init(MY_f_world);
	}

	private void init(World MY_f_world) {
		dustParticles.load(Gdx.files.internal("particles/vistrel.pfx"), Gdx.files.internal("particles"));
		plasma1.load(Gdx.files.internal("particles/plasma1.pfx"), Gdx.files.internal("particles"));
		plasma2.load(Gdx.files.internal("particles/plasma1.pfx"), Gdx.files.internal("particles"));
		plasma3.load(Gdx.files.internal("particles/plasma1.pfx"), Gdx.files.internal("particles"));
		boom.load(Gdx.files.internal("particles/boom_vrag.pfx"), Gdx.files.internal("particles"));
		animnorm = AssetsMy.instance.fish.vrag_trinity;
		animvistrel = AssetsMy.instance.fish.trinityvistrel;
		setAnimation(animnorm);
		viewDirection = VIEW_DIRECTION.RIGHT;
		dimension.set(1,1);

	
		
	
	
		body.getFixtureList().get(0).setDensity(1);
		body.getFixtureList().get(0).setFriction(1f);
		body.setFixedRotation(true);
	
	//	body.setUserData("333");
		body.getFixtureList().get(0).setUserData("vrag3");
		
	//	box2DSpritestvol.flip(true, false);
		body.resetMassData();
		origin.set(dimension.x / 2-0.5f, dimension.y / 2-0.5f);
		starty=body.getPosition().y;
		startx=body.getPosition().x;
		vistrelposy=body.getPosition().y;
		vistrelposx=body.getPosition().x;
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
	//	Fixture1.setFilterData(f);
		bools = new Array<NewPlasmaBall_Trin>();	
		bodyDef1 = new BodyDef();
	}
	
	public void vistrel(float deltaTime ,World MY_f_world,float x_igr1,float y_igr1){
		if ((Math.abs(rastoianiex))<=5){
	if(counter==0){
	Body body1 = MY_f_world.createBody(bodyDef1);
	ball1 = new NewPlasmaBall_Trin(body1);
	Body body2 = MY_f_world.createBody(bodyDef1);
	ball2 = new NewPlasmaBall_Trin(body2);
	Body body3 = MY_f_world.createBody(bodyDef1);
	ball3 = new NewPlasmaBall_Trin(body3);
	bools.add(ball1);
	bools.add(ball2);
	bools.add(ball3);
	counter=+1;}
		
		
		
		{if(animation!=animvistrel&&!ball1.getBody().isActive()&&!ball2.getBody().isActive()&&!ball3.getBody().isActive())
			setAnimation(animvistrel);}
		if(animation==animvistrel&&animvistrel.isAnimationFinished(stateTime)){	
		//	if(dustParticles.isComplete())
				dustParticles.start();
				
				
				
				
				if(body.getPosition().x>x_igr1)
				ball1.getBody().setTransform(body.getPosition().x,body.getPosition().y+0.7f,0);
				else
					ball1.getBody().setTransform(body.getPosition().x+1,body.getPosition().y+0.7f,0);
				ball1.getBody().setAngularDamping(0);
				ball1.getBody().setAngularVelocity(0);
				ball1.getBody().setLinearVelocity(0,0);
				ball1.getBody().setActive(true);
				ball1.timerfreez=0;				
				
				if(body.getPosition().x>x_igr1)
				ball2.getBody().setTransform(body.getPosition().x,body.getPosition().y+0.8f,0);
				else
					ball2.getBody().setTransform(body.getPosition().x+1,body.getPosition().y+0.8f,0);
				ball2.getBody().setAngularDamping(0);
				ball2.getBody().setAngularVelocity(0);
				ball2.getBody().setLinearVelocity(0,0);
				ball2.getBody().setActive(true);
				ball1.timerfreez=0;	
				if(body.getPosition().x>x_igr1)
				ball3.getBody().setTransform(body.getPosition().x,body.getPosition().y+0.9f,0);
				else
					ball3.getBody().setTransform(body.getPosition().x+1,body.getPosition().y+0.9f,0);
				ball3.getBody().setAngularDamping(0);
				ball3.getBody().setAngularVelocity(0);
				ball3.getBody().setLinearVelocity(0,0);
				ball3.getBody().setActive(true);
				ball1.timerfreez=0;	
				if(body.getPosition().x>x_igr1)
				{ball1.getBody().applyForce(-1,0,ball1.getBody().getPosition().x,ball1.getBody().getPosition().y,true);
				ball2.getBody().applyForce(-1,1,ball2.getBody().getPosition().x,ball2.getBody().getPosition().y,true);
				
				ball3.getBody().applyForce(-1,0.2f,ball3.getBody().getPosition().x,ball3.getBody().getPosition().y,true);}
				if(body.getPosition().x<x_igr1)
				{ball1.getBody().applyForce(1,0,ball1.getBody().getPosition().x,ball1.getBody().getPosition().y,true);
				ball2.getBody().applyForce(1,1,ball2.getBody().getPosition().x,ball2.getBody().getPosition().y,true);
				
				ball3.getBody().applyForce(1,0.2f,ball3.getBody().getPosition().x,ball3.getBody().getPosition().y,true);}
				
		//bodyDef1.type = BodyType.DynamicBody;
		
		
		//generate=0;
		vistrelposx=body.getPosition().x;
		vistrelposy=body.getPosition().y;
		MyAudioManager.instance.play(AssetsMy.instance.sounds.vistrel);
		}
		}
		
	
	
		
	}
	public void update(float deltaTime,World MY_f_world,float x_igr,float y_igr) {
		if (body.getFixtureList().get(0).getUserData().toString().contains("vrag2"))
			cvet=0.8f;
		if (body.getFixtureList().get(0).getUserData().toString().contains("vrag1"))
			cvet=0.5f;
		
		if(viewDirection==VIEW_DIRECTION.RIGHT)
		dustParticles.setPosition(body.getPosition().x+1f ,body.getPosition().y+0.7f);
		if(viewDirection==VIEW_DIRECTION.LEFT)
			dustParticles.setPosition(body.getPosition().x,body.getPosition().y+0.7f);
		super.update(deltaTime);
		rastoianiex=x_igr-body.getPosition().x;
		rastoianiey=y_igr-body.getPosition().y;
		rastoianie=(float) Math.sqrt(rastoianiex*rastoianiex+rastoianiey*rastoianiey);
		//rastoianie=(float) Math.sqrt((x_igr-body.getPosition().x)*(x_igr-body.getPosition().x)+(y_igr-body.getPosition().y)*(y_igr-body.getPosition().y));
		
	
		if(body.getLinearVelocity().x>0.2f)body.setLinearVelocity(0.2f,body.getLinearVelocity().y);
		if(body.getLinearVelocity().x<-0.2)body.setLinearVelocity(-0.2f,body.getLinearVelocity().y);
		//if(body.getLinearVelocity().y>0.2)body.setLinearVelocity(body.getLinearVelocity().x,0.2f);
		//if(body.getLinearVelocity().y<-0.2f)body.setLinearVelocity(body.getLinearVelocity().x,-0.2f);	
		if ((Math.abs(rastoianiex))<=6&&(Math.abs(rastoianiex))>=2){
			body.applyForce(new Vector2((x_igr-body.getPosition().x)*10*(1f/rastoianie),0), body.getPosition(), true);

		//	System.out.println(Math.abs(rastoianiex));
			uvidel=true;
		}
		
		
		vistrel(deltaTime, MY_f_world,x_igr,y_igr);
	
		if ((Math.abs(rastoianiex))<=5)
		{
			uvidel=true;
			testview(x_igr);
			
			vistrel(deltaTime, MY_f_world,x_igr,y_igr);
		
			
	//		body.applyForce(new Vector2(0,0), body.getPosition(), true);
		
			
	
		}
		if ((Math.abs(rastoianiex))>=7){uvidel=false;}
		
		if(uvidel==false&&body.getPosition().x<startx){
			body.applyForce(new Vector2(10,0), new Vector2(body.getPosition().x,body.getPosition().y), true);

		//	System.out.println("KISSS");
			}
		
		if(uvidel==false&&body.getPosition().x>startx){
			body.applyForce(new Vector2(-10,0),  new Vector2(body.getPosition().x,body.getPosition().y), true);
		//	System.out.println("KISSS");
			}
	
		
		
		for (NewPlasmaBall_Trin bool : bools)
		 { 
		
		bool.update(deltaTime);
			
		if(bool.getBody().getFixtureList().get(0).getUserData().equals("BAM")){	bool.getBody().setTransform(-5, -5, 0);bool.getBody().setActive(false);bool.getBody().setAwake(true);bool.getBody().getFixtureList().get(0).setUserData("222");}
		
		}
		
		
		if (body.getLinearVelocity().x < -0.1f ||body.getLinearVelocity().x > 0.1f) {
			viewDirection = body.getLinearVelocity().x > 0 ? VIEW_DIRECTION.RIGHT : VIEW_DIRECTION.LEFT;
			} 
		
		if (body.getFixtureList().get(0).getUserData().toString().contains("vrag2")&&live==2){
			boom.setPosition(body.getPosition().x+0.5f, body.getPosition().y+0.7f);
			boom.start();live=1;
							}
		if (body.getFixtureList().get(0).getUserData().toString().contains("vrag1")&&live==1){
			boom.setPosition(body.getPosition().x+0.5f, body.getPosition().y+0.7f);
			boom.start();live=0;
							}
		
		if (body.getFixtureList().get(0).getUserData().toString().contains("ubit")){
			boom.setPosition(body.getPosition().x+0.5f, body.getPosition().y+0.7f);
			boom.start();
			
			body.getFixtureList().get(0).setUserData("isovsem");
		//boom.allowCompletion();
			rendervrag=false;
			System.out.println("готов умереть");
		//	body.setUserData("BAM");
			}
		
		
		if(boom.isComplete()&&body.getFixtureList().get(0).getUserData().toString().contains("isovsem")){body.setUserData("BAM");}
		
		if(animation==animvistrel&&animvistrel.isAnimationFinished(stateTime)){animation=animnorm;
	//	if(dustParticles.isComplete())
	//	dustParticles.allowCompletion();
		}
		
		if(ball1!=null)
			plasma1.setPosition(ball1.getBody().getPosition().x,ball1.getBody().getPosition().y);plasma1.start();plasma1.update(deltaTime);plasma1.allowCompletion();
			if(ball2!=null)
				plasma2.setPosition(ball2.getBody().getPosition().x,ball2.getBody().getPosition().y);plasma2.start();plasma2.update(deltaTime);plasma2.allowCompletion();
				if(ball3!=null)
					plasma3.setPosition(ball3.getBody().getPosition().x,ball3.getBody().getPosition().y);plasma3.start();plasma3.update(deltaTime);plasma3.allowCompletion();

				
		dustParticles.update(deltaTime);	
	
		boom.update(deltaTime);	
	}
	
	public void testview(float x_igr){

		if (body.getPosition().x < x_igr) 
			viewDirection = VIEW_DIRECTION.RIGHT;
		else viewDirection=VIEW_DIRECTION.LEFT;
		
	}
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		
		
		
		
		
		TextureRegion reg = null;
		reg = animation.getKeyFrame(stateTime);
		//Переделал dimension.y*1.5f
		if(rendervrag){
			batch.setColor(1, cvet, cvet, 1f);
		batch.draw(reg.getTexture(), position.x, position.y
				, origin.x, origin.y, dimension.x, dimension.y*1.5f,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.RIGHT, false);}
		batch.setColor(1, 1, 1, 1f);
	//	box2DSpritestvol.draw(batch,body);
	
	//	 batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
			


	
		for (NewPlasmaBall_Trin bl : bools)
		{bl.render(batch);}
	
	//	box2DSprite.draw(batch,body);
		 dustParticles.draw(batch);
		 plasma1.draw(batch);
			plasma2.draw(batch);
			plasma3.draw(batch);
			boom.draw(batch);
	// batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
	}

}
