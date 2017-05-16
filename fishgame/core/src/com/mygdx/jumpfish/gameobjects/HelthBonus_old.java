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
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.gameobjects.Vrag.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;



public class HelthBonus_old extends AbstractGameObject {
	//public enum VIEW_DIRECTION {
	//	LEFT, RIGHT
	//	}
//	public VIEW_DIRECTION viewDirection;
	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public Fixture Fixture1;
	public HelthBonus_old(Body b) {
		
		body = b;
		
init();
}

	

	private void init() {
		
		animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.asrc);
	//	animatedSprite.pause();
		animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
		body.setUserData(animatedBox2DSprite);
	
		
		
	
		
		body.getFixtureList().get(0).setDensity(1);
		body.getFixtureList().get(0).setFriction(1);
		
		PolygonShape poly = new PolygonShape();	
		
		poly.setAsBox(0.35f,0.35f,new Vector2(0.5f,0.62f),0);
		Fixture1 = body.createFixture(poly,0);
		
		poly.dispose();
		
		Fixture1.setSensor(false);
		Fixture1.setDensity(0);
		Fixture1.setRestitution(0);
		Fixture1.setFriction(0);
		Fixture1.setUserData("123");
//	
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.h2o);
		body.setUserData(box2DSprite);
		
		body.resetMassData();
	//	body.applyLinearImpulse(9, 9, body.getPosition().x, body.getPosition().y, true);
		//box2DSprite.setY(1);
	
		//box2DSprite.setBounds(body.getPosition().x, body.getPosition().y, 0,0);
	
	//	sprite.setScale(camera.viewportWidth / Gdx.graphics.getWidth(), camera.viewportHeight / Gdx.graphics.getHeight());
		
		
	}
	
		
;

	public void update(float deltaTime) {
		
		super.update(deltaTime);
		if (body.getUserData().equals("BAM"))
		{
			body.setActive(false);	
		}
		}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
	
		 batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
	
		box2DSprite.draw(batch,Fixture1);
		 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	//	box2DSprite.draw(batch,body.getPosition().x+0.5f, body.getPosition().y+0.7f, 1, 1, rotation);
		animatedBox2DSprite.draw(batch,body.getFixtureList().get(0));
		
	}

}
