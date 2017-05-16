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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.gameobjects.Vrag.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;



public class Bonus extends AbstractGameObject {
	public enum BONUS {
		H2O,PL,GUN,DJ,WEEK,NULL
		}
	//private float generate=MathUtils.random(0, 20)*0.1f;
	public int kolvo_bons=0 ; //от 1 до 5
	public int shetchik=0;
	public boolean blind=false;
	public Array<BONUS> arrbonus ;
	private float generate=3;
	public BONUS bonus;
	public Box2DSprite prite_H2O;
	public Box2DSprite prite_PL;
	public Box2DSprite prite_GUN;
	public Box2DSprite prite_DJ;
	public Box2DSprite prite_WEEK;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public Fixture Fixture1;
	public Bonus(Body b) {
		
		body = b;
		
init();
}

	
public void addbtoarr(String bonusname){
	if(bonusname.contains("h2o")){
		arrbonus.add(BONUS.H2O);
		kolvo_bons=kolvo_bons+1;
	} 
	if(bonusname.contains("dj")){
		arrbonus.add(BONUS.DJ);
		kolvo_bons=kolvo_bons+1;
	}
	if(bonusname.contains("week")){
		arrbonus.add(BONUS.WEEK);
		kolvo_bons=kolvo_bons+1;
	}
	if(bonusname.contains("pl")){
		arrbonus.add(BONUS.PL);
		kolvo_bons=kolvo_bons+1;
	}
	if(bonusname.contains("gun")){
		arrbonus.add(BONUS.GUN);
		kolvo_bons=kolvo_bons+1;
	}
	if(!bonusname.contains("dj")&&!bonusname.contains("h2o")&&!bonusname.contains("week")&&!bonusname.contains("pl")&&!bonusname.contains("gun")){
		arrbonus.add(BONUS.NULL);
		kolvo_bons=kolvo_bons+1;
	}
	
//	arrbonus.add(BONUS.H2O);
//	kolvo_bons=kolvo_bons+1;
}
	private void init() {
		
		bonus=BONUS.NULL;
		if(body.getUserData().toString().contains("blind"))
		blind=true;
		arrbonus = new Array<Bonus.BONUS>();
		
		addbtoarr(body.getUserData().toString());
	//	addbtoarr("dj");
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
		prite_H2O = new Box2DSprite(AssetsMy.instance.fish.h2o);
	//	body.setUserData(prite_H2O);
		prite_PL = new Box2DSprite(AssetsMy.instance.fish.pl);
	//	body.setUserData(prite_PL);
		prite_GUN = new Box2DSprite(AssetsMy.instance.fish.gun);
	//	body.setUserData(prite_GUN);
		prite_DJ = new Box2DSprite(AssetsMy.instance.fish.dj);
	//	body.setUserData(prite_DJ);
		prite_WEEK = new Box2DSprite(AssetsMy.instance.fish.week);
	//	body.setUserData(prite_WEEK);
		
		
		body.resetMassData();
		
	//	body.applyLinearImpulse(9, 9, body.getPosition().x, body.getPosition().y, true);
		//box2DSprite.setY(1);
	
		//box2DSprite.setBounds(body.getPosition().x, body.getPosition().y, 0,0);
	
	//	sprite.setScale(camera.viewportWidth / Gdx.graphics.getWidth(), camera.viewportHeight / Gdx.graphics.getHeight());
		
		
	}
	
		
;

	public void update(float deltaTime) {
		
		super.update(deltaTime);
		generate=generate+deltaTime;
		if(generate>2f) {
				bonus=arrbonus.get(shetchik)	;
				shetchik=shetchik+1;
				if(shetchik==kolvo_bons)shetchik=0;
				generate=0;}
	
	//System.out.println(bonus);
		 switch (bonus){
		 case NULL:
			 body.getFixtureList().get(0).setUserData("h2o");
					break;
			case H2O:
			body.getFixtureList().get(0).setUserData("h2o");
				break;
			case WEEK:
				body.getFixtureList().get(0).setUserData("week");
					break;
			case DJ:
				body.getFixtureList().get(0).setUserData("dj");
					break;
			case GUN:
				body.getFixtureList().get(0).setUserData("gun");
					break;
			case PL:
				body.getFixtureList().get(0).setUserData("pl");
					break;
					
		 }
	//	 System.out.println(body.getFixtureList().get(0).getUserData());
		if (body.getUserData().equals("BAM"))
		{
			body.setActive(false);	
		}
		}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		if(blind==false){
	
		 batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		 switch (bonus){
		 case NULL:
			 prite_H2O.draw(batch,Fixture1);	
				break;
			case H2O:
			prite_H2O.draw(batch,Fixture1);
				break;
			case WEEK:
				prite_WEEK.draw(batch,Fixture1);
					break;
			case DJ:
				prite_DJ.draw(batch,Fixture1);
					break;
			case GUN:
				prite_GUN.draw(batch,Fixture1);
					break;
			case PL:
				prite_PL.draw(batch,Fixture1);
					break;
					
		 }
		 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	//	box2DSprite.draw(batch,body.getPosition().x+0.5f, body.getPosition().y+0.7f, 1, 1, rotation);
		animatedBox2DSprite.draw(batch,body.getFixtureList().get(0));
		}	
	}

}
