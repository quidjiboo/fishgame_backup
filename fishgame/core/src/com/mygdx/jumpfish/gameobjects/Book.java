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
import com.mygdx.jumpfish.util.AssetsMy;



public class Book extends AbstractGameObject {

	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	private int variant=1;
	//String varian1t;
	public Book(Body b) {
				body = b;
		init();
	}

	private void init() {
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		if(Math.random()>0.5f){
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.small_book1);}
		else {box2DSprite = new Box2DSprite(AssetsMy.instance.fish.small_book2);}
		if(Math.random()>0.5f){
			box2DSprite.setRotation(180);}
		variant=Integer.parseInt(body.getUserData().toString().substring(10));
	
	//	varian1t=body.getUserData().toString().substring(9);
	
		if (variant!=1){
		
			box2DSprite = new Box2DSprite(AssetsMy.instance.fish.big_book);
			
		}
		body.setUserData(box2DSprite);
		
		
		body.getFixtureList().get(0).setDensity(2);
		
		
		
		body.getFixtureList().get(0).setFriction(1);
		body.resetMassData();
	
	}
	
		
;

	public void update(float deltaTime) {
	//	System.out.println("var="+varian1t);
		super.update(deltaTime);
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
	}

}
