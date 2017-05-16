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


package com.mygdx.jumpfish.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumpfish.gameobjects.Book;
import com.mygdx.jumpfish.gameobjects.Platform;

public class GamePreferences {

	public static final String TAG = GamePreferences.class.getName();

	public static final GamePreferences instance = new GamePreferences();
	public int lang;
	public boolean cansave=true;
	public boolean sound;
	public boolean music;
	public float volSound;
	public float volMusic;
	public int charSkin;
	public boolean showFpsCounter;
	public boolean useMonochromeShader;
	public int levelpr;
	
	private Preferences prefs;

	// singleton: prevent instantiation from other classes
	private GamePreferences () {
		prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
		
		
	}
	public void loadmenu () {
		//	System.out.println("0000000!!!!!!!000000000");
		//	levelpr = prefs.getInteger("levelpr", 1);
//			music = prefs.getBoolean("music", true);
		sound = prefs.getBoolean("sound", true);
		music = prefs.getBoolean("music", true);
		
	//	levelpr = prefs.getInteger("levelpr", 5);
			volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);
			volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.75f), 0.0f, 1.0f);
			lang = prefs.getInteger("lang", 11);
			/*		charSkin = MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);
			showFpsCounter = prefs.getBoolean("showFpsCounter", false);
			useMonochromeShader = prefs.getBoolean("useMonochromeShader", false);*/
			
		}
	public void loadlevelkolvo () {
	//	System.out.println("0000000!!!!!!!000000000");
		levelpr = prefs.getInteger("levelpr", 1);
//		music = prefs.getBoolean("music", true);
	/*	sound = prefs.getBoolean("sound", true);
		music = prefs.getBoolean("music", true);
		volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);
		volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
		charSkin = MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);
		showFpsCounter = prefs.getBoolean("showFpsCounter", false);
		useMonochromeShader = prefs.getBoolean("useMonochromeShader", false);*/
		
	}
	public void loadlevelkolvo123 () {
		//	System.out.println("0000000!!!!!!!000000000");
			levelpr = 20;
//			music = prefs.getBoolean("music", true);
		/*	sound = prefs.getBoolean("sound", true);
			music = prefs.getBoolean("music", true);
			volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);
			volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
			charSkin = MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);
			showFpsCounter = prefs.getBoolean("showFpsCounter", false);
			useMonochromeShader = prefs.getBoolean("useMonochromeShader", false);*/
			
		}
	public void saveprogress (int level){
		if(cansave){
		if (level>=levelpr&&level>=5){
			levelpr=levelpr+1;
			prefs.putInteger("levelpr", levelpr);
		
			prefs.flush();
		}
		else{levelpr=6;
		prefs.putInteger("levelpr", levelpr);
		prefs.flush();}
	}}

	public void save () {
	
		if(cansave){
	/*	prefs.putBoolean("sound", sound);
		prefs.putBoolean("music", music);
	
		prefs.putInteger("charSkin", charSkin);
		prefs.putBoolean("showFpsCounter", showFpsCounter);
		prefs.putBoolean("useMonochromeShader", useMonochromeShader);
		prefs.flush();*/
		prefs.flush();
		}
	}
	public void savemenu () {
		if(cansave){
		prefs.putFloat("volSound", volSound);
		prefs.putFloat("volMusic", volMusic);
			prefs.putBoolean("sound", sound);
			prefs.putBoolean("music", music);
			prefs.putInteger("lang", lang);
			prefs.putInteger("lang", lang);
			
			prefs.flush();
		}
	}
}
