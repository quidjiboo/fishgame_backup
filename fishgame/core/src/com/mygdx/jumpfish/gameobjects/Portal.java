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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MyAudioManager;



public class Portal extends AbstractGameObject {
	private float timersound =0;
	private boolean playsound=false;
	public Animation portal_open;
	public Animation portal_close;
	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public ParticleEffect PortalParticles = new ParticleEffect();
	
	public Portal(Body b) {
				body = b;
		init();
	}

	private void init() {
		portal_open = AssetsMy.instance.fish.portal_open;
		portal_close = AssetsMy.instance.fish.portal_close;
		setAnimation(portal_close);
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.portal_open);
	//	animatedSprite.pause();
//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		
	//	box2DSprite = new Box2DSprite(AssetsMy.instance.fish.h2o);
	//	body.setUserData(box2DSprite);
		
		
		body.getFixtureList().get(0).setDensity(200);
		
		
		
		body.getFixtureList().get(0).setFriction(1);
		body.getFixtureList().get(0).setSensor(true);
		body.setType(BodyType.StaticBody);
		body.resetMassData();
		PortalParticles.load(Gdx.files.internal("particles/portal.pfx"), Gdx.files.internal("particles"));
		PortalParticles.setPosition(body.getPosition().x, body.getPosition().y+0.2f);
	
	}
	
		
;

	public void update(float deltaTime,int xx) {
		
		super.update(deltaTime);
		if (animation!=portal_open&&xx==0){
			setAnimation(portal_open);
			
		 
		}
		if (animation==portal_open){
			playsound=true;
		
			PortalParticles.start(); 
		}
		
	if (playsound){
		timersound=timersound+0.1f;
		if (timersound>3){
		MyAudioManager.instance.play(AssetsMy.instance.sounds.portalon,0.3f);
		timersound=0;}
	}
	//	if (body.isAwake()){
	//		
			
	//		animatedSprite.play();
	//	}
	//	else {
			//animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.anim_fish_preparing_to_fly);
	//		animatedSprite.pause();
	//		}
		PortalParticles.update(deltaTime);
	}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {

		TextureRegion reg = null;
		reg = animation.getKeyFrame(stateTime, true);
	//	batch.draw(reg.getTexture(),1,1);
	
		
		batch.draw(reg.getTexture(), position.x, position.y
				, origin.x, origin.y, dimension.x, dimension.y/2,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(),false, false);
		PortalParticles.draw(batch);	
	//	batch.draw(reg.getTexture(),body.getPosition().x, body.getPosition().y
	//			, 1, 1,1, 1,
	//			1,1, rotation, reg.getRegionX(), reg.getRegionY(),
	//			reg.getRegionWidth(), reg.getRegionHeight(),false, false);
//		animatedBox2DSprite.draw(batch,body);
		
	//	box2DSprite.draw(batch,body);
	
	}

}
