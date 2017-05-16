package com.mygdx.jumpfish.game;

import com.badlogic.gdx.math.MathUtils;

import sun.java2d.loops.DrawPolygons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.jumpfish.gameobjects.MyFish.VIEW_DIRECTION;
import com.mygdx.jumpfish.gameobjects.gui.Bar;
import com.mygdx.jumpfish.gameobjects.gui.GUI;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.CameraHelper;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.GamePreferences;







public class WorldRendererMy implements Disposable {
	static public int  mnozhitel_X=1;
	static public int  mnozhitel_Y=1;
//	public static Bar LIFEBAR;// подумать зачем статик 
	public static float  halfHeight;
	public static float  halfWidth;
	static float poscam_X;
	static float poscam_Y;
public float xtest;
public float ytest;
public static float x1;
public static float y1;
public static float x2;
public static float y2;
//static float x3;
//static float y3;
//static float x4;
//static float y4;
public static float tg;
public static  float cos;

public static float  viewtHeight1;
public static float viewWight1;
public static float gipotenuza;

//public static float  viewportHeight1;
//public static float viewportWight1;
public static float XXX;
public static float YYY;
public static float sin;
public static float UnitScale;
	private static final String TAG = WorldRendererMy.class.getName();
	private OrthogonalTiledMapRenderer renderer123;
	private Box2DDebugRenderer renderer;
	private SpriteBatch batch;
	static public OrthographicCamera cam;
	private OrthographicCamera cameraGUI;

	private WorldControllerMy worldController;
	public Pixmap pixmap;
	public Texture pixmaptex;
	public Texture pixmaptex1;
	public WorldRendererMy (WorldControllerMy worldController) {
		this.worldController = worldController;
		init();
	}
	private void init () {
		
		UnitScale =worldController.getlevel().parser.getUnitScale();
		
		renderer123 = new OrthogonalTiledMapRenderer(worldController.getlevel().map,UnitScale);
		renderer123.getBatch().enableBlending(); 
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		//cam.position.set(20, 10, 0);
		// cam.zoom = worldController.level.parser.getUnitScale()/4f;
		cam.update();
		renderer = new Box2DDebugRenderer();
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(true); // flip y-axis
		
		//cameraGUI.update();
		
		pixmap = new Pixmap( 16, 16, Format.RGBA8888 );
		pixmap.setColor( 1, 1,1, 1f);
		pixmap.fillCircle( 8, 8, 8);
		pixmaptex = new Texture(pixmap);
		pixmap.dispose();
		
		
	//	LIFEBAR= new	Bar(AssetsMy.instance.fish.power_bar,4,1);
		//	MYTe.setpersents(0);
		//	MYTe.incris(30);
		//	MYTe.incris(30);
		//	MYTe.incris(30);
		//	MYTe.incris(30);
		
		//worldController.cameraHelper.setTarget(worldController.getlevel().getLizard());
		}

	public void render () {
		 Gdx.gl20.glTexParameteri (GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_NEAREST);
		 Gdx.gl20.glTexParameteri( GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_EDGE );
		 Gdx.gl20.glTexParameteri( GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE );		  /// какойто параметр убирает полоски !?
		worldController.cameraHelper.applyTo(cam);
		
		batch.setProjectionMatrix(cam.combined);
		
		renderer123.setView(cam);
	//	renderFON(batch); // пока не починю фон!!!!!!
		
		renderer123.render(worldController.getlevel().wall);
		
	
		renderer123.render(worldController.getlevel().mebel);
		
		renderer123.render(worldController.getlevel().back);
		
		renderWorld(batch);

		renderer123.render(worldController.getlevel().shadow);
		
		
		if(worldController.getlevel().okoshko!=null){batch.begin();
				worldController.getlevel().okoshko.render(batch,worldController.getlevel().pokazattext,worldController.getlevel().pokazat); // УБРАЛ ПОКА ЧТО
		
		batch.end();}
		
		if(Gdx.app.getInput().isTouched()==true){
			 rendertest(batch);// вспомогательные точки
			}
		
	renderGui(batch);
		
		
//	 renderer.render(worldController.getlevel().getWorld(), cam.combined); // вспомогательные линии!!!!!!!!
		
		
	
	
	
		//worldController.globalcamx=cam.position.x-cam.viewportWidth/2+Gdx.app.getInput().getX()/xtest-worldController.level.getPlayer().body.getPosition().x; // убрал за ненадобностью
		//worldController.globalcamy=-(cam.position.y-cam.viewportWidth/2+Gdx.app.getInput().getY()/xtest-worldController.level.getPlayer().body.getPosition().y);// убрал за ненадобностью
		XXX = -cam.viewportWidth*cam.zoom/2;// для изменения положения камеры
		YYY=-cam.viewportHeight*cam.zoom/2;// для изменения положения камеры
	
		if(worldController.cameraHelper.hasTarget()){
		 x1=worldController.cameraHelper.getTarget().body.getPosition().x;
		 y1=worldController.cameraHelper.getTarget().body.getPosition().y;}
		else {
			x1=worldController.getlevel().getPlayer().body.getPosition().x; // убрать от сюда 
			y1=worldController.getlevel().getPlayer().body.getPosition().y;}// убрать от сюда
		 
		 y2=cam.position.y+(cam.viewportHeight)/2*cam.zoom-Gdx.app.getInput().getY()/ytest*cam.zoom; // косяк но поздно исправлять
		 x2=cam.position.x-(cam.viewportWidth)/2*cam.zoom+Gdx.app.getInput().getX()/xtest*cam.zoom;
		 
		 
		 if(x2<x1)
		mnozhitel_X=-1;
		 else mnozhitel_X=1;
		
		 if(y2<y1)
				mnozhitel_Y=-1;
				 else mnozhitel_Y=1;
		 tg = Math.abs(y2-y1)/Math.abs(x2-x1);
	
		 cos = (float) Math.cos(Math.atan(tg));
		 sin = (float) Math.sin(Math.atan(tg));
	//	 System.out.println( tg);
		 poscam_X=cam.position.x-(cam.viewportWidth)/2;
			poscam_Y=cam.position.y-(cam.viewportHeight)/2;
			
		}

	private void renderWorld (SpriteBatch batch) {
	
	//	worldController.cameraHelper.applyTo(cam);
		batch.begin();
		
	
		
		
		worldController.getlevel().render(batch);
	
		
		batch.end();
		}
private void renderFON (SpriteBatch batch) {
		//batch.setProjectionMatrix(cam.combined);
	//	worldController.cameraHelper.applyTo(cam);
		batch.begin();
		worldController.getlevel().renderfon(batch);
		batch.end();
		}
	private void renderGui (SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		
		worldController.getlevel().Mygui.render(batch,(int)worldController.getlevel().getPlayer().lives,worldController.getlevel().ECcollection.size,(int)cameraGUI.viewportWidth,worldController.getlevel().jump
				,worldController.getlevel().getPlayer().numberhelthbonus,!worldController.getlevel().getPlayer().canmoov
				,worldController.getlevel().getPlayer().numberplatforms,worldController.getlevel().getPlayer().addplatforms
				,worldController.getlevel().getPlayer().numbervistrel,worldController.getlevel().getPlayer().vistrel
				,worldController.getlevel().getPlayer().numberdoublejump,worldController.getlevel().getPlayer().doublejump 
				,worldController.getlevel().getPlayer().perezaridka);
	
		renderGuiGameOverMessage(batch);
		
		batch.end();
	}
	private void renderGuiGameOverMessage (SpriteBatch batch) {
		float x = cameraGUI.viewportWidth / 2;
		float y = cameraGUI.viewportHeight / 2;
		if (worldController.getlevel().goalReached==true) {
			
			BitmapFont fontGameOver = AssetsMy.instance.fonts.titleFont;
			
			fontGameOver.setColor(Color.RED);
			fontGameOver.setScale(2,-2);
			if(GamePreferences.instance.lang!=11){
			fontGameOver.drawMultiLine(batch, "YOU WIN!", x, y, 1, BitmapFont.HAlignment.CENTER);}
			else{fontGameOver.drawMultiLine(batch, "ТЫ ПОБЕДИЛ!", x, y+2, 1, BitmapFont.HAlignment.CENTER);}
			fontGameOver.setColor(1, 1, 1, 1);
		}
		if (worldController.isGameOver()==true) {
			BitmapFont fontGameOver = AssetsMy.instance.fonts.titleFont;
		
			fontGameOver.setColor(Color.BLUE);
			fontGameOver.setScale(2,-2);
			if(GamePreferences.instance.lang!=11){
			fontGameOver.drawMultiLine(batch, "GAME OVER!", x, y, 1, BitmapFont.HAlignment.CENTER);}
			else{fontGameOver.drawMultiLine(batch, "ПОТРАЧЕНО!", x, y+2, 1, BitmapFont.HAlignment.CENTER);}
			fontGameOver.setColor(1, 1, 1, 1);
		}
	}
	
	static	public Vector2 getTrajectoryPoint(int n,WorldControllerMy worldController){
		int znak =getDirection(x1,x2);
		Vector2 myvec=WorldControllerMy.getTrajectoryPoint_controller(worldController.getlevel().getPlayer().getBody().getPosition(),
				new Vector2(znak*worldController.getlevel().getPlayer().PreperingTime * 0.085f* WorldRendererMy.cos,worldController.getlevel().getPlayer().PreperingTime * 0.085f* WorldRendererMy.sin), 
				n, worldController.getlevel());
		return myvec;
	}
	 static	public int getDirection(float x1,float x2){
		 int x123;
		 if (x1 <= x2){
		   x123 = 1;}
		 else x123=-1;
			return x123;
		}
	private void rendertest (SpriteBatch batch) {
		
		batch.begin();
	
		//batch.draw(AssetsMy.instance.fish.power9,worldController.goalReachedtEST();
		 if (worldController.getlevel().getPlayer().PreperingTime>20&&y2>y1){
				
				
		for  (int i = 7; i < worldController.getlevel().getPlayer().PreperingTime-45;i=i+4){
		
			
		//	System.out.println(i);
		//batch.draw(AssetsMy.instance.fish.power9,getTrajectoryPoint(i,worldController).x,
		//		getTrajectoryPoint(i,worldController).y,
		//				0.12f,0.12f);
			
			
		//	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
			TextureRegion reg = null;
			
			if(i==7||i==11)
			reg = AssetsMy.instance.fish.power2;
			else if(i==15||i==19)
				reg = AssetsMy.instance.fish.power3;
			else	if(i==23||i==27)
				reg = AssetsMy.instance.fish.power4;
			else	if(i==31||i==35)
				reg = AssetsMy.instance.fish.power5;
			else	if(i==39||i==43)
				reg = AssetsMy.instance.fish.power6;
			else	if(i==47||i==51)
				reg = AssetsMy.instance.fish.power7;
			else
				reg = AssetsMy.instance.fish.power8;
			
			batch.draw(reg,getTrajectoryPoint(i,worldController).x,
					getTrajectoryPoint(i,worldController).y,
							0.2f,0.2f);
			
	//		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		/*if(i==11||i==19||i==27||i==35||i==51||i==43){
		batch.draw(reg,getTrajectoryPoint(i,worldController).x,
				getTrajectoryPoint(i,worldController).y,
						0.11f,0.11f);}
		else{
			batch.draw(reg,getTrajectoryPoint(i,worldController).x,
					getTrajectoryPoint(i,worldController).y,
							0.08f,0.08f);}*/
			
		}
		batch.setColor(1,1, 1, 1);}
		 
		 
		/*if (x2>x1&&y2>y1){
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
		y3=-tg*x3;}*/
		
		
	//	batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		///batch.draw(pixmaptex,x1+x3,y1+y3,0.1f,0.1f);
/* if (worldController.getlevel().getPlayer().PreperingTime<20){
	}
 else if (worldController.getlevel().getPlayer().PreperingTime>=45&&worldController.getlevel().getPlayer().PreperingTime<55){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 }
 else if (worldController.getlevel().getPlayer().PreperingTime>=55&&worldController.getlevel().getPlayer().PreperingTime<65){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
 }
 else if (worldController.getlevel().getPlayer().PreperingTime>=65&&worldController.getlevel().getPlayer().PreperingTime<75){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
 }
 else if (worldController.getlevel().getPlayer().PreperingTime>=75&&worldController.getlevel().getPlayer().PreperingTime<85){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
 }	
 else if (worldController.getlevel().getPlayer().PreperingTime>=85&&worldController.getlevel().getPlayer().PreperingTime<90){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
 }	
 else if (worldController.getlevel().getPlayer().PreperingTime>=90&&worldController.getlevel().getPlayer().PreperingTime<95){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power7,x1+x3*2f-0.2f,y1+y3*2f-0.2f,0.4f,0.4f);
 }
 else if (worldController.getlevel().getPlayer().PreperingTime>=95&&worldController.getlevel().getPlayer().PreperingTime<=98){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power7,x1+x3*2f-0.2f,y1+y3*2f-0.2f,0.4f,0.4f);
	 batch.draw(AssetsMy.instance.fish.power8,x1+x3*2.25f-0.2f,y1+y3*2.25f-0.2f,0.4f,0.4f);
 }
 else if (worldController.getlevel().getPlayer().PreperingTime>=98&&worldController.getlevel().getPlayer().PreperingTime<=100){
	 batch.draw( AssetsMy.instance.fish.power2,x1+x3*0.75f-0.1f,y1+y3*0.75f-0.15f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power3,x1+x3*1f-0.1f,y1+y3*1f-0.1f,0.2f,0.2f);
	 batch.draw(AssetsMy.instance.fish.power4,x1+x3*1.25f-0.15f,y1+y3*1.25f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power5,x1+x3*1.5f-0.15f,y1+y3*1.5f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power6,x1+x3*1.75f-0.15f,y1+y3*1.75f-0.15f,0.3f,0.3f);
	 batch.draw(AssetsMy.instance.fish.power7,x1+x3*2f-0.2f,y1+y3*2f-0.2f,0.4f,0.4f);
	 batch.draw(AssetsMy.instance.fish.power8,x1+x3*2.25f-0.2f,y1+y3*2.25f-0.2f,0.4f,0.4f);
	 batch.draw(AssetsMy.instance.fish.power9,x1+x3*2.6f-0.2f,y1+y3*2.6f-0.2f,0.4f,0.4f);
 }
 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);*/
		
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
		cam.viewportHeight=Constants.VIEWPORT_HEIGHT;
		
		cam.viewportWidth = ((Constants.VIEWPORT_HEIGHT / (float)height) * (float)width);
		viewtHeight1=cam.viewportHeight;
		viewWight1=cam.viewportWidth;
		gipotenuza=(float)Math.sqrt(viewtHeight1*viewtHeight1+viewWight1*viewWight1);
		cam.update();

	xtest = (float)width /  cam.viewportWidth;
	ytest = (float)height / cam.viewportHeight; 
	
	halfHeight = cam.viewportHeight / 2.0f;
	  halfWidth = cam.viewportWidth / 2.0f;
	
	}
	@Override
	public void dispose () {
		worldController.getlevel().Mygui.disposetitleFont();	
	batch.dispose();
	
	renderer123.dispose();
	
	
	renderer.dispose();
	}
}
