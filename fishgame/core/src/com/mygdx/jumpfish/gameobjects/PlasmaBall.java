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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.jumpfish.util.AssetsMy;



public class PlasmaBall extends AbstractGameObject {
	
	float xcel;
	float ycel;
	public Box2DSprite box2DSprite;
	private CircleShape shape;
	public Body emitboy;
	
	float rastoianie;
	public PlasmaBall(Body b) {
				body = b;
		init();
	}
	public PlasmaBall(Body b,Body bb,float x,float y) {
		xcel=x;
		ycel=y;
		emitboy=bb;
		body = b;
		
		
init();
}
	private void init() {
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		
		
		
		shape = new CircleShape();
		shape.setRadius(0.15f);
		body.createFixture(shape,0);
		//body.setTransform(emitboy.getPosition().x+1f,emitboy.getPosition().y+1f,0);
		//body.setTransform(1,2,0);
		body.setType(BodyType.DynamicBody);
	//	body.setUserData("fdsfsd");
		body.getFixtureList().get(0).setUserData("222");
	
		
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.yadro_pushka);
		body.setUserData(box2DSprite);
	//	body.setGravityScale(0);
		body.getFixtureList().get(0).setRestitution(0.1f);
		body.getFixtureList().get(0).setDensity(1);
		//body.setLinearVelocity(1,1);
		
		body.setGravityScale(0.2f);
			body.setTransform(emitboy.getPosition().x+0.5f,emitboy.getPosition().y+0.5f,0);
			rastoianie=(float) Math.sqrt((xcel-body.getPosition().x)*(xcel-body.getPosition().x)+(ycel-body.getPosition().y)*(ycel-body.getPosition().y));
		//	body.setLinearVelocity(new Vector2(1,4));
		//	System.out.println(rastoianie);
		//	System.out.println("x="+xcel+";  "+"y="+ycel);
		//	System.out.println("x="+body.getPosition().x+";  "+"y="+body.getPosition().y);
			//if(Math.abs(xcel-body.getPosition().x)<5&&Math.abs(ycel-body.getPosition().y)<5)
			body.applyForce(new Vector2((xcel-body.getPosition().x)*20/rastoianie,(ycel-body.getPosition().y)*20/rastoianie), body.getPosition(), true);
		//	else
		//		body.applyForce(new Vector2(MathUtils.random(-100,100)*0.1f,MathUtils.random(0, 100)*0.1f), body.getPosition(), true);
		
	//	body.applyLinearImpulse(MathUtils.random(0, 1000)*0.01f, 0.1f, body.getPosition().x, body.getPosition().y, true);
		body.resetMassData();
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
	}
	
		


	public void update(float deltaTime) {
	//	System.out.println(body.getFixtureList().get(0).getUserData());
		super.update(deltaTime);
		
	//	if (body.getFixtureList().get(0).equals("BAM"))
	//	{
	//		body.setActive(false);	
	//	}
		}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		//animatedBox2DSprite.draw(batch,body);
		box2DSprite.draw(batch,body);
	}

}
