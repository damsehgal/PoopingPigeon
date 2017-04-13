package com.bhandlogic.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.bhandlogic.game.states.PlayState;

/**
 * Created by dam on 22/1/17.
 */
public class BirdPoop
{
    private static final String TEXTURE_FILE_NAME = "tatti_sprite.png";
    private static float SCALE_VELOCITY_Y = 0.55f;
    public static final float SCALE_X = (float) 0.15;
    public static final float SCALE_Y = (float) 0.15;
    public static final int POOP_GROUND_OFFSET = 60;

    private static final int FRAME_COUNT = 4;
    private static final float CYCLE_TIME = 0.5f;

    private boolean girChukiH;
    private Animation tattiAnimation;
    private Texture texture;
    private Rectangle bounds;
    private GroundPoop groundPoop;
    private Vector3 position;


    public BirdPoop(float x, float y)
    {
        girChukiH = false;
        position = new Vector3(x, y, 0);
        texture = new Texture(TEXTURE_FILE_NAME);
        tattiAnimation = new Animation(new TextureRegion(texture, texture.getWidth(),
                                        texture.getHeight()),
                                        FRAME_COUNT,
                                        CYCLE_TIME);

        bounds = new Rectangle(x, y, SCALE_X * texture.getWidth(), SCALE_Y * texture.getHeight());
    }

    public void reposition(float x, float y)
    {
        position.x = x;
        position.y = y;
        bounds.setPosition(x, y);
        girChukiH = false;
    }

    public void update(float dt, boolean flag)
    {
        if(flag){
            if (!(position.y <= POOP_GROUND_OFFSET))
            {
                position.y -= dt * position.y * SCALE_VELOCITY_Y;
            }
            if (position.y <= POOP_GROUND_OFFSET && !girChukiH)
            {
                girChukiH = true;
                groundPoop = new GroundPoop(position.x);
            }
        }
        if (girChukiH)
        {
            groundPoop.reposition(position.x);
        }
        tattiAnimation.update(dt);
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public TextureRegion getTexture()
    {
        return tattiAnimation.getFrame();
    }

    public void dispose()
    {
        if (girChukiH)
        {
            groundPoop.dispose();
        }
        texture.dispose();
    }
}
