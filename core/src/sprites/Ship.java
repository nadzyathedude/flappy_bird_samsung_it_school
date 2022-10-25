package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ship {
    public static final int MOVEMENT = 100;
    public static  final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture ship;
    private Rectangle bounds;

    public Ship(int x, int y ){
        position = new Vector3(x, y, 0);

        velocity = new Vector3(0,0,0);
        ship = new Texture("ship.png");
        bounds = new Rectangle(x, y, ship.getWidth(), ship.getHeight());

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getShip() {
        return ship;
    }

    public void update (float dt){
        if (position.y > 0)
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if (position.y < 0 )
            position.y = 0;

        velocity.scl(1 / dt);


        bounds.setPosition(position.x, position.y);

    }
    public void jump(){
        velocity.y = 200;
    }


    public Rectangle getBounds(){
        return bounds;
    }



    public void dispose() {
        ship.dispose();
    }
}
