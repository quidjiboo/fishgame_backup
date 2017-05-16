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


package com.mygdx.jumpfish.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.startscreen.MenuWorldRendererMy;



public class MenuCameraHelper {

	private static final String TAG = MenuCameraHelper.class.getName();
	Vector2 lerpTarget = new Vector2();

	private float HelperX;
	private float HelperY;
	private float cof;
	//private float viewportHeight;
	//private float  viewportWidth;
//	private float viewportHeight1;
	//private float  viewportWidth1;
	private Vector2 position;
	private Vector2 zoom1;
	private float zoom;
	private AbstractGameObject target;

	public MenuCameraHelper () {
		
		position = new Vector2();
			position.x = 6;
		
			position.y = 7;
		zoom = 0.6f;
		
	//	zoom1 = new Vector2(1f,0);
	//	viewportHeight=Constants.VIEWPORT_HEIGHT;
	//	viewportWidth = ((Constants.VIEWPORT_HEIGHT / Gdx.graphics.getHeight()* Gdx.graphics.getWidth()));
	//	viewportHeight1=viewportHeight*2;
	//	viewportWidth1=viewportWidth*2;
	}
	
	
	public void update (float deltaTime) {
		if (!hasTarget()) return;
	
		if (hasTarget()	
)
					{
			
	
		//	setZoom(2f);
		
	//	position.x = 6;
		
	//	position.y = 7;
					}
	}

	public void setPosition (float x, float y) {
		this.position.set(x, y);
	}

	public Vector2 getPosition () {
		return position;
	}

	
			


	public void setZoom (float zoom) {
		this.zoom = zoom;
	//	System.out.println(viewportHeight1);
//		viewportHeight=viewportHeight1;
//		viewportWidth=viewportWidth1;
	}

	
	public float getZoom () {
		return zoom;
	}

	public void setTarget (AbstractGameObject target) {
		this.target = target;
	}

	public AbstractGameObject getTarget () {
		return target;
	}

	public boolean hasTarget () {
		return target != null;
	}

	public boolean hasTarget (AbstractGameObject target) {
		return hasTarget() && this.target.equals(target);
	}

	public void applyTo (OrthographicCamera camera) {
		camera.position.x = position.x;
		camera.position.y = position.y;
		camera.zoom = zoom;
		camera.update();
		
	}

}
