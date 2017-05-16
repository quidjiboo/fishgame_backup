package com.mygdx.jumpfish.gameobjects.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.mygdx.jumpfish.game.WorldControllerMy;
import com.mygdx.jumpfish.gameobjects.AbstractGameObject;
import com.mygdx.jumpfish.gameobjects.startscreen.MenuMyFish;
import com.mygdx.jumpfish.util.AssetsMy;

public class MenuGUI extends AbstractGameObject  {

	private int positionX;
	private int positionY;
	
	public  Bar helth_solid;
	private MenuMyFish myFistut;
	public int number;
	public  Bar  EnergyEC;
	public  Bar  ramkaEC;
	public  Bar  ramka;
	public Animation helth_arrow;
	public Animation helth_solid1;
	public MenuGUI(MenuMyFish myFish,int number) {
		this.number = number;
		myFistut = myFish;
		init();
	}
	

private void init() {
	positionX=10;
	positionY=0;
	helth_arrow = AssetsMy.instance.fish.helth_arrow;
	helth_solid1 = AssetsMy.instance.fish.helth_arrowup;
	setAnimation(helth_arrow);
	ramka = new	Bar(AssetsMy.instance.fish.ramka_bar,0,0,0);
	//ramka.flipy();
	helth_solid = new	Bar(AssetsMy.instance.fish.helth_solid,0,0,0);
	//EnergyEC = new	Bar(AssetsMy.instance.fish.EnergyEC,0,0,100);
	//ramkaEC = new	Bar(AssetsMy.instance.fish.ramkaEC,0,0,100);
	//EnergyEC.flip();
	//ramkaEC.flip();
	animation.setPlayMode(PlayMode.NORMAL);
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


public void render (SpriteBatch batch,int livs,int EC,int xx ) {
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
	AssetsMy.instance.fonts.defaultBig.draw(batch, (int)(myFistut.PreperingTime)+"", positionX+70,positionY+420);
	AssetsMy.instance.fonts.defaultBig.draw(batch, livs +"", positionX+70,positionY+400);
	AssetsMy.instance.fonts.defaultBig.setColor(0.75f,0f, 0.75f, 1);
	AssetsMy.instance.fonts.defaultBig.draw(batch, "FPS=" + (int)(Gdx.graphics.getFramesPerSecond()), positionX+70, 450);
	AssetsMy.instance.fonts.defaultBig.setColor(1,1,1, 1);
	}


@Override
public void render(SpriteBatch batch) {
	// TODO Auto-generated method stub
	
}

}