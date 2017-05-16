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



public class MenuSpaceShip extends AbstractGameObject {
	public boolean strt=false;
	public	float startx;
	public float starty;
	public int xxxx=0;
		public boolean polet = true;
		public boolean padenie = false;
		public boolean contact = false;
	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public ParticleEffect PortalParticlesq = new ParticleEffect();
	public ParticleEffect Bam = new ParticleEffect();
	public ParticleEffect BIGBam = new ParticleEffect();
	public ParticleEffect Dust = new ParticleEffect();
	public MenuSpaceShip(Body b) {
				body = b;
		init();
	}

	private void init() {
		PortalParticlesq.load(Gdx.files.internal("particles/shep.pfx"), Gdx.files.internal("particles"));
		Bam.load(Gdx.files.internal("particles/boom_nobland.pfx"), Gdx.files.internal("particles"));
		BIGBam.load(Gdx.files.internal("particles/BIGboom.pfx"), Gdx.files.internal("particles"));
		Dust.load(Gdx.files.internal("particles/dust.pfx"), Gdx.files.internal("particles"));
	//	PortalParticlesq.setPosition(body.getPosition().x, body.getPosition().y+0.4f);
		animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.space_ship);
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
	//	PortalParticlesq.start(); 
	}
	
		
;

	public void update(float deltaTime) {
		
		super.update(deltaTime);
		PortalParticlesq.start(); 
	
		PortalParticlesq.setPosition(body.getPosition().x+0.6f, body.getPosition().y+0.4f);
//	if (body.isAwake()){
		Bam.setPosition(body.getPosition().x+0.7f, body.getPosition().y+0.5f);
		Dust.setPosition(body.getPosition().x+0.9f, body.getPosition().y+0.6f);
		BIGBam.setPosition(3.2f+12.5f, 11.6f); 
	//		animatedSprite.play();
	//	}
//	else {
			//animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.anim_fish_preparing_to_fly);
	//		animatedSprite.pause();
//			}
		if (polet&&body.getPosition().x>6.5f+12.5f){body.setLinearVelocity(-5, 0);polet=false;padenie=true; xxxx=0;}
		if (padenie&&body.getPosition().x<=6.5f+12.5f){body.setLinearVelocity(-1.9f, -1.2f);padenie=false;Bam.start();	Dust.start();BIGBam.reset(); }
		if(!padenie&&body.getLinearVelocity().y==0){
			contact=true;	BIGBam.start();
			body.setTransform(startx, starty,0);
			
		}
		PortalParticlesq.update(deltaTime);
		Dust.update(deltaTime);
		Bam.update(deltaTime);
		BIGBam.update(deltaTime);
	
		}
	
	
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		if (contact&&xxxx<=45){
			xxxx=xxxx+1;
			BIGBam.draw(batch);
			//BIGBam.allowCompletion();
			}
		
		
		
		animatedBox2DSprite.draw(batch,body);
	//	box2DSprite.draw(batch,body);
		PortalParticlesq.draw(batch);
		if (body.getLinearVelocity().y<0){
			Bam.draw(batch);
			Dust.draw(batch);}
	}

}
