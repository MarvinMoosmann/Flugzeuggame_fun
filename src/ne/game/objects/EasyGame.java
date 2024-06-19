package ne.game.objects;
import org.newdawn.slick.*;
import org.newdawn.slick.util.pathfinding.navmesh.Space;

public class EasyGame extends BasicGame {

    private Image background;
    private Player player1;
    private Player player2;
    private Projektil projektil1;
    private Projektil projektil2;
    private Music backgroundmusic;
    private Sound explosion;
    private Sound jetengine;
    private Sound shot;
    private int lautstärke = 10;
    private int hitPlayer1 = 0;
    private int hitPlayer2 = 0;
    private AngelCodeFont font;
    private Animation animation1;
    private Animation animation2;
    private boolean planecolission = false;
    private boolean plane1hit = false;
    private boolean plane2hit = false;
    private int timer =0;

    public EasyGame() {
        super("EasyGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1920,1080 , true);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        SpriteSheet sheet = new SpriteSheet("assets/animation/player-animation/Player 1.png", 164, 250);
        animation1 = new Animation();
        for (int i=0;i<8;i++) {
            animation1.addFrame(sheet.getSprite(i,0), 50);
        }
        animation2 = new Animation();
        for (int i=0;i<8;i++) {
            animation2.addFrame(sheet.getSprite(i,0), 50);
        }

            font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
            background = new Image("assets/pics/background.jpg");
            projektil1 = new Projektil(-300,-100,new Image("assets/pics/Projektil1klein.png"),container.getInput());
            projektil2 = new Projektil (-400,-100,new Image("assets/pics/Projektil2klein.png"),container.getInput());
            player1 = new PlaneA(480,540,new Image("assets/pics/Player1klein.png"),container.getInput(),projektil1);
            player2 = new PlaneB(1440, 540,new Image("assets/pics/Player2klein.png"),container.getInput(),projektil2);
            backgroundmusic = new Music("assets/sounds/Backgroundmusic2.wav");
            explosion = new Sound("assets/sounds/crash.wav");
            jetengine = new Sound("assets/sounds/jetengine3.wav");
            shot = new Sound("assets/sounds/shot.wav");
            backgroundmusic.loop();
            jetengine.loop();

        }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        if (planecolission){
            SpriteSheet sheet = new SpriteSheet("assets/animation/crash-animation/crash.png", 164, 230);
            animation1 = new Animation();
            for (int i=0;i<8;i++) {
                animation1.addFrame(sheet.getSprite(i,0), 50);
            }

            animation2 = new Animation();
            for (int i=0;i<8;i++) {
                animation2.addFrame(sheet.getSprite(i,0), 50);
            }
            timer += delta;
            if (timer>=2000) {
                planecolission = false;
                timer = 0;
                sheet = new SpriteSheet("assets/animation/player-animation/Player 1.png", 164, 250);
                animation1 = new Animation();
                for (int i=0;i<8;i++) {
                    animation1.addFrame(sheet.getSprite(i,0), 50);
                }
                animation2 = new Animation();
                for (int i=0;i<8;i++) {
                    animation2.addFrame(sheet.getSprite(i,0), 50);
                }
            }
        }


        else if (plane2hit){
            SpriteSheet sheet = new SpriteSheet("assets/animation/crash-animation/crash.png", 164, 230);

            animation2 = new Animation();
            for (int i=0;i<8;i++) {
                animation2.addFrame(sheet.getSprite(i,0), 50);
            }
            timer += delta;
            if (timer>=100) {
                plane2hit = false;
                timer = 0;
                sheet = new SpriteSheet("assets/animation/player-animation/Player 1.png", 164, 250);

                animation2 = new Animation();
                for (int i=0;i<8;i++) {
                    animation2.addFrame(sheet.getSprite(i,0), 50);
                }
            }
        }


        else if (plane1hit) {
            SpriteSheet sheet = new SpriteSheet("assets/animation/crash-animation/crash.png", 164, 230);
            animation1 = new Animation();
            for (int i=0;i<8;i++) {
                animation1.addFrame(sheet.getSprite(i,0), 50);
            }
            timer += delta;
            if (timer>=100) {
                plane1hit = false;
                timer = 0;
                sheet = new SpriteSheet("assets/animation/player-animation/Player 1.png", 164, 250);
                animation1 = new Animation();
                for (int i=0;i<8;i++) {
                    animation1.addFrame(sheet.getSprite(i,0) , 50);
                }

            }
        }


        else {
                if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                    container.exit();
                }
                if (input.isKeyPressed(Input.KEY_2)) {
                    lautstärke = lautstärke + 1;
                    if (lautstärke >= 10) lautstärke = 10;
                    backgroundmusic.setVolume(lautstärke / 10f);
                }
                if (input.isKeyPressed(Input.KEY_1)) {
                    lautstärke = lautstärke - 1;
                    if (lautstärke < 1) lautstärke = 0;
                    backgroundmusic.setVolume(lautstärke / 10f);
                }
                player2.update(delta);
                player1.update(delta);
                projektil1.update(delta);
                projektil2.update(delta);

                if (player1.intersects(player2.getShape())) {
                    planecolission = true;
                    hitPlayer1 = 0;
                    hitPlayer2 = 0;
                    player1.setX(480);
                    player1.setY(540);
                    player2.setX(1440);
                    player2.setY(540);
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    explosion.play();
                }
                if (projektil1.intersects(player2.getShape())) {
                    plane2hit = true;
                    hitPlayer1++;
                    player2.setX(1440);
                    player2.setY(540);
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    explosion.play();
                    projektil1.setVisible(false);
                }
                if (projektil2.intersects(player1.getShape())) {
                    plane1hit = true;
                    hitPlayer2++;
                    player1.setX(480);
                    player1.setY(540);
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    explosion.play();
                    projektil2.setVisible(false);
                }
                if(projektil1.intersects(projektil2.getShape())){
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    explosion.play();
                    projektil2.setVisible(false);
                    projektil1.setVisible(false);
                }
            }
        }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        projektil1.draw(g);
        projektil2.draw(g);
        animation2.draw(player2.getX(), player2.getY());
        player2.draw(g);
        animation1.draw(player1.getX(), player1.getY());
        player1.draw(g);
        font.drawString(790, 25, "Player 1 => "+ hitPlayer1+":" + hitPlayer2 + " <= Player 2", Color.red);
        if (planecolission) {
            font.drawString(900, 340, "YOU DIED", Color.red);
        }
    }
}



