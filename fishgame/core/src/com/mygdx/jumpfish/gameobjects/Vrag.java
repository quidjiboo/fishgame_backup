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

import com.badlogic.gdx.graphics.GL20;
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
import com.mygdx.jumpfish.util.MyAudioManager;



public class Vrag extends AbstractGameObject {
	float cvet=1;
	float rastoianie;
	public Animation anim;
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
		}
	public VIEW_DIRECTION viewDirection;
	private int counter = 0;
//	private int lives = 3;
	private float generate=MathUtils.random(0, 10)*0.1f;
	private float starty;
	private float startx;
	private float timerfreez;
	private float vistrelposy;
	private float vistrelposx;
	//private float generate=0;
	
	public PlasmaBall ball;
	public Fixture  Fixture1;
	public Array<PlasmaBall> bools;
	public Vrag(Body b) {
				body = b;
		init();
	}

	private void init() {
		anim = AssetsMy.instance.fish.orb_ship;
		setAnimation(anim);
		viewDirection = VIEW_DIRECTION.RIGHT;
		dimension.set(1,1);

		bools = new Array<PlasmaBall>();
	
		
	
	
		body.getFixtureList().get(0).setDensity(1);
		body.getFixtureList().get(0).setFriction(1);
		body.setFixedRotation(true);
	
	//	body.setUserData("333");
		body.getFixtureList().get(0).setUserData("vrag3");
		
	//	box2DSpritestvol.flip(true, false);
		body.resetMassData();
		origin.set(dimension.x / 2-0.5f, dimension.y / 2-0.5f);
		starty=body.getPosition().y;
		startx=body.getPosition().x;
		vistrelposy=body.getPosition().y;
		vistrelposx=body.getPosition().x;
		Filter f = new Filter();
		
		f.groupIndex = -1;
		body.getFixtureList().first().setFilterData(f);
	//	Fixture1.setFilterData(f);
	}
	
	public void vistrel(float deltaTime ,World MY_f_world,float x_igr1,float y_igr1){
		if (rastoianie<=4){
		if(animation!=AssetsMy.instance.fish.orbvistrel&&counter < 2&&generate>3f)
		setAnimation(AssetsMy.instance.fish.orbvistrel);
		if(animation==AssetsMy.instance.fish.orbvistrel&&AssetsMy.instance.fish.orbvistrel.isAnimationFinished(stateTime))
		if(counter < 2&&generate>3f) 
		{	BodyDef bodyDef1 = new BodyDef();
		Body body1 = MY_f_world.createBody(bodyDef1);
		//bodyDef1.type = BodyType.DynamicBody;
		ball = new PlasmaBall(body1,body,x_igr1-0.2f,y_igr1+0.4f);
		counter++;
		bools.add(ball);
		generate=0;
		vistrelposx=body.getPosition().x;
		vistrelposy=body.getPosition().y;
		animation=AssetsMy.instance.fish.orb_ship;
		MyAudioManager.instance.play(AssetsMy.instance.sounds.vistrel);}
	generate=generate+deltaTime;}
	//
	}
	public void update(float deltaTime,World MY_f_world,float x_igr,float y_igr) {
		if (body.getFixtureList().get(0).getUserData().toString().contains("vrag2"))
			cvet=0.8f;
		if (body.getFixtureList().get(0).getUserData().toString().contains("vrag1"))
			cvet=0.5f;
		
		super.update(deltaTime);
		rastoianie=(float) Math.sqrt((x_igr-body.getPosition().x)*(x_igr-body.getPosition().x)+(y_igr-body.getPosition().y)*(y_igr-body.getPosition().y));
		
	
		if(body.getLinearVelocity().x>0.5f)body.setLinearVelocity(0.5f,body.getLinearVelocity().y);
		if(body.getLinearVelocity().x<-0.5)body.setLinearVelocity(-0.5f,body.getLinearVelocity().y);
		if(body.getLinearVelocity().y>0.5f)body.setLinearVelocity(body.getLinearVelocity().x,0.5f);
		if(body.getLinearVelocity().y<-0.5f)body.setLinearVelocity(body.getLinearVelocity().x,-0.5f);	
		if (rastoianie<=5&&body.getPosition().y<y_igr+1&&timerfreez==0){
			body.applyForce(new Vector2((x_igr-body.getPosition().x)*1f/rastoianie,11), body.getPosition(), true);
			
		}
		if (rastoianie<=5&&body.getPosition().y>y_igr+1&&timerfreez==0){
			body.applyForce(new Vector2((x_igr-body.getPosition().x)*1f/rastoianie,8), body.getPosition(), true);
			
		}
	//	if (rastoianie<=4)
			vistrel(deltaTime, MY_f_world,x_igr,y_igr);
		// if (rastoianie<6&&body.getLinearVelocity().x!=0&&body.getLinearVelocity().y!=0)
		if (rastoianie<3f)
		{
			timerfreez=timerfreez+deltaTime;
	
		
		if (body.getPosition().y<vistrelposy){
			body.applyForce(new Vector2((startx-body.getPosition().x)*1f/rastoianie,11), body.getPosition(), true);
			
		}
		if (body.getPosition().y>vistrelposy){
			body.applyForce(new Vector2((vistrelposx-body.getPosition().x)*1f/rastoianie,8), body.getPosition(), true);
			
		}
		}
		
		if (rastoianie>5&&body.getPosition().y<starty&&timerfreez==0){
			body.applyForce(new Vector2((vistrelposx-body.getPosition().x)*1f/rastoianie,11), body.getPosition(), true);
			
		}
		if (rastoianie>5&&body.getPosition().y>starty&&timerfreez==0){
			body.applyForce(new Vector2((startx-body.getPosition().x)*1f/rastoianie,8), body.getPosition(), true);
			
		}
		
	//	if (body.getPosition().y<(starty+2))
	//	{body.applyForce(new Vector2(-0.1f,10),body.getPosition(), true);}
		
		if (rastoianie>=3f) timerfreez=0;
		
	//	System.out.println(rastoianie);
		for (PlasmaBall bool : bools)
		{
			bool.update(deltaTime);
			
		if(bool.getBody().getFixtureList().get(0).getUserData().equals("BAM")){	MY_f_world.destroyBody(bool.body);bools.removeValue(bool, true);counter--;}
			}
		
		
		if (body.getLinearVelocity().x < -0.1f ||body.getLinearVelocity().x > 0.1f) {
			viewDirection = body.getLinearVelocity().x > 0 ? VIEW_DIRECTION.RIGHT : VIEW_DIRECTION.LEFT;
			} 
		
		if (body.getFixtureList().get(0).getUserData().toString().contains("ubit"))body.setUserData("BAM");
	
	}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		
		for (PlasmaBall bl : bools)
		{bl.render(batch);}
		
		TextureRegion reg = null;
		reg = animation.getKeyFrame(stateTime);
		batch.setColor(1, cvet, cvet, 1f);
		batch.draw(reg.getTexture(), position.x, position.y
				, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.RIGHT, false);
	
	//	box2DSpritestvol.draw(batch,body);
	
	
	//	box2DSprite.draw(batch,body);
		batch.setColor(1, 1, 1, 1f);
	}

}
