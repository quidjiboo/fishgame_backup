package com.mygdx.jumpfish.gameobjects.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.MyFish;
import com.mygdx.jumpfish.util.AssetsMy;

public class GUI extends AbstractGameObject  {
	private final String RUSSIAN_CHARACTERS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private BitmapFont font2;
	private int positionX;
	private int positionY;
	
	public  Bar helth_solid;
	private MyFish myFistut;
	public int number;
	public  Bar  EnergyEC;
	public  Bar  ramkaEC;
	public  Bar  ramka;
	public TextureRegion ramkabox;
	public TextureRegion ramkah2o;
	public TextureRegion ramkadj;
	public TextureRegion ramkapl;
	public TextureRegion ramkagun;
	
	public TextureRegion fishnotjump;
	public Animation helth_arrow;
	public Animation helth_solid1;
	public GUI(MyFish myFish,int number) {
		this.number = number;
		myFistut = myFish;
		init();
	}
	
	private void createFonts() {
		
		String FONT_CHARS = "";
		for( int i = 32; i < 127; i++ ) FONT_CHARS += (char)i; // С†РёС„СЂС‹ Рё РІРµСЃСЊ Р°РЅРіР»РёР№СЃРєРёР№
		for( int i = 1024; i < 1104; i++ ) FONT_CHARS += (char)i;// СЂСѓСЃ

		FreeTypeFontParameter ftfp1 = new FreeTypeFontParameter();
		// ftfp.characters = FONT_CHARS;
		ftfp1.characters =RUSSIAN_CHARACTERS;
		ftfp1.size =50;
		
		//FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("images/acitynova.ttf"));
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("data/hooge_05_55_cyr.ttf"));
		font2 = generator2.generateFont( ftfp1 );
		
		//.generateFont(42,FONT_CHARS,false);
		generator2.dispose();
		
	 ///   FileHandle fontFile = Gdx.files.internal("images/hooge_05_55_cyr.ttf");
	  //  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
	    
	   	
		
	 //   FreeTypeFontParameter Parameter = new FreeTypeFontParameter();
	  //  titleFont =   generator.generateFont(Parameter);
	 //   titleFont.setScale(0.7f);
	  //  generator.dispose();
	}
private void init() {
	createFonts() ;
	positionX=10;
	positionY=0;
	
	fishnotjump = AssetsMy.instance.fish.fishnotjump;
	helth_arrow = AssetsMy.instance.fish.helth_arrow;
	helth_solid1 = AssetsMy.instance.fish.helth_arrowup;
	setAnimation(helth_arrow);
	helth_solid = new	Bar(AssetsMy.instance.fish.helth_solid,0,0,0);

	ramka = new	Bar(AssetsMy.instance.fish.ramka_bar,0,0,0);
	EnergyEC = new	Bar(AssetsMy.instance.fish.EnergyEC,0,0,100);
	ramkaEC = new	Bar(AssetsMy.instance.fish.ramkaEC,0,0,100);
	
	ramka.flipy();
	
	EnergyEC.flipy();
	ramkaEC.flipy();
	if(!fishnotjump.isFlipY()){
	fishnotjump.flip(false, true);}
	
	animation.setPlayMode(PlayMode.NORMAL);
	
	ramkah2o= AssetsMy.instance.fish.H2O_GUI;
	if(!ramkah2o.isFlipY())
	ramkah2o.flip(false, true);
	ramkadj= AssetsMy.instance.fish.DJ_GUI;
	if(!ramkadj.isFlipY())
	ramkadj.flip(false, true);
	ramkapl= AssetsMy.instance.fish.PL_GUI;
	if(!ramkapl.isFlipY())
	ramkapl.flip(false, true);
	ramkagun= AssetsMy.instance.fish.GUN_GUI;
	if(!ramkagun.isFlipY())
	ramkagun.flip(false, true);
	ramkabox = AssetsMy.instance.fish.ramkabox;
	if(!ramkabox.isFlipY())
	ramkabox.flip(false, true);

}
public void update(float deltaTime,boolean inwater,int livs) {
	
	
	super.update(deltaTime);
	if (inwater&&animation!=helth_solid1){
		
		setAnimation(helth_solid1);
		
	}
	if (!inwater&&animation!=helth_arrow){
		
		setAnimation(helth_arrow);
		
	}
	
		if (livs%10>=7){
			animation.setFrameDuration(1f/2f);
		}
		else if (livs%10>=3&&livs%10<=7){
			animation.setFrameDuration(1f/4f);
		}
		else if (livs%10<3){
			animation.setFrameDuration(1f/6f);}
	
	//animation.setFrameDuration(1f/(10-(livs%10)));
	
}


public void render (SpriteBatch batch,int livs,int EC,int xx,boolean jump,int h20,boolean h201,int pl,boolean pl1,int gun,boolean gun1,int dj,boolean dj1,boolean perezaridka ) {

	
	
 	int TEST=positionX;
	int EC1;
	EC1=number-EC;
	int xxx;
	xxx=xx;
	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
	
	ramka.Drawpos(batch,positionX-5,positionY+5);
	
	 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	if  (livs>0){
	for (int i = 0; i <= (int)Math.floor(livs/10)	; i++) {
				//ramkaEC.Drawpos(batch,xxx-40,positionY+10);	
				if (i==(int)Math.floor(livs/10)){
				TextureRegion reg = null;
				reg = animation.getKeyFrame(stateTime, true);
				batch.draw(reg.getTexture(), TEST, positionY+10
						, origin.x, origin.y, 15, 32,
						1, 1, rotation, reg.getRegionX(), reg.getRegionY(),
						reg.getRegionWidth(), reg.getRegionHeight(),true, false);
			}
			else if(i!=(int)Math.floor(livs/10)){
				
			helth_solid.Drawpos(batch,TEST,positionY+10);	}
			TEST=TEST+15;
		}
	}
	
	
//	helth_solid.Drawpos(batch,positionX,positionY+10);	
//	helth_solid.Drawpos(batch,positionX+15,positionY+10);	
///	helth_solid.Drawpos(batch,positionX+30,positionY+10);
//	helth_solid.Drawpos(batch,positionX+45,positionY+10);
//	helth_solid.Drawpos(batch,positionX+60,positionY+10);
//	helth_solid.Drawpos(batch,positionX+75,positionY+10);
//	helth_solid.Drawpos(batch,positionX+90,positionY+10);
//	helth_solid.Drawpos(batch,positionX+105,positionY+10);
//	helth_solid.Drawpos(batch,positionX+120,positionY+10);
	//helth_solid.Drawpos(batch,positionX+135,positionY+10);
	
	
//	int xx=780;
for (int i = 0; i < number; i++) {
		
	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ramkaEC.Drawpos(batch,xxx-40,positionY+10);	
		 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		xxx=xxx-30;
	}
	for (int i = 0; i < EC1; i++) {
	EnergyEC.Drawpos(batch,xx-40,positionY+10);	
	xx=xx-30;
	}
//	AssetsMy.instance.fonts.defaultBig.draw(batch, (int)(myFistut.PreperingTime)+"", positionX+10,positionY+420);
//	AssetsMy.instance.fonts.defaultBig.draw(batch, livs +"", positionX+10,positionY+400);
	AssetsMy.instance.fonts.defaultBig.setColor(0.75f,0f, 0.75f, 1);
	//AssetsMy.instance.fonts.defaultBig.draw(batch, "FPS " + (int)(Gdx.graphics.getFramesPerSecond()),  positionX+10, 380);
	AssetsMy.instance.fonts.defaultBig.setColor(1,1,1, 1);
	if(!jump)
	
	batch.draw(fishnotjump,170, 10);
		batch.draw(ramkabox, 5,50,32,32);
		 font2.setScale(0.5f,-0.5f);
			font2.setColor(Color.WHITE);
			
	
	if (!h201){
				batch.setColor(0.3f, 0.3f,0.3f, 0.4f);
				batch.draw(ramkah2o, 5,50,32,32);
				batch.setColor(1, 1, 1, 1f);
				font2.setColor(1, 1, 1, 0.4f);
				font2.drawMultiLine(batch, "="+h20, 40,57, 0, BitmapFont.HAlignment.LEFT);	
				font2.setColor(1, 1, 1, 1f);
				}
		else{
			batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
			batch.draw(ramkah2o, 5,50,32,32);
			batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			font2.drawMultiLine(batch, "="+h20, 40,57, 0, BitmapFont.HAlignment.LEFT);
			}

	

	batch.draw(ramkabox, 5,84,32,32);
	
	if (!dj1){
		batch.setColor(0.3f, 0.3f,0.3f, 0.4f);
		batch.draw(ramkadj, 5,84,32,32);
		batch.setColor(1, 1, 1, 1f);
		font2.setColor(1, 1, 1, 0.4f);
		font2.drawMultiLine(batch, "="+dj, 40,91, 0, BitmapFont.HAlignment.LEFT);	
		font2.setColor(1, 1, 1, 1f);
		}
else{
	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
	batch.draw(ramkadj, 5,84,32,32);
	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	font2.drawMultiLine(batch, "="+dj, 40,91, 0, BitmapFont.HAlignment.LEFT);
	}

	
	//batch.draw(ramkadj, 5,84,32,32);
	
	batch.draw(ramkabox, 5,118,32,32);
	//batch.draw(ramkapl, 5,118,32,32);
	if (!pl1){
		batch.setColor(0.3f, 0.3f,0.3f, 0.4f);
		batch.draw(ramkapl, 5,118,32,32);
		batch.setColor(1, 1, 1, 1f);
		font2.setColor(1, 1, 1, 0.4f);
		font2.drawMultiLine(batch, "="+pl, 40,129, 0, BitmapFont.HAlignment.LEFT);	
		font2.setColor(1, 1, 1, 1f);
		}
else{
	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
	batch.draw(ramkapl, 5,118,32,32);
	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	font2.drawMultiLine(batch, "="+pl, 40,129, 0, BitmapFont.HAlignment.LEFT);
	}
	
	batch.draw(ramkabox, 5,152,32,32);
	
	
	//batch.draw(ramkagun, 5,152,32,32);
	if (!gun1){
		batch.setColor(0.3f, 0.3f,0.3f, 0.4f);
		batch.draw(ramkagun, 5,152,32,32);
		batch.setColor(1, 1, 1, 1f);
		font2.setColor(1, 1, 1, 0.4f);
		font2.drawMultiLine(batch, "="+gun, 40,163, 0, BitmapFont.HAlignment.LEFT);	
		font2.setColor(1, 1, 1, 1f);
		}
else{
	if(perezaridka){
		batch.setColor(1f, 0.3f,0.3f, 1f);
	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
	batch.draw(ramkagun, 5,152,32,32);
	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	font2.drawMultiLine(batch, "="+gun, 40,163, 0, BitmapFont.HAlignment.LEFT);
	batch.setColor(1, 1, 1, 1f);}
	else if(!perezaridka){
		batch.setColor(1, 1, 1, 1f);
		batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.draw(ramkagun, 5,152,32,32);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		font2.drawMultiLine(batch, "="+gun, 40,163, 0, BitmapFont.HAlignment.LEFT);}
	batch.setColor(1, 1, 1, 1f);
	}
	 
	
	
		
	
	}

public void disposetitleFont () {
	if (font2!=null)
	font2.dispose();
//	titleFont.dispose();
	
}

@Override
public void render(SpriteBatch batch) {
	// TODO Auto-generated method stub
	
}

}