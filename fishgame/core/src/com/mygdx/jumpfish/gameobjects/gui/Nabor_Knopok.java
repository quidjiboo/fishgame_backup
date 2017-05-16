package com.mygdx.jumpfish.gameobjects.gui;

import java.nio.charset.Charset;

import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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

public class Nabor_Knopok  {

	public Box2DSprite vistrelSprite;
	public Box2DSprite dubljumpSprite;
	public Box2DSprite helthSprite;
	public Box2DSprite buildSprite;
	public Oknovibora_MyFish Okno_vistrel;
	public Oknovibora_MyFish Okno_dubljump;
	public Oknovibora_MyFish Okno_helth;
	public Oknovibora_MyFish Okno_build;
	public Body bodyvistrel;
	public Body bodydublejump;
	public Body bodyhelth;
	public Body bodybuild;
	public World world;
	public float posx;
	public float posy;
	public Nabor_Knopok(float x,float y,World w) {
		world =w;
		BodyDef	bodyDef = new BodyDef();
		bodyvistrel = world.createBody(bodyDef);
		bodydublejump = world.createBody(bodyDef);
		bodyhelth = world.createBody(bodyDef);
		bodybuild = world.createBody(bodyDef);
//	body = b;
		posx = x;
		posy = y;
		
		
init();
}
private void init() {

	vistrelSprite  = new Box2DSprite(AssetsMy.instance.fish.gun);
	dubljumpSprite  = new Box2DSprite(AssetsMy.instance.fish.dj);
	helthSprite  = new Box2DSprite(AssetsMy.instance.fish.h2o);
	buildSprite  = new Box2DSprite(AssetsMy.instance.fish.pl);
	
	
	Okno_vistrel = new Oknovibora_MyFish(posx,posy,bodyvistrel);
	Okno_dubljump = new Oknovibora_MyFish(posx,posy,bodydublejump);
	Okno_helth = new Oknovibora_MyFish(posx,posy,bodyhelth);
	Okno_build = new Oknovibora_MyFish(posx,posy,bodybuild);
	
	
	Okno_vistrel.getBody().setUserData("Okno_vistrel");
	Okno_dubljump.getBody().setUserData("Okno_dubljump");
	Okno_helth.getBody().setUserData("Okno_helth");
	Okno_build.getBody().setUserData("Okno_build");
	
	}



public void update(float deltaTime,float xx,float yy) {
	
	Okno_vistrel.update(deltaTime,xx+1f, yy);
	Okno_dubljump.update(deltaTime,xx, yy-0.7f);
	Okno_helth.update(deltaTime,xx, yy+0.7f);
	Okno_build.update(deltaTime,xx-1f, yy);
}

	
	
	

	public void render(SpriteBatch batch,boolean show,int numOkno_vistrel) {
		if (show){
	
			batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Okno_vistrel.render(batch,vistrelSprite);
		
		
		Okno_dubljump.render(batch,dubljumpSprite);
		Okno_helth.render(batch,helthSprite);
		Okno_build.render(batch,buildSprite);
		 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// TODO Auto-generated method stub
		}	
	}
}
