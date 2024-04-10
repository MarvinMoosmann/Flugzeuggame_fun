package ne.game.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class SpielObjekt {
    private int x;
    private int y;
    private int with;
    private int height;
    private Image image;
    private Shape shape;

    public SpielObjekt(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.with = image.getWidth();
        this.height = image.getWidth();
    }

    public abstract void draw(Graphics g);
    public abstract Shape getShape();
    public abstract void update(int delta);


    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return with;
    }

    public void setWith(int with) {
        this.with = with;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
