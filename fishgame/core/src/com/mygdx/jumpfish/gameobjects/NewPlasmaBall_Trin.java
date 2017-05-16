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



public class NewPlasmaBall_Trin extends AbstractGameObject {
	boolean rendershar=false;
	float vrascenie;
	float xcel;
	float ycel;
	public Box2DSprite box2DSprite;
	private CircleShape shape;
	public float emitboyx;
	public float emitboyy;
	public float timerfreez=0;
	public float rastoianie;
	
	public NewPlasmaBall_Trin(Body b) {
				body = b;
		init();
	}
	
	private void init() {
		vrascenie= MathUtils.random(-100, 100)*0.01f;
		timerfreez=0;

	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		
		
		
		shape = new CircleShape();
		shape.setRadius(0.2f);
		body.createFixture(shape,0);
		//body.setTransform(emitboy.getPosition().x+1f,emitboy.getPosition().y+1f,0);
		//body.setTransform(1,2,0);
		body.setType(BodyType.DynamicBody);
	//	body.setUserData("fdsfsd");
		body.getFixtureList().get(0).setUserData("222");
	
		
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.yadro_vrag);
		body.setUserData(box2DSprite);
	//	body.setGravityScale(0);
		body.getFixtureList().get(0).setRestitution(0.1f);
		body.getFixtureList().get(0).setDensity(0.1f);
	
		body.resetMassData();
		body.setGravityScale(0f);
		body.setBullet(false);
		body.resetMassData();
		body.setActive(false);
			//body.setTransform(emitboyx,emitboyy,0);
		
	
		
	
		
	
		
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
	}
	
		


	public void update(float deltaTime) {
		
	if(body.isActive()){timerfreez=timerfreez+deltaTime;
	
		super.update(deltaTime);
		if(body.getLinearVelocity().x>0.6f)body.setLinearVelocity(0.6f,body.getLinearVelocity().y);
		if(body.getLinearVelocity().x<-0.6f)body.setLinearVelocity(-0.6f,body.getLinearVelocity().y);
		if(body.getLinearVelocity().y>0.6f)body.setLinearVelocity(body.getLinearVelocity().x,0.6f);
		if(body.getLinearVelocity().y<-0.6f)body.setLinearVelocity(body.getLinearVelocity().x,-0.6f);	

	
		
		if(timerfreez>=8)
			body.getFixtureList().get(0).setUserData("BAM");
		
	//	body.setAngularVelocity(vrascenie);
	}
		if(!body.isActive())
		timerfreez=0f;
//	if(timerfreez<9f)rendershar=true;
		}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
	//	if (rendershar){
		//	System.out.println(1/timerfreez);
			
		//animatedBox2DSprite.draw(batch,body);
			if(timerfreez>7f)	
			box2DSprite.setAlpha(7/timerfreez*2);
		box2DSprite.draw(batch,body);
		box2DSprite.setAlpha(1);
	//	}
	}

}
