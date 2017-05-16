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

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.util.AssetsMy;



public class Fon extends AbstractGameObject {
	public Animation animNormal; // может приват !! 
	public Animation preparing; // может приват !! 
	public Animation slap;
	public Animation fly;
	public  AtlasRegion see;
	public  AtlasRegion beach;
	public  Array<AtlasRegion> cloud;
	public  AtlasRegion zabor;
	public  AtlasRegion sky;
	//public Fixture playerPhysicsFixture;
	public float CLOUDX;
	
	public Fon() {
		init();
	}

	private void init() {
//		see = AssetsMy.instance.fish.see;
////		beach = AssetsMy.instance.fish.beach;
///		cloud = AssetsMy.instance.fish.cloud;
//		zabor = AssetsMy.instance.fish.zabor;
//		sky = AssetsMy.instance.fish.sky;
		CLOUDX=-25;
			}
	

	public void update(float deltaTime) {
		CLOUDX=CLOUDX+deltaTime;
		super.update(deltaTime);

	}

	
	
	
	public void render(SpriteBatch batch,Body body) {
		
		TextureRegion reg = null;
		
		reg = see;
	
		int k=0;
		for (int i = 0; i < 9; i++) {
		
		batch.draw(reg.getTexture(),-body.getPosition().x*0.02f+10+k, 18, origin.x, origin.y, dimension.x, dimension.y,
			scale.x*2, scale.y*2, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		k=k+2;
	}
		reg = sky;
		
		k=0;
		for (int i = 0; i < 9; i++) {
		
		batch.draw(reg.getTexture(),-body.getPosition().x*0.02f+10+k, 20, origin.x, origin.y, dimension.x, dimension.y,
			scale.x*2, scale.y*2, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		k=k+2;
	}
		reg = beach;
		k=0;
		for (int i = 0; i < 6; i++) {
		
		batch.draw(reg.getTexture(), -(body.getPosition().x*0.05f)+10+k,13.6f, origin.x, origin.y, dimension.x, dimension.y,
				scale.x*5, scale.y*5, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		k=k+5;
		}
		k=0;
		reg = cloud.get(0);
		for (int i = 0; i < 3; i++) {
				batch.draw(reg.getTexture(),k+CLOUDX*1.1f,19f, origin.x, origin.y, dimension.x, dimension.y,
				scale.x*3, scale.y*3, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		k=k+5;
		}
		k=0;
		reg = cloud.get(1);
		for (int i = 0; i < 3; i++) {
		
		batch.draw(reg.getTexture(),15+k+CLOUDX*1.1f,19f, origin.x, origin.y, dimension.x, dimension.y,
				scale.x*3, scale.y*3, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		k=k+7;
		}
		k=0;
		reg = cloud.get(2);
		for (int i = 0; i < 3; i++) {
			
			batch.draw(reg.getTexture(),5+k+CLOUDX*1.1f,19f, origin.x, origin.y, dimension.x, dimension.y,
					scale.x*3, scale.y*3, rotation, reg.getRegionX(), reg.getRegionY(),
					reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			k=k+13;
			}
			k=0;
				
		
if(CLOUDX>=23){CLOUDX=-25;}


		reg = zabor;
	
		k=0;
		for (int i = 0; i < 4; i++) {
		
		batch.draw(reg.getTexture(), -(body.getPosition().x*0.06f)+10+k,12.6f, origin.x, origin.y, dimension.x, dimension.y,
				scale.x*5, scale.y*5, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		k=k+5;
		}
		
		
		
		
	//	System.out.println(reg.getRegionWidth()*WorldRendererMy.UnitScale*5);
}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
