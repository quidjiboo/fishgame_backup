package com.mygdx.jumpfish.game.logika;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.mygdx.jumpfish.game.LevelMy;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.MyFish;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject.POSICION;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish;
import com.mygdx.jumpfish.startscreen.MenuWorldControllerMy;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.Constants;
import com.mygdx.jumpfish.util.MyAudioManager;
import com.sun.glass.ui.Window.Level;



public class Objects_logik {
	private static volatile Objects_logik instance;
	private Objects_logik(){
		 
		
		}	
public void LogikaMashinki (Contact contact,float deltaTime){

	/// ��������� ������
	if ( contact.isTouching()
			&& (contact.getFixtureA().getUserData() != null&&contact.getFixtureA().getUserData().equals("small_car")) 
			|| (contact.getFixtureB().getUserData() != null&&contact.getFixtureB().getUserData().equals("small_car"))
			)
			{
		
			if(contact.getFixtureA().getUserData().equals("small_car")
	//				&&!contact.getFixtureB().getUserData().equals("YES_ON")
					){
		Vector2 pos = contact.getFixtureA().getBody().getPosition();
		WorldManifold manifold = contact.getWorldManifold();
		
		for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
			if(manifold.getPoints()[j].x < pos.x&&manifold.getPoints()[j].y>pos.y+0.1f&&contact.getFixtureA().getBody().getLinearVelocity().x!=6)
			{
				contact.getFixtureA().getBody().setLinearVelocity(6f,0);
	//			contact.getFixtureA().getBody().applyLinearImpulse(20f, 0, contact.getFixtureA().getBody().getPosition().x, contact.getFixtureA().getBody().getPosition().y, true);
			
		//	System.out.println(pos.y);
		//	System.out.println(manifold.getPoints()[j].y);
			}
			if(manifold.getPoints()[j].x > pos.x&&manifold.getPoints()[j].y>pos.y+0.1f&&contact.getFixtureA().getBody().getLinearVelocity().x!=-6)
			//		&&Math.abs(contact.getFixtureA().getBody().getLinearVelocity().x)<=0.1f
			{
				contact.getFixtureA().getBody().setLinearVelocity(-6f,0);
		//		contact.getFixtureA().getBody().applyLinearImpulse(-20f, 0, contact.getFixtureA().getBody().getPosition().x, contact.getFixtureA().getBody().getPosition().y, true);
				//System.out.println(pos.y);
				}
		}
			
	}
		if (contact.getFixtureB().getUserData().equals("small_car")
	//			&& !contact.getFixtureA().getUserData().equals("YES_ON")
						) {
			Vector2 pos = contact.getFixtureB().getBody().getPosition();
			WorldManifold manifold = contact.getWorldManifold();

			for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
				if (manifold.getPoints()[j].x < pos.x&&manifold.getPoints()[j].y>pos.y+0.1f&&contact.getFixtureB().getBody().getLinearVelocity().x!=6) {
					contact.getFixtureB().getBody().setLinearVelocity(6, 0);
	//				contact.getFixtureA().getBody().applyLinearImpulse(20f, 0, contact.getFixtureA().getBody().getPosition().x, contact.getFixtureA().getBody().getPosition().y, true);
			//		System.out.println(pos.y);
					
				}
				if (manifold.getPoints()[j].x > pos.x&&manifold.getPoints()[j].y>pos.y+0.1f&&contact.getFixtureB().getBody().getLinearVelocity().x!=-6) {
					contact.getFixtureB().getBody().setLinearVelocity(-6f, 0);
		//			contact.getFixtureA().getBody().applyLinearImpulse(-20f, 0, contact.getFixtureA().getBody().getPosition().x, contact.getFixtureA().getBody().getPosition().y, true);
		//			System.out.println("DVA");
					
				}
			}

		}

	
}
}


public void OpredeleniePoverhnosti1(Contact contact,float deltaTime, MyFish fish,boolean BOOM,Vector2 BOOM_POS,boolean BOOM_smash,boolean below){
	//������ ������
//	boolean below = false;

	if (contact.isTouching()&& (contact.getFixtureA() == fish.playerPhysicsFixture || contact
					.getFixtureB() == fish.playerPhysicsFixture)) 		///����� ���� ���������� ������� ?? ��������
	{//MyAudioManager.instance.play(AssetsMy.instance.sounds.onground,1,1);
		
		
	if(contact.getFixtureA().getUserData().equals("h2o")){
		//power(fish,Constants.PowerUp);
		
		fish.numberhelthbonus=fish.numberhelthbonus+1;
		contact.getFixtureA().getBody().setUserData("BAM");
		MyAudioManager.instance.play(AssetsMy.instance.sounds.Powerup);
	}
	if(contact.getFixtureA().getUserData().equals("pl")){
		//power(fish,Constants.PowerUp);
		if(fish.numberplatforms<4)
		fish.numberplatforms=fish.numberplatforms+1;
		contact.getFixtureA().getBody().setUserData("BAM");
		MyAudioManager.instance.play(AssetsMy.instance.sounds.Powerup);
	}
	if(contact.getFixtureA().getUserData().equals("gun")){
		//power(fish,Constants.PowerUp);\
		if(fish.numbervistrel<10)
		fish.numbervistrel=fish.numbervistrel+1;
		if(fish.numbervistrel<10)
			fish.numbervistrel=fish.numbervistrel+1;
		//if(fish.numbervistrel<10)
	//		fish.numbervistrel=fish.numbervistrel+1;
			contact.getFixtureA().getBody().setUserData("BAM");
		MyAudioManager.instance.play(AssetsMy.instance.sounds.Powerup);
	}
	if(contact.getFixtureA().getUserData().equals("dj")){
		//power(fish,Constants.PowerUp);
		
		fish.numberdoublejump=fish.numberdoublejump+1;
		contact.getFixtureA().getBody().setUserData("BAM");
		MyAudioManager.instance.play(AssetsMy.instance.sounds.Powerup);
	}
	if(contact.getFixtureA().getUserData().equals("week")){
		//power(fish,Constants.PowerUp);
		
		LevelMy.jump=false;
		
		contact.getFixtureA().getBody().setUserData("BAM");
		MyAudioManager.instance.play(AssetsMy.instance.sounds.Powerup);
	}
	// contact.getFixtureA().getBody().setUserData("BAM"); //����ͨ�
		if(contact.getFixtureA().getUserData().equals("energycell")){
			
			MyAudioManager.instance.play(AssetsMy.instance.sounds.Pickup);
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
			//	fish.getBody().setFixedRotation(true);
				fish.mozhnodoublejump=true;
				fish.notuse = false;
				fish.onGround = true;
			contact.setFriction(2F);
				fish.inwater = false;
				
		// System.out.println("�����");
				 
			}
			if ( contact.getFixtureA().getUserData().equals("water") 
					) {
				fish.mozhnodoublejump=true;
				fish.notuse = false;
				contact.setFriction(0.2f);
				fish.inwater = true;
				fish.onGround = true;
				power(fish);
				 System.out.println("� ����");				
				 }
			else if ( !contact.getFixtureA().getUserData().equals("water")&&!contact.getFixtureA().getUserData().equals("YES_ON"))  {
				fish.inwater = false;
				fish.notuse = false;
				fish.onGround = true;
		//		contact.setFriction(1.8F); // ����� 
				fish.mozhnodoublejump=true;
//				 System.out.println("�� �����");
				
			}
		} 
	}
}	
public void power(MyFish fish) {
	fish.inwater = true;
	if (fish.lives < 99)
		fish.lives = fish.lives + 0.1f;

	// TODO Auto-generated method stub

}

public void power(MyFish fish,int z) {
	//level.getPlayer().inwater = true;
	//if (lives < 99)
	
	if (fish.lives  > 100-z){ 
		fish.lives=100;
	}
	else {fish.lives = fish.lives + z;}
	
	// TODO Auto-generated method stub

}


public void OtskokIgrokaOtMashinki(Contact contact,float deltaTime, MyFish fish){
	// ������ �������  �� �������
				if (contact.isTouching()
						&& contact.getFixtureA().getUserData() != null
						&& (contact.getFixtureB().getUserData() != null
								&& ((contact.getFixtureA().getUserData().equals("small_car")||contact.getFixtureA().getUserData().equals("333")||contact.getFixtureA().getUserData().equals("222"))
										&& (contact.getFixtureB()
										.getUserData().equals("play"))) ||
										((contact.getFixtureB().getUserData().equals("small_car")||contact.getFixtureB().getUserData().equals("333")||contact.getFixtureB().getUserData().equals("222"))&& contact
								.getFixtureA().getUserData().equals("play"))))
				{
					fish.BOOM=false;
					if(contact.getFixtureB().getUserData().equals("play"))
					{	fish.BOOM_smash=true;
					fish.BOOM=false;
					fish.BOOM_smash_POS=new Vector2(contact.getFixtureB().getBody().getWorldCenter());
					fish.smashplayer(contact.getFixtureB().getBody());
					}
					if(contact.getFixtureA().getUserData().equals("play"))
					{	fish.BOOM_smash=true; 
					fish.BOOM=false;
					fish.BOOM_smash_POS=new Vector2(contact.getFixtureA().getBody().getWorldCenter());
					fish.smashplayer(contact.getFixtureA().getBody());
					}
					fish.BOOM=false;
			}
		}

public void Shar(Contact contact,float deltaTime, MyFish fish){
	// ������ �������  �� �������
				if (contact.isTouching()
						&& contact.getFixtureA().getUserData() != null
						&& (contact.getFixtureB().getUserData() != null
								&& ((contact.getFixtureA().getUserData().equals("pool"))
										&& (contact.getFixtureB()
										.getUserData().equals("play"))) ||
										(contact.getFixtureB().getUserData().equals("pool"))&& contact
								.getFixtureA().getUserData().equals("play")))
				{
					fish.BOOM=false;
					if(contact.getFixtureB().getUserData().equals("play")&&(Math.abs(contact.getFixtureA().getBody().getLinearVelocity().y)>0.8f||Math.abs(contact.getFixtureA().getBody().getLinearVelocity().x)>1.2f))
					{	 System.out.println("SPEEDA="+contact.getFixtureA().getBody().getLinearVelocity().y);
						fish.BOOM_smash=true;
					fish.BOOM=false;
					fish.BOOM_smash_POS=new Vector2(contact.getFixtureB().getBody().getWorldCenter());
					fish.smashplayer(contact.getFixtureB().getBody());
					}
					else if(contact.getFixtureA().getUserData().equals("play")&&(Math.abs(contact.getFixtureB().getBody().getLinearVelocity().y)>0.8f||Math.abs(contact.getFixtureB().getBody().getLinearVelocity().x)>1.2f))
					{	System.out.println("SPEEDB="+contact.getFixtureB().getBody().getLinearVelocity().y);
						fish.BOOM_smash=true; 
					fish.BOOM=false;
					fish.BOOM_smash_POS=new Vector2(contact.getFixtureA().getBody().getWorldCenter());
					fish.smashplayer(contact.getFixtureA().getBody());
					}
					fish.BOOM=false;
			}
		}
public void VragprotivPlasmaball(Contact contact,float deltaTime){
	// ������ �������  �� �������
				if (contact.isTouching()
						&& contact.getFixtureA().getUserData() != null
						&& (contact.getFixtureB().getUserData() != null
								&& (contact.getFixtureB().getUserData().equals("fish_ball"))&& contact
								.getFixtureA().getUserData().toString().contains("vrag3")))
				{MyAudioManager.instance.play(AssetsMy.instance.sounds.vzriv);
					
					contact	.getFixtureA().setUserData("vrag2");
					}
				else if(contact.isTouching()
						&& contact.getFixtureA().getUserData() != null
						&& (contact.getFixtureB().getUserData() != null
								&& (contact.getFixtureB().getUserData().equals("fish_ball"))&& contact
								.getFixtureA().getUserData().toString().contains("vrag2")))
				{MyAudioManager.instance.play(AssetsMy.instance.sounds.vzriv);contact	.getFixtureA().setUserData("vrag1");}
				else if(contact.isTouching()
						&& contact.getFixtureA().getUserData() != null
						&& (contact.getFixtureB().getUserData() != null
								&& (contact.getFixtureB().getUserData().equals("fish_ball"))&& contact
								.getFixtureA().getUserData().toString().contains("vrag1")))
				{MyAudioManager.instance.play(AssetsMy.instance.sounds.vzriv);contact	.getFixtureA().setUserData("ubit");}
		}

	public static Objects_logik getInst() {
		if(instance==null)
			synchronized (Objects_logik.class ) {
				if (instance==null)
					instance=new Objects_logik();
			}
		return instance;
		
	}
}
		

	