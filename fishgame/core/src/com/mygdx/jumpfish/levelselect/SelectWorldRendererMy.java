package com.mygdx.jumpfish.levelselect;

import com.badlogic.gdx.math.MathUtils;

import sun.java2d.loops.DrawPolygons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.jumpfish.AlieInJumpFish;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.gameobjects.gui.Bar;
import com.mygdx.jumpfish.gameobjects.gui.GUI;
import com.mygdx.jumpfish.gameobjects.gui.Knopka;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.GamePreferences;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;






public class SelectWorldRendererMy implements Disposable {
	private final String RUSSIAN_CHARACTERS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private  BitmapFont font2;
//	public static Bar LIFEBAR;// подумать зачем статик 
	int viewportHeight;

	//BitmapFont textFont;
public float xtest;
public float ytest;
static float x1;
static float y1;
static float x2;
static float y2;
static float x3;
static float y3;
static float x4;
static float y4;
static float tg;
static float cos;
//public static float  viewportHeight1;
//public static float viewportWight1;
public static float XXX;
public static float YYY;
static float sin;
public static float UnitScale;
	private static final String TAG = SelectWorldRendererMy.class.getName();
	private OrthogonalTiledMapRenderer renderer123;
	private Box2DDebugRenderer renderer;
	private SpriteBatch batch;
	public OrthographicCamera cam;
	private OrthographicCamera cameraGUI;
	
	private SelectWorldControllerMy worldController;
	public Pixmap pixmap;
	public Texture pixmaptex;
	public Texture pixmaptex1;
	BitmapFont fontGameOver1;
	public SelectWorldRendererMy (SelectWorldControllerMy worldController) {
		this.worldController = worldController;
		init();
	}
	
	
	private void init () {
		
		UnitScale =worldController.level.parser.getUnitScale();
		
		renderer123 = new OrthogonalTiledMapRenderer(worldController.level.map,UnitScale);
		renderer123.getBatch().enableBlending(); 
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Constants.Menu_VIEWPORT_WIDTH, Constants.Menu_VIEWPORT_HEIGHT);
	
		renderer = new Box2DDebugRenderer();
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(true); // flip y-axis
		//cameraGUI.update();
		
	//	pixmap = new Pixmap( 16, 16, Format.RGBA8888 );
	///	pixmap.setColor( 1, 1,1, 1f);
	//	pixmap.fillCircle( 8, 8, 8);
	//	pixmaptex = new Texture(pixmap);
	//	pixmap.dispose();
		
		
	//	LIFEBAR= new	Bar(AssetsMy.instance.fish.power_bar,4,1);
		//	MYTe.setpersents(0);
		//	MYTe.incris(30);
		//	MYTe.incris(30);
		//	MYTe.incris(30);
		//	MYTe.incris(30);
		fontGameOver1 = AssetsMy.instance.fonts.MyFont;
		fontGameOver1.setColor(1, 1, 1, 1);
		
		fontGameOver1.setScale(0.02f);
		
		createFonts();
	//	AlieInJumpFish.hideads();
		}
	
	/*@SuppressWarnings("deprecation")
	private void createFonts() {
	    FileHandle fontFile = Gdx.files.internal("images/pixelart.ttf");
	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
	    
	 //   titleFont =   generator.generateFont(60);
	    FreeTypeFontParameter Parameter = new FreeTypeFontParameter();
	    titleFont =   generator.generateFont(Parameter);
	    titleFont.setScale(0.12f);
	    generator.dispose();
	}
*/
	
	private void createFonts() {
		
		String FONT_CHARS = "";
		for( int i = 32; i < 127; i++ ) FONT_CHARS += (char)i; // С†РёС„СЂС‹ Рё РІРµСЃСЊ Р°РЅРіР»РёР№СЃРєРёР№
		for( int i = 1024; i < 1104; i++ ) FONT_CHARS += (char)i;// СЂСѓСЃ
		String FONT_CHARS213 = "1234567890";
		FreeTypeFontParameter ftfp1 = new FreeTypeFontParameter();
		// ftfp.characters = FONT_CHARS;
		ftfp1.characters =RUSSIAN_CHARACTERS;
		ftfp1.size =50;
		
		//FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("images/acitynova.ttf"));
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("data/hooge_05_55_cyr.ttf"));
		font2 = generator2.generateFont( ftfp1 );
		font2.setFixedWidthGlyphs(FONT_CHARS213);
		//.generateFont(42,FONT_CHARS,false);
		generator2.dispose();
		
	 ///   FileHandle fontFile = Gdx.files.internal("images/hooge_05_55_cyr.ttf");
	  //  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
	    
	   	
		
	 //   FreeTypeFontParameter Parameter = new FreeTypeFontParameter();
	  //  titleFont =   generator.generateFont(Parameter);
	 //   titleFont.setScale(0.7f);
	  //  generator.dispose();
	}
	public void render () {
	
		worldController.cameraHelper.applyTo(cam);
		batch.setProjectionMatrix(cam.combined);
		
		renderer123.setView(cam);
	//	renderFON(batch);
		
		renderer123.render(worldController.level.wall);
		renderer123.render(worldController.level.mebel);
		renderer123.render(worldController.level.shadow);
		renderWorld(batch);
	
	
		
		
		if(Gdx.app.getInput().isTouched()==true){
	//		 rendertest(batch);// вспомогательные точки
			}
		
	//renderer.render(worldController.level.getWorld(), cam.combined); // вспомогательные линии
	//	renderGui(batch); // УБРАЛ!
		//worldController.globalcamx=cam.position.x-cam.viewportWidth/2+Gdx.app.getInput().getX()/xtest-worldController.level.getPlayer().body.getPosition().x; // убрал за ненадобностью
		//worldController.globalcamy=-(cam.position.y-cam.viewportWidth/2+Gdx.app.getInput().getY()/xtest-worldController.level.getPlayer().body.getPosition().y);// убрал за ненадобностью
		XXX = -cam.viewportWidth*cam.zoom/2;// для изменения положения камеры
		YYY=-cam.viewportHeight*cam.zoom/2;// для изменения положения камеры
	//	 x1=worldController.level.getPlayer().body.getPosition().x;
	//	 y1=worldController.level.getPlayer().body.getPosition().y;
		 y2=cam.position.y+(cam.viewportHeight)/2*cam.zoom-Gdx.app.getInput().getY()/ytest*cam.zoom;
		 x2=cam.position.x-(cam.viewportWidth)/2*cam.zoom+Gdx.app.getInput().getX()/xtest*cam.zoom;
	//	System.out.println(x2);
		 tg = Math.abs(y2-y1)/Math.abs(x2-x1);
		 cos = (float) Math.cos(Math.atan(tg));
		 sin = (float) Math.sin(Math.atan(tg));
		}

	private void renderWorld (SpriteBatch batch) {
		
		GamePreferences prefs = GamePreferences.instance;
	//	worldController.cameraHelper.applyTo(cam);
		batch.begin();
		worldController.level.render(batch);	
	//	titleFont.setColor(Color.WHITE);
	//	titleFont.draw(batch, "ALIEN     JUMP FISH", 1.5f, 1	3.5f); 
	//	titleFont.draw(batch, "IN", 8.5f, 11.5f); 
	//	titleFont.setColor(Color.YELLOW);
		//titleFont.setScale(0.12f);
	//	titleFont.setColor(Color.WHITE);
		font2.setScale(0.05f,0.04f);
		font2.setColor(Color.WHITE);
		if(GamePreferences.instance.lang!=11){
		font2.draw(batch, "Level  Select",18.0f, 14.5f);}
		else{		font2.draw(batch, "Выбор Уровня",17.2f, 14.5f);}
	//	titleFont.setScale(0.09f);
		font2.setScale(0.029f,0.029f);
		
		for (Knopka pl : worldController.level.kopki)
		{
			if (pl.intnomer<=prefs.levelpr)
			{	font2.setColor(Color.YELLOW);
			
			font2.draw(batch, pl.getname(), pl.getBody().getPosition().x+0.19f, pl.getBody().getPosition().y+1.4f);}
			else{	font2.setColor(Color.WHITE);
			font2.draw(batch, pl.getname(), pl.getBody().getPosition().x+0.19f, pl.getBody().getPosition().y+1.4f);
			}
				
		
		}	
		
			
		batch.end();
		}
	public static float getx(){
		return x2;
		
	}
	public static float gety(){
		return y2;
		
	}
private void renderFON (SpriteBatch batch) {
		//batch.setProjectionMatrix(cam.combined);
	//	worldController.cameraHelper.applyTo(cam);
		batch.begin();
	//	worldController.level.renderfon(batch);
		batch.end();
		}
	private void renderGui (SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		
		worldController.Mygui.render(batch,(int)worldController.level.getPlayer().lives,worldController.getlevel().ECcollection.size,(int)cameraGUI.viewportWidth);
	
		renderGuiGameOverMessage(batch);
		
		batch.end();
	}
	private void renderGuiGameOverMessage (SpriteBatch batch) {
		
		float x = cameraGUI.viewportWidth / 2;
		float y = cameraGUI.viewportHeight / 2;
		if (worldController.goalReached==true) {
			BitmapFont fontGameOver = AssetsMy.instance.fonts.defaultBig;
			fontGameOver.setColor(1, 0.75f, 0.25f, 1);
			fontGameOver.drawMultiLine(batch, "YOU WIN!", x, y, 1, BitmapFont.HAlignment.CENTER); //ПОтом воообще уберу
			fontGameOver.setColor(1, 1, 1, 1);
		}
		if (worldController.isGameOver()==true) {
			BitmapFont fontGameOver = AssetsMy.instance.fonts.defaultBig;
			fontGameOver.setColor(1, 0.75f, 0.25f, 1);
			fontGameOver.drawMultiLine(batch, "GAME OVER", x, y, 1, BitmapFont.HAlignment.CENTER);
			fontGameOver.setColor(1, 1, 1, 1);
		}
	}
	
	
	private void rendertest (SpriteBatch batch) {
		batch.begin();
		if (x2>x1&&y2>y1){
		 x3=(float) Math.sqrt(2/(1f+tg*tg));
		y3=tg*x3;}
		else if(x2<x1&&y2>y1)
		{
		 x3=-(float) Math.sqrt(2/(1f+tg*tg));
		y3=tg*-x3;}
		else if(x2<x1&&y2<y1)
		{
		 x3=-(float) Math.sqrt(2/(1f+tg*tg));
		y3=tg*x3;}
		else if(x2>x1&&y2<y1)
		{
		 x3=(float) Math.sqrt(2/(1f+tg*tg));
		y3=-tg*x3;}
		batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		///batch.draw(pixmaptex,x1+x3,y1+y3,0.1f,0.1f);
 if (worldController.level.getPlayer().PreperingTime<20){
	}
 else if (worldController.level.getPlayer().PreperingTime>=20&&worldController.level.getPlayer().PreperingTime<30){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 }
 else if (worldController.level.getPlayer().PreperingTime>=30&&worldController.level.getPlayer().PreperingTime<40){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
 }
 else if (worldController.level.getPlayer().PreperingTime>=40&&worldController.level.getPlayer().PreperingTime<50){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
 }
 else if (worldController.level.getPlayer().PreperingTime>=50&&worldController.level.getPlayer().PreperingTime<60){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
 }	
 else if (worldController.level.getPlayer().PreperingTime>=60&&worldController.level.getPlayer().PreperingTime<70){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
 }	
 else if (worldController.level.getPlayer().PreperingTime>=70&&worldController.level.getPlayer().PreperingTime<80){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power7,x1+x3*2f-0.2f,y1+y3*2f-0.2f,0.4f,0.4f);
 }
 else if (worldController.level.getPlayer().PreperingTime>=80&&worldController.level.getPlayer().PreperingTime<=90){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power7,x1+x3*2f-0.2f,y1+y3*2f-0.2f,0.4f,0.4f);
	 batch.draw(AssetsMy.instance.fish.power8,x1+x3*2.25f-0.2f,y1+y3*2.25f-0.2f,0.4f,0.4f);
 }
 else if (worldController.level.getPlayer().PreperingTime>=90&&worldController.level.getPlayer().PreperingTime<=100){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power7,x1+x3*2f-0.2f,y1+y3*2f-0.2f,0.4f,0.4f);
	 batch.draw(AssetsMy.instance.fish.power8,x1+x3*2.25f-0.2f,y1+y3*2.25f-0.2f,0.4f,0.4f);
	 batch.draw(AssetsMy.instance.fish.power9,x1+x3*2.6f-0.2f,y1+y3*2.6f-0.2f,0.4f,0.4f);
 }
 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
  batch.end();	
		//batch.draw(pixmaptex,cam.position.x+Gdx.app.getInput().getX()/xtest,cam.position.y-Gdx.app.getInput().getY()/ytest,0.1f,0.1f);
	//	batch.draw(pixmaptex,x1+(x2-x1)/2,y1+(y2-y1)/2,0.1f,0.1f);
	// batch.draw(pixmaptex,x2,y2,0.1f,0.1f); //точка нажатия
	//	batch.draw(pixmaptex,x1,y1,0.1f,0.1f); // середина рыбы
	}
	public float viewportWidth(){
		return cam.viewportHeight;
	}
	public void resize (int width, int height) {
		
	//	cameraGUI.viewportHeight = height;
	//	cameraGUI.viewportWidth=width;
		cameraGUI.viewportHeight=Constants.VIEWPORT_GUI_HEIGHT;
		
		cameraGUI.viewportWidth = ((Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width);
		
		//cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
		cameraGUI.update();
		System.out.println(cameraGUI.viewportWidth);
		
		worldController.cameraHelper.applyTo(cam);
		cam.viewportHeight=Constants.Menu_VIEWPORT_WIDTH;
		cam.viewportWidth = ((Constants.Menu_VIEWPORT_WIDTH / (float)height) * (float)width);
		cam.update();
	xtest = (float)width /  cam.viewportWidth;
	ytest = (float)height / cam.viewportHeight; 
	}
	@Override
	public void dispose () {
	batch.dispose();
	font2.dispose();

	renderer123.dispose();
	fontGameOver1.dispose();



	renderer.dispose();
	}
}
