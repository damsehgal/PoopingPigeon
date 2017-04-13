package com.bhandlogic.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dam on 14/1/17.
 */
public abstract class State
{
	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected GameStateManager gsm;
	public State(GameStateManager gsm)
	{
		cam = new OrthographicCamera();
		mouse = new Vector3();
		this.gsm = gsm;
	}
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch spriteBatch);
	public abstract void dispose();
}
