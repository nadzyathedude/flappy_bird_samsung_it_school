package states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {


	public static final String TITLE =  "See you, space cowboy";
	public static final float HEIGHT = 640;
	public static final float WIDTH =360 ;
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;

	
	@Override
	public void create () {
		batch = new SpriteBatch();


		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("ostt.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Texture img;
		///img = new Texture("badlogic.jpg");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push (new MenuState(gsm));


	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	

	}




	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
