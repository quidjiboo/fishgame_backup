package com.mygdx.jumpfish.game.logika;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.gameobjects.MyFish;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish;
import com.mygdx.jumpfish.startscreen.MenuWorldControllerMy;
import com.mygdx.jumpfish.util.Constants;



public class MenuObjects_logik {
	private static volatile MenuObjects_logik instance;
	private MenuObjects_logik(){
		 
		
		}	
public void LogikaMashinki (Contact contact,float deltaTime){

	/// машинкина логика
	if ( contact.isTouching()
			&& (contact.getFixtureA().getUserData() != null&&contact.getFixtureA().getUserData().equals("small_car")) 
			|| (contact.getFixtureB().getUserData() != null&&contact.getFixtureB().getUserData().equals("small_car"))) {
		
			if(contact.getFixtureA().getUserData().equals("small_car")&&!contact.getFixtureB().getUserData().equals("YES_ON")){
		Vector2 pos = contact.getFixtureA().getBody().getPosition();
		WorldManifold manifold = contact.getWorldManifold();
		
		for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
			if(manifold.getPoints()[j].x < pos.x&&manifold.getPoints()[j].y<pos.y+0.5f){
				contact.getFixtureA().getBody().setLinearVelocity(6f,0);
		//	System.out.println(pos.y);
		//	System.out.println(manifold.getPoints()[j].y);
			}
			if(manifold.getPoints()[j].x > pos.x&&manifold.getPoints()[j].y<pos.y+0.5f){
				contact.getFixtureA().getBody().setLinearVelocity(-6f,0);
				//System.out.println(pos.y);
				}
		}
			
	}
		if (contact.getFixtureB().getUserData().equals("small_car")
				&& !contact.getFixtureA().getUserData()
						.equals("YES_ON")) {
			Vector2 pos = contact.getFixtureB().getBody().getPosition();
			WorldManifold manifold = contact.getWorldManifold();

			for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
				if (manifold.getPoints()[j].x < pos.x&&manifold.getPoints()[j].y<pos.y+0.5f) {
					contact.getFixtureB().getBody()
							.setLinearVelocity(6, 0);
			//		System.out.println(pos.y);
				
				}
				if (manifold.getPoints()[j].x > pos.x&&manifold.getPoints()[j].y<pos.y+0.5f) {
					contact.getFixtureB().getBody()
							.setLinearVelocity(-6f, 0);
		//			System.out.println("DVA");
				}
			}

		}

	
}
}



public void OpredeleniePoverhnosti1(Contact contact,float deltaTime, MenuMyFish fish,boolean BOOM,Vector2 BOOM_POS,boolean BOOM_smash,boolean below){
	//ЛОГИКА ИГРОКА
//	boolean below = false;

	if (contact.isTouching()&& (contact.getFixtureA() == fish.playerPhysicsFixture || contact
					.getFixtureB() == fish.playerPhysicsFixture)) 		///может надо повтороить условие ?? подумать
	{
		
		
	if(contact.getFixtureA().getUserData().equals("asrc")){
		power(fish,Constants.PowerUp);
	}
		contact.getFixtureA().getBody().setUserData("BAM");
		if(contact.getFixtureA().getUserData().equals("energycell")){
			contact.getFixtureA().getBody().setUserData("BAM");
	
		
	}
		
		
		fish.z2++;
		Vector2 pos = fish.getPosition();
		WorldManifold manifold = contact.getWorldManifold();
	
	    below = true;
		for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
					
			below &= (manifold.getPoints()[j].y < pos.y);
			if(fish.getBody().isAwake()&&fish.pos==POSICION.FLY&&!fish.BOOM_smash)
			{
			fish.BOOM=true;
			if((contact.getFixtureB().getUserData().equals("YES_ON")
				||contact.getFixtureA().getUserData().equals("YES_ON")
					))
			{fish.BOOM_POS=new Vector2(manifold.getPoints()[j].x-0.5f,manifold.getPoints()[j].y );fish.BOOM=true;}
			else if((!contact.getFixtureB().getUserData().equals("YES_ON")
					||!contact.getFixtureA().getUserData().equals("YES_ON")
					)) 
			{fish.BOOM_POS=new Vector2(manifold.getPoints()[j].x,manifold.getPoints()[j].y);fish.BOOM=true;}
			//System.out.println("TUCTUC");
		}
		}

		if (below) {
		
			if (contact.getFixtureA().getUserData().equals("YES_ON")) 
			{
				fish.notuse = false;
				fish.onGround = true;
				contact.setFriction(100F);
				fish.inwater = false;
		//		 System.out.println("земля");
				 
			}
			if ( contact.getFixtureA().getUserData().equals("water") 
					) {
				fish.notuse = false;
				contact.setFriction(0.2f);
				fish.inwater = true;
				fish.onGround = true;
				power(fish);
				// System.out.println("В ВОДЕ");				
				 }
			else if ( !contact.getFixtureA().getUserData().equals("water")&&!contact.getFixtureA().getUserData().equals("YES_ON"))  {
				fish.inwater = false;
				fish.notuse = false;
				fish.onGround = true;
				contact.setFriction(1.8F);
				
			//	 System.out.println("не земля");
			}
		} 
	}
}	
public void power(MenuMyFish fish) {
	fish.inwater = true;
	if (fish.lives < 99)
		fish.lives = fish.lives + 0.1f;

	// TODO Auto-generated method stub

}

public void power(MenuMyFish fish,int z) {
	//level.getPlayer().inwater = true;
	//if (lives < 99)
	
	if (fish.lives  > 100-z){ 
		fish.lives=100;
	}
	else {fish.lives = fish.lives + z;}
	
	// TODO Auto-generated method stub

}
public void OtskokIgrokaOtMashinki(Contact contact,float deltaTime, MenuMyFish fish){
	// логика отскока  от МАШИНКИ
				if (contact.isTouching()
						&& contact.getFixtureA().getUserData() != null
						&& (contact.getFixtureB().getUserData() != null
								&& ((contact.getFixtureA().getUserData().equals("small_car")||contact.getFixtureA().getUserData().equals("222"))
										&& (contact.getFixtureB()
										.getUserData().equals("play"))) ||
										((contact.getFixtureB().getUserData().equals("small_car")||contact.getFixtureB().getUserData().equals("222"))&& contact
								.getFixtureA().getUserData().equals("play"))))
				{
					fish.BOOM=false;
					if(contact.getFixtureB().getUserData().equals("play"))
					{	fish.BOOM_smash=true;
					fish.BOOM=false;
					fish.BOOM_smash_POS=new Vector2(contact.getFixtureB().getBody().getWorldCenter());
					fish.smashplayer(contact.getFixtureB().getBody(), deltaTime);
					}
					if(contact.getFixtureA().getUserData().equals("play"))
					{	fish.BOOM_smash=true; 
					fish.BOOM=false;
					fish.BOOM_smash_POS=new Vector2(contact.getFixtureA().getBody().getWorldCenter());
					fish.smashplayer(contact.getFixtureA().getBody(), deltaTime);
					}	
					fish.BOOM=false;
						}
		}


	public static MenuObjects_logik getInst() {
		if(instance==null)
			synchronized (MenuObjects_logik.class ) {
				if (instance==null)
					instance=new MenuObjects_logik();
			}
		return instance;
		
	}
}
		

	