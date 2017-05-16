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


package com.mygdx.jumpfish.startscreen;


import net.dermetfan.utils.libgdx.box2d.Box2DMapObjectParser;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;
import net.dermetfan.utils.libgdx.maps.MapUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.Ball;
import com.mygdx.jumpfish.gameobjects.Book;
import com.mygdx.jumpfish.gameobjects.EnergyCell;
import com.mygdx.jumpfish.gameobjects.FlyBaloon;
import com.mygdx.jumpfish.gameobjects.Fon;
import com.mygdx.jumpfish.gameobjects.Bonus;
import com.mygdx.jumpfish.gameobjects.MyFish;
import com.mygdx.jumpfish.gameobjects.Platform;
import com.mygdx.jumpfish.gameobjects.Pushka;
import com.mygdx.jumpfish.gameobjects.SmallCar;
import com.mygdx.jumpfish.gameobjects.gui.KnopkaNotActiv;
import com.mygdx.jumpfish.gameobjects.startscreen.Alien;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuPortal;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuSpaceShip;
import com.mygdx.jumpfish.gameobjects.startscreen.Spaceorb;





public class MenuLevelMy {
	
	public boolean renderfish=false; // всегда рендеритьт
	public World MY_f_world; //1
	public boolean goalReachedalien=false;
	private MenuMyFish myFish; // ѕјЅЋ» ????
	public Alien alien;
	public MenuSpaceShip alienspaceship;// ѕјЅЋ» ????
	public Spaceorb spaceorb;// ѕјЅЋ» ????
	public KnopkaNotActiv knopkacredits;
	public KnopkaNotActiv knopka;
	public KnopkaNotActiv knopka_addon;
	public KnopkaNotActiv knopkalang;
	public KnopkaNotActiv knopkamuz;
	public KnopkaNotActiv knopkasfx;
	public Box2DMapObjectParser parser;
	private Fon fon;
	//static int  smallBallsCount;
//	public Array<Ball> bools;
	public Array<Pushka> guns;
	public Array<Book> books;
	public Array<EnergyCell> ECcollection;
	public Array<Bonus> HBcollection;
	public Array<SmallCar> SmallCars;
	public Array<FlyBaloon> baloons;
	public Array<Platform> platforms;
	public Array<Body> bodies1;
	
	public static float generate=2;
	
	public static final String TAG = MenuLevelMy.class.getName();
	
	public MenuPortal portal;
	private Ball ball;
	static int KKK;
	public TiledMap map; //  ј–“ј был статик
	//public int[] back = new int[] {1}, invis = new int[] {0}, first = new int[] {2};
	public int[] mebel = new int[] {1}, wall = new int[] {0}, shadow = new int[] {2};

	public MenuLevelMy () {
		
	//	
		
		init();
	}
	public MenuMyFish getPlayer(){
		return myFish;
	}
	public EnergyCell getEC(){
		
		
		return ECcollection.get(0); /// ну вот так вот надо 
	}
	public World getWorld(){
		return MY_f_world;
	}
	private void init () {
	//	smallBallsCount=0;
		fon = new Fon();
	TmxMapLoader loader = new TmxMapLoader();
	//map = loader.load("maps/map4.tmx");

	map = loader.load("maps/startmap.tmx");
	
	
	
	initPhysics();
	}

	private void initPhysics() {
		String f =  "unitScale";
	//	System.out.println("test "+ MapUtils.getProperty(map.getProperties(),f,1f));
		
		if (MY_f_world != null)
			MY_f_world.dispose();
		MY_f_world = new World(new Vector2(0, -9.81f), true);
		parser = new Box2DMapObjectParser();
		parser.load(MY_f_world,map);

		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(MapUtils.getProperty(map.getProperties(),"startPlayX",1f)/2-2.3f,8.7f);
	//	System.out.println(MapUtils.getProperty(map.getProperties(),"startPlayX",1f)/2-2.3f + "!!!!!!!!!!!!!!!!!!!!!");
		Body body = MY_f_world.createBody(bodyDef);
		body.setType(BodyType.DynamicBody);
		body.setUserData("play");
		myFish = new MenuMyFish(body);
		
	//	myFish = Fish; // созданное тело присволи уровню
		myFish.getBody().getFixtureList().get(0).setUserData("play");
		
		bodies1 = new Array<Body>();
		MY_f_world.getBodies(bodies1);
		books = new Array<Book>();
		HBcollection = new Array<Bonus>();
		ECcollection = new Array<EnergyCell>();
		SmallCars= new Array<SmallCar>();
		baloons= new Array<FlyBaloon >();
		platforms= new Array<Platform >();
		guns= new Array<Pushka>();
		
	//System.out.println(MY_f_world.getBodyCount());
		for (int i = 0; i < MY_f_world.getBodyCount();i++){
			
			Body testbody=bodies1.get(i);
			if (testbody.getUserData().equals("portal_body")){
				//	System.out.println("UUUUUUU");
				portal = new MenuPortal(bodies1.get(i));
				
				}
				if (testbody.getUserData().equals("book_body")){
				//	System.out.println("UUUUUUU");
					Book book = new Book(bodies1.get(i));
					books.add(book);
				}
				if (testbody.getUserData().toString().contains("pushka")){
					//	System.out.println("UUUUUUU");
						Pushka gunPushka = new Pushka(bodies1.get(i));
						guns.add(gunPushka);
					}
				if (testbody.getUserData().equals("energycell")){
				//	System.out.println("UUUUUUU");
					
					EnergyCell EC = new EnergyCell(bodies1.get(i));
					ECcollection.add(EC);
					
					
				}
				if (testbody.getUserData().equals("asrc")){
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
				
				if (testbody.getUserData().toString().contains("alien")){
					//System.out.println("baloon");
					alien = new Alien(bodies1.get(i));}
				if (testbody.getUserData().toString().contains("tarelka")){
					
						alienspaceship = new MenuSpaceShip(bodies1.get(i));
						
						}
				if (testbody.getUserData().toString().contains("orb")){
					System.out.println("ORB!!!!!!!!!!!!!!!!!!");
					spaceorb = new Spaceorb(bodies1.get(i));
					
					}
				if (testbody.getUserData().toString().contains("start")){
					System.out.println("ONO");
					
					knopka = new KnopkaNotActiv(bodies1.get(i));
					
					}
				if (testbody.getUserData().toString().contains("addon")){
					System.out.println("ONO");
					
					knopka_addon = new KnopkaNotActiv(bodies1.get(i));
					
					}
				if (testbody.getUserData().toString().contains("credits")){
					System.out.println("ONO");
					
					knopkacredits = new KnopkaNotActiv(bodies1.get(i));
					
					}
			
				if (testbody.getUserData().toString().contains("lang")){
					System.out.println("credits");
					
					knopkalang = new KnopkaNotActiv(bodies1.get(i));
					
					}
				if (testbody.getUserData().toString().contains("muz")){
					System.out.println("credits");
					
					knopkamuz = new KnopkaNotActiv(bodies1.get(i));
					
					}
				if (testbody.getUserData().toString().contains("sfx")){
					System.out.println("credits");
					
					knopkasfx = new KnopkaNotActiv(bodies1.get(i));
					
					}
				}
		
	}
	public void update (float deltaTime) {
			
		
	//	bodies1.clear();
	//	MY_f_world.getBodies(bodies1);

		
		MY_f_world.step(1/60f, 2, 3);
		fon.update(deltaTime);
		myFish.update(deltaTime);
		for (Pushka gPushka : guns)
		{gPushka.update(deltaTime,MY_f_world);}
		for (Book book : books)
			{book.update(deltaTime);}
		for (EnergyCell ec : ECcollection)
		{
			ec.update(deltaTime);
		if(ec.getBody().getUserData().equals("BAM")){
			ec.body.setTransform(13,- 2, 0);
			goalReachedalien=true;
			//MY_f_world.destroyBody(ec.body);ECcollection.removeValue(ec, true);
			ec.body.setUserData("energycell");
			
						
		}
		if (alien.go==true){
			ec.body.setTransform(15.6f+12.5f, 11,0);
		}
			}
		for (Bonus hb : HBcollection)
		{
			hb.update(deltaTime);
		if(hb.getBody().getUserData().equals("BAM")){	MY_f_world.destroyBody(hb.body);HBcollection.removeValue(hb, true);}
			}
		for (SmallCar car : SmallCars)
			{car.update(deltaTime);}
		for (FlyBaloon baloon : baloons)
		{baloon.update(deltaTime);}
		for (Platform pl : platforms)
		{pl.update(deltaTime);}
		portal.update(deltaTime,goalReachedalien);
		alien.update(deltaTime,alienspaceship.contact);
		spaceorb.update(deltaTime,alienspaceship.polet);
		alienspaceship.update(deltaTime);
		
	
		
		
	}
	

	public void render (SpriteBatch batch) {
		
	//	Box2DSprite.draw(batch, MY_f_world);
		portal.render(batch);
		//!!!!!!!!!!!!!!!
	//	knopka.render(batch); 
	//	knopkakred.render(batch);
		
		
		//if(renderfish){
				myFish.render(batch);
				//}
		
		for (Pushka gPushka : guns)
		{gPushka.render(batch);}
		for (Book book : books)
			{book.render(batch);}
		for (EnergyCell ec : ECcollection)
		{ec.render(batch);}
		for (Bonus hb : HBcollection)
		{hb.render(batch);}
		for (SmallCar car : SmallCars)
			{car.render(batch);}
		for (FlyBaloon baloon : baloons)
		{baloon.render(batch);}
		for (Platform pl : platforms)
		{pl.render(batch);}
			
		alien.render(batch);
		alienspaceship.render(batch);
		spaceorb.render(batch);
	}
	public void renderfon (SpriteBatch batch) {
		fon.render(batch, myFish.getBody());
	}
public void dispose() {
	myFish.dustParticles.dispose();
	myFish.dustParticles1.dispose();
	myFish.dustParticles2.dispose();
	map.dispose();
		if (MY_f_world != null)
			MY_f_world.dispose();
	}
}
