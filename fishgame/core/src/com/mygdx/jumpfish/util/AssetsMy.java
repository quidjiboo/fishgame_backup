package com.mygdx.jumpfish.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;






public class AssetsMy implements Disposable, AssetErrorListener {

	private final String RUSSIAN_CHARACTERS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private  BitmapFont testtitleFont;
	
	public static final String TAG = AssetsMy.class.getName();

	public static final AssetsMy instance = new AssetsMy();

	private AssetManager assetManager;
	public AssetSounds sounds;
	public AssetFonts fonts;

	public AssetMusic music;
	public AssetFish fish;
	
	// singleton: prevent instantiation from other classes
	private AssetsMy () {
	}
	public class AssetSounds {
		public final Sound onground;
		public final Sound longjump;
		public final Sound jump;
		public final Sound Hurt;
		public final Sound Pickup;
		public final Sound helicopter;
		public final Sound car;
		public final Sound portal;
		public final Sound portalon;
		public final Sound Powerup;
		public final Sound touch;
		public final Sound vistrel;
		public final Sound vistrel_fish;
		public final Sound vzriv;
		public AssetSounds (AssetManager am) {
			vistrel= am.get("sounds/vistrel.wav", Sound.class);
			vistrel_fish= am.get("sounds/vistrel_fish.wav", Sound.class);
			vzriv= am.get("sounds/vzriv.wav", Sound.class);
			portalon= am.get("sounds/portalon.wav", Sound.class);
			portal= am.get("sounds/portal.wav", Sound.class);
			Powerup= am.get("sounds/Powerup.wav", Sound.class);
			touch= am.get("sounds/touch.wav", Sound.class);
			onground = am.get("sounds/onground.wav", Sound.class);
			longjump = am.get("sounds/longjump.wav", Sound.class);
			jump = am.get("sounds/jump.wav", Sound.class);
			Hurt = am.get("sounds/Hurt.wav", Sound.class);
			Pickup = am.get("sounds/Pickup.wav", Sound.class);
			helicopter = am.get("sounds/helicopter.wav", Sound.class);
			car = am.get("sounds/car.wav", Sound.class);
		}
	}
	public class AssetMusic {
		public final Music song01;
		public final Music song02;
	
		public final Music song03;
		public final Music song04;
		public final Music song05;
		public final Music song06;

		public AssetMusic (AssetManager am) {
			song01 = am.get("music/RoccoW_-_05_-_Weeklybeats_2014_5_-Ive_Got_Nothing.mp3", Music.class);
			song02 = am.get("music/RoccoW_-_Break-A-Leg.mp3", Music.class);
			song03 = am.get("music/RoccoW_-_Chipho_instrumental.mp3", Music.class);
			song04 = am.get("music/RoccoW_-_Lady_Bad_Luck_VRC6.mp3", Music.class);
			song05 = am.get("music/RoccoW_-_Messeah.mp3", Music.class);
			song06 = am.get("music/RoccoW_-_xyce_-_Quelle_Surprise_VRC6_Remix.mp3", Music.class);
			
			
		}
	}
	public class AssetFonts {
		public  final BitmapFont titleFont;
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont MyFont;
		public final BitmapFont defaultBig;
		public final BitmapFont Big;
		public AssetFonts () {
		
			//titleFont = new BitmapFont(Gdx.files.internal("images/my_fnt.fnt"), true);
			titleFont=testtitleFont;
		
			
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("data/my_fnt.fnt"), true);
			defaultNormal = new BitmapFont(Gdx.files.internal("data/my_fnt.fnt"), false);
			MyFont = new BitmapFont(Gdx.files.internal("data/my_fnt.fnt"), false);
			defaultBig = new BitmapFont(Gdx.files.internal("data/my_fnt.fnt"), true);
			Big = new BitmapFont(Gdx.files.internal("data/my_fnt.fnt"), true);
			// set font sizes
			defaultSmall.setScale(0.25f);
			MyFont.setScale(0.25f);
		Big.setScale(0.45f);
			defaultBig.setScale(0.25f);
			// enable linear texture filterinРІР°РїРїРІg for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			MyFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//	defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	//		defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}


	public class AssetFish 	{
		public final Animation newplatform;
		public final AtlasRegion H2O_GUI;
		public final AtlasRegion DJ_GUI;
		public final AtlasRegion PL_GUI;
		public final AtlasRegion GUN_GUI;
		
		public final AtlasRegion week;
		public final AtlasRegion fishnotjump;
		public final AtlasRegion selectbox;
		public final AtlasRegion ramkaEC;
		public final AtlasRegion EnergyEC;
		public final AtlasRegion ramkabox;
		public final AtlasRegion ramka_bar;
		//public final AtlasRegion ramka_number;
		public final AtlasRegion helth_solid;
		public final AtlasRegion pool_ball_var2;
		public final AtlasRegion pool_ball_var1;
		public final AtlasRegion pool_ball_shadow;
		public final AtlasRegion small_coob1;
		public final AtlasRegion small_coob2;
		public final AtlasRegion power10;
		public final AtlasRegion power8;
		public final AtlasRegion power9;
		public final AtlasRegion power2;
		public final AtlasRegion power3;
		public final AtlasRegion power4;
		public final AtlasRegion power5;
		public final AtlasRegion power6;
		public final AtlasRegion power7;
		public final AtlasRegion Yazik;
	//	public final AtlasRegion see;
	//	public final AtlasRegion sky;
	//	public final AtlasRegion beach;
	//	public final Array<AtlasRegion> cloud;
	//	public final AtlasRegion zabor;
		public final AtlasRegion h2o;
		public final AtlasRegion dj;
		public final AtlasRegion pl;
		public final AtlasRegion gun;
		public final AtlasRegion yadro_vrag;
		public final AtlasRegion yadro_pushka;
		public final AtlasRegion stend_pushka;
		public final AtlasRegion stvol_pushka;
		public final AtlasRegion coob1;
		public final AtlasRegion big_book;
		public final AtlasRegion small_book1;
		public final AtlasRegion small_book2;
		public final Animation Lizaed_anim_fall;
		public final Animation Lizaed_anim_walk;
		public final Animation Lizaed_anim_stand;
		public final Animation Lizaed_anim_jump;
		public final Animation Lizaed_anim_yaz;
		public final Animation animNormal;
		public final Animation anim_fish_preparing_to_fly;
		public final Animation anim_fish_fly;
		public final Animation anim_fish_slap;
		public final Animation fish_swim;
		public final Animation smallcar;
		public final Animation helic;
		public final Animation ply_pl;
		public final Animation energycell;
		public final Animation asrc;
		public final Animation portal_close;
		public final Animation portal_open;
		public final Animation helth_arrow;
		public final Animation helth_arrowup;
		public final Animation alien_jump;
		public final Animation alien_jump_stop;
		public final Animation alien_run;
		public final Animation space_ship;
		public final Animation orb_ship;
		public final Animation vrag_trinity;
		public final Animation fish_vistrel;
		public final Animation trinityvistrel;
		public final Animation orbvistrel;
		public AssetFish (TextureAtlas atlas) {
			H2O_GUI= atlas.findRegion("h2o1");
			DJ_GUI= atlas.findRegion("dj1");
			PL_GUI= atlas.findRegion("pl1");
			GUN_GUI= atlas.findRegion("gun1");
			h2o = atlas.findRegion("h2o");
			gun = atlas.findRegion("gun");
			pl = atlas.findRegion("pl");
			dj = atlas.findRegion("dj");
			week=  atlas.findRegion("week");
			fishnotjump=  atlas.findRegion("fishnotjump");
			selectbox=  atlas.findRegion("selectbox");
			EnergyEC=  atlas.findRegion("cell_gui");
			ramkaEC =  atlas.findRegion("cell_no");
			
		//	ramka_number=  atlas.findRegion("ramka_number");
			ramka_bar =  atlas.findRegion("ramka");
			ramkabox =  atlas.findRegion("box");
			
			helth_solid =  atlas.findRegion("helth_solid");
			
			
			//power_bar.flip(true, true);
			pool_ball_var1 = atlas.findRegion("pool_ball_var1");
			pool_ball_var2 = atlas.findRegion("pool_ball_var2");
			pool_ball_shadow = atlas.findRegion("pool_ball_shadow");
			small_coob1 = atlas.findRegion("small_coob1");
			small_coob2 = atlas.findRegion("small_coob2");
			coob1 = atlas.findRegion("coob1");
			stvol_pushka = atlas.findRegion("stvol_pushka");
			yadro_pushka = atlas.findRegion("yadro_pushka");
			yadro_vrag = atlas.findRegion("vrag_vistrel");
			stend_pushka = atlas.findRegion("stend_pushka");
//			see = atlas.findRegion("see");
//			beach = atlas.findRegion("beach");
//			cloud = atlas.findRegions("cloud");
//			zabor = atlas.findRegion("zabor");
//			sky = atlas.findRegion("sky");
			power2 = atlas.findRegion("mis2");
		    power3 = atlas.findRegion("mis3");
			power4 = atlas.findRegion("mis4");
			power5 = atlas.findRegion("mis5");
			power6 = atlas.findRegion("mis6");
			power7 = atlas.findRegion("mis7");
			power8 = atlas.findRegion("mis8");
			power9 = atlas.findRegion("mis9");
			power10 = atlas.findRegion("mis10");
			Yazik = atlas.findRegion("yazik");
			big_book = atlas.findRegion("big_book");
			small_book1 = atlas.findRegion("small_book1");
			small_book2 = atlas.findRegion("small_book2");
			Array<AtlasRegion> regions = null;
			AtlasRegion region = null;
		
			// Animation:fish_vistre
						regions = atlas.findRegions("fish_vistrel");
						fish_vistrel = new Animation(1.0f / 6.0f, regions,PlayMode.LOOP);
			// Animation:newplatform
						regions = atlas.findRegions("newplatform");
						newplatform = new Animation(1.0f / 3.0f, regions,PlayMode.LOOP);
			
			
			
			// Animation:asrc
			regions = atlas.findRegions("asrc");
			asrc = new Animation(1.0f / 3.0f, regions,PlayMode.LOOP);

			// Animation: Energy cell
						regions = atlas.findRegions("cell");
						energycell = new Animation(1.0f / 3.0f, regions,PlayMode.LOOP);

						// Animation: Bunny Normal
						regions = atlas.findRegions("lizardwalk");
						Lizaed_anim_walk = new Animation(1.0f / 6.0f, regions,PlayMode.LOOP);
						// Animation: Bunny Normal
			
						regions=atlas.findRegions("lizardstand");
						regions.removeIndex(0);
						
						Lizaed_anim_stand = new Animation(1.0f / 3.0f, regions,PlayMode.LOOP_PINGPONG);
						
						regions=atlas.findRegions("liz_jump");
											
											
						Lizaed_anim_jump = new Animation(1.0f /12.0f, regions,PlayMode.NORMAL);
						
						regions=atlas.findRegions("liz_fall");
						
			
	Lizaed_anim_fall = new Animation(1.0f /4.0f, regions,PlayMode.LOOP);
	
						regions = atlas.findRegions("lizardyaz");
						Lizaed_anim_yaz = new Animation(1.0f / 16.0f, regions,PlayMode.NORMAL);
			// Animation: Bunny Normal
			regions = atlas.findRegions("stand");
			animNormal = new Animation(1.0f / 3.0f, regions,PlayMode.LOOP);

			// Animation: Bunny Copter - knot ears
			regions = atlas.findRegions("prizok");
			anim_fish_preparing_to_fly = new Animation(1.0f / 2.0f, regions,PlayMode.NORMAL);
			// Animation: Bunny Copter - knot ears
						regions = atlas.findRegions("polet");
						anim_fish_fly = new Animation(1.0f / 10.0f, regions,PlayMode.NORMAL);

			// Animation: Bunny Copter - unknot ears
			regions = atlas.findRegions("slap");
			anim_fish_slap = new Animation(1.0f / 1.0f, regions, PlayMode.LOOP);
			regions = atlas.findRegions("fish_swim");
			fish_swim = new Animation(1.0f / 2.0f, regions, PlayMode.LOOP);
			regions = atlas.findRegions("smallcar");
			smallcar = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("helic");
			helic = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("fly_platform");
			ply_pl = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("portal_close");
			portal_close = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("portal_open");
			portal_open = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("helth_arrow");
			helth_arrow = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("helth_solid");
			helth_arrowup = new Animation(1.0f / 2.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("alienmove");
			alien_run = new Animation(1.0f / 4.0f, regions, PlayMode.LOOP);
			regions = atlas.findRegions("alienjump");
			alien_jump = new Animation(1.0f / 1.0f, regions, PlayMode.NORMAL);
			region = atlas.findRegion("alienjump",1);
			regions.add(region);
			alien_jump_stop = new Animation(1.0f / 1.0f,regions, PlayMode.NORMAL);
			regions = atlas.findRegions("spaceship");
			space_ship = new Animation(1.0f / 2.0f, regions, PlayMode.LOOP);
			regions = atlas.findRegions("orb");
			orb_ship = new Animation(1.0f / 3.0f, regions, PlayMode.LOOP);
			regions = atlas.findRegions("trinity");
			vrag_trinity = new Animation(1.0f / 3.0f, regions, PlayMode.LOOP_PINGPONG);
			regions = atlas.findRegions("trinityvistrel");
			trinityvistrel = new Animation(1.0f / 3.0f, regions, PlayMode.NORMAL);
			regions = atlas.findRegions("orbvistrel");
			orbvistrel = new Animation(1.0f / 3.0f, regions, PlayMode.NORMAL);
		}
	}


private void createFonts() {
		
		String FONT_CHARS = "";
		for( int i = 32; i < 127; i++ ) FONT_CHARS += (char)i; // РЎвЂ Р С‘РЎвЂћРЎР‚РЎвЂ№ Р С‘ Р Р†Р ВµРЎРѓРЎРЉ Р В°Р Р…Р С–Р В»Р С‘Р в„–РЎРѓР С”Р С‘Р в„–
		for( int i = 1024; i < 1104; i++ ) FONT_CHARS += (char)i;// РЎР‚РЎС“РЎРѓ
		String FONT_CHARS213 = "1234567890";
		FreeTypeFontParameter ftfp1 = new FreeTypeFontParameter();
		// ftfp.characters = FONT_CHARS;
		ftfp1.characters =RUSSIAN_CHARACTERS;
		ftfp1.size =40;
		ftfp1.borderColor=Color.BLACK;
		
		//ftfp.borderStraight=true;
		ftfp1.borderWidth=1;
		
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("data/hooge_05_55_cyr.ttf"));
		testtitleFont = generator2.generateFont( ftfp1 );
		testtitleFont.setFixedWidthGlyphs(FONT_CHARS213);
		
		generator2.dispose();
		
	
	}
	public void init (AssetManager assetManager) {
	
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		System.out.println("Р—Р°РіСЂСѓР¶Р°СЋ СЂРёСЃСѓРЅРєРё");
		assetManager.load(Constants.MY_NEW_TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
				// start loading assets and wait until finished
		
		// load sounds
		assetManager.load("sounds/vistrel.wav", Sound.class);
		assetManager.load("sounds/vistrel_fish.wav", Sound.class);
		assetManager.load("sounds/vzriv.wav", Sound.class);
		assetManager.load("sounds/portalon.wav", Sound.class);
		assetManager.load("sounds/portal.wav", Sound.class);
		assetManager.load("sounds/Powerup.wav", Sound.class);
		assetManager.load("sounds/touch.wav", Sound.class);
		assetManager.load("sounds/onground.wav", Sound.class);
		assetManager.load("sounds/longjump.wav", Sound.class);
		assetManager.load("sounds/jump.wav", Sound.class);
		assetManager.load("sounds/helicopter.wav", Sound.class);
		assetManager.load("sounds/Hurt.wav", Sound.class);
		assetManager.load("sounds/Pickup.wav", Sound.class);
		assetManager.load("sounds/car.wav", Sound.class);
		assetManager.load("music/RoccoW_-_05_-_Weeklybeats_2014_5_-Ive_Got_Nothing.mp3", Music.class);
		assetManager.load("music/RoccoW_-_Break-A-Leg.mp3", Music.class);
		assetManager.load("music/RoccoW_-_Chipho_instrumental.mp3", Music.class);
		assetManager.load("music/RoccoW_-_Lady_Bad_Luck_VRC6.mp3", Music.class);
		assetManager.load("music/RoccoW_-_Messeah.mp3", Music.class);
		assetManager.load("music/RoccoW_-_xyce_-_Quelle_Surprise_VRC6_Remix.mp3", Music.class);
	
		
		assetManager.finishLoading();

		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}
		
		TextureAtlas myatlas = assetManager.get(Constants.MY_NEW_TEXTURE_ATLAS_OBJECTS);
		
//		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		// enable texture filtering for pixel smoothinР»РґР»РѕgСЂРїРѕСЂРїРѕСЂРїРѕСЂРїРѕРїСЂРЅРµРіРµРЅРі
//		for (Texture t : atlas.getTextures()) {
	//		t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	//	}

		// create game resource objects
		
		createFonts();
		fonts = new AssetFonts();
		
		
		
		
		fish = new AssetFish(myatlas);
		
		music = new AssetMusic(assetManager);
		
		sounds = new AssetSounds(assetManager);
	}
	

	@Override
	public void dispose () {
		System.out.println("РЈРґР°Р»РёР» СЂРёСЃСѓРЅРєРё");
		assetManager.dispose();
		fonts.defaultSmall.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultBig.dispose();
		fonts.MyFont.dispose();
		fonts.titleFont.dispose();
		testtitleFont.dispose();
	}

	

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'", (Exception)throwable);
	}

}