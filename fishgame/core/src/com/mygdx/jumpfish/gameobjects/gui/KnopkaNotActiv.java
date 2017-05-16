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

package com.mygdx.jumpfish.gameobjects.gui;

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
import com.mygdx.jumpfish.levelselect.SelectWorldRendererMy;
import com.mygdx.jumpfish.startscreen.MenuWorldRendererMy;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.MyAudioManager;



public class KnopkaNotActiv extends AbstractGameObject {

	public Box2DSprite box2DSprite;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	private String namepress;
	public KnopkaNotActiv(Body b) {
				body = b;
		init();
	}

	private void init() {
		namepress=body.getUserData().toString();
		
	//	namepress="start";
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.small_book1);
		body.setUserData(box2DSprite);
		
		
		body.getFixtureList().get(0).setDensity(0);
		
		
		
		body.getFixtureList().get(0).setFriction(0);
		body.setGravityScale(0);
		body.resetMassData();
		body.setActive(false);
	}
	
		
;

	public void update(float deltaTime) {
		
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
	public String PressKnopka(int x , int y)
	{System.out.println(MenuWorldRendererMy.getx()); 
	
	System.out.println(body.getPosition().x);
	
		String name = null;//System.out.println(ddd);
		//System.out.println(	body.getPosition().x);
		if (MenuWorldRendererMy.getx()<=body.getPosition().x+7.5f&&MenuWorldRendererMy.getx()>body.getPosition().x
			&&			MenuWorldRendererMy.gety()<=body.getPosition().y+2f&&MenuWorldRendererMy.gety()>body.getPosition().y
				)
				{
			
			name=namepress;
		//System.out.println(x+y);
	System.out.println(SelectWorldRendererMy.getx());
	System.out.println("Fuck yeh");
	//body.setActive(false);

	System.out.println(name);}
		else{name=null;}
		
		return name;
		}
	public String PressKnopkacred(int x , int y)
	{System.out.println(MenuWorldRendererMy.getx()); 
	
	System.out.println(body.getPosition().x);
	
		String name = null;//System.out.println(ddd);
		//System.out.println(	body.getPosition().x);
		if (MenuWorldRendererMy.getx()<=body.getPosition().x+7.5f&&MenuWorldRendererMy.getx()>body.getPosition().x
			&&			MenuWorldRendererMy.gety()<=body.getPosition().y+1f&&MenuWorldRendererMy.gety()>body.getPosition().y
				)
				{
			
			name=namepress;
		//System.out.println(x+y);
	System.out.println(SelectWorldRendererMy.getx());
	System.out.println("Fuck yeh");
	//body.setActive(false);

	System.out.println(name);}
		else{name=null;}
		return name;
		}
	
	public String Pressmuz(int x , int y)
	{System.out.println(MenuWorldRendererMy.getx()); 
	
	System.out.println(body.getPosition().x);
	
		String name = null;//System.out.println(ddd);
		//System.out.println(	body.getPosition().x);
		if (MenuWorldRendererMy.getx()<=body.getPosition().x+3.5f&&MenuWorldRendererMy.getx()>body.getPosition().x
			&&			MenuWorldRendererMy.gety()<=body.getPosition().y+0.5f&&MenuWorldRendererMy.gety()>body.getPosition().y
				)
				{
			
			name=namepress;
		//System.out.println(x+y);
	System.out.println(SelectWorldRendererMy.getx());
	System.out.println("Fuck yeh");
	//body.setActive(false);

	System.out.println(name);}
		else{name=null;}
		return name;
		}
	public String Presseng(int x , int y)
	{System.out.println(MenuWorldRendererMy.getx()); 
	
	System.out.println(body.getPosition().x);
	
		String name = null;//System.out.println(ddd);
		//System.out.println(	body.getPosition().x);
		if (MenuWorldRendererMy.getx()<=body.getPosition().x+1.5f&&MenuWorldRendererMy.getx()>body.getPosition().x
			&&			MenuWorldRendererMy.gety()<=body.getPosition().y+0.5f&&MenuWorldRendererMy.gety()>body.getPosition().y
				)
				{
			
			name=namepress;
		//System.out.println(x+y);
	System.out.println(SelectWorldRendererMy.getx());
	System.out.println("Fuck yeh");
	//body.setActive(false);

	System.out.println(name);}
		else{name=null;}
		return name;
		}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		//animatedBox2DSprite.draw(batch,body);
//		box2DSprite.draw(batch,body);
	}

}
