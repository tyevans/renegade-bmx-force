package com.tyevans.renegadebmxforce;

import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 50f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final int NUM_GROUND_VERTICES = APP_WIDTH;
    public static final int COURSE_MAX_LENGTH = APP_WIDTH*40;
}
