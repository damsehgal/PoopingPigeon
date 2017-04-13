package com.bhandlogic.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bhandlogic.game.PoopingGame;
import com.bhandlogic.game.sprites.Bird;
import com.bhandlogic.game.sprites.GroundPoop;
import com.bhandlogic.game.sprites.Khamba;
import com.bhandlogic.game.sprites.BirdPoop;
import com.bhandlogic.game.sprites.Person;
import com.bhandlogic.game.sprites.Taar;

import static com.bhandlogic.game.sprites.Khamba.KHAMBA_COUNT;
import static com.bhandlogic.game.sprites.Khamba.KHAMBA_SPACING;

/**
 * Created by dam on 14/1/17.
 */
public class PlayState extends State
{
    public static final int GROUND_Y_OFFSET = -50;
    private static final String GROUND_FILE_NAME = "ground.png";
    public static int GROUND_HEIGHT;
    private static final String BACKGROUND_FILE_NAME = "bg.png";

    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Person person;
    private Array<Khamba> khambe;
    private Array<Taar> wires1;
    private Array<Taar> wires2;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);

        // Initialize textures
        bg = new Texture(BACKGROUND_FILE_NAME);
        ground = new Texture(GROUND_FILE_NAME);

        GROUND_HEIGHT = ground.getHeight();

        person = new Person(Person.PERSON_X_OFFSET, ground.getHeight() + GROUND_Y_OFFSET + Person.PERSON_Y_OFFSET);

        cam.setToOrtho(false, PoopingGame.WIDTH / 2, PoopingGame.HEIGHT / 2);

        // Use 2 ground positions to create new ground when first goes out the viewport.
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(),
				GROUND_Y_OFFSET);

        initializeKhambe();
        initializeWires();
    }

    private void initializeWires()
    {
        wires1 = new Array<Taar>();
        wires2 = new Array<Taar>();
        for (int i = 0; i < KHAMBA_COUNT; ++i)
        {
            wires1.add(new Taar(Khamba.KHAMBA_WIDTH + (i + 1) * (Taar.TAAR_SPACING + Taar
					.TAAR_WIDTH)));
            wires2.add(new Taar((i + 1) * (Taar.TAAR_SPACING + Taar.TAAR_WIDTH)));
        }
    }

    private void initializeKhambe()
    {
        khambe = new Array<Khamba>();
        for (int i = 0; i < KHAMBA_COUNT; ++i)
        {
            khambe.add(new Khamba((i + 1) * (KHAMBA_SPACING + Khamba.KHAMBA_WIDTH)));
        }
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.isTouched()) {
            person.jump();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
        person.update(dt);
        updateGround();
        cam.position.x = person.getPosition().x + Person.PERSON_X_OFFSET;
        updateKhambaTaarAndBird(dt);
        cam.update();
    }

    private void updateKhambaTaarAndBird(float dt)
    {
        for (int i = 0; i < khambe.size; i++)
        {
            Khamba khamba = khambe.get(i);
            float camPosition = cam.position.x - (cam.viewportWidth / 2);

            if (camPosition > khamba.getXOffsetWithWidth())
            {
                khamba.reposition(khamba.getPosition().x + Khamba.NEXT_KHAMBA_POSITION);
            }
            Taar taar = wires1.get(i);
            if (camPosition > taar.getXOffsetWithWidth())
            {
                taar.reposition(taar.getPosition().x + Taar.NEXT_TAAR_POSITION);
            }

            Taar taar2 = wires2.get(i);
            if (camPosition > taar2.getXOffsetWithWidth())
            {
                taar2.reposition(taar2.getPosition().x + Taar.NEXT_TAAR_POSITION);
                taar2.refreshBirds();
            }
            for (Taar t_taar : wires2)
            {
                Array<Bird> bi = t_taar.getBirdArray();
                for (Bird bird : bi)
                {
                    bird.update(dt, cam.position.x > bird.getPosition().x);
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();

        // Draw Background
        spriteBatch.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        // Draw Khambe
        for (Khamba khamba : khambe)
        {
            spriteBatch.draw(khamba.getTexture(),
                    khamba.getPosition().x,
                    khamba.getPosition().y,
                    Khamba.KHAMBA_WIDTH,
                    Khamba.KHAMBA_HEIGHT);
        }

        // Draw Ground
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);

        // Draw all wires and birds
        for (Taar taar : wires1)
        {
            spriteBatch.draw(taar.getTexture(),
                    taar.getPosition().x,
                    taar.getPosition().y,
                    Taar.TAAR_WIDTH,
                    Taar.TAAR_HEIGHT);
        }
        for (Taar taar : wires2)
        {
            spriteBatch.draw(taar.getTexture(),
                    taar.getPosition().x,
                    taar.getPosition().y,
                    Taar.TAAR_WIDTH,
                    Taar.TAAR_HEIGHT);

            Array<Bird> pakshiArray = taar.getBirdArray();
            for (Bird bird : pakshiArray)
            {
                if (bird.isVisible())
                {
                    spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
                    BirdPoop birdPoop = bird.getBirdPoop();
                    spriteBatch.draw(birdPoop.getTexture(),
                            birdPoop.getPosition().x,
                            birdPoop.getPosition().y,
                            GroundPoop.SCALE_X * birdPoop.getTexture().getRegionWidth(),
                            GroundPoop.SCALE_Y * birdPoop.getTexture().getRegionHeight());
                }
            }
        }

        // Draw person
        TextureRegion texture = person.getTexture();
        spriteBatch.draw(texture,
                person.getPosition().x,
                person.getPosition().y,
                texture.getRegionWidth(),
                texture.getRegionHeight());

        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        bg.dispose();
        ground.dispose();
        person.dispose();
        for (int i = 0; i < khambe.size; i++)
        {
            khambe.get(i).dispose();
            wires1.get(i).dispose();
            wires2.get(i).dispose();
        }
    }

    private void updateGround()
    {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
        {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
        {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
