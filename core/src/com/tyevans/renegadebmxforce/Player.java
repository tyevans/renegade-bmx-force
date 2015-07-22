package com.tyevans.renegadebmxforce;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tyler on 7/20/2015.
 */
public class Player extends Sprite {
    public Body body;

    private Texture texture;

    public Player(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 500);

        body = world.createBody(bodyDef);
        texture = new Texture("./bike.png");

        CircleShape circle = new CircleShape();
        circle.setRadius(20f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.1f; // Make it bounce a little bit

        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();
    }

    @Override
    public void draw(Batch batch) {
        Vector2 pos = body.getPosition();
        setPosition(pos.x, pos.y);
//        batch.draw(texture, pos.x, pos.y);
    }


}
