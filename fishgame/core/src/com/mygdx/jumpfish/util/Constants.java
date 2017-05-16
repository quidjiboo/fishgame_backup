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

public class Constants {
	
		public static final int PowerUp = 20;
		public static final int Smash = 30;
		
		
	// Visible game world is 5 meters wide
	public static final float VIEWPORT_WIDTH = 5.0f;

	// Visible game world is 5 meters tall
	public static final float VIEWPORT_HEIGHT = 7f;
	

		// Visible game world is 5 meters tall
		public static final float Menu_VIEWPORT_HEIGHT = 15f;
		// Visible game world is 5 meters wide
				public static final float Menu_VIEWPORT_WIDTH = 25f;
	// GUI Width
	public static final float VIEWPORT_GUI_WIDTH = 800.0f;

	// GUI Height
	public static final float VIEWPORT_GUI_HEIGHT = 480.0f;

	
	
	// Location of image file for level 01
	public static final String LEVEL_01 = "levels/level-01.png";

	// Amount of extra lives at level start
	public static final int LIVES_START = 100;


	// Delay after game over
	public static final float TIME_DELAY_GAME_OVER = 3;

	// Game preferences file
	public static final String PREFERENCES = "SaveLevel.prefs";

	public static final String MY_NEW_TEXTURE_ATLAS_OBJECTS = "images/anim1.pack";;
}
