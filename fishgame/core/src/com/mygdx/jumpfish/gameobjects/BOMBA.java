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

import sun.net.www.content.audio.wav;
import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mygdx.jumpfish.util.AssetsMy;



public class BOMBA extends AbstractGameObject implements Poolable  {
	private float live=2; 
	public Box2DSprite box2DSprite;
	private CircleShape shape;
	protected final World MY_f_world1;
	private Body body;
	
	public BOMBA(World w) {
		this.MY_f_world1 = w;
		init();
	}

	private void init() {
		 live=5;
		BodyDef bodyDef = new BodyDef();
		body = MY_f_world1.createBody(bodyDef);
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
	
			body.setTransform(8,2,0);
			body.setLinearVelocity(1,1);
		
		body.setGravityScale(0.2f);
	//	body.applyLinearImpulse(MathUtils.random(0, 1000)*0.01f, 0.1f, body.getPosition().x, body.getPosition().y, true);
		body.resetMassData();
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
		body.setActive(true);
	
	}
	
		
;

	public void update(float deltaTime,Array<BOMBA> d,BOMBSPool pool) {
		live=live-deltaTime;
	//	System.out.println(body.getFixtureList().get(0).getUserData());
		super.update(deltaTime);
		
	//	if (body.getFixtureList().get(0).equals("BAM"))
	//	{
	//		body.setActive(false);	
	//	}
		// System.out.println(live);
		if (live<0){
			d.removeValue(this, true);
		pool.free(this);}
		}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		//animatedBox2DSprite.draw(batch,body);
		box2DSprite.draw(batch,body);
	}
	@Override
	public void reset() {
		live=5;
		body.setActive(false);
		body.setTransform(MathUtils.random(9f, 15f),5,0);
		// TODO Auto-generated method stub
		
	}

}
