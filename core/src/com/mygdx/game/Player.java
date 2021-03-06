package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class Player {
    private static final int BOX_SIZE = 32; //speed of character. lower = faster, higher = can't move
    private static final float PLAYER_DENSITY = 0.75f; //gravity
    public static final float JUMP_FORCE = 250f; //how high player jumps
    public static final float RUN_FORCE = 5f; //speed at which player moves horizontally (forward/backward)
    public static final float PLAYER_START_X = 8f; //where player starts x-coordinate. doesn't update
    public static final float PLAYER_START_Y = 4.5f; //where player starts y-coordinate. doesn't update
    private Body body; //the body of the player where physics is applied to
    private boolean isJumping = false;

    public Player(World world) {
        createBoxBody(world, PLAYER_START_X, PLAYER_START_Y);
    }
    // Overload Constructor can now take in custom coordinates from CovidAttack Class
    public Player(World world, float playerX, float playerY)
    {
        createBoxBody(world, playerX, playerY);
    }

    private void createBoxBody(World world, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.fixedRotation = true;
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(x, y);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(BOX_SIZE / CovidAttack.PIXEL_PER_METER / 2, BOX_SIZE / CovidAttack.PIXEL_PER_METER / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = PLAYER_DENSITY;
        body = world.createBody(bdef);
        body.createFixture(fixtureDef).setUserData(this);
    }
    //allows the CovidAttack() method to obtain the full player body that has physics, size, and starting coordinates.
    public Body getBody() {
        return body;
    }
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
    public boolean isJumping() {
        return isJumping;
    }
}