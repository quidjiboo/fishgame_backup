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
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;



public class CameraHelper {

	private static final String TAG = CameraHelper.class.getName();
	Vector2 lerpTarget = new Vector2();
	private final float MAX_ZOOM_IN = 1f;
	private final float MAX_ZOOM_OUT1 = 1.5f;
	private final float MAX_ZOOM_OUT = 6.5f;
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

	public CameraHelper () {
		
		position = new Vector2();
		
		zoom = 1.0f;
		zoom1 = new Vector2(1f,0);
	//	viewportHeight=Constants.VIEWPORT_HEIGHT;
	//	viewportWidth = ((Constants.VIEWPORT_HEIGHT / Gdx.graphics.getHeight()* Gdx.graphics.getWidth()));
	//	viewportHeight1=viewportHeight*2;
	//	viewportWidth1=viewportWidth*2;
	}
	
	public void setXY(float X,float Y){
		 HelperX=X/2-0.25f;
		 HelperY=Y/2-0.25f;	
	}
	public void update (float deltaTime) {
	
		if (!hasTarget()) return;
	
		if (hasTarget()
				&& target.pos== POSICION.PREPARING 
				&&target.PreperingTime >= 100)
					{
			
	
		
		
		position.x = target.position.x;
		position.x = Math.max(-WorldRendererMy.XXX+0.1f,position.x);
		
		position.y = target.position.y;
		position.y = Math.max(-WorldRendererMy.YYY+0.1f,position.y);
		
		//if(position.x>39.75f+WorldRendererMy.XXX+0.1f)
	//		position.x = 39.75f+WorldRendererMy.XXX+0.1f;
	//	if(position.y>24.75f+WorldRendererMy.YYY+0.1f)
	//		position.y = 24.75f+WorldRendererMy.YYY+0.1f;
		if(position.x>HelperX+WorldRendererMy.XXX+0.1f)
			position.x = HelperX+WorldRendererMy.XXX+0.1f;
		if(position.y>HelperY+WorldRendererMy.YYY+0.1f)
			position.y =HelperY+WorldRendererMy.YYY+0.1f;
		
		addZoom1(0.01f);}
		else if (hasTarget()& target.pos!= POSICION.PREPARING&&!target.Prepering)
			{
		
		//	System.out.println((zoom1.lerp(new Vector2(1f,0), deltaTime*1f)).x);
			
			//addZoom(zoom1.x);
		//	addZoom(-0.02f);
		//	setZoom(1f);
			
			position.x = target.position.x;
			position.x = Math.max(-WorldRendererMy.XXX+0.1f,position.x);
			
			position.y = target.position.y;
			
			position.y = Math.max(-WorldRendererMy.YYY+0.1f,position.y);
		
	///		if(position.x>39.75f+WorldRendererMy.XXX+0.1f)
	//			position.x = 39.75f+WorldRendererMy.XXX+0.1f;
	//		if(position.y>24.75f+WorldRendererMy.YYY+0.1f)
	//			position.y = 24.75f+WorldRendererMy.YYY+0.1f;
			if(position.x>HelperX+WorldRendererMy.XXX+0.1f)
				position.x = HelperX+WorldRendererMy.XXX+0.1f;
			if(position.y>HelperY+WorldRendererMy.YYY+0.1f)
				position.y =HelperY+WorldRendererMy.YYY+0.1f;
			//position.lerp(target.position,deltaTime);
			addZoom(-0.01f);
	//		setZoom1((zoom1.lerp(new Vector2(1f,0), deltaTime*1f).x));	
		
	
			
			}
		else {position.x = target.position.x;
		
		position.y = target.position.y;
		position.x = Math.max(-WorldRendererMy.XXX+0.1f,position.x);
		position.y = Math.max(-WorldRendererMy.YYY+0.1f,position.y);
	//	if(position.x>39.75f+WorldRendererMy.XXX+0.1f)
	//		position.x = 39.75f+WorldRendererMy.XXX+0.1f;
	//	if(position.y>24.75f+WorldRendererMy.YYY+0.1f)
	//		position.y = 24.75f+WorldRendererMy.YYY+0.1f;}
		if(position.x>HelperX+WorldRendererMy.XXX+0.1f)
			position.x = HelperX+WorldRendererMy.XXX+0.1f;
		if(position.y>HelperY+WorldRendererMy.YYY+0.1f)
			position.y =HelperY+WorldRendererMy.YYY+0.1f;}
		// Prevent camera from moving down too far
	//	position.y = Math.max(3.5f, position.y); // доделать что бы вычисляляась в зависимости от размера окна!!!!!
	//	position.y = Math.min(21.5f, position.y);// доделать что бы вычисляляась в зависимости от размера окна!!!!!
	//	System.out.println(position.x);
	//	position.x = (float) Math.max(5.85f*WorldRendererMy.XXX, position.x);
	//	position.x = (float) Math.min(34.25f*WorldRendererMy.XXX, position.x);
		
	//	position.x=(int)position.x+((position.x)-(int)position.x); 
	//	position.y=(int)position.y+((position.y)-(int)position.y);
	//	position.x=(int)position.x+(int)(((position.x)-(int)position.x)/0.03125f)*0.03125f;
	//	position.y=(int)position.y+(int)(((position.y)-(int)position.y)/0.03125f)*0.03125f;// округляю положение камеры
	//	position.x=(int)(position.x/0.03125f)*0.03125f;
	//	position.y=(int)(position.y/0.03125f)*0.03125f;
	//	System.out.println((int)(position.x/0.03125f)*0.03125f);
	//	position.x = Math.min(Math.max(zoom * WorldRendererMy.halfWidth, target.position.x),7 - WorldRendererMy.halfWidth) * 0.03125f;
	//	  position.y = Math.min(Math.max(zoom * WorldRendererMy.halfHeight, target.position.y), 5 - WorldRendererMy.halfHeight);
		
	}

	public void setPosition (float x, float y) {
		this.position.set(x, y);
	}

	public Vector2 getPosition () {
		return position;
	}

	public void addZoom (float amount) {
	//	if (zoom<=MAX_ZOOM_OUT)
		setZoom(zoom + amount);
		zoom1.x=zoom1.x+0.01f;
	}
	public void addZoom1 (float amount) {
	//	if (zoom<=MAX_ZOOM_OUT)
		setZoom1(zoom + amount);
		zoom1.x=zoom1.x+0.01f;
	}
			
	public void setZoom1 (float zoom) {
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT1);
	//	System.out.println(viewportHeight1);
//		viewportHeight=viewportHeight1;
//		viewportWidth=viewportWidth1;
	}


	public void setZoom (float zoom) {
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
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
