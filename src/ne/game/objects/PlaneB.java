package ne.game.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class PlaneB extends Player{
    public PlaneB(int x, int y, Image image, Input input, Projektil projektil) {
        super(x, y, image, input, projektil);
    }

    @Override
    public void update(int delta) {
        boolean vor = false;
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            rotation++;
            this.getImage().setRotation(this.getImage().getRotation()+45f);
            if (rotation==8) {
                rotation = 0;
            }
        }
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            rotation--;
            this.getImage().setRotation(this.getImage().getRotation()-45f);
            if (rotation<0) {
                rotation = 7;
            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            vor = true;
            setRotation(rotation,true,delta);
        }

        if (input.isKeyDown(Input.KEY_DOWN)) {
            vor = false;
            setRotation(rotation,false,delta);
        }

        if (input.isKeyPressed(Input.KEY_RSHIFT) && !projektil.isVisible()) {
            projektil.setVisible(true);
            projektil.setVor(!vor);
            projektil.setRotation(rotation);
            projektil.setX(getX());
            projektil.setY(getY());
        }
        super.update(delta);
    }
}
