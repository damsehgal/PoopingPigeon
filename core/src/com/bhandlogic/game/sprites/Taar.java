package com.bhandlogic.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by dam on 15/1/17.
 */
public class Taar
{
    public static final int TAAR_WIDTH = Khamba.KHAMBA_SPACING + Khamba.KHAMBA_WIDTH;
    public static final int TAAR_HEIGHT = 50;
    public static final int TAAR_OFFSET = 250;
    public static final int TAAR_SPACING = 0;
    public static final int BIRD_COUNT = 6;
    public static final int NEXT_TAAR_POSITION = TAAR_WIDTH + TAAR_SPACING * Khamba.KHAMBA_COUNT;
    private static final String TEXTURE_FILE_NAME = "taar.png";

    private Texture texture;
    private Vector3 position;

    private static Array<Vector2> birdPoints;
    private Array<Bird> birdArray;

    // Initialize bird points
    static
    {
        birdPoints = new Array<Vector2>();
        for (int i = 1; i < BIRD_COUNT; i++)
        {
            birdPoints.add(new Vector2(
                    i * TAAR_WIDTH / BIRD_COUNT,
                    Khamba.KHAMBA_HEIGHT - birdDistances(i) * TAAR_HEIGHT
            ));
        }
    }

    private static float birdDistances(int index)
    {
        if (index == 1 || index == 5)
        {
            return 0.465f;
        }
        else if (index == 2 || index == 4)
        {
            return 0.665f;
        }
        else
        {
            return 0.73f;
        }
    }

    public static Array<Vector2> getBirdPoints()
    {
        return birdPoints;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public float getXOffsetWithWidth(){
        return position.x + TAAR_WIDTH;
    }

    public Taar(float x)
    {
        texture = new Texture(TEXTURE_FILE_NAME);
        position = new Vector3(x, TAAR_OFFSET, 0);
        birdArray = new Array<Bird>();
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(3) + 2; i++)
        {
            int index = rand.nextInt(5);
            birdArray.add(new Bird(x, index));
        }
    }

    public Array<Bird> getBirdArray()
    {
        return birdArray;
    }

    public void reposition(float x)
    {
        for (Bird bird : birdArray)
        {
            bird.reposition(x);
        }
        position.set(x, TAAR_OFFSET, 0);
    }

    public void update(float dt, boolean flag)
    {
        for (Bird bird : birdArray)
        {
            bird.update(dt, flag);

        }

    }

    public void dispose()
    {
        for (Bird bird : birdArray)
        {
            bird.dispose();
        }
        texture.dispose();
    }
}
