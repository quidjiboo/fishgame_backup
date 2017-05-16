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

import com.badlogic.gdx.graphics.g2d.Animation;
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



public class Alien extends AbstractGameObject {
	public	float timerok=0; 
	public	float startx;
	public float starty;
public boolean stop=false;
public boolean go=false;
public boolean fly=false;
public boolean alieninwater=false;
	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public Alien(Body b) {
				body = b;
		init();
	}

	private void init() {
		animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.alien_jump_stop);
		
		animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
		body.setUserData(animatedBox2DSprite);
		animatedSprite.pause();
	//	box2DSprite = new Box2DSprite(AssetsMy.instance.fish.small_book1);
	//	body.setUserData(box2DSprite);
		
		
		body.getFixtureList().get(0).setDensity(0);
		
		
		
		body.getFixtureList().get(0).setFriction(1);
		body.resetMassData();
		startx=body.getPosition().x;
		starty=body.getPosition().y;
		body.setActive(false);
	}
	
		
;

	public void update(float deltaTime,boolean start) {
		
		super.update(deltaTime);
		if(start)
		timerok=timerok+deltaTime;
		
//if (body.isAwake()){
			
			
//		animatedSprite.play();
	//	}
//	else {
			//animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.anim_fish_preparing_to_fly);
	//		animatedSprite.pause();
	//		}
		if (timerok>=0.5f&& start&&body.getLinearVelocity().x!=1&&body.getPosition().x<6.1f+12.5f&&stop==false&&fly==false){go=true;body.setActive(true);}
		if (timerok>=1&& start&&body.getLinearVelocity().x!=1&&body.getPosition().x<6.1f+12.5f&&stop==false&&fly==false){body.setLinearVelocity(1, 0); 
		animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.alien_run); animatedSprite.play(); }
		if (body.getPosition().x>=6+12.5f&&stop==false){body.setLinearVelocity(0, 0);stop=true; }
		
		if (stop==true&&fly==false){body.applyLinearImpulse(2.1f,6.5f,1,1, true);fly=true;		
		animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.alien_jump);}
		if (body.getPosition().y<9.5f){alieninwater=true;
		stop=false;
		
		
		body.setTransform(startx, starty, 0);
		
		 go=false;
		animatedBox2DSprite.setAnimation(AssetsMy.instance.fish.alien_jump_stop);
		body.setActive(false);
		}
		//if (reset == true){
//			stop=false;
		
			
	//	}
		
		}
	
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		if((go)){
		animatedBox2DSprite.draw(batch,body);
	//	box2DSprite.draw(batch,body);
	}
	}
}
