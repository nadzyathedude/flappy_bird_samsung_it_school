package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends State {
    private Texture gameover;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGdxGame.WIDTH * 18 , MyGdxGame.HEIGHT * 18 );
      gameover = new Texture ("gameover.png");
       // playBtn = new Texture("playbtn.png");

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
        sb.setProjectionMatrix(camera.combined);
    sb.begin();

    sb.draw(gameover, -800, 0);
    //sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
    sb.end();

}

    @Override
    public void dispose() {
        gameover.dispose();


    }
}