package com.bhandlogic.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bhandlogic.game.PoopingGame;

/**
 * Created by dam on 14/1/17.
 */
public class MenuState extends State
{
	private static final String BACKGROUND_FILE_NAME = "bg.png";
	private static final String PLAY_BUTTON_FILE_NAME = "playbtn.png";
	private Texture background;
	private Texture playBtn;
	public MenuState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, PoopingGame.WIDTH / 2, PoopingGame.HEIGHT / 2);
		background = new Texture(BACKGROUND_FILE_NAME);
		playBtn = new Texture(PLAY_BUTTON_FILE_NAME);
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			gsm.set(new PlayState(gsm));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(background, 0,0);
		sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
		sb.end();
	}

	@Override
	public void dispose() {
		background.dispose();
		playBtn.dispose();
	}
}
