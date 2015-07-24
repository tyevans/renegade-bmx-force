package com.tyevans.renegadebmxforce;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;

public class Player extends Sprite {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public Body chassisBody;
    public Body backWheelBody;
    public Body frontWheelBody;
    public WheelJoint backWheelJoint;
    public WheelJoint frontWheelJoint;
    public float startingY = 200;

    private float speed = 50;

    public Player(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 0.2f;

        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(20f / Constants.PPM);

        PolygonShape chassisShape = new PolygonShape();
        chassisShape.setAsBox(50f / Constants.PPM, 10f / Constants.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = wheelShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 0.2f; // Make it bounce a little bit

        bodyDef.position.set(100f / Constants.PPM, startingY / Constants.PPM);
        backWheelBody = world.createBody(bodyDef);
        backWheelBody.createFixture(fixtureDef);

        bodyDef.position.set(150 / Constants.PPM, startingY / Constants.PPM);
        frontWheelBody = world.createBody(bodyDef);
        frontWheelBody.createFixture(fixtureDef);

        bodyDef.position.set(125f / Constants.PPM, (startingY + 40f) / Constants.PPM);
        fixtureDef.shape = chassisShape;
        chassisBody = world.createBody(bodyDef);
        chassisBody.createFixture(fixtureDef);

        WheelJointDef wheelJointDef = new WheelJointDef();
        wheelJointDef.bodyA = chassisBody;
        wheelJointDef.bodyB = backWheelBody;
        wheelJointDef.localAnchorA.set(new Vector2(-50 / Constants.PPM, -30 / Constants.PPM));
        wheelJointDef.localAnchorB.set(new Vector2(0, 0));
        wheelJointDef.localAxisA.set(Vector2.Y);
        wheelJointDef.maxMotorTorque = 0.2f;
        wheelJointDef.frequencyHz = 3f;
        wheelJointDef.dampingRatio = 0.9f;
        wheelJointDef.motorSpeed = 20 / Constants.PPM;
        backWheelJoint = (WheelJoint) world.createJoint(wheelJointDef);

        wheelJointDef.bodyB = frontWheelBody;
        wheelJointDef.localAnchorA.set(new Vector2(50f / Constants.PPM, -30 / Constants.PPM));
        frontWheelJoint = (WheelJoint) world.createJoint(wheelJointDef);

        wheelShape.dispose();
        chassisShape.dispose();
    }

    @Override
    public void draw(Batch batch) {
        Vector2 pos = backWheelBody.getPosition();
        setPosition(pos.x, pos.y);
//        batch.draw(texture, pos.x, pos.y);
    }


    public void move(int direction) {
        if (direction == LEFT) {
            backWheelJoint.setMotorSpeed(speed);
            frontWheelJoint.setMotorSpeed(speed);
        } else if (direction == RIGHT) {
            backWheelJoint.setMotorSpeed(-speed);
            frontWheelJoint.setMotorSpeed(-speed);
        }
        backWheelJoint.enableMotor(true);
        frontWheelJoint.enableMotor(true);
    }

    public void stop() {
        backWheelJoint.enableMotor(false);
        frontWheelJoint.enableMotor(false);
    }

    public void rotateChassis(float torque) {
        chassisBody.applyTorque(torque, true);
    }
}
