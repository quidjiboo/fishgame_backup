package com.mygdx.jumpfish.gameobjects.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;



public class Bar{
 
Texture tex;
Vector2 position;
TextureRegion reg1;
//Target Dimension of image
 
int targetWidth;
int targetHeight;
 
//Src Dimensions of Image
 
int srcWidth;
int srcHeight;
int srcX;
int srcY;
 
float multiper;

public Bar(TextureRegion reg,float x,float y,float multiper){
 this.multiper=multiper;
 reg1=reg;
    tex=reg1.getTexture();
    position=new Vector2(x,y);
    srcX=reg1.getRegionX();
    srcY=reg1.getRegionY();
    srcWidth=reg.getRegionWidth();
    
    srcHeight=reg.getRegionHeight();
 
   
    multiper=100;
    
}
//public void flip() {
//	reg1.flip(true, true);
//}
public void flipy() {
	if (!reg1.isFlipY())
	reg1.flip(false, true);
}


public void Draw(SpriteBatch sp){

	sp.draw(reg1, position.x-targetWidth/2,position.y-targetHeight/2, srcWidth, srcHeight);
	
}
public void Drawpos(SpriteBatch sp,int x,int y){
	
	sp.draw(reg1, x,y, srcWidth, srcHeight);
	
}
public void Draw(SpriteBatch sp,int multiper){
	if( multiper > 100)multiper = 100;
//sp.draw(tex, position.x-targetWidth/2, position.y-targetHeight/2,  targetWidth/2, targetHeight/2,
      //  	      clipWidth, clipHeight,1, 1, 0, startX, startY,
      //  	      clipSrcWidth, clipSrcHeight, false, false);
//multiper=1;
    //    sp.draw(tex, position.x-targetWidth/2, position.y-targetHeight/2,  targetWidth/2, targetHeight/2,
 //    1f*multiper*130, 130,1*0.03125f, 1*0.03125f, 0, srcX, srcY,
 //    (int)(srcWidth*multiper), srcHeight, false, false);
	
        sp.draw(tex, position.x-targetWidth/2,position.y-targetHeight/2, targetWidth/2,targetHeight/2,
        	    multiper+1, 34,1, 1, 0, srcX, srcY,
        	    multiper+1, srcHeight, false, false);
        
}
// public void Draw12(SpriteBatch sp,int multiper){
	
    //sp.draw(tex, position.x-targetWidth/2, position.y-targetHeight/2,  targetWidth/2, targetHeight/2,
  //  	      clipWidth, clipHeight,1, 1, 0, startX, startY,
  //  	      clipSrcWidth, clipSrcHeight, false, false);
//multiper=1;
//    sp.draw(tex, position.x-targetWidth/2, position.y-targetHeight/2,  targetWidth/2, targetHeight/2,
//    1f*multiper*130, 130,1*0.03125f, 1*0.03125f, 0, srcX, srcY,
//    (int)(srcWidth*multiper), srcHeight, false, false);
//	if( multiper > 100)multiper = 100;
//    sp.draw(tex, position.x-targetWidth/2,position.y-targetHeight/2, targetWidth/2,targetHeight/2,
 //   	    multiper+1, 34,1, 1, 0, srcX, srcY,
///    	    multiper+1, srcHeight, true,true);
    
//}


 
}