package com.tyevans.renegadebmxforce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameStage extends Stage {

    private World world;
    private Ground ground;

    private final float TIME_STEP = 1 / 60f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private SpriteBatch batch;
    private Player player;
    private boolean jumping = false;

    public GameStage() {
        world = WorldUtils.createWorld();
        renderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        ground = new Ground(world);
        player = new Player(world);

        setupCamera();
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / Constants.PPM, Gdx.graphics.getHeight() / Constants.PPM);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        Vector2 vel = player.backWheelBody.getLinearVelocity();
        Vector2 pos = player.backWheelBody.getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -100) {
            player.applyTorque(0.1f, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < 100) {
            player.applyTorque(-0.1f, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && vel.x < 100) {
            if (!jumping) {
                player.backWheelBody.applyLinearImpulse(0, 0.1f, 20, 20, true);
                jumping = true;
            }
        } else {
            jumping = false;
        }


    }

    @Override
        public void draw () {
        super.draw();

        camera.position.set(player.getX(), player.getY(), 0f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();

        renderer.render(world, camera.combined);
    }

}