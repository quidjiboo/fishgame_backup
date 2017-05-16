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

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MyAudioManager;



public class SmallCar extends AbstractGameObject {
	public int number=5;
	public Animation anim;
	public boolean Preperinganim;
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
	}
	public VIEW_DIRECTION viewDirection;
	public SmallCar(Body b) {
				body= b;
		init();
	}

	private void init() {
		
		
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.smallcar);
	//	animatedSprite.pause(); 
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
	//	carfixtur.setUserData("small_car");
		viewDirection = VIEW_DIRECTION.LEFT;
		body.getFixtureList().get(0).setUserData("small_car");
		body.getFixtureList().get(0).setFriction(1.5f);
		body.getFixtureList().get(0).setDensity(1);
		
		body.setFixedRotation(true);
		anim = AssetsMy.instance.fish.smallcar;
		setAnimation(anim);
		origin.set(dimension.x / 2, dimension.y / 2);
		body.resetMassData();
		//PolygonShape poly = new PolygonShape();		
		//poly.setAsBox(1f,1f);
		//carfixtur = body.createFixture(poly,0);
	//	animatedSprite.play();
	//	animatedSprite.setFlip(false, true);
		
	}
	
		
;

	public void update(float deltaTime,float volum) {
		super.update(deltaTime);
		if (body.getLinearVelocity().x>4.90f){MyAudioManager.instance.play(AssetsMy.instance.sounds.car, volum*GamePreferences.instance.volSound*0.5f);body.setLinearVelocity(4.8f, 0);
number=number-1;}
		if (body.getLinearVelocity().x<-4.90f){MyAudioManager.instance.play(AssetsMy.instance.sounds.car);body.setLinearVelocity(-4.8f, 0);
		number=number-1;}
		if (body.isAwake()){	Preperinganim=true;}
		else{		Preperinganim=false;}
		
		if (body.getLinearVelocity().x < -0.3f ||body.getLinearVelocity().x > 0.3f) {
			viewDirection = body.getLinearVelocity().x > 0 ? VIEW_DIRECTION.RIGHT : VIEW_DIRECTION.LEFT;
			}
		if(number<1){body.getFixtureList().get(0).setUserData("small_car1");}
	}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		
		TextureRegion reg = null;
		reg = animation.getKeyFrame(stateTime, Preperinganim);
		batch.draw(reg.getTexture(), position.x, position.y
				, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.LEFT, false);
		//animatedBox2DSprite.draw(batch,body);
		}
		
	

}
