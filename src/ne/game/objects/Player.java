package ne.game.objects;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player extends SpielObjekt{

    public Input input;
    private Rectangle shape;
    public int rotation =0;
    private int speed = 0;
    private int speedfactor = 4;
    public Projektil projektil;
    public Player(int x, int y, Image image, Input input, Projektil projektil) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x,y,image.getWidth(),image.getHeight());
        this.projektil = projektil;
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
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }
    public void setRotation(int rotation,boolean vor, int delta){
        speed=delta/speedfactor;
        int ivor = 1;
        if (!vor) ivor = -1;
        switch(rotation)  {
            case 0:
                this.setY(this.getY() + speed*ivor);
                break;
            case 1:
                this.setY(this.getY() + speed*2/3*ivor);
                this.setX(this.getX() - speed*2/3*ivor);
                break;
            case 2:
                this.setX(this.getX() - speed*ivor);
                break;
            case 3:
                this.setY(this.getY() - speed+2/3*ivor);
                this.setX(this.getX() - speed*2/3*ivor);
                break;
            case 4:
                this.setY(this.getY() - speed*ivor);
                break;
            case 5:
                this.setY(this.getY() - speed*2/3*ivor);
                this.setX(this.getX() + speed*2/3*ivor);
                break;
            case 6:
                this.setX(this.getX() + speed*ivor);
                break;
            case 7:
                this.setY(this.getY() + speed*2/3*ivor);
                this.setX(this.getX() + speed*2/3*ivor);
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

