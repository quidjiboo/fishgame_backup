package com.mygdx.jumpfish.gameobjects;





import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;

public class BOMBSPool extends Pool<BOMBA>{

	protected final World MY_f_world1;
	public BOMBSPool(World w) {
		this.MY_f_world1 = w;
		
	}

	@Override
	protected BOMBA newObject() {
		return new BOMBA(MY_f_world1);
	}

}
