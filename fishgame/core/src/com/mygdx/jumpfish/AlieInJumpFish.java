package com.mygdx.jumpfish;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.jumpfish.Screens.DirectedGameMy;
import com.mygdx.jumpfish.Screens.GameScreenMy;
import com.mygdx.jumpfish.Screens.MenuScreenMy;
import com.mygdx.jumpfish.Screens.SelectMenuScreenMy;
import com.mygdx.jumpfish.transactions.ScreenTransition;
import com.mygdx.jumpfish.transactions.ScreenTransitionSlice;
import com.mygdx.jumpfish.transactions.ScreenTransitionSlide;
import com.mygdx.jumpfish.util.AssetsMy;
import com.mygdx.jumpfish.util.GamePreferences;
import com.mygdx.jumpfish.util.MyAudioManager;








public class AlieInJumpFish extends DirectedGameMy {
	public AlieInJumpFish(AdsController adsController){
		if (adsController != null) {
            this.adsController = adsController;
        } else {
            this.adsController = new DummyAdsController();
        }
    }
	public AlieInJumpFish(){
       
    }
	
    static  AdsController adsController;
	@Override
	public void create() {
		
		// Set Libgdx log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.graphics.setVSync(true);

		// Load assets
		GamePreferences.instance.loadlevelkolvo();
		AssetsMy.instance.init(new AssetManager());
		// Start game at menu screen
		ScreenTransition transition = ScreenTransitionSlide.init(0.75f, ScreenTransitionSlide.DOWN, false, Interpolation.bounceOut);
		
		 GamePreferences.instance.loadmenu();
		
		MyAudioManager.instance.play(AssetsMy.instance.music.song02);
	
		//MyAudioManager.instance.stopMusic();
		
		//ScreenTransition transition = null;
		
		setScreen(new MenuScreenMy(this),transition);
		adsController.showBannerAd();
	

	}
	 static public void hideads(){
		
		adsController.hideBannerAd();
		
	}
	 static public void showads(){
			
			adsController.showBannerAdmy();
			System.out.println("Покажись реклама");
		}
	 static public boolean isconnect(){
			
			return adsController.isWifiConnected();
			
		}
		
	
}