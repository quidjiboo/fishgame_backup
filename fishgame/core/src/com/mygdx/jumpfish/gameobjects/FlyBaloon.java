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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.CameraHelper;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MyAudioManager;



public class FlyBaloon extends AbstractGameObject {
	public long soundId;
	public float skorostx;
	public float skorosty;
	public float startvisota;
	public Animation helic;
	public boolean Preperinganim;
	public boolean vverh;
	public String dlina;
	public int dlina1;
	private float timer=4.2f;
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
	}
	public VIEW_DIRECTION viewDirection;
	public FlyBaloon(Body b) {
				body= b;
		init();
	}

	private void init() {
		
		
	//	animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.smallcar);
	//	animatedSprite.pause(); 
	//	animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
	
	//	body.setUserData(animatedBox2DSprite);
	//	carfixtur.setUserData("small_car");
		viewDirection = VIEW_DIRECTION.LEFT;
		body.getFixtureList().get(0).setUserData("333");
		body.getFixtureList().get(0).setFriction(0.3f);
		body.getFixtureList().get(0).setDensity(0);
		body.getFixtureList().get(0).setRestitution(0);;
		helic = AssetsMy.instance.fish.helic;
		setAnimation(helic);
		origin.set(dimension.x / 2, dimension.y / 2);
		//PolygonShape poly = new PolygonShape();		
		//poly.setAsBox(1f,1f);
		//carfixtur = body.createFixture(poly,0);
	//	animatedSprite.play();
	//	animatedSprite.setFlip(false, true);
		body.resetMassData();
		dlina=body.getUserData().toString();
		dlina=dlina.substring(7);
		dlina1=Integer.parseInt(dlina);
	//	body.setLinearVelocity(0f, 1f);
		skorosty=body.getLinearVelocity().y;
		startvisota=body.getPosition().y;
		 soundId  = AssetsMy.instance.sounds.helicopter.loop();
		 AssetsMy.instance.sounds.helicopter.setVolume(soundId,0);
		//	AssetsMy.instance.sounds.helicopter.loop();
	}
	
		


	public void update(float deltaTime,float volum) {
		
		super.update(deltaTime);
		AssetsMy.instance.sounds.helicopter.setVolume(soundId, volum*GamePreferences.instance.volSound);
	
		
		//if (body.isAwake()){	Preperinganim=true;}
	//	else{		Preperinganim=false;}
	if (body.getPosition().y>=startvisota){ vverh=false;body.setLinearVelocity(0, 0f);}
	else if (body.getPosition().y<startvisota&&body.getPosition().y>=startvisota-dlina1&&vverh)
	{body.setLinearVelocity(0, body.getLinearVelocity().y+0.20f);}
	else if (body.getPosition().y<startvisota&&body.getPosition().y>=startvisota-dlina1&&!vverh)
	{body.setLinearVelocity(0, body.getLinearVelocity().y+0.15f);}
	else if (body.getPosition().y<startvisota-dlina1){
		body.setLinearVelocity(0,1f);
		vverh=true;
	}
		
			skorosty=body.getLinearVelocity().y;
		//	System.out.println(dlina1);
	
	}
	
	private float addskorost(float y){
		if (y<=3)y=y+0.2f;
		return y;
	}
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		
		TextureRegion reg = null;
		reg = animation.getKeyFrame(stateTime, true);
		batch.draw(reg.getTexture(), position.x, position.y
				, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.LEFT, false);
		//animatedBox2DSprite.draw(batch,body);
		}
		
	

}
