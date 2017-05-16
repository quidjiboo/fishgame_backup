package com.mygdx.jumpfish.game.logika;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.jumpfish.game.LevelMy;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.game.WorldControllerMy.TARGETCAM;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.gameobjects.MyLizard.JUMPNAPRAVLENIE;
import com.mygdx.jumpfish.gameobjects.MyLizard.POSICIONLIZARD;

public class MyContactListener implements ContactListener {
	LevelMy Level;
	World world;
//	private Object fixtureUserData;
	public MyContactListener(World world,LevelMy Level){
		super();
		this.world = world;
		this.Level = Level;
	}

	@Override
	public void beginContact(Contact contact) {
		
		// TODO Auto-generated method stub
	
		String fixtureUserDatadown = contact.getFixtureA().getUserData().toString();
		String fixtureYaz = contact.getFixtureB().getUserData().toString();
		
		if(WorldControllerMy.TargetCAM==TARGETCAM.LIZARD){
			
		 if ( fixtureUserDatadown == "l_down"){
			 Level.getLizard().fixturesDOWN.add(contact.getFixtureB());	
		//	 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
				// Level.getLizard().posliz=POSICIONLIZARD.DOWN;
					 }
		 else
		 if ( fixtureUserDatadown == "l_up"){
			 Level.getLizard().fixturesUP.add(contact.getFixtureB());	
		//	 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.UP;
					 }
		 else
		 if ( fixtureUserDatadown == "_right"){
			 Level.getLizard().fixturesRIGHT.add(contact.getFixtureB());	
		//	 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.RIGHT_WALL;
					 }
		 else
		 if ( fixtureUserDatadown == "_left"){
			 Level.getLizard().fixturesLEFT.add(contact.getFixtureB());	
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.LEFT_WALL;
					 }
		 if ( fixtureUserDatadown == "energycell"&&fixtureYaz=="rope"){
			// System.out.print("dsfdsfds");
			 contact.getFixtureA().getBody().setUserData("BAM");
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.LEFT_WALL;
					 }
		 if ( contact.getFixtureA().getUserData().toString().contains("333")&&fixtureYaz=="lizard"){
			 System.out.print("dsfdsfds");
			Level.getPlayer().smashplayer(contact.getFixtureB().getBody());
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.LEFT_WALL;
					 }
		 
		}
		
		 if ( fixtureUserDatadown == "lizard"){
			 Level.getLizard().numFootContacts++;	

					 }
		 
		 
		 fixtureUserDatadown = contact.getFixtureB().getUserData().toString();
		fixtureYaz = contact.getFixtureA().getUserData().toString();
		 
		 if(WorldControllerMy.TargetCAM==TARGETCAM.LIZARD){
			 
			 
		 if ( fixtureUserDatadown == "l_down" ){
			 Level.getLizard().fixturesDOWN.add(contact.getFixtureA());
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE; 
		//	 Level.getLizard().posliz=POSICIONLIZARD.DOWN;
			 
		 }
		 else
		 if ( fixtureUserDatadown == "l_up"){
			 Level.getLizard().fixturesUP.add(contact.getFixtureA());	
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
		//		 Level.getLizard().posliz=POSICIONLIZARD.UP;
					 }
		 else
		 if ( fixtureUserDatadown == "_right"){
			 Level.getLizard().fixturesRIGHT.add(contact.getFixtureA());	
		//	 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
		//		 Level.getLizard().posliz=POSICIONLIZARD.RIGHT_WALL;
					 }
		 else
		 if ( fixtureUserDatadown == "_left"){
			 Level.getLizard().fixturesLEFT.add(contact.getFixtureA());	
		//	 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
		//		 Level.getLizard().posliz=POSICIONLIZARD.LEFT_WALL;
					 }
		 if ( fixtureUserDatadown == "energycell"&&fixtureYaz=="rope"){
			 contact.getFixtureB().getBody().setUserData("BAM");
	//		 System.out.print("dsfdsfds");
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.LEFT_WALL;
					 }
		 
		 if ( contact.getFixtureB().getUserData().toString().contains("333")&&fixtureYaz=="lizard"){
			 System.out.print("dsfdsfds");
			Level.getPlayer().smashplayer(contact.getFixtureA().getBody());
	//		 Level.getLizard().Jump_Napravlenie=JUMPNAPRAVLENIE.NONE;
			//	 Level.getLizard().posliz=POSICIONLIZARD.LEFT_WALL;
					 }
		 }
		 
		 if ( fixtureUserDatadown == "lizard"){
			 Level.getLizard().numFootContacts++;	

					 }
		 
		 
		 
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		String fixtureUserDatadown = contact.getFixtureA().getUserData()
				.toString();
		
		if(WorldControllerMy.TargetCAM==TARGETCAM.LIZARD){
			
			 
		if (fixtureUserDatadown == "l_down") {
			Level.getLizard().fixturesDOWN.remove(contact.getFixtureB());

		} else
		if (fixtureUserDatadown == "l_up") {
			Level.getLizard().fixturesUP.remove(contact.getFixtureB());

		} else
		if (fixtureUserDatadown == "_right") {
			Level.getLizard().fixturesRIGHT.remove(contact.getFixtureB());

		} else
		if (fixtureUserDatadown == "_left") {
			Level.getLizard().fixturesLEFT.remove(contact.getFixtureB());

		}
		
		
		}
		 if ( fixtureUserDatadown == "lizard"){
			 Level.getLizard().numFootContacts--;	

					 }
		fixtureUserDatadown = contact.getFixtureB().getUserData().toString();
		
		if(WorldControllerMy.TargetCAM==TARGETCAM.LIZARD){
			
		if (fixtureUserDatadown == "l_down") {
			Level.getLizard().fixturesDOWN.remove(contact.getFixtureA());

		} else
		if (fixtureUserDatadown == "l_up") {
			Level.getLizard().fixturesUP.remove(contact.getFixtureA());
		} else
		if (fixtureUserDatadown == "_right") {
			Level.getLizard().fixturesRIGHT.remove(contact.getFixtureA());

		} else
		if (fixtureUserDatadown == "_left") {
			Level.getLizard().fixturesLEFT.remove(contact.getFixtureA());

		}}
		 if ( fixtureUserDatadown == "lizard"){
			 Level.getLizard().numFootContacts--;	

					 }
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
