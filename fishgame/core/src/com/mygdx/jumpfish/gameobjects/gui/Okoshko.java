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
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;

public class Okoshko extends AbstractGameObject {
	//public static final String RUSSIAN_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
  //          + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
	
	public static final String RUSSIAN_CHARACTERS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	public Fixture OkoshkoFix;
	public Box2DSprite box2DSprite;
	BitmapFont titleFont;
	public static BitmapFont font1;
	public String name;
	public String what;
	float xxx=1;
	float yyy=1;
	 private Texture dropImage;
	 private String text;
	public Okoshko(Body b,float x,float y,String n,String w) {
		what=w;
		name=n;
		body = b;
		xxx=x+6;yyy=y+4;
init();
}
private void init() {
	FileHandle file = Gdx.files.internal("Text/"+what);
	
	text = file.readString("windows-1251");

//	dropImage = new Texture(Gdx.files.internal("images/"+name));
	dropImage = new Texture(Gdx.files.internal("images/test.png"));
	
	box2DSprite = new Box2DSprite(dropImage);
	body.setUserData(box2DSprite);
		PolygonShape poly = new PolygonShape();		
		poly.setAsBox(4f,2f);
		OkoshkoFix = body.createFixture(poly,0);
		poly.dispose();		
		
		OkoshkoFix.setDensity(0);
		OkoshkoFix.setRestitution(0);
		OkoshkoFix.setFriction(0);
		OkoshkoFix.setUserData("okoshko");
	body.setTransform(xxx, yyy, 0);

		body.setActive(false);
		body.resetMassData();
		createFonts() ;
	}
@SuppressWarnings("deprecation")
private void createFonts() {
	
	String FONT_CHARS = "";
	for( int i = 32; i < 127; i++ ) FONT_CHARS += (char)i; // С†РёС„СЂС‹ Рё РІРµСЃСЊ Р°РЅРіР»РёР№СЃРєРёР№
	for( int i = 1024; i < 1104; i++ ) FONT_CHARS += (char)i;// СЂСѓСЃ

	FreeTypeFontParameter ftfp = new FreeTypeFontParameter();
	// ftfp.characters = FONT_CHARS;
	ftfp.characters =RUSSIAN_CHARACTERS;
	ftfp.size = 80;
	ftfp.borderColor=Color.BLACK;
	
	//ftfp.borderStraight=true;
	ftfp.borderWidth=3;
	FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("data/hooge_05_55_cyr.ttf"));
	font1 = generator1.generateFont( ftfp );
	
	//.generateFont(42,FONT_CHARS,false);
	generator1.dispose();
	
 ///   FileHandle fontFile = Gdx.files.internal("images/hooge_05_55_cyr.ttf");
  //  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
    
   	
	
 //   FreeTypeFontParameter Parameter = new FreeTypeFontParameter();
  //  titleFont =   generator.generateFont(Parameter);
 //   titleFont.setScale(0.7f);
  //  generator.dispose();
}
public Body getBody(){
	return body;
}
public void update(float deltaTime) {
	
		super.update(deltaTime);

}

	
	public void render(SpriteBatch batch,boolean text1,boolean kartinka) {
		if (kartinka){
		// TODO Auto-generated method stub
		box2DSprite.draw(batch,body); 
		
	///		titleFont.setScale(0.07f);
	//	titleFont.setColor(Color.YELLOW);
		}
		if (text1){
			
		font1.setScale(0.01f);
		font1.setColor(Color.PURPLE);
		font1.drawMultiLine(batch, text, xxx, yyy+2, 0, BitmapFont.HAlignment.CENTER);
	
		}
		
	}
	public void disposetitleFont () {
		if (font1!=null)
		font1.dispose();
	//	titleFont.dispose();
		if (dropImage!=null)
		dropImage.dispose();
	}
	@Override
	public void render(SpriteBatch batch) {
		
		
		
		// TODO Auto-generated method stub
		
	}
}
