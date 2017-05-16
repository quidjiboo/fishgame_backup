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



public class Ball extends AbstractGameObject {

	public Box2DSprite box2DSprite;
	private CircleShape shape;
	public Body emitboy;
	float ugol1;
	public Ball(Body b) {
				body = b;
		init();
	}
	public Ball(Body b,Body bb,float ugol) {
		emitboy=bb;
		body = b;
		ugol1 = ugol;
		
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
		//body.setLinearVelocity(1,1);
		if(ugol1<45){
			body.setTransform(emitboy.getPosition().x+1.3f,emitboy.getPosition().y+0.5f,0);
			body.setLinearVelocity(new Vector2((float)Math.cos(ugol1)*4,(float)Math.sin(ugol1)*4));}
		if(ugol1<90&&ugol1>=45){
		body.setTransform(emitboy.getPosition().x+1f,emitboy.getPosition().y+1f,0);
		body.setLinearVelocity(new Vector2((float)Math.cos(ugol1)*4,(float)Math.sin(ugol1)*4));}
		if(ugol1==90)
		{
			body.setTransform(emitboy.getPosition().x+0.5f,emitboy.getPosition().y+1.3f,0);
			body.setLinearVelocity(new Vector2(0.1f*MathUtils.random(-1,1)*2f,1*3.7f));	
			
		}
		if(ugol1>90&&ugol1<=135){
			body.setTransform(emitboy.getPosition().x,emitboy.getPosition().y+1f,0);
			body.setLinearVelocity(new Vector2(-(float)MathUtils.cos(180-ugol1)*4,(float)MathUtils.sin(180-ugol1)*4));	
		}
		if(ugol1>135){
			body.setTransform(emitboy.getPosition().x-0.3f,emitboy.getPosition().y+0.5f,0);
			body.setLinearVelocity(new Vector2(-(float)MathUtils.cos(180-ugol1)*4,(float)MathUtils.sin(180-ugol1)*4));	
		}
		body.setGravityScale(0.2f);
	//	body.applyLinearImpulse(MathUtils.random(0, 1000)*0.01f, 0.1f, body.getPosition().x, body.getPosition().y, true);
		body.resetMassData();
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
	}
	
		
;

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
