package com.mygdx.jumpfish.gameobjects;

import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.mygdx.jumpfish.game.WorldRendererMy;
import com.mygdx.jumpfish.util.AssetsMy;

public class YazikRope extends AbstractGameObject{
	public Box2DSprite box2DSprite;
	static public boolean ropeshoot=false; 
	public Body[] segments;
	public float Xlizard=-15;
	public float Ylizard=-15;
	public int length;
	public int timer=25;
	
	public YazikRope(World w,int length1) {

		box2DSprite = new Box2DSprite(AssetsMy.instance.fish.Yazik);
	//	box2DSprite.scale(1f);
	//	box2DSprite.setScale(2);
		length=length1;

		
		segments = new Body[length];
		RevoluteJoint[] joints = new RevoluteJoint[length - 1];
		RopeJoint[] ropeJoints = new RopeJoint[length - 1];
		Filter f = new Filter();
		f.groupIndex = -1;


		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;

		float width =0.16f, height =0.16f;

		CircleShape shape = new CircleShape();
		shape.setRadius(width/2);

		for(int i = 0; i < segments.length; i++) {
			
			segments[i] = w.createBody(bodyDef);
		
			segments[i].createFixture(shape, 0.5f);
			segments[i].getFixtureList().get(0).setUserData("rope");
		segments[i].getFixtureList().first().setFilterData(f);
		}

		shape.dispose();

		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.collideConnected=false;
		jointDef.localAnchorA.y = -height / 2;
		jointDef.localAnchorB.y = height / 2;

		for(int i = 0; i < joints.length; i++) {
			jointDef.bodyA = segments[i];
			jointDef.bodyB = segments[i + 1];
		
		//	jointDef.initialize(segments[i ],  segments[i+ 1], anchor);
		//	w.createJoint(jointDef);
			joints[i] = (RevoluteJoint) w.createJoint(jointDef);
		}

		RopeJointDef ropeJointDef = new RopeJointDef();
		ropeJointDef.localAnchorA.set(0, -height / 2);
		ropeJointDef.localAnchorB.set(0, height / 2);
		ropeJointDef.maxLength = height;

		for(int i = 0; i < ropeJoints.length; i++) {
			ropeJointDef.bodyA = segments[i];
			ropeJointDef.bodyB = segments[i + 1];
			ropeJoints[i] = (RopeJoint) w.createJoint(ropeJointDef);
		}
		
		segments[0].setType(BodyType.StaticBody);
		for(int i = 0; i < segments.length; i++){
			segments[i].setActive(false);
		
			
		}
		

	}
	
	public Body getsergent(int number){
		return segments[number];
	}
	public void Ropeshoot(float startx,float starty) {
		Xlizard=startx;
		Ylizard=starty;
	//	System.out.println("ropeshoot");
	
			//	yazik.setActive(true);
	//	yazik.setTransform(myLizard.getBody().getPosition().x, myLizard.getBody().getPosition().y, 0);
		//segments[0].setTransform(myLizard.getBody().getPosition().x, myLizard.getBody().getPosition().y, 0);
		//segments[0].setType(BodyType.StaticBody);;		
		
		for(int i = 0; i < segments.length; i++){
			
		segments[i].setTransform(startx,starty, 0);}
		for(int i = 0; i < segments.length; i++){
		
		//	segments[0].setType(BodyType.StaticBody);
		
			
				segments[i].setActive(true);
				//segments[i].applyLinearImpulse(new Vector2(-i*0.02f,-i*0.02f), segments[i].getPosition(), true);;
				
			}
		
					segments[length-1].applyLinearImpulse(new Vector2(0.5f*WorldRendererMy.cos*WorldRendererMy.mnozhitel_X,0.5f*WorldRendererMy.sin*WorldRendererMy.mnozhitel_Y), segments[length-1].getPosition(), true);
					ropeshoot=true;
					
	}
	
public void Ropegoback() {
		
	segments[length-1].applyLinearImpulse(new Vector2(-0.5f*WorldRendererMy.cos*WorldRendererMy.mnozhitel_X,-0.5f*WorldRendererMy.sin*WorldRendererMy.mnozhitel_Y), segments[length-1].getPosition(), true);
		
		
		}
	public void Ropeshootoff() {
		
		
		//	yazik.setActive(false);
		//	myLizard.getBody().getFixtureList().get(0).setFriction(1);
			for(int i = 0; i < segments.length; i++){
				
				
		//	segments[i].setTransform(myLizard.getBody().getPosition().x, myLizard.getBody().getPosition().y, 0);
			//segments[i].setGravityScale(0);
			segments[i].setActive(false);
			segments[i].setLinearVelocity(0, 0);
			timer=25;
			ropeshoot=false;
			}
		}
	public void update(float deltaTime) {
	//	System.out.println(ropeshoot);
		super.update(deltaTime);
		if(ropeshoot)
			timer--;
		
		if(timer==6)Ropegoback();
		if(timer==0)Ropeshootoff();
	}
	@Override
	public void render(SpriteBatch batch) {
		if(ropeshoot){
			for(int i = 0; i < 5; i++){
				box2DSprite.setScale(1f);
			box2DSprite.draw(batch,segments[i]);
			// TODO Auto-generated method stub
			}
		for(int i = 5; i < segments.length; i++){
			box2DSprite.setScale(1f+0.05f*i);
		box2DSprite.draw(batch,segments[i]);
		// TODO Auto-generated method stub
		}
	}}
}
