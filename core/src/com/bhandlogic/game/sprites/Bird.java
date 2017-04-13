package com.bhandlogic.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam on 22/1/17.
 */
public class Bird
{
    private static final String TEXTURE_FILE_NAME = "bird.png";
    Texture texture;
    Vector2 position;
    BirdPoop birdPoop;
    boolean visible;

    public final Vector2 posRelativeToTaar;

    public boolean isVisible()
    {
        return visible;
    }

    public Bird(float distanceFromPole, int index)
    {
        texture = new Texture(TEXTURE_FILE_NAME);
        posRelativeToTaar = Taar.getBirdPoints().get(index);
        position = new Vector2(posRelativeToTaar.x + distanceFromPole, posRelativeToTaar.y);
        birdPoop = new BirdPoop(position.x, position.y - texture.getHeight());
    }

    public BirdPoop getBirdPoop()
    {
        return birdPoop;
    }

    public Vector2 getPosition()
    {

        return position;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public void update(float dt, boolean flag)
    {
        birdPoop.update(dt, flag);
    }

    public void reposition(float x)
    {
        position.x = x + posRelativeToTaar.x;
        birdPoop.reposition(x + posRelativeToTaar.x, position.y - texture.getHeight());
    }

    public void dispose()
    {
        texture.dispose();
        birdPoop.dispose();
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
}
