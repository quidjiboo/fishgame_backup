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

import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.util.AssetsMy;



public class Spaceorb extends AbstractGameObject {
	public	float smeh_x=0.6f;
	public float smeh_y=0.4f;
	public	float startx;
	public float starty;
		public boolean polet = true;
		public boolean padenie = false;
		public boolean contact = false;
		public boolean fasa1 = true;
		public boolean fasa2 = false;
		public boolean fasa3 = false;
	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public ParticleEffect PortalParticlesq = new ParticleEffect();
	public ParticleEffect lasert = new ParticleEffect();
	public Spaceorb(Body b) {
				body = b;
		init();
	}

	private void init() {
		PortalParticlesq.load(Gdx.files.internal("particles/shep.pfx"), Gdx.files.internal("particles"));
		lasert.load(Gdx.files.internal("particles/laser.pfx"), Gdx.files.internal("particles"));
		PortalParticlesq.setPosition(body.getPosition().x, body.getPosition().y);
		//lasert.setPosition(body.getPosition().x, body.getPosition().y);
		animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.orb_ship);
		animatedSprite.play();
		animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
		body.setUserData(animatedBox2DSprite);
		
	//	box2DSprite = new Box2DSprite(AssetsMy.instance.fish.small_book1);
	//	body.setUserData(box2DSprite);
		
		
		body.getFixtureList().get(0).setDensity(0);
		
		
		
		body.getFixtureList().get(0).setFriction(1);
		
		startx=body.getPosition().x;
		starty=body.getPosition().y;
		body.setGravityScale(0);
		body.resetMassData();
		PortalParticlesq.start(); 
		lasert.start(); 
	}
	
		
;

	public void update(float deltaTime, boolean start) {
		
		super.update(deltaTime);
		PortalParticlesq.start(); 
		lasert.start(); 
		PortalParticlesq.setPosition(body.getPosition().x+smeh_x, body.getPosition().y+smeh_y);
		lasert.setPosition(body.getPosition().x+smeh_x-0.5f, body.getPosition().y+smeh_y-0.2f);
//	if (body.isAwake()){
			
			
	//		animatedSprite.play();
	//	}
//	else {
			//animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.anim_fish_preparing_to_fly);
	//		animatedSprite.pause();
//			}
		if (start&&body.getPosition().x>1f+12.5f&&fasa1==true&&fasa2==false&&fasa3==false){body.setLinearVelocity(-5, 0);}
		if (body.getPosition().x<=0.5f+12.5f&&fasa1==true&&fasa2==false&&fasa3==false){body.setLinearVelocity(0.3f, -5);fasa1=false;fasa2=true;
		smeh_x=0; }
		if (body.getPosition().y<=2f&&fasa1==false&&fasa2==true&&fasa3==false){body.setLinearVelocity(5, 1);fasa2=false;fasa3=true;
		smeh_x=-0.4f;
		
	
		}
		if (body.getPosition().x>=28f+12.5f&&fasa1==false&&fasa2==false&&fasa3==true){body.setTransform(startx, starty,0);fasa3=false;fasa1=true;
		smeh_x=0.6f;
		}
		
	//	if (padenie&&body.getPosition().x<=6.5f){body.setLinearVelocity(-1.9f, -1.2f);padenie=false;}
	//	if(!padenie&&body.getLinearVelocity().y==0){
	//		contact=true;
	//		body.setTransform(startx, starty,0);
			
	//	}
		
		

	
		if (body.getLinearVelocity().x > 0 && animatedBox2DSprite.isFlipX()==false) {
			animatedBox2DSprite.flipFrames(true, false);
			System.out.print("sdaffdsf");
		}
		if (body.getLinearVelocity().x < 0 && animatedBox2DSprite.isFlipX()==true) {
			animatedBox2DSprite.flipFrames(true, false);
			System.out.print("123123sdaffdsf");
		}
		
		
		PortalParticlesq.update(deltaTime);
		if(fasa1&&body.getPosition().x<startx){
		
		lasert.update(deltaTime);}
		}
	
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		
		animatedBox2DSprite.draw(batch,body);
		
	//	box2DSprite.draw(batch,body);
	//	PortalParticlesq.draw(batch);
		if(fasa1&&body.getPosition().x<startx&&body.getPosition().x>10){
			
			lasert.start();
//			batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
			lasert.draw(batch);
	//		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			}
		
	}

}
