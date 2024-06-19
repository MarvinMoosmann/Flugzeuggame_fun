package ne.game.objects;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Projektil extends SpielObjekt{


    private Rectangle shape;
    private int rotation =0;
    private int speed = 0;
    private int shootspeedfactor = 2;
    private boolean vor = false;
    private boolean visible = false;
    private Sound shot;
    public Projektil(int x, int y, Image image, Input input) throws SlickException {
        super(x, y, image);
        shape = new Rectangle(x,y,image.getWidth(),image.getHeight());
        shot = new Sound("assets/sounds/shot.wav");
    }

    @Override
    public void draw(Graphics g) {
        if (visible) {
            this.getImage().drawCentered(this.getX(), this.getY());
            shot.play();
        }
    }
    @Override
    public Shape getShape() {
        return shape;
    }

    public boolean isVor() {
        return vor;
    }

    public void setVor(boolean vor) {
        this.vor = vor;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        switch(rotation)  {
            case 0:
                this.getImage().setRotation(0);
                break;
            case 1:
                this.getImage().setRotation(45);
                break;
            case 2:
                this.getImage().setRotation(90);
                break;
            case 3:
                this.getImage().setRotation(135);
                break;
            case 4:
                this.getImage().setRotation(180);
                break;
            case 5:
                this.getImage().setRotation(225);
                break;
            case 6:
                this.getImage().setRotation(270);
                break;
            case 7:
                this.getImage().setRotation(315);
                break;
        }
    }

    @Override
    public void update(int delta) {
        if (visible) move(rotation,this.vor, delta);
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }
    private void move(int rotation,boolean vor, int delta){
        speed=delta*shootspeedfactor;
        switch(rotation)  {
            case 0:
                this.setY(this.getY() + speed);
                break;
            case 1:
                this.setY(this.getY() + speed/2);
                this.setX(this.getX() - speed/2);
                break;
            case 2:
                this.setX(this.getX() - speed);
                break;
            case 3:
                this.setY(this.getY() - speed/2);
                this.setX(this.getX() - speed/2);
                break;
            case 4:
                this.setY(this.getY() - speed);
                break;
            case 5:
                this.setY(this.getY() - speed/2);
                this.setX(this.getX() + speed/2);
                break;
            case 6:
                this.setX(this.getX() + speed);
                break;
            case 7:
                this.setY(this.getY() + speed/2);
                this.setX(this.getX() + speed/2);
                break;
        }
        if ((this.getY() < -200) || (this.getY() > 1280) || (this.getX() < -200) || (this.getX() > 2120)) {
            setVisible(false);
        }
    }

    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }
}

