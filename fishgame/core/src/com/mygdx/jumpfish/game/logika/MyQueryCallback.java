package com.mygdx.jumpfish.game.logika;

import java.util.HashSet;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.mygdx.jumpfish.game.LevelMy;

public class MyQueryCallback implements QueryCallback {
//	public HashSet<Body> findBodys;
	  private Body fBody;
	  private Fixture fFixture;
	 
	 
	  @Override
	  public boolean reportFixture(Fixture fixture) {
	//	if(fixture.getBody()!=null){
			
		
				
//	    fBody=fixture.getBody();
	 //   findBodys.add(fixture.getBody());
	    LevelMy.Sensorfixtures.add(fixture);
	    //}
	    return true;
	  }
	
	}
