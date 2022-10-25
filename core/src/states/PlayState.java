package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import sprites.Ship;
import sprites.Tube;

public class PlayState extends State {
    public static final int TUBE_SPACING = 165;
    public static final int TUBE_COUNT = 4;


    private Ship ship;
    private Texture bg;
    private Tube tube;


    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        ship = new Ship(50, 300);
        bg = new Texture("ksm.png");


        camera.setToOrtho(false, MyGdxGame.WIDTH / 1, MyGdxGame.HEIGHT  /1);

        tubes = new Array<>();

        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));



        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            ship.jump();

    }

    @Override
    public void update(float dt) {
        handleInput();

        ship.update(dt);
        camera.position.x = ship.getPosition().x + 80;


        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(ship.getBounds()))
                gsm.set(new GameOver(gsm));
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();



        sb.draw(bg , camera.position.x  - (camera.viewportWidth / 2 ) , 0 );
        sb.draw(ship.getShip(),ship.getPosition().x, ship.getPosition().y);


        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }



        sb.end();
        }



    @Override
    public void dispose() {
        bg.dispose();
        ship.dispose();

        for (Tube tube : tubes)
            tube.dispose();

    }

    }



