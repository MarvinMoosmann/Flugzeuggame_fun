package ne.game.objects;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Projektil1 extends SpielObjekt{

    private Input input;
    private Rectangle shape;
    private int rotation =0;
    private int speed = 10;
    private int speedfactor = 2;
    private boolean shoot = false;
    private int shootspeedfactor = 2;
    public Projektil1(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(),this.getY());

    }
    @Override
    public Shape getShape() {
        return shape;
    }



    @Override
    public void update(int delta) {

        if (input.isKeyPressed(Input.KEY_D)) {
            if (rotation<=7) {
                rotation++;
                this.getImage().setRotation(this.getImage().getRotation()+45f);
            } else {
                rotation = 0;
            }
        }
        if (input.isKeyPressed(Input.KEY_A)) {
            if (rotation>=0) {
                rotation--;
                this.getImage().setRotation(this.getImage().getRotation()-45f);
            } else {
                rotation = 7;
            }

        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            setRotation(rotation,true,delta);
            shoot=true;
        }
        if (input.isKeyDown(Input.KEY_W)) {
            setRotation(rotation,true,delta);
        }

        if (input.isKeyDown(Input.KEY_S)) {
            setRotation(rotation,false,delta);
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }
    private void setRotation(int rotation,boolean vor, int delta){
        if(shoot){
            speed=delta*shootspeedfactor;
            shoot = false;
        } else {
            speed=delta/speedfactor;
        }
        int ivor = 1;
        if (!vor) ivor = -1;
        switch(rotation)  {
            case 0:
                this.setY(this.getY() + speed * ivor);
                break;
            case 1:
                this.setY(this.getY() + speed/2 * ivor);
                this.setX(this.getX() - speed/2 * ivor);
                break;
            case 2:
                this.setX(this.getX() - speed*ivor);
                break;
            case 3:
                this.setY(this.getY() - speed/2*ivor);
                this.setX(this.getX() - speed/2*ivor);
                break;
            case 4:
                this.setY(this.getY() - speed*ivor);
                break;
            case 5:
                this.setY(this.getY() - speed/2*ivor);
                this.setX(this.getX() + speed/2*ivor);
                break;
            case 6:
                this.setX(this.getX() + speed*ivor);
                break;
            case 7:
                this.setY(this.getY() + speed/2*ivor);
                this.setX(this.getX() + speed/2*ivor);
                break;
        }
        if (this.getY() < 0+this.getHeight()/2) this.setY(0+this.getHeight()/2);
        if (this.getY() > 1080-this.getHeight()/2) this.setY(1080-this.getHeight()/2);
        if (this.getX() < 0+this.getWidth()/2) this.setX(0+this.getWidth()/2);
        if (this.getX() > 1920-this.getWidth()/2) this.setX(1920-this.getWidth()/2);
    }

    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }
}

