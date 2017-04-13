package com.bhandlogic.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by dam on 15/1/17.
 */
public class Khamba
{
	public static final int KHAMBA_SPACING = 180;
	public static final int KHAMBA_COUNT = 2;
	public static final int KHAMBA_WIDTH = 60;
	public static final int NEXT_KHAMBA_POSITION = (KHAMBA_WIDTH + KHAMBA_SPACING) * KHAMBA_COUNT;
	public static final int KHAMBA_HEIGHT = 300;
	private static final String TEXTURE_FILE_NAME  = "single_pole.png";

	private Texture texture;
	private Vector3 position;
	public Khamba(float x)
	{
		texture = new Texture(TEXTURE_FILE_NAME);
		position = new Vector3(x,0,0);
	}
	public float getXOffsetWithWidth(){
		return position.x + KHAMBA_WIDTH;
	}
	public Vector3 getPosition()
	{
		return position;
	}
	public void reposition(float x)
	{
		position.set(x,0,0);
	}
	public Texture getTexture()
	{
		return texture;
	}
	public void dispose()
	{
		texture.dispose();
	}

}
