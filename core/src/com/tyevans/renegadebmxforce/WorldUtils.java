package com.tyevans.renegadebmxforce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

public class WorldUtils {
    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        Random randomGenerator = new Random();
        ChainShape shape = new ChainShape();
        int xIndex = (int) Constants.NUM_GROUND_VERTICES;
        Vector2[] terrainVertices = new Vector2[(int) (Constants.NUM_GROUND_VERTICES+2)];
        terrainVertices[0] = new Vector2(0, Gdx.graphics.getHeight());
        for (int i=1; i<=Constants.NUM_GROUND_VERTICES; i++) {
            terrainVertices[i] = new Vector2((float) (i * (xIndex++)), (float) (randomGenerator.nextInt(100) + 310) / 100f);
        }
        terrainVertices[((int) (Constants.NUM_GROUND_VERTICES + 1))] = new Vector2(Constants.COURSE_MAX_LENGTH, Gdx.graphics.getHeight());

        shape.createLoop(terrainVertices);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        shape.dispose();
        return body;
    }
}
