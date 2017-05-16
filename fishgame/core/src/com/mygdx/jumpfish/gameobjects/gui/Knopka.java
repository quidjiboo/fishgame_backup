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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.levelselect.SelectWorldControllerMy;
import com.mygdx.jumpfish.levelselect.SelectWorldRendererMy;
import com.mygdx.jumpfish.transactions.ScreenTransition;
import com.mygdx.jumpfish.transactions.ScreenTransitionFade;
import com.mygdx.jumpfish.util.AssetsMy;



public class Knopka extends AbstractGameObject {
	
	public String nomer;
	public int intnomer;
	public float dlina1=0.1f;
//	public BitmapFont titleFont;
	public float startvisota;
	public Box2DSprite box2DSprite;
	public Box2DSprite box2DSpritenpjump;
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public boolean vverh=false;
	
	
	
	public Knopka(Body b) {
				body = b;
		init();
	}

	private void init() {
		
		nomer=body.getUserData().toString();
		nomer=nomer.substring(11);
		intnomer=Integer.parseInt(nomer);
		//System.out.println(nomer);
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.animNormal);
	//	animatedSprite.pause();
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
		
		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.selectbox);
		box2DSpritenpjump = new Box2DSprite(AssetsMy.instance.fish.fishnotjump);
		body.setUserData(box2DSprite);
		
		
		body.getFixtureList().get(0).setDensity(1);
		body.setGravityScale(0.1f);
		
		
		body.getFixtureList().get(0).setFriction(1);
		body.resetMassData();
		startvisota=body.getPosition().y;
		
//		createFonts();
	}
	public String  getname(){
		return nomer;
		
	}
//	private void createFonts() {
//	    FileHandle fontFile = Gdx.files.internal("images/pixelart.ttf");
//	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
	    
	 //   titleFont =   generator.generateFont(60);
	//    FreeTypeFontParameter Parameter = new FreeTypeFontParameter();
	//    titleFont =   generator.generateFont(Parameter);
	//    titleFont.setScale(0.07f);
	//    generator.dispose();
//	}
		
public String PressKnopka(int x , int y)
{String start = null;//System.out.println(ddd);
	//System.out.println(	body.getPosition().x);
	if (SelectWorldRendererMy.getx()<=body.getPosition().x+2f&&SelectWorldRendererMy.getx()>body.getPosition().x&&
			SelectWorldRendererMy.gety()<=body.getPosition().y+2f&&SelectWorldRendererMy.gety()>body.getPosition().y	){
		
		start=nomer;
	//System.out.println(x+y);
System.out.println(SelectWorldRendererMy.getx());
System.out.println("Fuck yeh");
//body.setActive(false);

	}
	else{start=null;}
	return start;
	}

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

		if (body.getPosition().y>=startvisota){ vverh=false;body.setLinearVelocity(0, 0f);}
		else if (body.getPosition().y<startvisota&&body.getPosition().y>=startvisota-dlina1&&vverh)
		{body.setLinearVelocity(0,0.3f);}
			//	body.getLinearVelocity().y+0.20f);}
		//else if (body.getPosition().y<startvisota&&body.getPosition().y>=startvisota-dlina1&&!vverh)
		//{body.setLinearVelocity(0, body.getLinearVelocity().y+0.15f);}
		else if (body.getPosition().y<startvisota-dlina1){
			body.setLinearVelocity(0,0.3f);
		//	System.out.println(vverh+"dsfdsfdsfdsf");
			vverh=true;
		}
		
		
	}
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
	//	titleFont.draw(batch, nomer,body.getPosition().x+0.1f,body.getPosition().y+1.1f);	
		//animatedBox2DSprite.draw(batch,body);
		if(intnomer==2||intnomer==1||intnomer==3||intnomer==4||intnomer==5){
			box2DSpritenpjump.setColor(1, 1, 1, 0.4f);
			box2DSpritenpjump.setScale(0.75f);
			box2DSpritenpjump.draw(batch,body);
			box2DSpritenpjump.setColor(1, 1, 1,1);
			box2DSpritenpjump.setFlip(false, false);}
		box2DSprite.draw(batch,body);
		
	}
	
//	public void disp(){
//		titleFont.dispose();
//	}
}
