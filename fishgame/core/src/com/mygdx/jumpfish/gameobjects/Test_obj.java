package com.mygdx.jumpfish.gameobjects;

import java.nio.charset.Charset;

import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
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
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;

public class Test_obj extends AbstractGameObject {
	public AnimatedBox2DSprite animatedBox2DSprite;
	public AnimatedSprite animatedSprite;
	public Fixture Oknovibora;
	public Animation animation;
	public float Timer=0;
	public float posx;
	public float posy;
	public Test_obj(float x,float y,Body b) {
	body = b;
		posx = x;
		posy = y;
		
		
init();
}
private void init() {
	
//	box2DSprite = new Box2DSprite(dropImage);
//	body.setUserData(box2DSprite);
	PolygonShape poly = new PolygonShape();		
	poly.setAsBox(0.398f,0.25f);
	//	CircleShape circle = new CircleShape();		
	//		circle.setRadius(0.3f);
					//	circle.setRadius(1.4f);
		Oknovibora = body.createFixture(poly,0);

	//	circle.dispose();		
		
		body.setType(BodyType.DynamicBody);
	body.setUserData("fishplatforma");
	//	Oknovibora.setSensor(false);
		Oknovibora.setDensity(1);
		Oknovibora.setRestitution(0);
		Oknovibora.setFriction(1);
		Oknovibora.setUserData("fishplatforma");
		body.setTransform(posx, posy,0);
		body.setActive(false);
	
		animation = AssetsMy.instance.fish.newplatform;

		animatedSprite = new AnimatedSprite(AssetsMy.instance.fish.newplatform);
		//	animatedSprite.pause();
			animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
		
			body.setUserData(animatedBox2DSprite);
	}


public Body getBody(){
	return body;
}
public void update(float deltaTime) {
	
		super.update(deltaTime);
		if(body.isActive()){
			
		Timer=Timer+0.1f;
		body.setLinearVelocity(0, 0.2f);}
		if(Timer > 60&&body.isActive()){
			System.out.println("llldlflsdfldsfds");
			Timer=0;
			body.setActive(false);
			body.setTransform(-10,-10,0);
		}
		
}

	
	
	
	public void render(SpriteBatch batch,Box2DSprite b) {
		b.draw(batch,body);
		
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(SpriteBatch batch) {
	//	TextureRegion reg = null;
	//	reg = animation.getKeyFrame(stateTime, true);
	//	batch.draw(reg, position.x, position.y,1f,1f);
		// TODO Auto-generated method stub
		 batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
			animatedBox2DSprite.draw(batch,body);
			 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
	}
}
