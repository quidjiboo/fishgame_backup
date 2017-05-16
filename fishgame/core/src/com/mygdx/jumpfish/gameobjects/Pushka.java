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
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;



public class Pushka extends AbstractGameObject {
	private int counter = 0;
	public Box2DSprite box2DSprite;
	public Box2DSprite box2DSpritestvol;
	private float generate=MathUtils.random(0, 20)*0.1f;
	//private float generate=0;
	private float ugol=0;
	public Ball ball;
	public Fixture  Fixture1;
	public Array<Ball> bools;
	public Pushka(Body b) {
				body = b;
		init();
	}

	private void init() {
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
		dimension.set(1,1);
	//	body.setUserData(animatedBox2DSprite);
		bools = new Array<Ball>();
		//box2DSprite = new Box2DSprite(AssetsMy.instance.fish.stend_pushka);
		box2DSpritestvol  = new Box2DSprite(AssetsMy.instance.fish.stvol_pushka);
	
	//	body.setUserData(box2DSpritestvol);
	//	body.setType(BodyType.StaticBody); 
		body.getFixtureList().get(0).setDensity(20);
		body.getFixtureList().get(0).setFriction(20);
		
		ugol=Integer.parseInt(body.getUserData().toString().substring(7,10));
		System.out.println(ugol);
		
	PolygonShape poly = new PolygonShape();	
		
	//	poly.setAsBox(0.5f,0.5f,new Vector2(0.5f,0.49f),0);
	
	CircleShape circle = new CircleShape();		
	circle.setRadius(0.4f);
	circle.setPosition(new Vector2(0.5f, 0.5f));
		Fixture1 = body.createFixture(circle, 0);

	//Fixture1 = body.createFixture(poly,0);
		
		poly.dispose();
		
		
	//	Fixture1.setDensity(1);
	//	Fixture1.setRestitution(0);
	//	Fixture1.setFriction(1);
		Fixture1.setUserData("small_car1");

		body.setUserData("box2DSpritestvol");
		
		
	//	box2DSpritestvol.flip(true, false);
		body.resetMassData();
		origin.set(dimension.x / 2-0.5f, dimension.y / 2-0.5f);
		
		box2DSpritestvol.setRotation(ugol);
		box2DSpritestvol.flip(true, false);
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
		Fixture1.setFilterData(f);
	}
	
	public void vistrel(float deltaTime ,World MY_f_world){
		if(counter < 2&&generate>2f) 
		{	BodyDef bodyDef1 = new BodyDef();
		Body body1 = MY_f_world.createBody(bodyDef1);
		//bodyDef1.type = BodyType.DynamicBody;
		ball = new Ball(body1,body,ugol);
		counter++;
		bools.add(ball);
		generate=0;}
	generate=generate+deltaTime;
		
	}
	public void update(float deltaTime,World MY_f_world) {
		vistrel(deltaTime, MY_f_world);
		super.update(deltaTime);
		
		for (Ball bool : bools)
		{
			bool.update(deltaTime);
		//	System.out.println(bool.getBody().getFixtureList().get(0).getUserData());
		if(bool.getBody().getFixtureList().get(0).getUserData().equals("BAM")){	MY_f_world.destroyBody(bool.body);bools.removeValue(bool, true);counter--;}
			}
	}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		for (Ball bl : bools)
		{bl.render(batch);}
		//animatedBox2DSprite.draw(batch,body);
		box2DSpritestvol.draw(batch,body);
		TextureRegion reg = null;
		reg = AssetsMy.instance.fish.stvol_pushka;
		
		reg = AssetsMy.instance.fish.stend_pushka;
		
		batch.draw(reg.getTexture(), position.x - origin.x, position.y - origin.y, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), true,
				false);
		
	
	//	box2DSprite.draw(batch,body);
		
	}

}
