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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.util.AssetsMy;



public class Pool_ball extends AbstractGameObject {
	
	public Box2DSprite box2DSprite;
	public Box2DSprite box2DSprite1;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public TextureRegion a123;
	public Fixture playerPhysicsFixture;
	public Pool_ball(Body b) {
				body = b;
		init();
	}

	private void init() {
		a123 = AssetsMy.instance.fish.pool_ball_shadow;
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		box2DSprite1 = new Box2DSprite(AssetsMy.instance.fish.pool_ball_shadow);
		if(Math.random()>0.5f){
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.pool_ball_var1);}
		else {box2DSprite = new Box2DSprite(AssetsMy.instance.fish.pool_ball_var2);}
		if(Math.random()>0.5f){
			box2DSprite.setRotation(90);}
		
	
		body.getFixtureList().get(0).setUserData("pool");
		body.setUserData(box2DSprite);
		
		body.getFixtureList().get(0).setDensity(5);
		
		
		
		body.getFixtureList().get(0).setFriction(1);
		body.resetMassData();

	//	box2DSprite1.setOrigin(body.getPosition().x+0.5f, body.getPosition().y+0.5f);

//body.resetMassData();
	}
	
		
;

	public void update(float deltaTime) {
		
		super.update(deltaTime);
	//	 System.out.println(body.getWorldCenter().x);
	//	if (body.isAwake()){
	//		
			
	//		animatedSprite.play();
	//	}
	//	else {
			//animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.anim_fish_preparing_to_fly);
	//		animatedSprite.pause();
	//		}
		
	}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		//animatedBox2DSprite.draw(batch,body);
		
		box2DSprite.draw(batch,body);
		//box2DSprite1.setOriginCenter();
		
		box2DSprite1.draw(batch, body.getWorldCenter().x,body.getWorldCenter().y, 1, 1, 0);
	//	batch.draw(a123.getTexture(), position.x, position.y
		//		, origin.x, origin.y, dimension.x, dimension.y,
			//	scale.x, scale.y, 0, a123.getRegionX(), a123.getRegionY(),
			//	a123.getRegionWidth(), a123.getRegionHeight(), false, false);
		//box2DSprite1.draw(batch,body);
	}

}
