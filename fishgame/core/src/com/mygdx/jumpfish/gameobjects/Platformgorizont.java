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
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;



public class Platformgorizont extends AbstractGameObject {
	public float skorostx;
	public float skorosty;
	public float startvisota;
	public float startx;
	public TextureRegion small_coob1;
	public boolean Preperinganim;
	public boolean vverh;
	public boolean vpravo=true;
	public String dlina;
	public Animation ply_pl;
	public int dlina1;
	public enum VIEW_DIRECTION {
		LEFT, RIGHT
	}
	public VIEW_DIRECTION viewDirection;
	public Platformgorizont(Body b) {
				body= b;
		init();
	}

	private void init() {
		
		
	
	
	

		
	//	body.getFixtureList().get(0).setUserData("small_car");
		body.getFixtureList().get(0).setFriction(0.8f);
		body.getFixtureList().get(0).setDensity(0);
		body.getFixtureList().get(0).setRestitution(0);;
		small_coob1 = AssetsMy.instance.fish.coob1;
		
		ply_pl = AssetsMy.instance.fish.ply_pl;
		setAnimation(ply_pl);
		origin.set(dimension.x / 2, dimension.y / 2);
		body.setType(BodyType.KinematicBody);
		body.setGravityScale(0);
		body.resetMassData();
		dlina=body.getUserData().toString();
		dlina=dlina.substring(7);
		dlina1=Integer.parseInt(dlina);
	//	body.setLinearVelocity(0f, 1f);
	
		startvisota=body.getPosition().y;
		startx=body.getPosition().x;
	}
	
		
;

	public void update(float deltaTime) {
		super.update(deltaTime);

	
//	if (body.getPosition().y>=startvisota){ vverh=false;body.setLinearVelocity(0, 0);}
	

//	else if (body.getPosition().y<startvisota-dlina1||vverh){
		
	//	body.setLinearVelocity(0,1f);
	//	vverh=true;
//	}
		if(body.getPosition().x<startx){vpravo=true;}
		if(body.getPosition().x<=startx+dlina1&&vpravo){body.setLinearVelocity(1, 0);}

		if(body.getPosition().x>startx+dlina1){body.setLinearVelocity(-1, 0);vpravo=false;}
		
		//	System.out.println(dlina1);
		if (body.getPosition().y<startvisota-0.02f){body.setLinearVelocity(0, 0.4f);}
	
		if (body.getPosition().y>startvisota){body.setLinearVelocity(0, 0);}
	}
	
	
	public Body getBody(){
		return body;
	}

	
	public void render(SpriteBatch batch) {
		
		TextureRegion reg = null;
		reg = small_coob1;
	//	batch.draw(reg, position.x, position.y,0.5f,0.5f);
		batch.draw(reg.getTexture(), position.x-0.5f, position.y
				, origin.x, origin.y, dimension.x/2, dimension.y/2,
				scale.x, 2, 90, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(),false, false);
		reg = null;
		reg = animation.getKeyFrame(stateTime, true);
		batch.draw(reg.getTexture(), position.x+0.25f, position.y-0.5f
				, origin.x, origin.y, dimension.x/2, dimension.y/2,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(),false, false);
		//animatedBox2DSprite.draw(batch,body);
		}
		
	

}
