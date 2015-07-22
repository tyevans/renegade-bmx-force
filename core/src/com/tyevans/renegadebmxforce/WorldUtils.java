package com.tyevans.renegadebmxforce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

public class WorldUtils {
    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }
}
