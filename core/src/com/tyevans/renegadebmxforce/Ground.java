package com.tyevans.renegadebmxforce;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.g2d.Batch;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
        import com.badlogic.gdx.math.Vector2;
        import com.badlogic.gdx.physics.box2d.*;

        import java.util.Random;

public class Ground extends Sprite {
    private Vector2[] terrainVertices;
    private Body body;

    private void generateVertices() {
        Random randomGenerator = new Random();
        int segmentLength = Constants.COURSE_MAX_LENGTH / Constants.NUM_GROUND_VERTICES;
        terrainVertices = new Vector2[Constants.NUM_GROUND_VERTICES+2];
        terrainVertices[0] = new Vector2(0, 10);
        for (int i=0; i<Constants.NUM_GROUND_VERTICES; i++) {
            terrainVertices[i+1] = new Vector2((float) (i * segmentLength) / Constants.PPM, (randomGenerator.nextInt(5000) / 100f) / Constants.PPM);
        }
        terrainVertices[Constants.NUM_GROUND_VERTICES+1] = new Vector2(Constants.COURSE_MAX_LENGTH / Constants.PPM, 10);
    }

    public Ground(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        body = world.createBody(bodyDef);
        ChainShape shape = new ChainShape();
        generateVertices();
        shape.createChain(terrainVertices);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        shape.dispose();
    }

    @Override
    public void draw(Batch batch) {

    }
}