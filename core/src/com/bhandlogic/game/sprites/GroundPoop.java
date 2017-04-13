package com.bhandlogic.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.bhandlogic.game.states.PlayState;

/**
 * Created by dam on 16/1/17.
 */
public class GroundPoop
{
    private static final String TEXTURE_FILE_NAME = "tatti_sprite.png";

    public static final float SCALE_X = (float) 0.08;
    public static final float SCALE_Y = (float) 0.08;
    private static final int FRAME_COUNT = 4;
    private static final float CYCLE_TIME = 0.5f;
    private final int POS_Y = PlayState.GROUND_HEIGHT - PlayState.GROUND_Y_OFFSET + 10;

    private Animation tattiAnimation;
    private Texture texture;
    private Rectangle bounds;
    private Vector3 position;

    public GroundPoop(float x)
    {
        position = new Vector3(x, POS_Y, 0);
        texture = new Texture(TEXTURE_FILE_NAME);
        tattiAnimation = new Animation(
                new TextureRegion(texture, texture.getWidth(),
                        texture.getHeight()
                ),
                FRAME_COUNT,
                CYCLE_TIME
        );
        bounds = new Rectangle(x, POS_Y, SCALE_X * texture.getWidth(), SCALE_Y * texture.getHeight());
    }

    public void reposition(float x)
    {
        position.set(x, POS_Y, 0);
        bounds.setPosition(x, POS_Y);
    }

    public void update(float dt)
    {
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
        texture.dispose();
    }
}
