package com.bhandlogic.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dam on 14/1/17.
 */
public class Person
{
	public static final int PERSON_Y_OFFSET = -5;
	private static int MOVEMENT = 125;
	private static final String TEXTURE_FILE_NAME = "person_new.png";
	private static final int FRAME_COUNT = 3;
	private static final float CYCLE_TIME = 0.6f;
	public static final int PERSON_X_OFFSET = 50;

	private Vector3 position;
	private Vector3 velocity;
	private Rectangle bounds;
	private Animation personAnimation;
	private Texture texture;

	public Person(int x, int y)
	{
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		texture = new Texture(TEXTURE_FILE_NAME);
		personAnimation = new Animation(new TextureRegion(texture, texture.getWidth() / 2, texture.getHeight()), FRAME_COUNT, CYCLE_TIME);
		bounds = new Rectangle(x, y, texture.getWidth() / 6, texture.getHeight());
	}
	public void update(float dt)
	{
		personAnimation.update(dt);
		velocity.scl(dt);
		position.add(MOVEMENT * dt, velocity.y, 0);
		velocity.scl(1 / dt);
		bounds.setPosition(position.x, position.y);
	}
	public TextureRegion getTexture()
	{
		return personAnimation.getFrame();
	}
	public Vector3 getPosition()
	{
		return position;
	}
	public void dispose()
	{
		texture.dispose();
	}

	public void jump()
	{
		position.y += 30;
	}
}
