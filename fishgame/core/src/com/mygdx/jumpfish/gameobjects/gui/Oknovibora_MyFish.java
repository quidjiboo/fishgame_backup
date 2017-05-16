package com.mygdx.jumpfish.gameobjects.gui;

import java.nio.charset.Charset;

import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;

public class Oknovibora_MyFish extends AbstractGameObject {
	public Fixture Oknovibora;


	public float posx;
	public float posy;
	public Oknovibora_MyFish(float x,float y,Body b) {
	body = b;
		posx = x;
		posy = y;
		
		
init();
}
private void init() {
	
//	box2DSprite = new Box2DSprite(dropImage);
//	body.setUserData(box2DSprite);
	PolygonShape poly = new PolygonShape();
	
		CircleShape circle = new CircleShape();		
			circle.setRadius(0.25f);
					//	circle.setRadius(1.4f);
		Oknovibora = body.createFixture(circle,0);

		circle.dispose();		
		
		body.setType(BodyType.DynamicBody);
	//	body.setUserData("okno");
		Oknovibora.setSensor(false);
		Oknovibora.setDensity(0);
		Oknovibora.setRestitution(0);
		Oknovibora.setFriction(0);
	//	Oknovibora.setUserData("okoshko");
		body.setActive(false);
	
		

		
	}


public Body getBody(){
	return body;
}
public void update(float deltaTime,float xx,float yy) {
	
		super.update(deltaTime);
		
		body.setTransform(xx, yy, 0);
}

	
	
	
	public void render(SpriteBatch batch,Box2DSprite b) {
	//	b.draw(batch, body.getPosition().x,  body.getPosition().y, 1, 1,0);
		b.draw(batch,body);
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
}
