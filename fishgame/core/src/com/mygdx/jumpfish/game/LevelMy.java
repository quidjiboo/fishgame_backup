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


package com.mygdx.jumpfish.game;


import java.util.HashSet;

import sun.security.jca.GetInstance.Instance;
import net.dermetfan.utils.libgdx.box2d.Box2DMapObjectParser;
import net.dermetfan.utils.libgdx.box2d.Chain;
import net.dermetfan.utils.libgdx.box2d.MouseJointAdapter;
import net.dermetfan.utils.libgdx.box2d.Chain.DefShapeBuilder;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;
import net.dermetfan.utils.libgdx.maps.MapUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.mygdx.jumpfish.game.logika.MyContactListener;
import com.mygdx.jumpfish.game.logika.MyQueryCallback;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.BOMBA;
import com.mygdx.jumpfish.gameobjects.BOMBSPool;
import com.mygdx.jumpfish.gameobjects.Ball;
import com.mygdx.jumpfish.gameobjects.Book;
import com.mygdx.jumpfish.gameobjects.Coub_long;
import com.mygdx.jumpfish.gameobjects.Coub_small;
import com.mygdx.jumpfish.gameobjects.EnergyCell;
import com.mygdx.jumpfish.gameobjects.FlyBaloon;
import com.mygdx.jumpfish.gameobjects.Fon;
import com.mygdx.jumpfish.gameobjects.Bonus;
import com.mygdx.jumpfish.gameobjects.MyFish;
import com.mygdx.jumpfish.gameobjects.MyLizard;
import com.mygdx.jumpfish.gameobjects.Platform;
import com.mygdx.jumpfish.gameobjects.Platformgorizont;
import com.mygdx.jumpfish.gameobjects.Pool_ball;
import com.mygdx.jumpfish.gameobjects.Portal;
import com.mygdx.jumpfish.gameobjects.Pushka;
import com.mygdx.jumpfish.gameobjects.SmallCar;
import com.mygdx.jumpfish.gameobjects.Test_obj;
import com.mygdx.jumpfish.gameobjects.Vrag;
import com.mygdx.jumpfish.gameobjects.VragTrinity;
import com.mygdx.jumpfish.gameobjects.YazikRope;
import com.mygdx.jumpfish.gameobjects.gui.GUI;
import com.mygdx.jumpfish.gameobjects.gui.Nabor_Knopok;
import com.mygdx.jumpfish.gameobjects.gui.Oknovibora_MyFish;
import com.mygdx.jumpfish.gameobjects.gui.Okoshko;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.CameraHelper;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MyAudioManager;
import com.sun.glass.ui.Window.Level;





public class LevelMy {
	
	private String currentlevel;
	
	public MyQueryCallback Callback;
	static public HashSet<Fixture> Sensorfixtures;

	public YazikRope LizardYazik;
	

	
	public Array<BOMBA> drops;
	private BOMBSPool pool;
	
	public  boolean canmoov=true;
	public  boolean pause=false;
	public  boolean goalReached = false;
	public  boolean notvisiblfish = false;
	public static  boolean globaljump=true;
	public static  boolean jump=true;
	public  boolean pokazat=false;
	public  boolean pokazattext=true;
	public GUI Mygui;
	private World MY_f_world; //1
	public Box2DMapObjectParser parser;
//	private Fon fon;
	//static int  smallBallsCount;
//	public Array<Ball> bools;
	public Array<Pushka> guns;
	public Array<Vrag> vrags;
	public Array<VragTrinity> Tvrags;
	public Array<Book> books;
	public Array<Pool_ball> pool_balls;
	public Array<Coub_long> coobs;
	public Array<Coub_small> coobs_small;
	public Array<EnergyCell> ECcollection;
	public Array<Bonus> HBcollection;
	public Array<SmallCar> SmallCars;
	public Array<FlyBaloon> baloons;
	public Array<Platform> platforms;
	
	public Array<Platformgorizont> platform_goriz;
	public Array<Body> bodies1;
	public BodyDef bodyDef;
	public static float generate=2;
	public static float timer;
	public static final String TAG = LevelMy.class.getName();
	private MyFish myFish; // ПАБЛИК????
	private MyLizard myLizard; 
	private Nabor_Knopok nabor;
//	private Oknovibora_MyFish myFish_Okno;
	
	private Test_obj Test_obj;
	public Array<Test_obj> Test_objcts;
	public Portal portal;
	public Okoshko okoshko;
	private Ball ball;
	static int KKK;
	public TiledMap map; // КАРТА был статик
	//public int[] back = new int[] {1}, invis = new int[] {0}, first = new int[] {2};
	public int[] mebel = new int[] {1}, wall = new int[] {0}, shadow = new int[] {2}, back = new int[] {3};

	public LevelMy (String namemap) {
		
	//	
		
		init(namemap);
	
	}
	public MyFish getPlayer(){
		return myFish;
	}
	public MyLizard getLizard(){
		return myLizard;
	}
	public World getWorld(){
		return MY_f_world;
	}
	private void init (String namemap) {
	//	smallBallsCount=0;
		currentlevel=namemap;	
	//	fon = new Fon();
		TmxMapLoader.Parameters loaderparams = new TmxMapLoader.Parameters();
		loaderparams.textureMagFilter = TextureFilter.Nearest; // ВАЖНО!
		loaderparams.textureMinFilter = TextureFilter.Nearest;// ВАЖНО!
	TmxMapLoader loader = new TmxMapLoader();
	
	//map = loader.load("maps/map4.tmx");

	map = loader.load("maps/"+namemap,loaderparams);
	
	
	
	initPhysics();
	
	globaljump=(MapUtils.getProperty(map.getProperties(),"jumpornot",true));
		jump=globaljump;
		
		
		
		
		
		drops = new Array<BOMBA>();
		pool = new BOMBSPool(MY_f_world);
			
	//	newbomba(1,1); // Пока не надо
	}
	
	
	
	public void newbomba(float x,float y){
	BOMBA drop = pool.obtain();
	drop.getBody().setActive(true);
	drops.add(drop);
	
	}

	
public void addplatform(float x,float y){
	if(myFish.numberplatforms!=0)
	for  (int i3 = 0; i3 < Test_objcts.size;i3++){
		if (!Test_objcts.get(i3).getBody().isActive()){
			Test_objcts.get(i3).getBody().setTransform(x,y, 0);
			Test_objcts.get(i3).getBody().setActive(true);
			myFish.numberplatforms=myFish.numberplatforms-1;
			MyAudioManager.instance.play(AssetsMy.instance.sounds.portalon);
			break;
			
		}
		
	}
	/*if(Test_obj==null){
	Body body2 = MY_f_world.createBody(bodyDef);
	body2.setTransform(x,y, 0);
	Test_obj = new Test_obj(x,y,body2);
	Test_objcts.add(Test_obj);
	}
	else{Test_obj.getBody().setTransform(x, y, 0);
	Test_obj.getBody().setActive(true);}
	*/
}
	private void initPhysics() {
				timer=0;
		String f =  "unitScale";
//		System.out.println("test "+ MapUtils.getProperty(map.getProperties(),f,1f));
		
		if (MY_f_world != null)
			MY_f_world.dispose();
		MY_f_world = new World(new Vector2(0, -9.81f), true);
		MY_f_world.setContactListener(new MyContactListener(MY_f_world,this));
		
		parser = new Box2DMapObjectParser();
		parser.load(MY_f_world,map);
		
		
		bodyDef = new BodyDef();
		
		bodyDef.position.set(MapUtils.getProperty(map.getProperties(),"startPlayX",1f),MapUtils.getProperty(map.getProperties(),"startPlayY",1f));
		Body body = MY_f_world.createBody(bodyDef);
		body.setType(BodyType.DynamicBody);
		body.setUserData("play");
		myFish = new MyFish(body);
		
		
	
	
		
		
		
	//	Body body2 = MY_f_world.createBody(bodyDef);
	//	myFish_Okno = new Oknovibora_MyFish(myFish.getBody().getPosition().x,myFish.getBody().getPosition().y,body2);
		nabor = new Nabor_Knopok(myFish.getBody().getPosition().x,myFish.getBody().getPosition().y,MY_f_world);
	//	myFish = Fish; // созданное тело присволи уровню
		myFish.getBody().getFixtureList().get(0).setUserData("play");
		
		bodies1 = new Array<Body>();
		
		books = new Array<Book>();
		coobs = new Array<Coub_long>();
		coobs_small = new Array<Coub_small>();
		pool_balls = new Array<Pool_ball>();
		HBcollection = new Array<Bonus>();
		ECcollection = new Array<EnergyCell>();
		SmallCars= new Array<SmallCar>();
		baloons= new Array<FlyBaloon >();
		platforms= new Array<Platform >();
		platform_goriz = new Array<Platformgorizont>();
		guns= new Array<Pushka>();
		vrags= new Array<Vrag>();
		Tvrags= new Array<VragTrinity>();
		Test_objcts = new Array<Test_obj>();
	
	
		for  (int i1 = 0; i1 < 4;i1++){
			
			Body body2 = MY_f_world.createBody(bodyDef);
			Test_obj = new Test_obj(-10,-10,body2);
			Test_objcts.add(Test_obj);
		}
		MY_f_world.getBodies(bodies1);
	//System.out.println(MY_f_world.getBodyCount());
		for (int i = 0; i < MY_f_world.getBodyCount();i++){
			
			
			Body testbody=bodies1.get(i);
			
			/*if (testbody.getUserData().toString().contains("environment")){
				System.out.println("!@#DFSFDSFSDF@!#BVCBXB   "+  testbody.getFixtureList().size);
				
				for (int i1 = 0; i1 < testbody.getFixtureList().size;i1++){
					Fixture testfix=testbody.getFixtureList().get(i1);
					System.out.println("!tttttttttttt  " +testfix.getUserData().toString());
					if(testfix.getUserData().toString().contains("YES_ON")){
						System.out.println("!tttttttttttt  " +testfix.getUserData().toString());
							testfix.setSensor(true);}
				}
				
			}*/
			
			if (testbody.getUserData().equals("portal_body")){
				//	System.out.println("UUUUUUU");
				portal = new Portal(bodies1.get(i));
				
				}
				if (testbody.getUserData().toString().contains("book_body")){
				//	System.out.println("UUUUUUU");
					Book book = new Book(bodies1.get(i));
					books.add(book);
				}
				if (testbody.getUserData().toString().contains("pool_ball")){
					//	System.out.println("UUUUUUU");
						Pool_ball coub = new Pool_ball(bodies1.get(i));
						pool_balls.add(coub);
					}
				if (testbody.getUserData().toString().contains("coub_long")){
					//	System.out.println("UUUUUUU");
						Coub_long coub = new Coub_long(bodies1.get(i));
						coobs.add(coub);
					}
				if (testbody.getUserData().toString().contains("coub_small")){
					//	System.out.println("UUUUUUU");
						Coub_small coub = new Coub_small(bodies1.get(i));
						coobs_small.add(coub);
					}
				
				if (testbody.getUserData().toString().contains("pushka")){
					//	System.out.println("UUUUUUU");
						Pushka gunPushka = new Pushka(bodies1.get(i));
						guns.add(gunPushka);
					}
				if (testbody.getUserData().toString().contains("vrag")){
					//	System.out.println("UUUUUUU");
						Vrag vrag = new Vrag(bodies1.get(i));
						vrags.add(vrag);
					}
				if (testbody.getUserData().toString().contains("trinity")){
					//	System.out.println("UUUUUUU");
						VragTrinity Tvrag = new VragTrinity(bodies1.get(i),MY_f_world);
						Tvrags.add(Tvrag);
					}
				if (testbody.getUserData().equals("energycell")){
				//	System.out.println("UUUUUUU");
					EnergyCell EC = new EnergyCell(bodies1.get(i));
					ECcollection.add(EC);
				}
				if (testbody.getUserData().toString().contains("asrc")){
					//	System.out.println("UUUUUUU");
					Bonus book = new Bonus(bodies1.get(i));
					HBcollection.add(book);
					}
				if (testbody.getUserData().equals("small_car")){
					//System.out.println("CAR");
					SmallCar car = new SmallCar(bodies1.get(i));
					SmallCars.add(car);
				}
				if (testbody.getUserData().toString().contains("baloon")){
				//	System.out.println("baloon");
					FlyBaloon baloon = new FlyBaloon(bodies1.get(i));
					baloons.add(baloon);
				}
				if (testbody.getUserData().toString().contains("platfr")){
					//System.out.println("baloon");
					Platform Platf = new Platform(bodies1.get(i));
					platforms.add(Platf);
				}
				if (testbody.getUserData().toString().contains("plagor")){
					//System.out.println("baloon");
					Platformgorizont Platf = new Platformgorizont(bodies1.get(i));
					platform_goriz.add(Platf);
				}
				
				}
		
		
	//	Body body1 = MY_f_world.createBody(bodyDef);
		
	//	okoshko = new Okoshko(body1,WorldRendererMy.poscam_X ,WorldRendererMy.poscam_Y,MapUtils.getProperty(map.getProperties(),"namelevel","test.png"));
		
	//	okoshko = new Okoshko(body1,myFish.getBody().getPosition().x ,myFish.getBody().getPosition().y,MapUtils.getProperty(map.getProperties(),"namelevel","test.png"));
		Mygui = new GUI(myFish,ECcollection.size);
		System.out.println("currentlevel="+currentlevel);
		if(currentlevel.contains("20.tmx")){
		myLizard = new MyLizard(MY_f_world);		
	Sensorfixtures = new HashSet<Fixture>()	;
		LizardYazik =  new YazikRope(MY_f_world,11);
		Callback = new MyQueryCallback();}
		
		
	}
	
	
	
	
	
	public void okoshko(){
		if (okoshko==null){
	Body body1 = MY_f_world.createBody(bodyDef);
		if(GamePreferences.instance.lang==1){
		okoshko = new Okoshko(body1,WorldRendererMy.poscam_X ,WorldRendererMy.poscam_Y,MapUtils.getProperty(map.getProperties(),"namelevel","test.png"),MapUtils.getProperty(map.getProperties(),"what_en","test_en.txt"));}
		else{
			okoshko = new Okoshko(body1,WorldRendererMy.poscam_X ,WorldRendererMy.poscam_Y,MapUtils.getProperty(map.getProperties(),"namelevel","test.png"),MapUtils.getProperty(map.getProperties(),"what_ru","test_ru.txt"));
		}		
	}}
	

	
	public void updateokno (float deltaTime) {
		//pause=false;
		if (timer<1)
			timer=timer+0.01f;
				
			
			if (timer>0.4&&timer<0.45){
				 okoshko();
					pause=true;
				}
			if(timer>=1){timer=2;
		//	pause=false;
					
				}
	}
	public boolean touch(float minX, float minY, float maxX, float maxY) { 
		Sensorfixtures.clear();
		
	                MY_f_world.QueryAABB(Callback, minX, minY, maxX, maxY);
	       
	    return false;
	        
	    }
	public void update (float deltaTime) {
		
//touch(Sensor.getPosition().x,Sensor.getPosition().y,Sensor.getPosition().x+0.25f,Sensor.getPosition().y+0.25f);

//System.out.println(Sensorfixtures.size());
	//	segments[0].setTransform(myLizard.getBody().getPosition().x, myLizard.getBody().getPosition().y, 0);
	//	if (timer<1)
	//	timer=timer+0.01f;
			
		
	//	if (timer>0.4)
	//		 okoshko();	
		
		
		
	//	bodies1.clear();
	//	MY_f_world.getBodies(bodies1);
		
	//myFish.resetgrav(deltaTime); - убрать гравитация
		MY_f_world.step(1/60f, 2, 2);
	//	fon.update(deltaTime);
		myFish.update(deltaTime,MY_f_world,jump,notvisiblfish);
		if(myLizard!=null)
		
		myLizard.update(deltaTime);
		
		for (Pushka gPushka : guns)
		{gPushka.update(deltaTime,MY_f_world);}

		for (Vrag vrag : vrags)
		{vrag.update(deltaTime,MY_f_world,myFish.getPosition().x,myFish.getPosition().y);
		if(vrag.getBody().getUserData().equals("BAM")){
		vrag.body.setActive(false);
		MY_f_world.destroyBody(vrag.body);vrags.removeValue(vrag, true);
		System.out.println("УМЕР !!!");
		}
		}
		for (VragTrinity Tvrag : Tvrags)
		{Tvrag.update(deltaTime,MY_f_world,myFish.getPosition().x,myFish.getPosition().y);
		
		if(Tvrag.getBody().getUserData().equals("BAM")){
		Tvrag.body.setActive(false);
		if(Tvrag.ball1!=null){
		Tvrag.ball1.getBody().setActive(false);MY_f_world.destroyBody(Tvrag.ball1.getBody()); }//пули пропадают вместе с врагом можно переделать вынести в массив 
		if(Tvrag.ball2!=null){
		Tvrag.ball2.getBody().setActive(false);MY_f_world.destroyBody(Tvrag.ball2.getBody());}//пули пропадают вместе с врагом можно переделать вынести в массив
		if(Tvrag.ball3!=null){
		Tvrag.ball3.getBody().setActive(false);MY_f_world.destroyBody(Tvrag.ball3.getBody());}//пули пропадают вместе с врагом можно переделать вынести в массив 
		MY_f_world.destroyBody(Tvrag.body);
		Tvrags.removeValue(Tvrag, true);
		System.out.println("УМЕР !!!");
		}
		
		}
		for (Book book : books)
			{book.update(deltaTime);}
		for (Pool_ball pool : pool_balls)
		{pool.update(deltaTime);}
		for (Coub_long book : coobs)
		{book.update(deltaTime);}
		for (Coub_small book : coobs_small)
		{book.update(deltaTime);}
		for (EnergyCell ec : ECcollection)
		{
			ec.update(deltaTime);
		if(ec.getBody().getUserData().equals("BAM")){ec.body.setActive(false);	MY_f_world.destroyBody(ec.body);ECcollection.removeValue(ec, true);}
			}
		for (Bonus ec : HBcollection)
		{
			ec.update(deltaTime);
		if(ec.getBody().getUserData().equals("BAM")){ec.body.setActive(false);	MY_f_world.destroyBody(ec.body);HBcollection.removeValue(ec, true);}
			}
		for (SmallCar car : SmallCars)
			{car.update(deltaTime,tetsrast(car.getBody(),myFish.getBody()));}
		for (FlyBaloon baloon : baloons)
		{baloon.update(deltaTime,tetsrast(baloon.getBody(),myFish.getBody()));			
		}
		for (Platform pl : platforms)
		{pl.update(deltaTime);}
		for (Platformgorizont pl : platform_goriz)
		{pl.update(deltaTime);}
		portal.update(deltaTime,ECcollection.size);
		Mygui.update(deltaTime,myFish.inwater,(int)myFish.lives);
	
		nabor.update(deltaTime,myFish.getBody().getPosition().x,myFish.getBody().getPosition().y);
		
		for (Test_obj Test_obj : Test_objcts)
		{Test_obj.update(deltaTime);}
		
		for(BOMBA drop : drops)
			drop.update(deltaTime,drops,pool);
		if(LizardYazik!=null)
		LizardYazik.update(deltaTime);
		
	}
	
	
	public float tetsrast(Body body,Body bodyfish){
		float 	xxx=0;
		float 	yyy=0;
		float 	xy=0;
		if (Math.abs(body.getPosition().x-myFish.getPosition().x)<WorldRendererMy.viewWight1/2&&Math.abs(body.getPosition().y-myFish.getPosition().y)<WorldRendererMy.viewtHeight1)
		{	xxx=Math.abs((Math.abs(body.getPosition().x-myFish.getPosition().x)/(WorldRendererMy.viewWight1/2))-1);
		yyy=Math.abs((Math.abs(body.getPosition().y-myFish.getPosition().y)/WorldRendererMy.viewtHeight1)-1);
		xy=yyy*xxx;}
		else 
			{xxx=0;
		
	//	if (Math.abs(body.getPosition().y-myFish.getPosition().y)<WorldRendererMy.viewtHeight1-1)
	//	{	yyy=Math.abs((Math.abs(body.getPosition().y-myFish.getPosition().y)/WorldRendererMy.viewtHeight1)-1);}
	//	else 
			yyy=0;}
		
	//	xxx=(float)Math.sqrt(Math.abs(body.getPosition().x-myFish.getPosition().x)*Math.abs(body.getPosition().x-myFish.getPosition().x)
	//			+Math.abs(body.getPosition().y-myFish.getPosition().y)*Math.abs(body.getPosition().y-myFish.getPosition().y))/(WorldRendererMy.gipotenuza);
	//System.out.println(Math.abs(WorldRendererMy.poscam_X+WorldRendererMy.viewWight1/2-body.getPosition().x));
//	System.out.println(WorldRendererMy.poscam_X+(WorldRendererMy.viewWight1/2));
//	System.out.println("GS"+body.getPosition().x);
	//System.out.println(WorldRendererMy.viewWight1/2);
	//System.out.println(xxx);
//	System.out.println(xy);
		
	//	if (xxx>=1)xxx=0;
	

//	System.out.println(WorldRendererMy.viewWight1);
//	System.out.println(WorldRendererMy.viewtHeight1);
		return xy;
		
	}
	public void render (SpriteBatch batch) {
	//	Box2DSprite.draw(batch, MY_f_world);
		
	
		portal.render(batch);
		myFish.render(batch,notvisiblfish);
		if(myLizard!=null)
		myLizard.render(batch);
	for(BOMBA drop : drops)
			drop.render(batch);
		
		
		for (Pushka gPushka : guns)
		{gPushka.render(batch);}
		for (Vrag vrag : vrags)
		{vrag.render(batch);}
		for (VragTrinity Tvrag : Tvrags)
		{Tvrag.render(batch);}
		for (Book book : books)
			{book.render(batch);}
		for (Pool_ball pool : pool_balls)
		{pool.render(batch);}
		for (Coub_long coub : coobs)
			{coub.render(batch);}
		for (Coub_small coub : coobs_small)
		{coub.render(batch);}

		for (EnergyCell ec : ECcollection)
			{ec.render(batch);}
		for (Bonus ec : HBcollection)
		{ec.render(batch);}
		for (SmallCar car : SmallCars)
			{car.render(batch);}
		for (FlyBaloon baloon : baloons)
		{baloon.render(batch);}
		for (Platform pl : platforms)
		{pl.render(batch);}
		for (Platformgorizont pl : platform_goriz)
		{pl.render(batch);}
		for (Test_obj Test_obj : Test_objcts)
		{Test_obj.render(batch);}
		
	nabor.render(batch,myFish.menu,myFish.numbervistrel);
	if(LizardYazik!=null)
	LizardYazik.render(batch);
	}
	public void renderfon (SpriteBatch batch) {
	//	fon.render(batch, myFish.getBody());
	}
	
	public void baloonoff(){
		for (FlyBaloon baloon : baloons)
		{
			AssetsMy.instance.sounds.helicopter.stop(baloon.soundId);
		}	
	}
public void dispose() {
	if (okoshko!=null)
	okoshko.disposetitleFont();
	
	
//	for (FlyBaloon baloon : baloons)
//	{AssetsMy.instance.sounds.helicopter.stop(baloon.soundId);
//	}
	for (VragTrinity Tvrag : Tvrags)
	{Tvrag.plasma1.dispose();
	Tvrag.plasma2.dispose();
	Tvrag.plasma3.dispose();
	Tvrag.dustParticles.dispose();
	Tvrag.boom.dispose();
	}
		myFish.dustParticles.dispose();
	myFish.dustParticles1.dispose();
	myFish.dustParticles2.dispose();

		if (MY_f_world != null){
			MY_f_world.dispose();}
		map.dispose();
		
		System.out.println("удалил уровень");
	}
}
